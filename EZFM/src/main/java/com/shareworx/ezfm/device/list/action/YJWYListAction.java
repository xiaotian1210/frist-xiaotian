package com.shareworx.ezfm.device.list.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.dms365.ezfm.limitcheck.annotation.LimitCheck;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService;
import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.ezfm.device.list.service.YJWYListService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.ObectUtils;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.limitcheck.type.DeviceType;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;

/**
 * FM数据同步操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/device/list")
public class YJWYListAction {

	final static Logger log = Logger.getLogger(YJWYListAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/list/device_list";
	/** 地点页面 */
	public final static String SONPAGE_PLACE = "ezfm/device/list/add_device_place";
	
	/** 地点页面 */
	public final static String SONPAGE_SYS = "ezfm/device/list/add_sys";

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(YJWYListService.ID)
	private YJWYListService yjwyListService;
	public void setYjwyListService(YJWYListService yjwyListService) {
		this.yjwyListService = yjwyListService;
	}
	@Autowired
	@Qualifier(YJWYEqService.ID)
	private YJWYEqService yjwyEqService;

	@Autowired
	@Qualifier(YJWYEqDomainService.ID)
	YJWYEqDomainService eqDomainService;
	public void setYJWYEqService(YJWYEqService yjwyEqService) {
		this.yjwyEqService = yjwyEqService;
	}
	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}
	@Autowired
	@Qualifier(YJWYRoomDomainService.ID)
	private YJWYRoomDomainService roomService;
    public void setYjwyRoomService(YJWYRoomDomainService roomService) {
		this.roomService = roomService;
	}
    @Autowired
  	@Qualifier(YJWYEqBusinessService.ID)
    private YJWYEqBusinessService yjwyeqBusinessService;
    
    
    @Autowired
  	@Qualifier(YJWYEqDomainService.ID)
    private YJWYEqDomainService yjwyEqDomainService;
    
    @Autowired
  	@Qualifier(YJWYRoomBusinessService.ID)
    private YJWYRoomBusinessService roomBusinessService;

	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService yjwyResourcesBusinessService;




	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 转向地点
	 * 
	 * @return
	 */
	@RequestMapping(value = "place/room", method = RequestMethod.GET)
	public ModelAndView placeForward() {
		ModelAndView mv = new ModelAndView(SONPAGE_PLACE);
		return mv;
	}

	/**
	 * 转向地点
	 *
	 * @return
	 */
	@RequestMapping(value = "place/sys", method = RequestMethod.GET)
	public ModelAndView SysForward() {
		ModelAndView mv = new ModelAndView(SONPAGE_SYS);
		return mv;
	}
	/**
	 * 查询操作
	 * 所属系统关联设备数对不上，设备所属系统加起来的总数，之前有设备没有房间，使用join只有但两边都有的时候才会显示，left join 会有很多多余的数据
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		//默认查询正常设备
		if(params.getActive() == null){
			params.setActive(1);
		}

		List<YJWYResourcesModel> resourcesModels ;
		//查找资源,处理树形资源查询
        List<String> roomIdsByResources = new ArrayList<>();
        if(params.getId() != null){
            if(params.getProject_name() == null){
                params.setProject_name("byId");
            }
            if(params.getType() == null || params.getType().equals("0")){
//                resourcesModels = yjwyResourcesBusinessService.findAllResourceByProject(params.getId());
                YJWYRoomModel roomModel = new YJWYRoomModel();
                roomModel.setSite_id(params.getId());
                List<YJWYRoomModel> yjwyRoomModels = roomService.queryByExample(roomModel);
                for(YJWYRoomModel item:yjwyRoomModels){
                    roomIdsByResources.add(item.getRm_id());
                }
            }else {
                resourcesModels = yjwyResourcesBusinessService.findResoucesIncludeAllChilds(params.getId());
				roomIdsByResources.addAll(roomBusinessService.getRoomIdsByResources(resourcesModels));
            }
            params.setList(roomIdsByResources);
        }
        YJWYProjectModel[] projects = null;
        Set<String> siteids = new HashSet<String>();
        if(params.getPk_area()==null||"".equals(params.getPk_area())) {
        	projects = UserUtil.getUserProject();
        } else {
        	if(params.getPk_project()==null||"".equals(params.getPk_project())) {
        		projects = UserUtil.getUserProject(params.getPk_area());
        	} else {
        		siteids.add(params.getSite_id());
        	}
        }
        if(projects!=null&&projects.length>0) {
        	for(int i=0;i<projects.length;i++) {
        		siteids.add(projects[i].getPk_project());
        	}
        }
        params.setSite_ids(siteids);	
        	
        List<YJWYListModel> models = yjwyListService.queryList2(params);
        for(int i=0;i<models.size();i++) {
			YJWYListModel model = models.get(i);
			String rm_id = model.getRm_id();
			String rm_name = roomBusinessService.getRoomPlaceByRoomId(rm_id);
			model.setRm_name(rm_name);
		}
		long count = yjwyListService.queryCount(params);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}
	/**
	 * 查询标牌信息
	 *
	 * @return
	 */
	@RequestMapping(value = "query/labels", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryInformation(ParamEntity params) {
		List<Map<String,Object>> taskList =yjwyListService.queryEqPatrolTask(params.getPk_project());
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("taskList", taskList);
		return mar;
	}

	/**
	 * 查询标牌信息
	 *
	 * @return
	 */
	@RequestMapping(value = "query/labels/eq_id", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryInformationByEqId(ParamEntity params) {
		List<Map<String,Object>> taskList =yjwyListService.queryEqPatrolTaskByEqId(params.getEq_id());
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("taskList", taskList);
		return mar;
	}

	/**
	 * 查询区域操作 site_id引用到pk_project 通过yjwy_fmdata_site_project表
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/area", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryPk_area(
			@RequestParam(value = "site_id", required = false) String site_id) {
		// String pk_project = yjwyListService.queryPk_project(site_id);
		String pk_area = yjwyListService.queryPk_area(site_id);
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("pk_area", pk_area);
		// mar.setAttribute("pk_project", pk_project);
		return mar;
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYListModel[] models) {
		try {
			YJWYEqModel[] list = new YJWYEqModel[models.length];
			// 获取主键id
			for (int i = 0; i < models.length; i++) {
				// 执行删除
				YJWYEqModel model = new YJWYEqModel();
				model.setEq_id(models[i].getEq_id());
                YJWYEqModel model1 = eqDomainService.queryById(model.getEq_id());
                ObectUtils.fillData(model,model1);
                model.setDelete_flag(1);
				list[i] = model;
			}
			yjwyEqService.updateModels(list);
			return new ModelAndResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	@RequestMapping(value = "inactive", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult inactive(@RequestBody YJWYListModel[] models) {
		try {
			// 获取主键id
			for (int i = 0; i < models.length; i++) {
				YJWYListModel model = models[i];
				YJWYEqModel example = new YJWYEqModel();
				example.setEq_id(model.getEq_id());
				YJWYEqModel[] list = yjwyeqBusinessService.load(example);
				if(list==null||list.length==0) {
					continue;
				}
				example = list[0];
				example.setActive(0);
				yjwyEqService.updateModels(new YJWYEqModel[]{example});
			}
			return new ModelAndResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	
	/**
	 * 新增操作
	 * 
	 * @return
	 */
	@LimitCheck(type=DeviceType.class)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYEqModel model) {

//		YJWYUserModel userModel = UserUtil.getCurrentUser();
//		userModel.getUser_name();
		model.setCreate_by(UserUtil.getCurrentUser().getUser_name());
//        model.setCreate_by(UserUtil.getCurrentUserPk());
        model.setCreate_date(DateTimeUtil.getTimestampStr());
//        model.setLast_modify_user(UserUtil.getCurrentUserPk());
        model.setLast_modify_user(UserUtil.getCurrentUser().getUser_name());
        model.setLast_modify_time(DateTimeUtil.getTimestampStr());
        model.setUpdate_date(System.currentTimeMillis()+"");
		
		model.setEq_id(UUID.randomUUID().toString());
		model.setActive(1);
		model.setFlag(model.getActive());
		model = completeYJWYEqModel(model);
		model.setDelete_flag(0);
		try {
			yjwyEqService.saveModels(new YJWYEqModel[] { model });
			return new ModelAndResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYEqModel model) {
		try {
			JdbcTemplate read= dao.getReadTemplate();
			String sql="select count(eq_id) from yjwy_fmdata_eq where flag='1' and fm_code='"
					+ model.getFm_code()
					+ "' and pk_crop='"
					+ UserUtil.getCurrentUser().getPk_crop()
					+ "'";
			int count=read.queryForObject(sql, Integer.class);
			if(count==1){
				sql="select count(eq_id) from yjwy_fmdata_eq where flag='1' and fm_code='"
						+ model.getFm_code()
						+ "' and pk_crop='"
						+ UserUtil.getCurrentUser().getPk_crop()
						+ "' and eq_id='"
						+ model.getEq_id()
						+ "'";
				 count=read.queryForObject(sql, Integer.class);
				 if(count!=1){
					 return new ModelAndResult(false,"此编码已存在！");
				 }
			}
			YJWYEqModel example = new YJWYEqModel();
			example.setEq_id(model.getEq_id());
			YJWYEqModel[] models = yjwyeqBusinessService.load(example);
			if(models==null||models.length==0) {
				return new ModelAndResult(false);
			}
			model = completeYJWYEqModel(model);
//			Object active =model.getAttribute("active");
//			model.setFlag(Integer.parseInt(active+""));
			YJWYEqModel dataFromDatabase = yjwyEqDomainService.queryById(model.getEq_id());
			ObectUtils.fillData(model, dataFromDatabase);
			
//			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setLast_modify_user(UserUtil.getCurrentUser().getUser_name());
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setUpdate_date(System.currentTimeMillis()+"");
			yjwyEqService.updateModels(new YJWYEqModel[] { model });
		
			return new ModelAndResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "imptemplete/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/device/设备导入模板.xls");
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	//TODO 前台页面已将该功能去掉，暂时未测试，下次启用时需注意创建设备的授权限制
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "import/excel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(HttpServletRequest request,
			@RequestParam("excleFile") MultipartFile file) {
		String[] files = new String[] { "name", "fm_code", "site_id", "rm_id", "csi_id", "use_name", "use_dept",
				"service_dept", "brand", "factory", "power", "model", "parameter1", "parameter2", "eq_description" };
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, "导入失败");
		}
		List<YJWYEqModel> list = JSONArray.parseArray(jsonArray.toJSONString(), YJWYEqModel.class);
		String name = "";
		String fm_code = "";
		String site_id = "";
		String rm_id = "";
		String csi_id = "";
		String use_name = "";
		String use_dept = "";
		int connt = list.size();
		int row = 0;
		// 循环判断数据准确性，问题数据跳过不提交；
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				YJWYEqModel model = list.get(i);
				// 判断名称非空
				if (StringUtils.isEmpty(model.getName())) {
					name += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断编号非空
				if (StringUtils.isEmpty(model.getFm_code())) {
					fm_code += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断项目编号非空
				if (StringUtils.isEmpty(model.getSite_id())) {
					site_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断机房编号非空
				if (StringUtils.isEmpty(model.getRm_id())) {
					rm_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断分类非空
				if (StringUtils.isEmpty(model.getCsi_id())) {
					csi_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断惯用名非空
				if (StringUtils.isEmpty(model.getUse_name())) {
					use_name += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断使用部门非空
				if (StringUtils.isEmpty(model.getUse_dept())) {
					use_dept += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				JdbcTemplate read = dao.getReadTemplate();
				// 判断项目存在
				YJWYProjectModel[] projectModels = projectService.query(Query.from(YJWYProjectModel.META_ID)
						.and(Condition.create("project_code_", model.getSite_id())));
				if (projectModels == null || projectModels.length == 0) {
					site_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					model.setSite_id(projectModels[0].getPk_project());
				}
				// 判断机房编号存在
				YJWYRoomModel roomModel = roomService.queryOneByCondition(Query.from(YJWYRoomModel.META_ID)
						.and(Condition.create("fm_code", model.getRm_id())));
				if (roomModel == null) {
					rm_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					model.setRm_id(roomModel.getRm_id());
				}
				// 判断编号唯一性
				String countSql1 = "select count(eq_id) from yjwy_fmdata_eq where fm_code='" + model.getFm_code() + "'";
				int countCode1 = read.queryForObject(countSql1, Integer.class);
				if (countCode1 > 0) {
					fm_code += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断设备分类存在
				String countSql2 = "select count(csi_id) from yjwy_fmdata_csi where csi_id='" + model.getCsi_id() + "'";
				int countCode2 = read.queryForObject(countSql2, Integer.class);
				if (countCode2 <= 0) {
					csi_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断用户权限可以导入设备
				String countsql3 = "select count(t3.org_project_) from ( "
						+ "select pk_station_ from yjwy_pub_user_station where pk_user_ = '"
						+ UserUtil.getCurrentUserPk() + "' ) t1 "
						+ "left join yjwy_pub_station t2 on t1.pk_station_ = t2.pk_station_ "
						+ "left join yjwy_pub_org t3 on t2.pk_org_ = t3.pk_org_ and t3.org_project_ = '"
						+ model.getSite_id() + "'";
				int countCode3 = read.queryForObject(countsql3, Integer.class);
				if (countCode3 <= 0) {
					site_id += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				model.setEq_id(UUID.randomUUID().toString());
				model = completeYJWYEqModel(model);
				// 设备是否可用 = 是否报废
				model.setStatus("1");
				model.setActive(1);
				model.setFlag(1);
				try {
					ModelAndResult mr = saveDevice(model);
					if(mr!=null&&(mr.get(ModelAndResult.SUCCESS)+"").equals("true")) {
						
					} else {
						list.remove(i);
						i--;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//				yjwyEqService.saveModels(new YJWYEqModel[] { model });
				row++;
			}
		}
		int fail = connt - list.size();
		String output = "导入成功【" + list.size() + "】条，失败【" + fail + "】条。";
		if (!StringUtils.isEmpty(name)) {
			output += "名称问题【" + name + "】行。";
		}
		if (!StringUtils.isEmpty(fm_code)) {
			output += "编号问题【" + fm_code + "】行。";
		}
		if (!StringUtils.isEmpty(site_id)) {
			output += "项目编号问题【" + site_id + "】行。";
		}
		if (!StringUtils.isEmpty(rm_id)) {
			output += "机房编号问题【" + rm_id + "】行。";
		}
		if (!StringUtils.isEmpty(csi_id)) {
			output += "分类编号问题【" + csi_id + "】行。";
		}
		if (!StringUtils.isEmpty(use_name)) {
			output += "惯用名问题【" + use_name + "】行。";
		}
		if (!StringUtils.isEmpty(use_dept)) {
			output += "使用部门问题【" + use_dept + "】行。";
		}
		return new ModelAndResult(true, output);
	}

	@LimitCheck(type=DeviceType.class)
	public ModelAndResult saveDevice(YJWYEqModel model) {
		yjwyEqService.saveModels(new YJWYEqModel[] { model });
		return new ModelAndResult();
	}

	/**
	 * check fm_code
	 */
	@RequestMapping(value = "queryFmeq", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryFmeq(@RequestBody YJWYEqModel model){
		Query query = Query.from(YJWYEqModel.META_ID);
		query.where(new Condition("fm_code", QueryContents.TYPE_EQ,model.getFm_code()));
		YJWYEqModel[] queryResults = yjwyeqBusinessService.query(query);
		if(queryResults.length==0)
			return new ModelAndResult(true);
		else{
			if(!StringUtils.isEmpty(model.getEq_id())){
				if(queryResults[0].getEq_id().equals(model.getEq_id()))
					return new ModelAndResult(true);
				else 
					return new ModelAndResult(false);
			}
			return new ModelAndResult(false);
		}
			
	}
	
	/**
	 * 完善设备的部分信息
	 * 
	 * @param model
	 * @return
	 */
	public YJWYEqModel completeYJWYEqModel(YJWYEqModel model) {
		model.setPk_crop(DeviceUtil.getPkCrop());
		model.setList_code(model.getFm_code());
		model.setDms_update_time(DateTimeUtil.getTimestampStr());
		model.setLong_description(model.getRm_id()+"|" + model.getName());
		model.setUsual_name(model.getRm_id() + "=" + model.getUse_name());
		model.setRm_id(getRoom(model.getRm_id(), model.getAttribute("fk_resource_id")+"", model.getAttribute("rm_name")+"", model.getSite_id()));
		return model;
	}
	/**
	 * 查询机房名称，如果不存在则新增一个机房
	 * @param name
	 * @return
	 */
	/*
	public String getRoom(String name,String site_id){
		Query query = Query.from(YJWYRoomModel.META_ID);
		query.where(new Condition("name", QueryContents.TYPE_EQ,name));
		List<YJWYRoomModel> roomList = roomService.queryListByCondition(query);
		if(!roomList.isEmpty()){
			return roomList.get(0).getRm_id();
		}else{
			YJWYRoomModel model = new YJWYRoomModel();
			model.setRm_id(UUID.randomUUID().toString());
			model.setFm_code(UUID.randomUUID().toString());
			model.setName(name);
			model.setDms_update_time(DateTimeUtil.getTimestampStr());
			model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			model.setSite_id(site_id);
			model.setFlag(1);
			return roomService.save(model).get(0).getRm_id();
		}
	}
	*/
	
	public String getRoom(String id, String resource_id, String name, String site_id){
		Query query = Query.from(YJWYRoomModel.META_ID);
		query.where(Condition.eq("site_id", site_id).and(new Condition("rm_id", QueryContents.TYPE_EQ,id).or(new Condition("fk_resource_id", QueryContents.TYPE_EQ,resource_id))));
		List<YJWYRoomModel> roomList = roomService.queryListByCondition(query);
		if(!roomList.isEmpty()){
			return roomList.get(0).getRm_id();
		}
		YJWYRoomModel model = new YJWYRoomModel();
		model.setRm_id(UUID.randomUUID().toString());
		model.setFm_code(UUID.randomUUID().toString());
		model.setName(name);
		model.setFk_resource_id(resource_id);
		model.setDms_update_time(DateTimeUtil.getTimestampStr());
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setSite_id(site_id);
		model.setFlag(1);
		return roomService.save(model).get(0).getRm_id();
	}
}
