package com.shareworx.ezfm.baseinfo.role.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.role.dao.YJWYRoleDao;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleBusinessService;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleUserBusinessService;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.pub.easyui.tree.vo.EasyUiPlanTreeVO;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 角色操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/role")
public class YJWYRoleAction {

	final static Logger log = Logger.getLogger(YJWYRoleAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/role";
	/** 跳转岗位关联用户页面 */
	public final static String SON_USER_PAGE_FORWARD = "baseinfo/role_add_user";

	@Autowired
	@Qualifier(YJWYRoleBusinessService.ID)
	private YJWYRoleBusinessService service;

	public void setService(YJWYRoleBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(YJWYRoleUserBusinessService.ID)
	private YJWYRoleUserBusinessService ruService;

	public void setRuService(YJWYRoleUserBusinessService ruService) {
		this.ruService = ruService;
	}

	@Autowired
	@Qualifier(YJWYRoleFuncBusinessService.ID)
	private YJWYRoleFuncBusinessService rfService;

	public void setRfService(YJWYRoleFuncBusinessService rfService) {
		this.rfService = rfService;
	}

	@Autowired
	@Qualifier(YJWYRoleFuncDomainService.ID)
	private YJWYRoleFuncDomainService rfDomainService;

	public void setRfDomainService(YJWYRoleFuncDomainService rfDomainService) {
		this.rfDomainService = rfDomainService;
	}

	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService baseInfoService;

	public void setBaseInfoService(IBaseInfoQueryService baseInfoService) {
		this.baseInfoService = baseInfoService;
	}

	@Autowired
	@Qualifier(YJWYAPPRoleFuncDomainService.ID)
	private YJWYAPPRoleFuncDomainService appRoleFuncDomainService;

	public void setAppRoleFuncDomainService(YJWYAPPRoleFuncDomainService appRoleFuncDomainService) {
		this.appRoleFuncDomainService = appRoleFuncDomainService;
	}

	@Autowired
	@Qualifier(YJWYRoleUserBusinessService.ID)
	private YJWYRoleUserBusinessService roleUserBusinessService;

	public void setRoleUserBusinessService(YJWYRoleUserBusinessService roleUserBusinessService) {
		this.roleUserBusinessService = roleUserBusinessService;
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
	 * @author zhenwei.shi 转向岗位关联用户页面
	 * @return
	 */
	@RequestMapping(value = "son/user/index", method = RequestMethod.GET)
	public ModelAndView sonUserForward() {
		ModelAndView mav = new ModelAndView(SON_USER_PAGE_FORWARD);
		return mav;
	}

	/**
	 * zhenwei.shi 加入用户操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "user/add/{pk_role}/{pk_user}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult addUser(@PathVariable(value = "pk_role") String pk_role, @PathVariable(value = "pk_user") String pk_user) {
		YJWYRoleUserModel rum = new YJWYRoleUserModel();
		rum.setPk_role(pk_role);
		rum.setPk_user(pk_user);
		rum.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		rum.setCreate_time(DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS));
		rum.setCreate_user(UserUtil.getCurrentUser().getPk_user());
		YJWYRoleUserModel[] models = roleUserBusinessService.save(new YJWYRoleUserModel[] { rum });
		return new ModelAndResult(models);
	}

	/**
	 * @author zhenwei.shi 移除用户操作
	 * @return
	 */
	@RequestMapping(value = "user/remove/{pk_role}", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteUser(@PathVariable(value = "pk_role") String pk_role, @RequestBody YJWYUserModel[] models) {
		List<YJWYRoleUserModel> list = new ArrayList<YJWYRoleUserModel>();
		
		for (YJWYUserModel model : models) {
			YJWYRoleUserModel user = new YJWYRoleUserModel();
			user.setPk_role(pk_role);
			user.setPk_user(model.getPk_user());
			user.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			list.add(user);
		}
		roleUserBusinessService.delete(list.toArray(new YJWYRoleUserModel[] {}));
		return new ModelAndResult();
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYRoleModel[] models = service.query(query);
		YJWYRoleDao domainDao = SpringUtils.getBean(YJWYRoleDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYRoleModel model) {
		// YJWYBillModelUtils.covert(model);
		JdbcTemplate read=dao.getReadTemplate();
		if (!doValidateCode(model.getRole_code(), model.getPk_role(),model.getPk_crop())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		String sql="select count(pk_role_)from yjwy_pub_role where role_name_='"
				+ model.getRole_name()
				+ "' and pk_crop_='"
				+model.getPk_crop()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count > 0){
			return new ModelAndResult(false,"公司已存在该角色名称，请更换角色名称");
		}
		model.setCreate_time(DateTimeUtil.getTimestampStr());
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		YJWYRoleModel[] rs = service.save(new YJWYRoleModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYRoleModel model) {
		// YJWYBillModelUtils.covert(model);
		JdbcTemplate read = dao.getReadTemplate();
		if (!doValidateCode(model.getRole_code(), model.getPk_role(),model.getPk_crop())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		String sql="select count(pk_role_)from yjwy_pub_role where role_name_='"
				+ model.getRole_name()
				+ "' and pk_crop_='"
				+model.getPk_crop()
				+ "'";
		int count=read.queryForObject(sql, Integer.class);
		if(count==1){
			 sql="select count(pk_role_)from yjwy_pub_role where role_name_='"
					+ model.getRole_name()
					+ "' and pk_crop_='"
					+model.getPk_crop()
					+ "' and pk_role_='"
					+ model.getPk_role()
					+ "'";
			 count=read.queryForObject(sql, Integer.class);
			 if(count!=1){
				 return new ModelAndResult(false,"公司已存在该角色名称，请更换角色名称");
			 }
		}
		
		model.setLast_modify_time(DateTimeUtil.getTimestampStr());
		model.setLast_modify_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(System.currentTimeMillis() + "");
		YJWYRoleModel[] rs = service.update(new YJWYRoleModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYRoleModel[] models) {
		String inSql = "";
		String inCrop = "";
		for (YJWYRoleModel model : models) {
			if (model.getIs_pre()) {
				throw new ShareworxAuthencatinException("系统预制角色，不可删除");
			}
			inSql = inSql + "," + model.getPk_role();
			inCrop = inCrop + "," + model.getPk_crop();
		}
		String inField[] = inSql.substring(1).split(",");
		String inCropField[] = inCrop.substring(1).split(",");
		YJWYRoleUserModel[] rus = ruService.query(Query.from(YJWYRoleUserModel.META_ID).and(Condition.in("pk_role_", inField).and(Condition.in("pk_crop_", inCropField))));
		if (null != rus && rus.length > 0) {
			ruService.delete(rus);
		}
		YJWYRoleFuncModel[] rfs = rfService.query(Query.from(YJWYRoleFuncModel.META_ID).and(Condition.in("pk_role_", inField).and(Condition.in("pk_crop_", inCropField))));
		if (null != rfs && rfs.length > 0) {
			rfService.delete(rfs);
		}
		/*
		 * YJWYRoleMenuModel[] btns =
		 * roleBtnService.query(Query.from(YJWYRoleMenuModel.META_ID).and(
		 * Condition.in("pk_role_", inField))); if(null!=btns && btns.length>0){
		 * roleBtnService.delete(btns); }
		 */
		YJWYRoleModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 验证编码是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateCode(String code, String id,String crop) {
		if (!StringUtils.isEmpty(id)) {
			YJWYRoleModel model = service.query(Query.from(YJWYRoleModel.META_ID).and(Condition.create("pk_role_", id)))[0];
			if (code.equals(model.getRole_code())) {
				return true;
			}
		}

		YJWYRoleModel[] models = service.query(Query.from(YJWYRoleModel.META_ID).and(Condition.create("role_code_", code)));
		if (null != models && models.length > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 权限分配操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "authorization", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult systemAuthorization(@RequestBody YJWYRoleFuncModel[] models) {
		Condition[] cons = new Condition[] { Condition.create("pk_crop_", models[0].getPk_crop()), Condition.create("pk_role_", models[0].getPk_role()) };
		Delete delete = Delete.delete(YJWYRoleFuncModel.META_ID).and(cons);
		if (StringUtils.isEmpty(models[0].getPk_func())) {
			int count = rfDomainService.deleteByConditions(delete);
			return new ModelAndResult(count);
		}
		String inSql = "";
		List<YJWYRoleFuncModel> list = new ArrayList<>();
		for (YJWYRoleFuncModel model : models) {
			inSql = inSql + "," + model.getPk_func();
			list.add(model);
		}
		String[] inFiled = inSql.substring(1).split(",");
		Delete deleteNoExist = Delete.delete(YJWYRoleFuncModel.META_ID).and(cons).and(Condition.notin("pk_func_", inFiled));
		rfDomainService.deleteByConditions(deleteNoExist);
		YJWYRoleFuncModel[] rfms = rfService.query(Query.from(YJWYRoleFuncModel.META_ID).and(cons));
		Iterator<YJWYRoleFuncModel> it = list.iterator();
		while (it.hasNext()) {
			YJWYRoleFuncModel m = it.next();
			for (YJWYRoleFuncModel rfm : rfms) {
				if (m.getPk_func().equals(rfm.getPk_func())) {
					it.remove();
					break;
				}
			}
			m.setCreate_time(DateTimeUtil.getTimestampStr());
			m.setCreate_user(UserUtil.getCurrentUserPk());
		}
		if (list.size() > 0) {
			rfDomainService.save(list.toArray(new YJWYRoleFuncModel[] {}));
		}
		return new ModelAndResult(models);
	}

	/**
	 * APP终端权限查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "app/function/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult appFunctionQuery(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		List<YJWYAPPRoleFuncModel> selectedFuns = appRoleFuncDomainService.queryListByCondition(query);
		Map<String, String> map = new HashMap<>();
		for (YJWYAPPRoleFuncModel selectedFun : selectedFuns) {
			map.put(selectedFun.getPk_func(), selectedFun.getPk_func());
		}
		YJWYDictModel[] appFuncs = baseInfoService.queryDictionaryForArray("AppPermission", 1);
		List<EasyUiPlanTreeVO> list = new ArrayList<>();
		for (YJWYDictModel dict : appFuncs) {
			EasyUiPlanTreeVO vo = new EasyUiPlanTreeVO();
			vo.setId(dict.getPk_dict());
			vo.setText(dict.getDict_name());
			vo.setPid("root");
			if (!StringUtils.isEmpty(map.get(vo.getId()))) {
				vo.setChecked(true);
			} else {
				vo.setChecked(false);
			}
			vo.setState("open");
			vo.setAttributes(dict);
			list.add(vo);
		}
		return new ModelAndResult(list);
	}

	/**
	 * APP终端权限授权
	 * 
	 * @return
	 */
	@RequestMapping(value = "app/authorization", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult apptemAuthorization(@RequestBody YJWYAPPRoleFuncModel[] models) {
		Condition[] cons = new Condition[] { Condition.create("pk_crop_", models[0].getPk_crop()), Condition.create("pk_role_", models[0].getPk_role()) };
		Delete delete = Delete.delete(YJWYAPPRoleFuncModel.META_ID).and(cons);
		if (StringUtils.isEmpty(models[0].getPk_func())) {
			int count = appRoleFuncDomainService.deleteByConditions(delete);
			return new ModelAndResult(count);
		}
		appRoleFuncDomainService.deleteByConditions(delete);
		for (YJWYAPPRoleFuncModel model : models) {
			model.setCreate_time(DateTimeUtil.getTimestampStr());
			model.setCreate_user(UserUtil.getCurrentUserPk());
		}
		List<YJWYAPPRoleFuncModel> list = appRoleFuncDomainService.save(models);
		return new ModelAndResult(list);
	}
}
