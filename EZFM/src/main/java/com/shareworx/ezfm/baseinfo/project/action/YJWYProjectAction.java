package com.shareworx.ezfm.baseinfo.project.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shareworx.ezfm.device.util.DictUtils;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dms365.ezfm.limitcheck.annotation.LimitCheck;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.limitcheck.type.ProjectType;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 项目管理操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/project")
public class YJWYProjectAction {

	final static Logger log = Logger.getLogger(YJWYProjectAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/project";
	/** 跳转页面 */
	public final static String MAP_RAIL_FORWARD = "baseinfo/project_map";
	/** BIM跳转页面*/
	public final static String BIM_FORWARD = "baseinfo/bim";
	/** BIM2跳转页面*/
	public final static String BIM_FORWARD2 = "baseinfo/bim2";
	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService service;
	
	public void setService(YJWYProjectBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService orgService;
	
	public void setOrgService(DefaultOrgBusinessService orgService) {
		this.orgService = orgService;
	}
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService domainService;



	
	public void setDomainService(YJWYProjectDomainService domainService) {
		this.domainService = domainService;
	}
	
	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService baseService;
	
	public void setBaseService(IBaseInfoQueryService baseService) {
		this.baseService = baseService;
	}
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="bim", method=RequestMethod.GET)
	public ModelAndView bimForward(){
		return new ModelAndView(BIM_FORWARD2);
	}

	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="bim2", method=RequestMethod.GET)
	public ModelAndView bimForward2(){
		return new ModelAndView(BIM_FORWARD);
	}
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;
	
	/**
	 * 转向项目围栏页面
	 * @return
	 */
	@RequestMapping(value="rail/index/{id}", method=RequestMethod.GET)
	public ModelAndView mapRailForward(@PathVariable(value="id") String id){
		YJWYProjectModel model = domainService.queryById(id);
		ModelAndView mav = new ModelAndView(MAP_RAIL_FORWARD);
		mav.addObject("lon", model.getSite_lon());
		mav.addObject("lat",model.getSite_lat());
		mav.addObject("rails", model.getSite_rails());
		mav.addObject("projectId", model.getPk_project());
		if(StringUtils.isEmpty(model.getSite_lon())&&StringUtils.isEmpty(model.getSite_lat())&&StringUtils.isEmpty(model.getSite_rails())){
			if(!StringUtils.isEmpty(model.getPk_city())){
				Map<String,Object> map = baseService.queryCityForMap(Integer.parseInt(model.getPk_city()));
                String  name = (String) map.get("name");
                if(name.equals("市辖区")||name.equals("县")){
                    Map<String, Object> parent_id = baseService.queryCityForMap(Integer.parseInt(map.get("parent_id") + ""));
                   name = (String) parent_id.get("name");
                }
				mav.addObject("city", name);
			}
		}
		return mav;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.neq("delete_flag_", "1"));
		//默认查询当前人所属项目所有数据
//		Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
//		query.and(Condition.in("pk_project_",projectIds.toArray()));
		YJWYProjectModel[] projects = UserUtil.getUserProject();
		String projectid = "";
		List<String> projectIdList = new ArrayList<String>();
		if(projects!=null && projects.length>0) {
			for(int i=0;i<projects.length;i++) {
				projectid += ("'"+projects[i].getPk_project()+"'");
				if(i!=projects.length-1) {
					projectid+=",";
				}
				projectIdList.add(projects[i].getPk_project());
			}
		}
		query.and(Condition.in(YJWYProjectModel.PK_PROJECT+"_", projectIdList.toArray(new String[]{})));
		YJWYProjectModel[] models = service.query(query);
		YJWYProjectDao domainDao = SpringUtils.getBean(YJWYProjectDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@LimitCheck(type=ProjectType.class)
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYProjectModel model) {
		//YJWYBillModelUtils.covert(model);BUG太多不于使用
		if(!doValidateCode(model.getProject_code(),model.getPk_project())){
			return new ModelAndResult(false,"编码已存在，请更换编码");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis()+"");
		model.setDelete_flag("0");
		YJWYProjectModel[] rs = service.save(new YJWYProjectModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	//TODO 未验证数据权限，公有云存在用户修改别人数据的风险
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYProjectModel model) {
		//YJWYBillModelUtils.covert(model);BUG不必使用
		if(!doValidateCode(model.getProject_code(),model.getPk_project())){
			return new ModelAndResult(false,"编码已存在，请更换编码");
		}
		
		YJWYProjectModel example = new YJWYProjectModel();
		example.setPk_project(model.getPk_project());
		YJWYProjectModel[] models = service.load(example);

		if(models==null||models.length==0) {
			return new ModelAndResult(false,"数据不存在");
		}
		model.setDelete_flag(models[0].getDelete_flag());
		
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis()+"");
		YJWYProjectModel[] rs = service.update(new YJWYProjectModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYProjectModel[] models) {
		String inSql = "";
		for(YJWYProjectModel model : models){
			inSql = inSql+","+model.getPk_project();
		}
		inSql = inSql.substring(1);
		DefaultOrgModel[] orgmodels = orgService.query(Query.from(DefaultOrgModel.META_ID).and(Condition.in("org_project_", inSql.split(",")).and(Condition.neq("delete_flag_", "1"))));
		if(null!=orgmodels && orgmodels.length>0){
			return new ModelAndResult(false,"删除的项目已关联组织结构，不可以删除");
		}
		models = service.query(Query.from(YJWYProjectModel.META_ID).and(Condition.in("pk_project_", inSql.split(","))));
		for (YJWYProjectModel model : models) {
			model.setDelete_flag("1");
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis()+""); 
		}
		YJWYProjectModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}
	
	/**
	 * 不建议使用 后期不于维护
	 * 建议使用baseinfo.pu包下BaseInfoQuesyAction
	 * @param param
	 * @return
	 */
	@RequestMapping(value="combo", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult combo(@RequestParam(value="param", required=false) String param) {
		JSONArray array = new JSONArray();
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.neq("delete_flag_", "1"));
		YJWYProjectModel[] models = service.query(query);
		
		for(YJWYProjectModel m : models){
			JSONObject j = new JSONObject();
			j.put("value", m.getPk_project());
			j.put("text", m.getProject_name());
			array.add(j);
		}
		ModelAndResult mar = new ModelAndResult(array);
		return mar;
	}
	/**
	 * 验证编码是否重复
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateCode(String code,String id){
		if(!StringUtils.isEmpty(id)){
			YJWYProjectModel model = service.query(Query.from(YJWYProjectModel.META_ID).and(Condition.create("pk_project_", id)))[0];
			if(code.equals(model.getProject_code())){
				return true;
			}
		}
		
		YJWYProjectModel[] models = service.query(Query.from(YJWYProjectModel.META_ID).and(Condition.create("project_code_", code)));
		if(null!=models && models.length>0){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 更新项目围栏
	 * @param rails
	 * @param lon
	 * @param lat
	 * @return
	 */
	@RequestMapping(value="rails/save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult updateRails( @RequestParam String id,@RequestParam String rails,@RequestParam String lon,@RequestParam String lat) {
		YJWYProjectModel model = domainService.queryById(id);
		model.setSite_rails(rails);
		model.setSite_lon(lon);
		model.setSite_lat(lat);
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis()+"");
		YJWYProjectModel[] rs = service.update(new YJWYProjectModel[]{model});
		return new ModelAndResult(rs);
	}

    /**
     * 工具类测试
     * @return
     */
	@RequestMapping(value="test", method=RequestMethod.GET)
	public ModelAndResult test(){
		YJWYDictModel engineer_specialtys = DictUtils.getDictByCode("engineer_specialtys");
        YJWYDictModel[] engineer_specialtys1 = DictUtils.getChildDictByCode("engineer_specialtys");
        YJWYDictModel yjwyDictModel = DictUtils.queryById("000000201608080000W7");
        YJWYDictModel dictByNameAndParentCode = DictUtils.getDictByNameAndParentCode("engineer_specialtys", "强电");
        return new ModelAndResult();
	}
}
