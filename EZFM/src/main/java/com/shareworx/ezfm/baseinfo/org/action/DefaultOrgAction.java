package com.shareworx.ezfm.baseinfo.org.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.org.dao.DefaultOrgDao;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService;
import com.shareworx.ezfm.easyui.model.EasyUiTreeModel;
import com.shareworx.ezfm.easyui.model.EasyUiTreeUtil;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 组织架构操作控制器
 * 
 * @author zhentong.jiaT
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/org")
public class DefaultOrgAction {

	final static Logger log = Logger.getLogger(DefaultOrgAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/org";

	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService service;
	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService staService;
	@Autowired
	@Qualifier(UserStationBusinessService.ID)
	private UserStationBusinessService usService;
	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService uService;

	public void setService(DefaultOrgBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

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
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.neq("delete_flag_", "1"));
		DefaultOrgModel[] models = service.query(query);
		DefaultOrgDao domainDao = SpringUtils.getBean(DefaultOrgDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/all", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTree(@RequestParam(value = "crop", required = false) String crop) {
		String sql = " select  o.hierarchy_ids_ 'hierarchy_ids',o.pk_org_ 'pk_org',o.pk_crop_ 'pk_crop',o.pk_parent_ 'pk_parent',o.org_code_ 'org_code',o.org_name_ 'org_name',o.org_type_ 'org_type',o.memo_ 'memo',o.org_area_ 'org_area',o.org_project_ 'org_project',o.create_user_ 'create_user',o.create_time_ 'create_time', o.last_modify_user_ 'last_modify_user', o.last_modify_time_ 'last_modify_time', o.update_time_ 'update_time', o.delete_flag_ 'delete_flag',o.sort_ 'sort',p.project_name_ 'project_name',a.area_name_ 'area_name' from(select * from yjwy_pub_org where 1=1 and delete_flag_ <> 1 and pk_crop_ = '"+crop+"')o" + " left join yjwy_pub_project p on o.org_project_ = p.pk_project_" + " left join yjwy_pub_area a on o.org_area_ = a.pk_area_" + " order by o.sort_";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);
		/*
		 * JONSONJSON.parseArray(JSON.toJSONString(list)); DefaultOrgModel[]
		 * models = service.query(query);
		 */
		List<ZtreeModel> tree = new ArrayList<>();
		for (Map<String, Object> m : list) {
			ZtreeModel model = new ZtreeModel();
			model.setId(m.get("pk_org") + "");
			model.setpId(m.get("pk_parent") + "");
			model.setName(m.get("org_name") + "");
			model.setAttributes(m);
			tree.add(model);
		}
		// List<EasyUiTreeModel> tree = EasyUiTreeUtil.covertTreeModel(models);
		ModelAndResult mar = new ModelAndResult(tree);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/easytree", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEasyUiTree(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.neq("delete_flag_", "1")).order(QueryOrder.asc("sort_"));
		DefaultOrgModel[] models = service.query(query);
		List<EasyUiTreeModel> tree = EasyUiTreeUtil.covertTreeModel(models);
		ModelAndResult mar = new ModelAndResult(tree);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody DefaultOrgModel model) {
		// YJWYBillModelUtils.covert(model);BUG 不要使用
		if (!doValidateCode(model.getOrg_code(), model.getPk_org())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		JdbcTemplate read = dao.getReadTemplate();
		String sql="select count(pk_org_) from yjwy_pub_org where org_name_='"
				+ model.getOrg_name()
				+ "' and pk_parent_='"
				+ model.getPk_parent()
				+ "' and delete_flag_='0'";
		int count = read.queryForObject(sql, Integer.class);
		if(count>0){
			return new ModelAndResult(false,"该组织下已存在此名称，请查证后重试！");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		model.setDelete_flag("0");
		DefaultOrgModel[] rs = service.save(new DefaultOrgModel[] { model });
		DefaultOrgModel tempModel = rs[0];
		// 设置层级字段
		String pk_parent = tempModel.getPk_parent();
		// 顶级
		if ("root".equals(pk_parent)) {
			model.setHierarchy_ids(pk_parent + "|" + tempModel.getPk_org() + "|");
		} else {
			model.setHierarchy_ids(tempModel.getPk_org() + "|");
			putHierarchy_ids(tempModel, tempModel);
		}
		rs = service.update(new DefaultOrgModel[] { tempModel });
		return new ModelAndResult(rs);
	}

	/**
	 * 递归读取层级model，设置层级字段
	 * 
	 * @param orgModel
	 * @param bigOrg
	 */
	private void putHierarchy_ids(DefaultOrgModel orgModel, DefaultOrgModel bigOrg) {
		String pk_parent = bigOrg.getPk_parent();
		orgModel.setHierarchy_ids(pk_parent + "|" + orgModel.getHierarchy_ids());
		if (!"root".equals(pk_parent)) {
			DefaultOrgModel[] orgModels = service.query(Query.from(DefaultOrgModel.META_ID).where(new Condition("pk_org_", QueryContents.TYPE_EQ, pk_parent)));
			putHierarchy_ids(orgModel, orgModels[0]);
		}
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody DefaultOrgModel model) {
		// YJWYBillModelUtils.covert(model);BUG太多不要使用
		if (!doValidateCode(model.getOrg_code(), model.getPk_org())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		JdbcTemplate read = dao.getReadTemplate();
		String sql="select count(pk_org_) from yjwy_pub_org where org_name_='"
				+ model.getOrg_name()
				+ "' and pk_parent_='"
				+ model.getPk_parent()
				+ "' and delete_flag_='0'";
		int count = read.queryForObject(sql, Integer.class);
		if(count==1){
			String sql1="select count(pk_org_) from yjwy_pub_org where org_name_='"
					+ model.getOrg_name()
					+ "' and pk_parent_='"
					+ model.getPk_parent()
					+ "' and delete_flag_='0' and pk_org_='"
					+ model.getPk_org()
					+ "'";
			int count1=read.queryForObject(sql1, Integer.class);
			if(count1!=1){
				return new ModelAndResult(false,"该组织下已存在此名称，请查证后重试！");
			}
			
		}
		if(count>1){
			return new ModelAndResult(false,"该组织下已存在此名称，请查证后重试！");
		}
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		DefaultOrgModel oldModel = dao.queryById(DefaultOrgModel.META_ID, model.getPk_org());
		if(oldModel.getOrg_area()==null)
			oldModel.setOrg_area("");
		if(oldModel.getOrg_project()==null)
			oldModel.setOrg_project("");
		if(oldModel.getOrg_type().equals("1") && !oldModel.getOrg_area().equals(model.getOrg_area())){
			dao.getWriteTemplate().execute("update yjwy_pub_org set org_area_ = '"+model.getOrg_area()+"',org_project_='' where hierarchy_ids_ like '%|"+model.getPk_org()+"|%' and (org_type_ = '2' or org_type_ = '3')");
		}else if(oldModel.getOrg_type().equals("2") && !oldModel.getOrg_project().equals(model.getOrg_project())){
			dao.getWriteTemplate().execute("update yjwy_pub_org set org_project_='"+model.getOrg_project()+"' where hierarchy_ids_ like '%|"+model.getPk_org()+"|%' and org_type_ = '3'");
		}
		DefaultOrgModel[] rs = service.update(new DefaultOrgModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody DefaultOrgModel[] models) {
		String inSql = "";
		for (DefaultOrgModel model : models) {
			inSql = inSql + "," + model.getPk_org();
		}
		inSql = inSql.substring(1);
		YJWYStationModel[] stas = staService.query(Query.from(YJWYStationModel.META_ID).and(Condition.in("pk_org_", inSql.split(",")).and(Condition.neq("delete_flag_", "1"))));
		if (null != stas && stas.length > 0) {
			return new ModelAndResult(false, "该组织已被岗位关联，不可以删除");
		}
		models = service.query(Query.from(DefaultOrgModel.META_ID).and(Condition.in("pk_org_", inSql.split(","))));
		for (DefaultOrgModel model : models) {
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setDelete_flag("1");
		}
		DefaultOrgModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 组织人员关联关系
	 * 
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult user(@RequestParam(value = "pk_org", required = false) String pk_org) {
		Query query = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_org_").eq(pk_org));
		YJWYStationModel[] sma = staService.query(query);
		if (ArrayUtils.isEmpty(sma)) {
			return new ModelAndResult();
		}
		List<String> inStationFeild = new ArrayList<String>();
		for (YJWYStationModel m : sma) {
			inStationFeild.add(m.getPk_station());
		}

		Query query2 = Query.from(UserStationModel.META_ID).and(Condition.in("pk_station_", inStationFeild.toArray(new String[0])));
		UserStationModel[] usl = usService.query(query2);
		if (ArrayUtils.isEmpty(usl)) {
			return new ModelAndResult();
		}
		List<String> inUserField = new ArrayList<String>();
		for (UserStationModel m : usl) {
			inUserField.add(m.getPk_user());
		}
		Query query3 = Query.from(YJWYUserModel.META_ID).and(Condition.in("pk_user_", inUserField.toArray(new String[0])).and(Condition.neq("delete_flag_", "1")));
		YJWYUserModel[] us = uService.query(query3);
		return new ModelAndResult(us);
	}

	/**
	 * 判断编码是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateCode(String code, String id ) {
		if (!StringUtils.isEmpty(id)) {
			DefaultOrgModel model = service.query(Query.from(DefaultOrgModel.META_ID).and(Condition.create("pk_org_", id)))[0];
			if (code.equals(model.getOrg_code())) {
				return true;
			}
		}
		DefaultOrgModel[] models = service.query(Query.from(DefaultOrgModel.META_ID).and(Condition.create("org_code_", code)).and(Condition.create("delete_flag_", 0)));
		if (null != models && models.length > 0) {
			return false;
		}
//		DefaultOrgModel [] modelsname=service.query(Query.from(DefaultOrgModel.META_ID).and(Condition.create("org_name_",name)).and(Condition.create("pk_parent_",parent)));
//		if(null != modelsname && modelsname.length > 0){
//			return false;
//		}
		return true;
	}
}
