package com.shareworx.ezfm.device.patrol.plan.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService;
import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.list.service.YJWYListService;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanService;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保计划操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/patrol/plan")
public class YJWYPlanAction {

	final static Logger log = Logger.getLogger(YJWYPlanAction.class);

	/** 跳转页面 */
	public final static String CHECK = "ezfm/device/patrol/plan/check_plan_listcard";
	public final static String MAINT = "ezfm/device/patrol/plan/maint_plan_listcard";

	@Autowired
	@Qualifier(YJWYPlanBusinessService.ID)
	private YJWYPlanBusinessService planBusinessService;

	public void setPlanBusinessService(YJWYPlanBusinessService planBusinessService) {
		this.planBusinessService = planBusinessService;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(YJWYEqBusinessService.ID)
	private YJWYEqBusinessService eqBusinessService;

	public void setEqBusinessService(YJWYEqBusinessService eqBusinessService) {
		this.eqBusinessService = eqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYCsiBusinessService.ID)
	private YJWYCsiBusinessService csiBusinessService;

	public void setCsiBusinessService(YJWYCsiBusinessService csiBusinessService) {
		this.csiBusinessService = csiBusinessService;
	}

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService roomService;

	public void setRoomService(YJWYRoomBusinessService roomService) {
		this.roomService = roomService;
	}

	@Autowired
	@Qualifier(YJWYPmpBusinessService.ID)
	private YJWYPmpBusinessService pmpBusinessService;

	public void setPmpBusinessService(YJWYPmpBusinessService pmpBusinessService) {
		this.pmpBusinessService = pmpBusinessService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(YJWYGroupBusinessService.ID)
	private YJWYGroupBusinessService groupBusinessService;

	public void setGroupBusinessService(YJWYGroupBusinessService groupBusinessService) {
		this.groupBusinessService = groupBusinessService;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProjectService;

	public void setSiteProjectService(YJWYSiteProjectService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Autowired
	@Qualifier(YJWYPlanService.ID)
	private YJWYPlanService planService;

	public void setPlanService(YJWYPlanService planService) {
		this.planService = planService;
	}

	@Autowired
	@Qualifier(YJWYPlanEqBusinessService.ID)
	private YJWYPlanEqBusinessService planEqBusinessService;

	public void setPlanEqBusinessService(YJWYPlanEqBusinessService planEqBusinessService) {
		this.planEqBusinessService = planEqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYListService.ID)
	private YJWYListService listService;

	public void setListService(YJWYListService listService) {
		this.listService = listService;
	}

	@Autowired
	@Qualifier(YJWYTaskBusinessService.ID)
	private YJWYTaskBusinessService taskBusinessService;

	public void setTaskBusinessService(YJWYTaskBusinessService taskBusinessService) {
		this.taskBusinessService = taskBusinessService;
	}
	@Autowired
	@Qualifier(YJWYDictBusinessService.ID)
	private YJWYDictBusinessService dictService;
	/**
	 * 转向巡检计划列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkindex", method = RequestMethod.GET)
	public ModelAndView checkListForward() {
		return new ModelAndView(CHECK);
	}

	/**
	 * 转向维保计划列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "maintindex", method = RequestMethod.GET)
	public ModelAndView maintListForward() {
		return new ModelAndView(MAINT);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		
		YJWYProjectModel[] projects = null;
		Set<String> projectids = new HashSet<String>();
		if(params.getPk_area()==null||"default".equals(params.getPk_area())){
			projects = UserUtil.getUserProject();
		}else{
			if(params.getPk_project()==null||"default".equals(params.getPk_project())){
				projects=UserUtil.getUserProject(params.getPk_area());
			}else{
				projectids.add(params.getPk_project());				
			}
		}
		if(projects != null && projects.length>0){
			for(int i=0;i<projects.length;i++){
				projectids.add(projects[i].getPk_project());
			}
		}
		params.setProject_ids(projectids);
		
		List<Map<String, Object>> list = planService.queryMap(params);
		long count = planService.queryCount(params);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", list);
		return mar;
	}

	/**
	 * 查询工艺步骤
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "query/pmp", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryPmp(ParamEntity params) {
		Query query = Query.from(YJWYPmpModel.META_ID);
		query.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
		query.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		query.and(new Condition("bus_type", QueryContents.TYPE_EQ, params.getBus_type()));
		YJWYPmpModel[] models = pmpBusinessService.query(query);
		ModelAndResult mr = new ModelAndResult();
		mr.setTotal(models.length);
		mr.setAttribute("rows", models);
		return mr;
	}

	/**
	 * 查询设备列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/eq", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEq(@RequestParam(value = "rm_id", required = false) String rm_id, @RequestParam(value = "rm_ids[]", required = false) List<String> rm_ids) {
		Query query = Query.from(YJWYEqModel.META_ID);
		query.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
		query.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		ModelAndResult mr = new ModelAndResult();
		List<DomainModel> list = new ArrayList<>();
		if (!DeviceUtil.stringIsEmpty(rm_id)) {
			// 查询条件
			query.and(new Condition("rm_id", QueryContents.TYPE_EQ, rm_id));
		} else {
			if (null != rm_ids && rm_ids.size() > 0) {
				// 查询条件
				query.and(new Condition("rm_id", QueryContents.TYPE_IN, rm_ids));
			} else {
				mr.setAttribute("rows", list);
				return mr;
			}
		}
		YJWYEqModel[] models = eqBusinessService.query(query);
		Set<String> csi_ids = new HashSet<>();
		for (YJWYEqModel yjwyEqModel : models) {
			yjwyEqModel.setAttribute("id", yjwyEqModel.getEq_id());
			yjwyEqModel.setAttribute("pid", yjwyEqModel.getCsi_id());
			csi_ids.add(yjwyEqModel.getCsi_id());
		}
		list.addAll(Arrays.asList(models));
		Query query2 = Query.from(YJWYDictModel.META_ID);
		query2.where(new Condition("dict_parent_", QueryContents.TYPE_EQ, "csiTypes"));
//		Query query2 = Query.from(YJWYCsiModel.META_ID);
//		query2.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
//		query2.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
//		query2.and(new Condition("csi_id", QueryContents.TYPE_IN, csi_ids));
//		YJWYCsiModel[] csiModels = csiBusinessService.query(query2);
		YJWYDictModel[] dictModels = dictService.query(query2);
		for (YJWYDictModel dictModel : dictModels) {
			dictModel.setAttribute("id", dictModel.getPk_dict());
			dictModel.setAttribute("name", dictModel.getDict_name());
			dictModel.setAttribute("pid", 0);
		}
		list.addAll(Arrays.asList(dictModels));
		mr.setAttribute("rows", list);
		return mr;
	}

	/**
	 * 查询设备分类列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/csi", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryCsi(@RequestParam(value = "csi_id", required = false) String csi_id, @RequestParam(value = "csi_ids[]", required = false) List<String> csi_ids) {
		ModelAndResult mr = new ModelAndResult();
		Query query = Query.from(YJWYDictModel.META_ID);
		query.where(new Condition("dict_parent_", QueryContents.TYPE_EQ, "csiTypes"));
		if (!DeviceUtil.stringIsEmpty(csi_id)) {
			// 查询条件
			query.and(new Condition("pk_dict_", QueryContents.TYPE_EQ, csi_id));
		} else {
			if (null != csi_ids && csi_ids.size() > 0) {
				// 查询条件
				query.and(new Condition("pk_dict_", QueryContents.IN, csi_ids));
			} else {
				mr.setAttribute("rows", new YJWYDictModel[] {});
				return mr;
			}
		}
		// 查询数据
		YJWYDictModel[] models = dictService.query(query);
		mr.setAttribute("rows", models);
		return mr;
	}

	/**
	 * 查询机房列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/room", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryRoom(ParamEntity params) {
		Query roomQuery = Query.from(YJWYRoomModel.META_ID);
		roomQuery.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
		roomQuery.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		// YJWY的项目编号
		String pk_project = params.getPk_project();
		ModelAndResult mr = new ModelAndResult();
		if (null != pk_project && !"default".equals(pk_project)) {
//			String[] siteIds = siteProjectService.queryIds(new String[] { pk_project });
			String[] siteIds = new String[] { pk_project };
			if (DeviceUtil.arrayIsNotEmpty(siteIds)) {
				// 查询条件
				roomQuery.and(new Condition("site_id", QueryContents.IN, siteIds));
			} else {
				mr.setAttribute("rows", new YJWYCsiModel[] {});
				return mr;
			}
		} else {
			mr.setAttribute("rows", new YJWYCsiModel[] {});
			return mr;
		}
		// 查询数据
		YJWYRoomModel[] models = roomService.query(roomQuery);
		mr.setAttribute("rows", models);
		return mr;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYPlanModel model) {
		DeviceUtil.setCreateInfo(model);
		JdbcTemplate read =dao.getReadTemplate();
		String sql="select count(plan_id) from yjwy_patrol_plan where plan_name='"
				+ model.getPlan_name()
				+ "' and pk_project='"
				+ model.getPk_project()
				+ "'";
		int count= read.queryForObject(sql, Integer.class);
		if(count==1){
			return new ModelAndResult(false,"该项目下已存在此名称，请更换名称！");
		}
		try {
			YJWYPlanModel[] rs = planService.saveModels(new YJWYPlanModel[] { model });
			return new ModelAndResult(rs);
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
	public @ResponseBody ModelAndResult update(@RequestBody YJWYPlanModel model) {
		DeviceUtil.setUpdateInfo(model);
		JdbcTemplate read =dao.getReadTemplate();
		String sql="select count(plan_id) from yjwy_patrol_plan where plan_name='"
				+ model.getPlan_name()
				+ "' and pk_project='"
				+ model.getPk_project()
				+ "'";
		int count= read.queryForObject(sql, Integer.class);
		if(count==1){
			 sql="select count(plan_id) from yjwy_patrol_plan where plan_name='"
					+ model.getPlan_name()
					+ "' and pk_project='"
					+ model.getPk_project()
					+ "' and plan_id='"
					+ model.getPlan_id()
					+ "'";
			count= read.queryForObject(sql, Integer.class);
			if(count!=1){
				return new ModelAndResult(false,"该项目下已存在此名称，请更换名称！");
			}
		}
		try {
			YJWYPlanModel[] rs = planService.updateModels(new YJWYPlanModel[] { model });
			return new ModelAndResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	/**
	 * 修改启用状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateEnable", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult updateEnable(@RequestBody YJWYPlanModel model) {
		DeviceUtil.setUpdateInfo(model);
		// 判断启用
		if (model.getIs_enable() == 1) {
			model.setNext_time(planService.getInitNext_time(model));
		}
		YJWYPlanModel[] models = planBusinessService.update(new YJWYPlanModel[] { model });
		return new ModelAndResult(models);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYPlanModel[] models) {
		// 获取主键id集合
		String[] plan_ids = DeviceUtil.getPrimaryKeyValue(models);
		// 判断是否被关联使用
		YJWYTaskModel[] taskModels = taskBusinessService.query(Query.from(YJWYTaskModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_IN, plan_ids)));
		if (!ArrayUtils.isEmpty(taskModels)) {
			ModelAndResult mar = new ModelAndResult();
			mar.setAttribute("usedFlag", 1);
			mar.setData(taskModels);
			return mar;
		}
		// 执行删除
		try {
			YJWYPlanModel[] rs = planService.deleteModels(models, plan_ids);
			return new ModelAndResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndResult(false);
		}
	}

	/**
	 * 根据计划id查询设备列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryEqsByPlan", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEqsByPlan(@RequestBody YJWYPlanModel model) {
		ModelAndResult mar = new ModelAndResult();
		List<Map<String, Object>> models = planService.queryEqMap(model.getPlan_id());
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 查询已关联的机房、设备、分类、工艺数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryEmEqCsi", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEmEqCsi(@RequestBody YJWYPlanModel model) {
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("eqs", this.getEqList(model));
		mar.setAttribute("rms", this.getRoomList(model));
		mar.setAttribute("csis", this.getCsiList(model));
		mar.setAttribute("row", model);
		return mar;
	}

	private List<Map<String, Object>> getRoomList(YJWYPlanModel model) {
		// 项目下全部的机房
		List<Map<String, Object>> roomList = deviceService.queryRoomsByPkProject(model.getPk_project());
		// 当前计划包含的机房id集合
		Set<String> idSet = deviceService.queryRoomIdsByPlanId(model.getPlan_id());
		if (roomList.size() > 0 && idSet.size() > 0) {
			String temp = null;
			for (Map<String, Object> room : roomList) {
				temp = (String) room.get("rm_id");
				// 初始：不选中
				room.put("checked", false);
				for (String id : idSet) {
					if (id.equals(temp)) {
						// 选中
						room.put("checked", true);
						break;
					}
				}
			}
		}
		return roomList;
	}

	private List<DomainModel> getEqList(YJWYPlanModel model) {
		List<DomainModel> list = new ArrayList<>();
		// 当前计划包含的机房id集合
		Set<String> idSet = deviceService.queryRoomIdsByPlanId(model.getPlan_id());
		if (idSet.size() <= 0) {
			return new ArrayList<>();
		}
		// 根据id集合获取机房下所有的设备
		YJWYEqModel[] eqModels = eqBusinessService.query(Query.from(YJWYEqModel.META_ID).where(new Condition("rm_id", QueryContents.TYPE_IN, idSet)).and(new Condition("flag", QueryContents.TYPE_EQ, 1)));
		// 获取计划包含的设备集合
		YJWYPlanEqModel[] planEqModels = planEqBusinessService.query(Query.from(YJWYPlanEqModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_EQ, model.getPlan_id())));
		Set<String> csi_ids = new HashSet<>();
		Set<String> checked_csi_ids = new HashSet<>();
		// 设置已选设备标识checked
		String temp = null;
		for (YJWYEqModel yjwyEqModel : eqModels) {
			yjwyEqModel.setAttribute("id", yjwyEqModel.getEq_id());
			yjwyEqModel.setAttribute("pid", yjwyEqModel.getCsi_id());
			csi_ids.add(yjwyEqModel.getCsi_id());
			temp = yjwyEqModel.getEq_id();
			for (YJWYPlanEqModel planEqModel : planEqModels) {
				if (planEqModel.getEq_id().equals(temp)) {
					checked_csi_ids.add(yjwyEqModel.getCsi_id());
					// 选中
					yjwyEqModel.put("checked", true);
					break;
				}
			}
		}
		list.addAll(Arrays.asList(eqModels));
		// 获取csi分类model
		Query query2 = Query.from(YJWYDictModel.META_ID);
		query2.where(new Condition("dict_parent_", QueryContents.TYPE_EQ, "csiTypes"));
//		Query query2 = Query.from(YJWYCsiModel.META_ID);
//		query2.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
//		query2.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
//		query2.and(new Condition("csi_id", QueryContents.TYPE_IN, csi_ids));
		YJWYDictModel[] dictModels = dictService.query(query2);
		for (YJWYDictModel yjwyDictModel : dictModels) {
			yjwyDictModel.setAttribute("id", yjwyDictModel.getPk_dict());
			yjwyDictModel.setAttribute("name", yjwyDictModel.getDict_name());
			yjwyDictModel.setAttribute("pid", 0);
			temp = yjwyDictModel.getPk_dict();
			// 分类已选设置
			for (String csi_id : checked_csi_ids) {
				if (csi_id.equals(temp)) {
					yjwyDictModel.put("checked", true);
					break;
				}
			}
		}
		list.addAll(Arrays.asList(dictModels));
		return list;
	}

	private List<Map<String, Object>> getCsiList(YJWYPlanModel model) {
		return deviceService.queryCsiPmpByPlanId(model.getPlan_id());
	}

}
