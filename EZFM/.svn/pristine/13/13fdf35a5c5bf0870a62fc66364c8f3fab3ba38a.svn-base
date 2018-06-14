package com.shareworx.ezfm.baseinfo.station.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.station.dao.YJWYStationDao;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 岗位管理操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/station")
public class YJWYStationAction {

	final static Logger log = Logger.getLogger(YJWYStationAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/station";
	/** 跳转岗位关联标准版本页面 */
	public final static String SON_EDITION_PAGE_FORWARD = "baseinfo/station_add_stanedtion";
	/** 跳转岗位关联核查标准页面 */
	public final static String SON_STANDARD_PAGE_FORWARD = "baseinfo/station_add_standard";
	/** 跳转岗位关联用户页面 */
	public final static String SON_USER_PAGE_FORWARD = "baseinfo/station_add_user";

	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService service;

	public void setService(YJWYStationBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(EditionStationBusinessService.ID)
	private EditionStationBusinessService stationEditionService;

	public void setStationEditionService(EditionStationBusinessService stationEditionService) {
		this.stationEditionService = stationEditionService;
	}

	@Autowired
	@Qualifier(StandardStationBusinessService.ID)
	private StandardStationBusinessService stationStandardService;

	public void setStationStandardService(StandardStationBusinessService stationStandardService) {
		this.stationStandardService = stationStandardService;
	}

	@Autowired
	@Qualifier(UserStationBusinessService.ID)
	private UserStationBusinessService userStationBusinessService;

	public void setUserStationBusinessService(UserStationBusinessService userStationBusinessService) {
		this.userStationBusinessService = userStationBusinessService;
	}

	@Autowired
	@Qualifier(EditionStationBusinessService.ID)
	private EditionStationBusinessService editionStationService;

	public void setEditionStationService(EditionStationBusinessService editionStationService) {
		this.editionStationService = editionStationService;
	}

	@Autowired
	@Qualifier(StandardStationBusinessService.ID)
	private StandardStationBusinessService standardStationService;

	public void setStandardStationService(StandardStationBusinessService standardStationService) {
		this.standardStationService = standardStationService;
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
	 * @author zhenwei.shi 转向岗位关联标准版本页面
	 * @return
	 */
	@RequestMapping(value = "son/edition/index", method = RequestMethod.GET)
	public ModelAndView sonEditionForward() {
		ModelAndView mav = new ModelAndView(SON_EDITION_PAGE_FORWARD);
		return mav;
	}

	/**
	 * @author zhenwei.shi 转向岗位关联标准版本页面
	 * @return
	 */
	@RequestMapping(value = "son/standard/index", method = RequestMethod.GET)
	public ModelAndView sonStandardForward() {
		ModelAndView mav = new ModelAndView(SON_STANDARD_PAGE_FORWARD);
		return mav;
	}

	/**
	 * @author zhenwei.shi 转向岗位关联用户页面
	 * @return
	 */
	@RequestMapping(value = "son/user/index", method = RequestMethod.GET)
	public ModelAndView sonUserForward() {
		ModelAndView mav = new ModelAndView(SON_USER_PAGE_FORWARD);
		return mav;
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
		YJWYStationModel[] models = service.query(query);
		YJWYStationDao domainDao = SpringUtils.getBean(YJWYStationDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	public static void filter (Object o,String... param){
		//利用反射，将object 属性设置为空


	}



	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/all", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTree(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		List<Condition> conds = query.getAndList();
		String andSql = "";
		for (Condition c : conds) {
			String operator = " = ";
			andSql = andSql + " and " + c.getKey() + operator + " '" + c.getValue() + "' ";
		}
		String sql = " select s.pk_station_ 'pk_station',s.pk_crop_ 'pk_crop',s.station_code_ 'station_code',s.station_name_ 'station_name',s.task_level_ 'task_level',s.station_level_ 'station_level',s.pk_dept_ 'pk_dept',s.pk_org_ 'pk_org',s.memo_ 'memo',s.pk_parent_ 'pk_parent',s.create_user_ 'create_user',s.create_time_ 'create_time',s.last_modify_user_ 'last_modify_user',s.last_modify_time_ 'last_modify_time',s.update_time_ 'update_time',s.delete_flag_ 'delete_flag',s.sort_ 'sort',d.dict_name_ 'task_level_name',d2.dict_name_ 'station_level_name',d3.dict_name_ 'pk_dept_name',o.org_name_ 'pk_org_name' from(select * from yjwy_pub_station where delete_flag_ <> '1' " + andSql + ") s" + " left join yjwy_pub_dict d on s.task_level_ = d.dict_code_"
				+ " left join yjwy_pub_dict d2 on s.station_level_ = d2.dict_code_" + " left join yjwy_pub_dict d3 on s.pk_dept_ = d3.dict_code_" + " left join yjwy_pub_org o on s.pk_org_ = o.pk_org_ order by CAST(s.sort_ as SIGNED) ";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);
		List<ZtreeModel> tree = new ArrayList<>();
		for (Map<String, Object> m : list) {
			ZtreeModel model = new ZtreeModel();
			model.setId(m.get("pk_station") + "");
			model.setpId(m.get("pk_parent") + "");
			model.setName(m.get("station_name") + "");
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
	@RequestMapping(value = "userstation/query/all", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTreeByUser(@RequestParam String pk_crop,@RequestParam String pk_user) {
		Query query = Query.from(UserStationModel.META_ID).and(Condition.create("pk_crop_",pk_crop)).and(Condition.create("pk_user_", pk_user));
		UserStationModel[] usms = userStationBusinessService.query(query);
		Map<String,String> map = new HashMap<>();
		for(UserStationModel us :usms){
			map.put(us.getPk_station(), us.getPk_station());
		}
		
		String sql = " select pk_station_ 'pk_station',pk_crop_ 'pk_crop',station_name_ 'station_name',pk_parent_ 'pk_parent',station_code_ 'station_code' from yjwy_pub_station where delete_flag_ <> 1 and pk_crop_ ='"+pk_crop+"' order by CAST(sort_ as SIGNED)  ";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);
		List<ZtreeModel> tree = new ArrayList<>();
		for (Map<String, Object> m : list) {
			ZtreeModel model = new ZtreeModel();
			model.setId(m.get("pk_station") + "");
			model.setpId(m.get("pk_parent") + "");
			model.setName(m.get("station_name") + "");
			if(!StringUtils.isEmpty(map.get(model.getId()))){
				model.setChecked(true);
			}
			model.setOpen(true);
			tree.add(model);
		}
		// List<EasyUiTreeModel> tree = EasyUiTreeUtil.covertTreeModel(models);
		ModelAndResult mar = new ModelAndResult(tree);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYStationModel model) {
		// YJWYBillModelUtils.covert(model);
		JdbcTemplate read =dao.getReadTemplate();
		if (!doValidateCode(model.getStation_code(), model.getPk_station())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		String sql="select count(pk_station_) from yjwy_pub_station where delete_flag_='0' and station_name_='"
				+ model.getStation_name()
				+ "' and pk_parent_='"
				+ model.getPk_parent()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count==1){
			return new ModelAndResult(false,"同节点下不能有相同的岗位名称，请更换名称！");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		model.setDelete_flag("0");
		YJWYStationModel[] rs = service.save(new YJWYStationModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYStationModel model) {
		// YJWYBillModelUtils.covert(model);
		JdbcTemplate read= dao.getReadTemplate();
		if (!doValidateCode(model.getStation_code(), model.getPk_station())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		String sql="select count(pk_station_) from yjwy_pub_station where delete_flag_='0' and station_name_='"
				+ model.getStation_name()
				+ "' and pk_parent_='"
				+ model.getPk_parent()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count>=1){
			 sql="select count(pk_station_) from yjwy_pub_station where delete_flag_='0' and station_name_='"
					+ model.getStation_name()
					+ "' and pk_parent_='"
					+ model.getPk_parent()
					+ "' and pk_station_='"
					+ model.getPk_station()
					+ "'";
			 count=read.queryForObject(sql, Integer.class);
			 if(count!=1){
				 return new ModelAndResult(false,"同节点下不能有相同的岗位名称，请更换名称！");
			 }
		}
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		YJWYStationModel[] rs = service.update(new YJWYStationModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYStationModel[] models) {
		String inSql = "";
		for (YJWYStationModel model : models) {
			inSql = inSql + "," + model.getPk_station();
		}
		String[] inField = inSql.substring(1).split(",");
		UserStationModel[] uss = userStationBusinessService.query(Query.from(UserStationModel.META_ID).and(Condition.in("pk_station_", inField)));
		if (null != uss && uss.length > 0) {
			return new ModelAndResult(false, "岗位下含有人员，请先移除该岗位人员");
		}
		EditionStationModel[] esms = editionStationService.query(Query.from(EditionStationModel.META_ID).and(Condition.in("pk_station", inField)));
		if (null != esms && esms.length > 0) {
			return new ModelAndResult(false, "岗位下含有标准版本，请先移除标准版本");
		}
		StandardStationModel[] ssms = standardStationService.query(Query.from(StandardStationModel.META_ID).and(Condition.in("pk_station", inField)));
		if (null != ssms && ssms.length > 0) {
			return new ModelAndResult(false, "岗位下含有核查标准，请先移除核查标准");
		}
		models = service.query(Query.from(YJWYStationModel.META_ID).and(Condition.in("pk_station_", inField)));
		for (YJWYStationModel model : models) {
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setDelete_flag("1");
		}
		YJWYStationModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * @author zhenwei.shi 移除版本操作
	 * @return
	 */
	@RequestMapping(value = "edition/remove/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteESM(@PathVariable(value = "pk_station") String pk_station, @RequestBody StandardEditionModel[] models) {
		String temIn = "";
		for (StandardEditionModel model : models) {
			temIn = temIn + "," + "'" + model.getPk_edition() + "'";
		}
		temIn = temIn.substring(1);
		String inSql = " select count(*) from yjwy_quality_inspectstandard where fk_standardedition in" + " (select pk_edition from yjwy_quality_edition_station where pk_station = '" + pk_station + "' and pk_edition in(" + temIn + "))" + " and pk_inspstan in" + " (select pk_standard from yjwy_quality_standard_station where pk_station = '" + pk_station + "')";
		JdbcTemplate read = dao.getReadTemplate();
		int sonCount = read.queryForObject(inSql, Integer.class);
		if (sonCount > 0) {
			return new ModelAndResult(false, "请先移除版本下已被岗位关联的标准");
		}
		List<EditionStationModel> list = new ArrayList<EditionStationModel>();
		for (StandardEditionModel model : models) {
			EditionStationModel esm = new EditionStationModel();
			esm.setPk_station(pk_station);
			esm.setPk_edition(model.getPk_edition());
			esm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			list.add(esm);
		}
		stationEditionService.delete(list.toArray(new EditionStationModel[] {}));
		return new ModelAndResult();
	}

	/**
	 * zhenwei.shi 加入用户操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "user/add/{pk_station}/{pk_user}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult addUser(@PathVariable(value = "pk_station") String pk_station, @PathVariable(value = "pk_user") String pk_user) {
		UserStationModel usm = new UserStationModel();
		usm.setPk_station(pk_station);
		usm.setPk_user(pk_user);
		usm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		UserStationModel[] models = userStationBusinessService.save(new UserStationModel[] { usm });
		return new ModelAndResult(models);
	}
	/**
	 * @author chao.peng 批量加入版本操作
	 * @return
	 */
	@RequestMapping(value = "user/batchadd/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult batchUser(@PathVariable(value = "pk_station") String pk_station, @RequestBody YJWYUserModel[] models) {
		List<UserStationModel> usms = new ArrayList<UserStationModel>();
		for (YJWYUserModel model : models) {
			UserStationModel usm = new UserStationModel();
			usm.setPk_station(pk_station);
			usm.setPk_user(model.getPk_user());
			usm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			usms.add(usm);
		}
		UserStationModel[] us = userStationBusinessService.save(usms.toArray(new UserStationModel[] {}));
		return new ModelAndResult(us);
	}
	/**
	 * @author zhenwei.shi 移除用户操作
	 * @return
	 */
	@RequestMapping(value = "user/remove/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteUser(@PathVariable(value = "pk_station") String pk_station, @RequestBody YJWYUserModel[] models) {
		List<UserStationModel> list = new ArrayList<UserStationModel>();
		UserStationModel user = null;
		for (YJWYUserModel model : models) {
			user = new UserStationModel();
			user.setPk_station(pk_station);
			user.setPk_user(model.getPk_user());
			list.add(user);
		}
		userStationBusinessService.delete(list.toArray(new UserStationModel[] {}));
		updateInspectTask(pk_station, models);
		return new ModelAndResult();
	}
	//当人员的岗位变动时，将该人员之前的岗位的核查任务关闭
	public void updateInspectTask(String pk_station, YJWYUserModel[] users){
		JdbcTemplate jdbc = dao.getWriteTemplate();
		for(YJWYUserModel user : users){
			String sql = "update yjwy_quality_inspecttask set task_end_time = '"+DateTimeUtil.getTimestampStr()+"',task_state='60',update_user='"+UserUtil.getCurrentUserPk()+"',update_time='"+System.currentTimeMillis()+"' where pk_crop='"+user.getPk_crop()+"' and task_state=10 and fk_taskuser = '"+user.getPk_user()+"' and fk_job = '"+pk_station+"' ";
			jdbc.execute(sql);
		}
	}
	/**
	 * zhenwei.shi 加入版本操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "edition/add/{pk_station}/{pk_edition}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult addESM(@PathVariable(value = "pk_station") String pk_station, @PathVariable(value = "pk_edition") String pk_edition) {
		EditionStationModel esm = new EditionStationModel();
		esm.setPk_station(pk_station);
		esm.setPk_edition(pk_edition);
		esm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		EditionStationModel[] models = stationEditionService.save(new EditionStationModel[] { esm });
		return new ModelAndResult(models);
	}
	/**
	 * @author chao.peng 批量加入版本操作
	 * @return
	 */
	@RequestMapping(value = "edition/batchadd/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult batchESM(@PathVariable(value = "pk_station") String pk_station, @RequestBody StandardEditionModel[] models) {
		List<EditionStationModel> esms = new ArrayList<EditionStationModel>();
		for (StandardEditionModel model : models) {
			EditionStationModel esm = new EditionStationModel();
			esm.setPk_station(pk_station);
			esm.setPk_edition(model.getPk_edition());
			esm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			esms.add(esm);
		}
		EditionStationModel[] ms = stationEditionService.save(esms.toArray(new EditionStationModel[] {}));
		return new ModelAndResult(ms);
	}
	/**
	 * @author zhenwei.shi 移除标准操作
	 * @return
	 */
	@RequestMapping(value = "standard/remove/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteSSM(@PathVariable(value = "pk_station") String pk_station, @RequestBody InspectStandardModel[] models) {
		List<StandardStationModel> list = new ArrayList<StandardStationModel>();
		for (InspectStandardModel model : models) {
			StandardStationModel ssm = new StandardStationModel();
			ssm.setPk_station(pk_station);
			ssm.setPk_standard(model.getPk_inspstan());
			ssm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			list.add(ssm);
		}
		stationStandardService.delete(list.toArray(new StandardStationModel[] {}));
		return new ModelAndResult();
	}

	/**
	 * zhenwei.shi 加入标准操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "standard/add/{pk_station}/{pk_standard}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult addSSM(@PathVariable(value = "pk_station") String pk_station, @PathVariable(value = "pk_standard") String pk_standard) {
		StandardStationModel ssm = new StandardStationModel();
		ssm.setPk_station(pk_station);
		ssm.setPk_standard(pk_standard);
		ssm.setCreate_time(DateTimeUtil.getTimestampStr());
		ssm.setUpdate_time(System.currentTimeMillis() + "");
		ssm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		StandardStationModel[] models = stationStandardService.save(new StandardStationModel[] { ssm });
		return new ModelAndResult(models);
	}

	/**
	 * @author zhenwei.shi 批量加入标准操作
	 * @return
	 */
	@RequestMapping(value = "standard/batchadd/{pk_station}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult batchSSM(@PathVariable(value = "pk_station") String pk_station, @RequestBody InspectStandardModel[] models) {
		List<StandardStationModel> ssms = new ArrayList<StandardStationModel>();
		for (InspectStandardModel model : models) {
			StandardStationModel ssm = new StandardStationModel();
			ssm.setPk_station(pk_station);
			ssm.setPk_standard(model.getPk_inspstan());
			ssm.setCreate_time(DateTimeUtil.getTimestampStr());
			ssm.setUpdate_time(System.currentTimeMillis() + "");
			ssm.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			ssms.add(ssm);
		}

		StandardStationModel[] ms = stationStandardService.save(ssms.toArray(new StandardStationModel[] {}));
		return new ModelAndResult(ms);
	}

	public boolean doValidateCode(String code, String id) {
		String pk_crop = UserUtil.getCurrentUser().getPk_crop();
		YJWYStationModel[] models = service.query(Query.from(YJWYStationModel.META_ID).and(Condition.eq("station_code_", code).and(Condition.eq("pk_crop_", pk_crop)).and(Condition.eq("delete_flag_", 0)).and(Condition.neq("pk_station_", id))));
		if (null != models && models.length > 0) {
			return false;
		} else {
			return true;
		}
	}
}
