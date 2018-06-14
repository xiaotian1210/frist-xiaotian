package com.shareworx.ezfm.baseinfo.role.action;

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
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.role.dao.YJWYRoleUserDao;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleBusinessService;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleUserBusinessService;
import com.shareworx.ezfm.baseinfo.role.vo.UserRoleQueryVo;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.meta.itf.YJWYBillModelUtils;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 角色人员关系操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/role/user")
public class YJWYRoleUserAction {

	final static Logger log = Logger.getLogger(YJWYRoleUserAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/role";

	@Autowired
	@Qualifier(YJWYRoleUserBusinessService.ID)
	private YJWYRoleUserBusinessService service;
	@Autowired
	@Qualifier(YJWYRoleBusinessService.ID)
	private YJWYRoleBusinessService roleService;

	public void setService(YJWYRoleUserBusinessService service) {
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
		YJWYRoleUserModel[] models = service.query(query);
		YJWYRoleUserDao domainDao = SpringUtils.getBean(YJWYRoleUserDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYRoleUserModel[] model) {
		for (YJWYRoleUserModel m : model) {
			m.setCreate_time(DateTimeUtil.getTimestampStr());
			m.setCreate_user(UserUtil.getCurrentUserPk());
		}
		YJWYRoleUserModel[] rs = service.save(model);
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYRoleUserModel model) {
		YJWYBillModelUtils.covert(model);
		YJWYRoleUserModel[] rs = service.update(new YJWYRoleUserModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYRoleUserModel[] models) {
		YJWYRoleUserModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	@RequestMapping(value = "show", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult show(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYRoleUserModel[] models = service.query(query);
		if (!ArrayUtils.isEmpty(models)) {
			List<String> rl = new ArrayList<>();
			for (YJWYRoleUserModel m : models) {
				rl.add(m.getPk_role());
			}
			// List<Condition> andList = new ArrayList<>();
			// Query query2 = new Query();
			// query2.getMetas().add(RoleModel.META_ID);
			// andList.add(new Condition("pk_role_",QueryContents.IN
			// ,rl.toArray(new String[0])));
			// query2.setAndList(andList);
			// RoleModel[] l = roleService.query(query2);
			// ModelAndResult mar = new ModelAndResult(l);
			// return mar;

			Query query2 = Query.from(YJWYRoleModel.META_ID).and(Condition.create("pk_role_").in(rl.toArray(new String[0])));
			YJWYRoleModel[] l = roleService.query(query2);
			ModelAndResult mar = new ModelAndResult(l);
			return mar;
		}
		return new ModelAndResult(new YJWYRoleModel[0]);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "filter", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult filter(@RequestParam(value = "param", required = false) String param) {
		JSONObject p = JSON.parseObject(param);
		Query query = JSON.parseObject(param, Query.class);
		YJWYRoleUserModel[] models = service.query(query);
		Set<String> roleSet = new HashSet<>();
		for (YJWYRoleUserModel m : models) {
			roleSet.add(m.getPk_role());
		}
		Query query2 = Query.from(YJWYRoleModel.META_ID).and(Condition.create("pk_crop_").eq(p.get("pk_crop")));
		YJWYRoleModel[] rl = roleService.query(query2);
		List<YJWYRoleModel> rs = new ArrayList<>();
		for (YJWYRoleModel r : rl) {
			String pk_role = r.getPk_role();
			if (roleSet.contains(pk_role)) {
				continue;
			}
			rs.add(r);
		}
		ModelAndResult mar = new ModelAndResult(rs);
		return mar;
	}

	/**
	 * 通过StationId查询其关联的用户 通过StationId查询其没有关联的用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "roleuser/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryUserByRoleId(@RequestBody UserRoleQueryVo query) {
		JdbcTemplate read = dao.getReadTemplate();
		String notInSql = " select * from yjwy_pub_user u where delete_flag_='0' and pk_user_ not in(select pk_user_ from yjwy_pub_role_user u_r where u_r.pk_role_ = '" + query.getPk_role() + "') ";
		String inSql = " select * from yjwy_pub_user u where delete_flag_='0' and pk_user_ in(select pk_user_ from yjwy_pub_role_user u_r where u_r.pk_role_ = '" + query.getPk_role() + "') ";
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
}
