package com.shareworx.ezfm.baseinfo.area.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.dao.YJWYAreaDao;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 区域管理操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/area")
public class YJWYAreaAction {

	final static Logger log = Logger.getLogger(YJWYAreaAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/area";
	
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService service;

	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	YJWYAreaDomainService areaDomainService;
	
	public void setService(YJWYAreaBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService orgService;
	
	public void setOrgService(DefaultOrgBusinessService orgService) {
		this.orgService = orgService;
	}
	
	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;
	
	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYAreaModel[] areas = UserUtil.getUserArea();
		String areaid = "";
		List<String> areaIdList = new ArrayList<String>();
		if(areas!=null && areas.length>0) {
			for(int i=0;i<areas.length;i++) {
				areaid += ("'"+areas[i].getPk_area()+"'");
				if(i!=areas.length-1) {
					areaid+=",";
				}
				areaIdList.add(areas[i].getPk_area());
			}
		}
		query.and(Condition.neq("delete_flag_", "1"));
		query.and(Condition.in(YJWYAreaModel.PK_AREA+"_", areaIdList.toArray(new String[]{})));
		YJWYAreaModel[] models = service.query(query);
		YJWYAreaDao domainDao = SpringUtils.getBean(YJWYAreaDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		Map<String,YJWYAreaModel> mp = new HashMap<>();
		for(YJWYAreaModel m : models){
			mp.put(m.getPk_area(), m);
		}
		mar.setOthers(mp);
		return mar;
	}
	/**
	 * 建议使用baseinfo.pu包下BaseInfoQuesyAction/service
	 * @author zhenwei.shi
	 * 区域字典查询操作
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value="dictionary/query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForCombobox(HttpServletRequest req) {
		Query query = Query.from(YJWYAreaModel.META_ID);
		query.and(Condition.neq("delete_flag_", "1"));
		query.addSelect(new String[]{"pk_area_","area_name_"});
		YJWYAreaModel[] models = service.query(query);
		return new ModelAndResult(models);
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYAreaModel model) {
		//YJWYBillModelUtils.covert(model);一堆BUG不建议使用
//        String s = doValidateCode(model.getArea_code(), model.getPk_area(), model.getArea_name());
//        if(s != null){
//			return new ModelAndResult(false,s);
//		}
		JdbcTemplate read =dao.getReadTemplate();
		String sql="select count(pk_area_) from yjwy_pub_area where delete_flag_='0' and area_code_='"
				+ model.getArea_code()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count==1){
			return new ModelAndResult(false,"编号重复，请更换编号");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis()+"");
		model.setDelete_flag("0");
		YJWYAreaModel[] rs = service.save(new YJWYAreaModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYAreaModel model) {
//        String s = doValidateCode(model.getArea_code(), model.getPk_area(), model.getArea_name());
//        if(s != null){
//            return new ModelAndResult(false,s);
//        }
		JdbcTemplate read =dao.getReadTemplate();
		String sql="select count(pk_area_) from yjwy_pub_area where delete_flag_='0' and area_code_='"
				+ model.getArea_code()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count==1){
			 sql="select count(pk_area_) from yjwy_pub_area where delete_flag_='0' and area_code_='"
					+ model.getArea_code()
					+ "'";
			 count=read.queryForObject(sql, Integer.class);
			 if(count!=1){
				 return new ModelAndResult(false,"编号重复，请更换编号");
			 }
		}
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis()+""); 
		YJWYAreaModel[] rs = service.update(new YJWYAreaModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYAreaModel[] models) {
		String inSql = "";
		for(YJWYAreaModel model : models){
			inSql = inSql+","+model.getPk_area();
		}
		inSql = inSql.substring(1);
		DefaultOrgModel[] orgmodels = orgService.query(Query.from(DefaultOrgModel.META_ID).and(Condition.in("org_area_", inSql.split(",")).and(Condition.neq("delete_flag_", "1"))));
		if(null!=orgmodels && orgmodels.length>0){
			return new ModelAndResult(false,"删除的区域已关联组织结构，不可以删除");
		}
		
		YJWYProjectModel[] projectmodels = projectService.query(Query.from(YJWYProjectModel.META_ID).and(Condition.in("pk_area_", inSql.split(",")).and(Condition.neq("delete_flag_", "1"))));
		if(null!=projectmodels && projectmodels.length>0){
			return new ModelAndResult(false,"删除的区域已关联项目，不可以删除");
		}
		models = service.query(Query.from(YJWYAreaModel.META_ID).and(Condition.in("pk_area_", inSql.split(","))));
		for(YJWYAreaModel model : models){
			model.setDelete_flag("1");
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis()+""); 
		}
		YJWYAreaModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}
	
	/**
	 * 不建议使用 后期不于维护
	 * 建议使用baseinfo.pu包下BaseInfoQuesyAction/Service
	 * @param param
	 * @return
	 */
	@RequestMapping(value="combo", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult combo(@RequestParam(value="param", required=false) String param) {
		JSONArray array = new JSONArray();
		Query query = JSON.parseObject(param, Query.class);
		YJWYAreaModel[] models = service.query(query);
		for(YJWYAreaModel m : models){
			JSONObject j = new JSONObject();
			j.put("value", m.getPk_area());
			j.put("text", m.getArea_name());
			array.add(j);
		}
		ModelAndResult mar = new ModelAndResult(array);
		return mar;
	}
	
	public String doValidateCode(String code,String id ,String name){
//		if(!StringUtils.isEmpty(id)){
//			YJWYAreaModel model = service.query(Query.from(YJWYAreaModel.META_ID).
//					and(Condition.create("pk_area_", id)))[0];
//			if(code.equals(model.getArea_code())){
//				return true;
//			}
//		}
//		Query area_code_ = Query.from(YJWYAreaModel.META_ID)
//				.and(Condition.create("area_code_", code))
//				.and(Condition.create("delete_flag","0"));
//		YJWYAreaModel[] models = service.query(area_code_);
//		if(null!=models && models.length>0){
//			return false;
//		}

		YJWYAreaModel model = new YJWYAreaModel();
		model.setDelete_flag("0");
		model.setArea_code(code);
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		List<YJWYAreaModel> yjwyAreaModels = areaDomainService.queryByExample(model);
		if(!(yjwyAreaModels.size() == 0 ||yjwyAreaModels.size() == 1 && yjwyAreaModels.get(0).getPk_area().equals(id))){
			return "编号重复，请更换编号";
		}

		model.remove("area_code");

		model.setArea_name(name);
		List<YJWYAreaModel> yjwyAreaModels2 = areaDomainService.queryByExample(model);
		if(!(yjwyAreaModels2.size() == 0 ||yjwyAreaModels2.size() == 1 && yjwyAreaModels.get(0).getPk_area().equals(id))){
			return "名称重复，请更换名称";
		}


		return null;
	}
}
