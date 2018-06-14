package com.shareworx.ezfm.baseinfo.user.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dms365.ezfm.limitcheck.annotation.LimitCheck;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleUserBusinessService;
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDeleteService;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.limitcheck.type.UserType;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.service.IYJWYLoginAuthorizationService;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.ShortMessageUtil;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.MD5Utils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 系统用户操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/user")
public class YJWYUserAction {

	final static Logger log = Logger.getLogger(YJWYUserAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/user";
	public final static String SELFUPDATEPAGE_FORWARD = "system/userinfo_update";

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService service;

	public void setService(YJWYUserBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYUserDeleteService.ID)
	private YJWYUserDeleteService deleteService;

	public void setDeleteService(YJWYUserDeleteService deleteService) {
		this.deleteService = deleteService;
	}

	@Autowired
	@Qualifier(YJWYRoleUserBusinessService.ID)
	private YJWYRoleUserBusinessService rowUserService;

	public void setRowUserService(YJWYRoleUserBusinessService rowUserService) {
		this.rowUserService = rowUserService;
	}

	@Autowired
	@Qualifier(UserStationBusinessService.ID)
	private UserStationBusinessService userStationService;

	public void setUserStationService(UserStationBusinessService userStationService) {
		this.userStationService = userStationService;
	}

	@Autowired
	@Qualifier(IYJWYLoginAuthorizationService.ID)
	private IYJWYLoginAuthorizationService loginAuthorizationService;

	public void setLoginAuthorizationService(IYJWYLoginAuthorizationService loginAuthorizationService) {
		this.loginAuthorizationService = loginAuthorizationService;
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
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "self/update/index", method = RequestMethod.GET)
	public ModelAndView selfUpdateForward() {
		return new ModelAndView(SELFUPDATEPAGE_FORWARD);
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
		YJWYUserModel[] models = service.query(query);
		YJWYUserDao domainDao = SpringUtils.getBean(YJWYUserDao.ID);
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
	@LimitCheck(type=UserType.class)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYUserModel model) {
		// YJWYBillModelUtils.covert(model);
		if (!doValidateEmCode(model.getEm_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工工号已存在，请更换工号");
		}
		if (!doValidateUserCode(model.getUser_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工账号已存在，请更换账号");
		}
		if (LoginCommons.QYUSERCODE.equals(model.getUser_code())) {
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		} else if (LoginCommons.DMSUSERCODE.equals(model.getUser_code())) {
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		model.setDelete_flag("0");
		model.setPassword(MD5Utils.getMD5String("111111"));// 新增用户设置初始化密码
		model.setIs_sign("0");
		// model.setPassword(MD5Utils.getMD5String(model.getPassword()));
		YJWYUserModel[] rs = service.save(new YJWYUserModel[] { model });
		
		//发送短信
		ShortMessageUtil.SendAccount(model.getPhone(), model.getUser_code(), "111111");
		
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(HttpServletRequest req, HttpServletResponse resp, @RequestBody YJWYUserModel model) {
		// YJWYBillModelUtils.covert(model);
		if (!doValidateEmCode(model.getEm_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工工号已存在，请更换工号");
		}
		if (!doValidateUserCode(model.getUser_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工账号已存在，请更换账号");
		}
		if (LoginCommons.QYUSERCODE.equals(model.getUser_code())) {
			throw new ShareworxAuthencatinException("员工账号已存在，请更换账号");
		} else if (LoginCommons.DMSUSERCODE.equals(model.getUser_code())) {
			throw new ShareworxAuthencatinException("员工账号已存在，请更换账号");
		}
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		YJWYUserModel[] rs = service.update(new YJWYUserModel[] { model });
		// 判断当前修改的用户是否为当前登录用户
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		if (currUser.getPk_user().equals(rs[0].getPk_user())) {
			// 更新session
			return loginAuthorizationService.doLogin(rs[0], req, resp, false);
		}
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "self/update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult selfUpdate(@RequestBody YJWYUserModel model) {
		// YJWYBillModelUtils.covert(model);
		if (!doValidateEmCode(model.getEm_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工工号已存在，请更换工号");
		}
		if (!doValidateUserCode(model.getUser_code(), model.getPk_user())) {
			return new ModelAndResult(false, "员工账号已存在，请更换账号");
		}
		if (LoginCommons.QYUSERCODE.equals(model.getUser_code())) {
			return new ModelAndResult(false, "员工账号已存在，请更换账号");
			// throw new ShareworxAuthencatinException("编码重复，请换个编码");
		} else if (LoginCommons.DMSUSERCODE.equals(model.getUser_code())) {
			return new ModelAndResult(false, "员工账号已存在，请更换账号");
			// throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		YJWYUserModel[] rs = service.update(new YJWYUserModel[] { model });
		return new ModelAndResult(rs);
	}


	/**
	 * 重置密码保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult reset(@RequestBody YJWYUserModel[] models) {
		// YJWYBillModelUtils.covert(model);
		for (YJWYUserModel model : models) {
			model.setPassword(MD5Utils.getMD5String("111111"));// 新增用户设置初始化密码
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
		}
		YJWYUserModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	
	
	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYUserModel[] models) {
		String inSql = "";
		for (YJWYUserModel model : models) {
			if (model.getIs_able()) {
				return new ModelAndResult(false, "不可以删除启用中的用户");
			}
			if (model.getIs_pre()) {
				return new ModelAndResult(false, "不可以删除系统预制管理员或用户");
			}
			inSql = inSql + "," + model.getPk_user();
		}
		String[] inField = inSql.substring(1).split(",");
		YJWYRoleUserModel[] rowUsers = rowUserService.query(Query.from(YJWYRoleUserModel.META_ID).and(Condition.in("pk_user_", inField)));
		if (null != rowUsers && rowUsers.length > 0) {
			rowUserService.delete(rowUsers);
		}
		UserStationModel[] uss = userStationService.query(Query.from(UserStationModel.META_ID).and(Condition.in("pk_user_", inField)));
		if (null != uss && uss.length > 0) {
			userStationService.delete(uss);
		}

		ModelAndResult mar = deleteService.beforeDete(models);
		if (!mar.isSuccess()) {
			return mar;
		}

		models = service.query(Query.from(YJWYUserModel.META_ID).and(Condition.in("pk_user_", inField)));
		for (YJWYUserModel model : models) {
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setDelete_flag("1");
			model.setIs_able(false);
		}
		YJWYUserModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 启用操作
	 * 
	 * @param models
	 * @return
	 */
	@LimitCheck(type=UserType.class)
	@RequestMapping(value = "enable", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult enable(@RequestBody YJWYUserModel[] models) {
		for (YJWYUserModel model : models) {
			// YJWYBillModelUtils.covert(model);
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setIs_able(true);
		}
		// YJWYUserModel[] rs = service.execEnable(models);
		YJWYUserModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 停用操作
	 * 
	 * @param models
	 * @return
	 */
	@RequestMapping(value = "disable", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult disable(@RequestBody YJWYUserModel[] models) {
		for (YJWYUserModel model : models) {
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setIs_able(false);
		}
		// YJWYUserModel[] rs = service.execDisable(models);
		YJWYUserModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 判断工号是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateEmCode(String code, String id) {
		if (!StringUtils.isEmpty(id)) {
			YJWYUserModel model = service.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("pk_user_", id)).and(Condition.eq("delete_flag_", "0")))[0];
			if (code.equals(model.getEm_code())) {
				return true;
			}
		}

		YJWYUserModel[] models = service.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("em_code_", code)).and(Condition.eq("delete_flag_", "0")));
		if (null != models && models.length > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 判断用户账号是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateUserCode(String code, String id) {
		if (!StringUtils.isEmpty(id)) {
			YJWYUserModel model = service.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("pk_user_", id)).and(Condition.eq("delete_flag_", "0")))[0];
			if (code.equals(model.getUser_code())) {
				return true;
			}
		}

		YJWYUserModel[] models = service.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("user_code_", code)).and(Condition.eq("delete_flag_", "0")));
		if (null != models && models.length > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 导出
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "key", required = false) String key) {
		Query query = Query.from(YJWYUserModel.META_ID);
		query.where(Condition.neq("delete_flag_", "1"));
		query.and(Condition.eq("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		if (!StringUtils.isEmpty(value)) {
			query.and(Condition.like(key, value));
		}
		YJWYUserModel[] models = service.query(query);
		JSONArray jsonArr = (JSONArray) JSONArray.toJSON(models);
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			if (jsonObj.getBooleanValue("is_able")) {
				jsonObj.put("is_able", "启用");
			} else {
				jsonObj.put("is_able", "停用");
			}
		}
		// return ();
		return ImpAndExpExcel.doExpExcel(jsonArr, new String[] { "user_name", "user_code", "em_code", "phone", "email", "is_able" }, "templates/templates/baseinfo/用户信息.xls", 2);
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/baseinfo/用户信息导入模版.xls");
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(HttpServletRequest request, @RequestParam("excleFile") MultipartFile file) {
		String[] files = new String[] { "user_name", "user_code", "em_code", "phone", "email" };
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, "导入失败");
		}
		List<YJWYUserModel> list = JSONArray.parseArray(jsonArray.toJSONString(), YJWYUserModel.class);
		YJWYUserModel model = null;
		int len = 0;
		for (int i = 0; i < list.size(); i++) {
			model = list.get(i);
			// 防止空白行
			if (model == null) {
				continue;
			}
			if (StringUtils.isEmpty(model.getEm_code()) && StringUtils.isEmpty(model.getUser_code()) && StringUtils.isEmpty(model.getUser_name())) {
				continue;
			}
			len++;
		}
		YJWYUserModel[] models = new YJWYUserModel[len];

		// 用于判断导入表内是否数据重复
		Set<String> em_codes = new HashSet<>();
		Set<String> user_codes = new HashSet<>();
		for (int i = 0; i < list.size(); i++) {
			model = list.get(i);
			// 防止空白行
			if (model == null) {
				continue;
			}
			if (StringUtils.isEmpty(model.getEm_code()) && StringUtils.isEmpty(model.getUser_code()) && StringUtils.isEmpty(model.getUser_name())) {
				continue;
			}
			if (StringUtils.isEmpty(model.getEm_code())) {
				return new ModelAndResult(false, "员工工号不能为空，请检查");
			}
			if (StringUtils.isEmpty(model.getUser_code())) {
				return new ModelAndResult(false, "员工账号不能为空，请检查");
			}
			em_codes.add(model.getEm_code());
			user_codes.add(model.getUser_code());
			model.setIs_able(true);
			if (!StringUtils.isEmpty(model.getPhone())) {
				if (!DeviceUtil.isMobile(model.getPhone())) {
					return new ModelAndResult(false, "员工电话格式不正确，请检查");
				}
			}
			if (!StringUtils.isEmpty(model.getEmail())) {
				if (!DeviceUtil.isEmail(model.getEmail())) {
					return new ModelAndResult(false, "员工邮箱格式不正确，请检查");
				}
			}
			if (!doValidateEmCode(model.getEm_code(), model.getPk_user())) {
				return new ModelAndResult(false, "员工工号已存在，请更换工号");
			}
			if (!doValidateUserCode(model.getUser_code(), model.getPk_user())) {
				return new ModelAndResult(false, "员工账号已存在，请更换账号");
			}
			if (LoginCommons.QYUSERCODE.equals(model.getUser_code())) {
				throw new ShareworxAuthencatinException("编码重复，请换个编码");
			} else if (LoginCommons.DMSUSERCODE.equals(model.getUser_code())) {
				throw new ShareworxAuthencatinException("编码重复，请换个编码");
			}
			model.setCreate_time(DateTimeUtil.getTimestampStr());
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setDelete_flag("0");
			model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			model.setPassword(MD5Utils.getMD5String("111111"));// 新增用户设置初始化密码
			model.setIs_sign("0");
			models[i] = model;
		}
		if (em_codes.size() != len) {
			return new ModelAndResult(false, "表内员工工号重复，请检查");
		}
		if (user_codes.size() != len) {
			return new ModelAndResult(false, "表内员工账号重复，请检查");
		}
		service.save(models);
		return new ModelAndResult(true, "数据导入成功");
	}
}
