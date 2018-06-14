package com.shareworx.ezfm.baseinfo.userstation.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.userstation.dao.UserStationDao;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService;
import com.shareworx.ezfm.baseinfo.userstation.vo.UserStationQueryVo;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.meta.itf.YJWYBillModelUtils;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 人员岗位关系操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/userstation")
public class UserStationAction {

	final static Logger log = Logger.getLogger(UserStationAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/userstation";

	@Autowired
	@Qualifier(UserStationBusinessService.ID)
	private UserStationBusinessService service;

	public void setService(UserStationBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService stationService;

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward(@RequestParam(value = "func", required = false) String func) {

		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 通过StationId查询其关联的用户 通过StationId查询其没有关联的用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "station/user/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryUserByStationId(@RequestBody UserStationQueryVo query) {
		JdbcTemplate read = dao.getReadTemplate();
		String notInSql = " select * from yjwy_pub_user u where delete_flag_='0' and pk_user_ not in(select pk_user_ from yjwy_pub_user_station u_s where u_s.pk_station_ = '" + query.getPk_station() + "') ";
		String inSql = " select * from yjwy_pub_user u where delete_flag_='0' and pk_user_ in(select pk_user_ from yjwy_pub_user_station u_s where u_s.pk_station_ = '" + query.getPk_station() + "') ";
		String sql;
		if ("in".equalsIgnoreCase(query.getFlag())) {
			sql = inSql;
		} else {
			sql = notInSql;
		}
		if (!StringUtils.isEmpty(query.getUser_name())) {
			sql = sql + " and user_name_ like '%" + query.getUser_name() + "%' ";
		}
		sql = sql + " and pk_crop_ ='" + UserUtil.getCurrentUser().getPk_crop() + "' ";
		String countSql = "select count(*) from (" + sql + ")c";
		int count = read.queryForObject(countSql, Integer.class);
		sql = sql + " order by u.create_time_ desc limit " + query.getStart() + "," + query.getLimit();
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>() {
			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel user = new YJWYUserModel();
				user.setPk_user(rs.getString("pk_user_"));
				user.setPk_crop(rs.getString("pk_crop_"));
				user.setEm_code(rs.getString("em_code_"));
				user.setUser_name(rs.getString("user_name_"));
				user.setUser_code(rs.getString("user_code_"));
				user.setPassword(rs.getString("password_"));
				user.setEmail(rs.getString("email_"));
				user.setPhone(rs.getString("phone_"));
				user.setIs_pre(rs.getBoolean("is_pre_"));
				user.setIs_able(rs.getBoolean("is_able_"));
				user.setHeader_img(rs.getString("header_img_"));
				user.setCreate_user(rs.getString("create_user_"));
				user.setCreate_time(rs.getString("create_time_"));
				user.setLast_modify_user(rs.getString("last_modify_user_"));
				user.setLast_modify_time(rs.getString("last_modify_time_"));
				user.setUpdate_time(rs.getString("update_time_"));
				user.setDelete_flag(rs.getString("delete_flag_"));
				return user;
			}
		});
		ModelAndResult mar = new ModelAndResult(list.toArray(new YJWYUserModel[] {}));
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		UserStationModel[] models = service.query(query);
		UserStationDao domainDao = SpringUtils.getBean(UserStationDao.ID);
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
	@RequestMapping(value = "rel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult UserRelation(@RequestParam(value = "param", required = false) String param) {
		JSONObject p = JSON.parseObject(param);
		// 获取岗位
		Query query2 = JSON.parseObject(param, Query.class);
		UserStationModel[] models = service.query(query2);
		Set<String> relSt = new HashSet<>();
		for (UserStationModel sl : models) {
			relSt.add(sl.getPk_station());
		}
		// 获取岗位关系

		Query query = Query.from(YJWYStationModel.META_ID).and(Condition.create("pk_crop_").eq(p.get("pk_crop")));
		;
		YJWYStationModel[] stat = stationService.query(query);
		List<ZtreeModel> tree = new ArrayList<>();
		for (YJWYStationModel m : stat) {
			ZtreeModel model = new ZtreeModel();
			if (relSt.contains(m.getPk_station())) {
				model.setChecked(true);
			}
			model.setId(m.getPk_station());
			model.setpId(m.getPk_parent());
			model.setName(m.getStation_name());
			model.setAttributes(m);
			tree.add(model);
		}
		ModelAndResult mar = new ModelAndResult(tree);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody UserStationModel[] models) {
		//String inSql = models[0].getPk_user();
		/*for (UserStationModel model : models) {
			inSql = inSql + "," + model.getPk_user();
		}*/
		
		//String[] inFiled = inSql.substring(1).split(",");
		UserStationModel[] quondam = service.query(Query.from(UserStationModel.META_ID).and(Condition.eq("pk_user_", models[0].getPk_user())));
		if (null != quondam && quondam.length > 0) {
			service.delete(quondam);
		}
		if (StringUtils.isEmpty(models[0].getPk_station())) {
			return new ModelAndResult();
		}
		
		for (UserStationModel model : models) {
			// YJWYBillModelUtils.covert(m);
			model.setCreate_time(DateTimeUtil.getTimestampStr());
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
		}
		List<UserStationModel> unUs = new ArrayList<>();
		for(UserStationModel us : quondam){
			int i = 0;
			for (UserStationModel model : models) {
				++i;
				if(us.getPk_station().equals(model.getPk_station())){
					break;
				}
				if(models.length==i){
					unUs.add(us);
				}
			}
		}
		updateInspectTask(unUs);
		UserStationModel[] rs = service.save(models);
		return new ModelAndResult(rs);
	}
	
	//当人员的岗位变动时，将该人员之前的岗位的核查任务关闭
	public void updateInspectTask(List<UserStationModel> unUs){
		JdbcTemplate jdbc = dao.getWriteTemplate();
		for(UserStationModel station : unUs){
			String sql = "update yjwy_quality_inspecttask set task_end_time = '"+DateTimeUtil.getTimestampStr()+"',task_state='60',update_user='"+UserUtil.getCurrentUserPk()+"',update_time='"+System.currentTimeMillis()+"' where pk_crop='"+station.getPk_crop()+"' and task_state=10 and fk_taskuser = '"+station.getPk_user()+"' and fk_job = '"+station.getPk_station()+"' ";
			jdbc.execute(sql);
		}
	}
	
	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody UserStationModel model) {
		YJWYBillModelUtils.covert(model);
		UserStationModel[] rs = service.update(new UserStationModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody UserStationModel[] models) {
		UserStationModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

}
