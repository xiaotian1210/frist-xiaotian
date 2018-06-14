package com.shareworx.ezfm.device.patrol.task.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保任务表操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/patrol/task")
public class YJWYTaskAction {

	final static Logger log = Logger.getLogger(YJWYTaskAction.class);

	/** 跳转页面 */
	public final static String CHECK = "ezfm/device/patrol/task/check_task_listcard";
	public final static String MAINT = "ezfm/device/patrol/task/maint_task_listcard";

	@Autowired
	@Qualifier(YJWYTaskBusinessService.ID)
	private YJWYTaskBusinessService taskBusinessService;

	public void setTaskBusinessService(YJWYTaskBusinessService taskBusinessService) {
		this.taskBusinessService = taskBusinessService;
	}

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userService;

	public void setUserService(YJWYUserBusinessService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier(YJWYTaskService.ID)
	private YJWYTaskService taskService;

	public void setTaskService(YJWYTaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(YJWYTaskEqBusinessService.ID)
	private YJWYTaskEqBusinessService taskEqBusinessService;

	public void setTaskEqBusinessService(YJWYTaskEqBusinessService taskEqBusinessService) {
		this.taskEqBusinessService = taskEqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService roomBusinessService;

	public void setRoomBusinessService(YJWYRoomBusinessService roomBusinessService) {
		this.roomBusinessService = roomBusinessService;
	}

	@Autowired
	@Qualifier(YJWYEqBusinessService.ID)
	private YJWYEqBusinessService eqBusinessService;

	public void setEqBusinessService(YJWYEqBusinessService eqBusinessService) {
		this.eqBusinessService = eqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 转向巡检任务列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkindex", method = RequestMethod.GET)
	public ModelAndView checkListForward() {
		return new ModelAndView(CHECK);
	}

	/**
	 * 转向维保任务列表页面
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
		// 获取当前用户
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		String pk_user = currUser.getPk_user();
		// 获取所属角色models
		YJWYRoleModel[] roleModels = deviceService.getRoleModelsByPkUser(pk_user);
		// 0代表无结束任务权限1代表有权限
		int finishFlag = 0;
		if (DeviceUtil.arrayIsNotEmpty(roleModels)) {
			for (YJWYRoleModel yjwyRoleModel : roleModels) {
				if ("admin".equals(yjwyRoleModel.getRole_code())) {
					finishFlag = 1;
				}
			}
		}
		
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
		
		List<Map<String, Object>> list = taskService.queryTaskMap(params);
		for (Map<String, Object> map : list) {
			map.put("finishFlag", finishFlag);
		}
		long count = taskService.queryTaskCount(params);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", list);
		mar.setAttribute("finishFlag", finishFlag);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYTaskModel model) {
		DeviceUtil.setCreateInfo(model);
		YJWYTaskModel[] rs = taskBusinessService.save(new YJWYTaskModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYTaskModel model) {
		DeviceUtil.setUpdateInfo(model);
		YJWYTaskModel[] rs = taskBusinessService.update(new YJWYTaskModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYTaskModel[] models) {
		YJWYTaskModel[] rs = taskBusinessService.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 生成任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "createTask", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult createTask() {
		taskService.getTaskModels();
		return new ModelAndResult();
	}

	/**
	 * 任务过期
	 * 
	 * @return
	 */
	@RequestMapping(value = "judgeExpired", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult judgeExpired() {
		taskService.judgeExpired();
		return new ModelAndResult();
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "finish", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult finish(@RequestBody YJWYTaskModel model) {
		model.setTask_state(5);
		DeviceUtil.setUpdateInfo(model);
		YJWYTaskModel[] rs = taskBusinessService.update(new YJWYTaskModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 查询机房、设备列表操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryRmEqList", method = RequestMethod.POST)
	public @ResponseBody String queryRmEqList(ParamEntity params) {
		Query taskEqQuery = Query.from(YJWYTaskEqModel.META_ID);
		taskEqQuery.where(new Condition("task_id", QueryContents.TYPE_EQ, params.getTask_id()));
		// 查询任务设备关联关系models
		YJWYTaskEqModel[] taskEqModels = taskEqBusinessService.query(taskEqQuery);
		YJWYRoomModel[] roomModels = null;
		if (null != taskEqModels && taskEqModels.length > 0) {
			// 机房id集合
			Set<String> roomIdSet = new HashSet<>();
			YJWYEqModel[] eqModels = null;
			for (YJWYTaskEqModel yjwyTaskEqModel : taskEqModels) {
				yjwyTaskEqModel.setAttribute("id", yjwyTaskEqModel.getEq_id());
				// 查询设备model
				Query eqQuery = Query.from(YJWYEqModel.META_ID);
				eqQuery.where(new Condition("eq_id", QueryContents.TYPE_EQ, yjwyTaskEqModel.getEq_id()));
				eqModels = eqBusinessService.query(eqQuery);
				if (null != eqModels && eqModels.length > 0) {
					// 设置name属性
					yjwyTaskEqModel.setAttribute("name", eqModels[0].getName());
				}
				roomIdSet.add(yjwyTaskEqModel.getRm_id());
			}
			// 查询机房model
			Query roomQuery = Query.from(YJWYRoomModel.META_ID);
			roomQuery.where(new Condition("rm_id", QueryContents.TYPE_IN, roomIdSet.toArray(new String[] {})));
			roomModels = roomBusinessService.query(roomQuery);
			List<YJWYTaskEqModel> taskEqList = null;
			String rm_id = null;
			// 机房属性设置
			for (YJWYRoomModel yjwyRoomModel : roomModels) {
				rm_id = yjwyRoomModel.getRm_id();
				yjwyRoomModel.setAttribute("id", rm_id);
				taskEqList = new ArrayList<>();
				// 设备放入机房下
				for (YJWYTaskEqModel yjwyTaskEqModel : taskEqModels) {
					if (rm_id.equals(yjwyTaskEqModel.getRm_id())) {
						taskEqList.add(yjwyTaskEqModel);
					}
				}
				yjwyRoomModel.setArrayAttribute("children", taskEqList);
			}
		}

		if (null != roomModels) {
			String string = JSON.toJSONString(roomModels);
			return string;
		}
		return "";
	}
}
