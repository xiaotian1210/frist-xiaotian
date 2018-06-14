package com.shareworx.ezfm.device.basic.executor.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.basic.executor.dao.YJWYGroupDao;
import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService;
import com.shareworx.ezfm.device.basic.executor.service.YJWYGroupService;
import com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 巡检/维保人员分组操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/basic/executor")
public class YJWYGroupAction {

	final static Logger log = Logger.getLogger(YJWYGroupAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/basic/executor/executor_listcard";

	// 分组service
	@Autowired
	@Qualifier(YJWYGroupBusinessService.ID)
	private YJWYGroupBusinessService service;

	// 人员service
	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userService;

	// 中间表service
	@Autowired
	@Qualifier(YJWYGroupUserBusinessService.ID)
	private YJWYGroupUserBusinessService groupUserService;

	@Autowired
	@Qualifier(YJWYGroupService.ID)
	private YJWYGroupService groupService;

	public void setGroupService(YJWYGroupService groupService) {
		this.groupService = groupService;
	}

	@Autowired
	@Qualifier(YJWYPlanBusinessService.ID)
	private YJWYPlanBusinessService planBusinessService;

	public void setPlanBusinessService(YJWYPlanBusinessService planBusinessService) {
		this.planBusinessService = planBusinessService;
	}

	public void setGroupUserService(YJWYGroupUserBusinessService groupUserService) {
		this.groupUserService = groupUserService;
	}

	public void setService(YJWYGroupBusinessService service) {
		this.service = service;
	}

	public void setUserService(YJWYUserBusinessService userService) {
		this.userService = userService;
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
	public @ResponseBody ModelAndResult query(ParamEntity param) {
		Query query = Query.from(YJWYGroupModel.META_ID);
		query.where(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		Integer rows = param.getRows();
		Integer page = param.getPage();
		if (null != rows && null != page) {
			query.limit(rows);
			query.start((page - 1) * rows);
		}
		YJWYGroupModel[] models = service.query(query);
		YJWYGroupDao domainDao = SpringUtils.getBean(YJWYGroupDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 查询备选人员列表操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryUsers", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryUsers(ParamEntity param) {
		Query query = Query.from(YJWYUserModel.META_ID);
		query.where(new Condition("delete_flag_ ", QueryContents.TYPE_NEQ, "1"));
		query.and(new Condition("pk_crop_", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		String user_name = null;
		// 条件筛选搜索
		if (null != param) {
			user_name = param.getUser_name();
			if (!DeviceUtil.stringIsEmpty(user_name)) {
				query.and(new Condition("user_name_", QueryContents.TYPE_LIKE, user_name));
			}
		}
		YJWYUserModel[] models = userService.query(query);
		YJWYUserDao domainDao = SpringUtils.getBean(YJWYUserDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 查询已选人员列表操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "querySelectedUsers", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult querySelectedUsers(@RequestBody YJWYGroupModel model) {
		String pk_group = model.getPk_group();
		Query query = Query.from(YJWYGroupUserModel.META_ID);
		query = query.where(new Condition("pk_group", QueryContents.TYPE_EQ, pk_group));
		// 中间表model
		YJWYGroupUserModel[] models = groupUserService.query(query);
		if (models.length == 0) {
			ModelAndResult mar = new ModelAndResult();
			mar.setTotal(0);
			return mar;
		}
		// 存放user集合
		YJWYUserModel[] userModels = new YJWYUserModel[models.length];
		for (int i = 0; i < models.length; i++) {
			String pk_user = models[i].getPk_user();
			// userModel查询query
			Query userQuery = Query.from(YJWYUserModel.META_ID);
			// 增加查询条件
			userQuery = userQuery.where(new Condition("pk_user_", QueryContents.TYPE_EQ, pk_user));
			YJWYUserModel[] userTemp = userService.query(userQuery);
			if (DeviceUtil.arrayIsNotEmpty(userTemp)) {
				userModels[i] = userTemp[0];
			}
		}
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(userModels.length);
		mar.setAttribute("rows", userModels);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYGroupModel model) {
		DeviceUtil.setCreateInfo(model);
		YJWYGroupModel[] rs = null;
		ModelAndResult mar = new ModelAndResult();
		try {
			rs = groupService.saveModels(new YJWYGroupModel[] { model });
		} catch (Exception e) {
			e.printStackTrace();
			mar.setSuccess(false);
		}
		mar.setData(rs);
		return mar;
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYGroupModel model) {
		DeviceUtil.setUpdateInfo(model);
		YJWYGroupModel[] rs = null;
		ModelAndResult mar = new ModelAndResult();
		try {
			rs = groupService.updateModels(new YJWYGroupModel[] { model });
		} catch (Exception e) {
			e.printStackTrace();
			mar.setSuccess(false);
		}
		mar.setData(rs);
		return mar;
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYGroupModel[] models) {
		ModelAndResult mar = new ModelAndResult();
		String[] pk_groups = null;
		YJWYPlanModel[] planModels = null;
		// 获取分组id
		if (DeviceUtil.arrayIsNotEmpty(models)) {
			pk_groups = DeviceUtil.getPrimaryKeyValue(models);
		}

		// 判断是否被关联使用
		if (DeviceUtil.arrayIsNotEmpty(pk_groups)) {
			planModels = planBusinessService.query(Query.from(YJWYPlanModel.META_ID).where(new Condition("group_id", QueryContents.TYPE_IN, pk_groups)));
		}
		if (DeviceUtil.arrayIsNotEmpty(planModels)) {
			mar.setAttribute("usedFlag", 1);
			mar.setData(planModels);
			return mar;
		}
		// 没有被关联则进行删除
		YJWYGroupModel[] rs = null;
		try {
			rs = groupService.deleteModels(models);
		} catch (Exception e) {
			e.printStackTrace();
			mar.setSuccess(false);
		}
		mar.setData(rs);
		return mar;
	}
}
