package com.shareworx.ezfm.device.patrol.task.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.metadata.MetadataFactory;
import com.shareworx.platform.metadata.service.MetaCodeService;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.device.patrol.plan.dao.YJWYPlanDao;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanService;
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskDao;
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskEqDao;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保任务service实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYTaskService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYTaskServiceImpl implements YJWYTaskService {

	@Autowired
	@Qualifier(YJWYPlanBusinessService.ID)
	private YJWYPlanBusinessService planBusinessService;

	public void setPlanBusinessService(YJWYPlanBusinessService planBusinessService) {
		this.planBusinessService = planBusinessService;
	}

	@Autowired
	@Qualifier(YJWYPlanService.ID)
	private YJWYPlanService planService;

	public void setPlanService(YJWYPlanService planService) {
		this.planService = planService;
	}

	@Autowired
	@Qualifier(YJWYPlanEqBusinessService.ID)
	private YJWYPlanEqBusinessService planEqBusinessService;

	public void setPlanEqBusinessService(YJWYPlanEqBusinessService planEqBusinessService) {
		this.planEqBusinessService = planEqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(YJWYTaskDao.ID)
	private YJWYTaskDao taskDao;

	public void setTaskDao(YJWYTaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService baseInfoQueryService;

	public void setBaseInfoQueryService(IBaseInfoQueryService baseInfoQueryService) {
		this.baseInfoQueryService = baseInfoQueryService;
	}

	@Autowired
	@Qualifier(YJWYTaskEqDao.ID)
	private YJWYTaskEqDao taskEqDao;

	public void setTaskEqDao(YJWYTaskEqDao taskEqDao) {
		this.taskEqDao = taskEqDao;
	}

	@Autowired
	@Qualifier(YJWYPlanDao.ID)
	private YJWYPlanDao planDao;

	public void setPlanDao(YJWYPlanDao planDao) {
		this.planDao = planDao;
	}

	@Autowired
	@Qualifier(MetaCodeService.ID)
	private MetaCodeService metaCodeService;

	public void setMetaCodeService(MetaCodeService metaCodeService) {
		this.metaCodeService = metaCodeService;
	}

	private MetadataFactory factory;

	public MetadataFactory getFactory() {
		if (factory == null) {
			factory = SpringUtils.getBean("metadataFactory");
		}
		return factory;
	}

	/**
	 * 根据计划生成任务
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public void getTaskModels() {
		Date now_date = new Date();
		String now_str = DeviceUtil.date2String(now_date, "yyyy-MM-dd");
		// String now_str = "2016-08-10";
		YJWYPlanModel[] planModels = planService.queryModelsByNextTime(now_str);
		YJWYTaskModel[] taskModels = null;
		List<YJWYTaskEqModel> taskEqList = new ArrayList<>();
		// 判断是否有计划需要生成任务
		if (null != planModels && planModels.length > 0) {
			taskModels = new YJWYTaskModel[planModels.length];
			YJWYPlanModel planModel = null;
			YJWYTaskModel taskModel = null;
			YJWYTaskEqModel taskEqModel = null;
			YJWYPlanEqModel[] planEqModels = null;
			String task_id = null;
			String newNext_time = null;
			for (int i = 0; i < planModels.length; i++) {
				// 计划model
				planModel = planModels[i];
				// 获取下次生成时间
				newNext_time = getNext_time(planModel);
				// 组装任务model
				taskModel = new YJWYTaskModel();
				// 设置主键
				List<MetaField> fieldList = getFactory().getMetaClassByName(YJWYTaskModel.META_ID).getFields();
				for (MetaField metaField : fieldList) {
					if ("task_id".equals(metaField.getColumn())) {
						metaCodeService.execGenerateCode(metaField.getSerialcode(), metaField.getId(), false, taskModel);
						break;
					}
				}
				taskModel.setPlan_id(planModel.getPlan_id());
				taskModel.setPlan_name(planModel.getPlan_name());
				taskModel.setBegin_time(now_str);
				// 如果结束时间等于本次生成任务时间，则任务有效期为结束时间的下一天
				if ((planModel.getEnd_time()).compareTo(planModel.getNext_time()) == 0) {
					taskModel.setEnd_time(DeviceUtil.getNextDate(planModel.getEnd_time(), "yyyy-MM-dd", 1));
				} else {
					taskModel.setEnd_time(newNext_time);
				}
				// 初始任务状态：未派单
				taskModel.setTask_state(6);
				taskModel.setTask_type(planModel.getPlan_type());
				// 初始处理状态：空
				taskModel.setTask_result(2);
				taskModel.setTask_dealt(-1);
				taskModel.setPk_crop(planModel.getPk_crop());
				DeviceUtil.setCreateAndUpdateTime(taskModel);
				taskModels[i] = taskModel;
				task_id = taskModel.getTask_id();
				// 计划和设备中间表集合
				planEqModels = queryPlanEqModels(planModel.getPlan_id());
				if (null != planEqModels && planEqModels.length > 0) {
					for (YJWYPlanEqModel planEqModel : planEqModels) {
						// 组装任务设备中间表model
						taskEqModel = new YJWYTaskEqModel();
						taskEqModel.setTask_id(task_id);
						taskEqModel.setEq_id(planEqModel.getEq_id());
						taskEqModel.setPlan_id(planEqModel.getPlan_id());
						taskEqModel.setCsi_id(planEqModel.getCsi_id());
						taskEqModel.setPmp_id(planEqModel.getPmp_id());
						taskEqModel.setRm_id(planEqModel.getRm_id());
						taskEqModel.setPk_crop(planEqModel.getPk_crop());
						taskEqModel.setEq_state(-1);
						DeviceUtil.setCreateAndUpdateTime(taskEqModel);
						taskEqList.add(taskEqModel);
					}
				}
				DeviceUtil.setUpdateTime(planModel);
				// 设置新的生成时间
				planModel.setNext_time(newNext_time);
			}
		}
		if (null != taskModels) {
			YJWYTaskEqModel[] taskEqModels = new YJWYTaskEqModel[taskEqList.size()];
			for (int i = 0; i < taskEqList.size(); i++) {
				taskEqModels[i] = taskEqList.get(i);
			}
			// 持久化
			taskDao.saveModels(taskModels);
			taskEqDao.saveModels(taskEqModels);
			planDao.updateModels(planModels);
		}
	}

	/**
	 * 获取计划下一次生成任务的时间
	 */
	public String getNext_time(YJWYPlanModel model) {
		// 频次
		String frequency = model.getFrequency();
		// 当前下次生成时间
		Date next_time = DeviceUtil.string2Date(model.getNext_time(), "yyyy-MM-dd");
		// 截取频次数量和单位
		int lastIndex = frequency.lastIndexOf("/");
		Integer frequency_num = Integer.parseInt(frequency.substring(0, lastIndex));
		String frequency_unit = frequency.substring(lastIndex + 1);
		// 计算距离下一次生成任务之间的天数
		int days = 0;
		if ("日".equals(frequency_unit)) {
			days = frequency_num;
		} else if ("周".equals(frequency_unit)) {
			days = frequency_num * 7;
		} else if ("月".equals(frequency_unit)) {
			days = frequency_num * 30;
		} else if ("年".equals(frequency_unit)) {
			days = frequency_num * 365;
		}
		Date newNext_time = DeviceUtil.getNextDate(next_time, days);
		return DeviceUtil.date2String(newNext_time, "yyyy-MM-dd");
	}

	/**
	 * 查询计划设备关系集
	 */
	public YJWYPlanEqModel[] queryPlanEqModels(Serializable id) {
		YJWYPlanEqModel[] planEqModels = null;
		if (null != id) {
			Query planEqQuery = Query.from(YJWYPlanEqModel.META_ID);
			planEqQuery.where(new Condition("plan_id", QueryContents.TYPE_EQ, id));
			planEqModels = planEqBusinessService.query(planEqQuery);
		}
		return planEqModels;
	}

	/**
	 * 任务查询列表
	 */
	public List<Map<String, Object>> queryTaskMap(ParamEntity params) {
		return taskDao.queryMap(this.getSqlString(params, true));
	}

	/**
	 * 任务列表数量查询
	 */
	public Long queryTaskCount(ParamEntity params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(task.task_id) from (" + this.getSqlString(params, false) + ") task");
		return taskDao.queryCount(sql.toString());
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString(ParamEntity params, boolean flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String task_type = params.getTask_type();
		String pk_area = params.getPk_area();
		String pk_project = params.getPk_project();
		String pk_user = params.getPk_user();
		String task_state = params.getTask_state();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("task.task_result task_result,task.task_dealt task_dealt,task.task_desc task_desc,");
		sql.append("task.task_id task_id,task.plan_id plan_id,task.plan_name plan_name,task.executor_id executor_id,task.begin_time begin_time, ");
		sql.append("task.end_time end_time,task.finish_time finish_time,task.task_state task_state,task.task_type task_type,task.pk_crop pk_crop, ");
		sql.append("task.create_time create_time,task.create_user create_user,task.update_time update_time,task.update_user update_user, ");
		sql.append("user.user_name_ executor_name,info.pk_area pk_area,info.pk_project pk_project,info.group_id group_id,info.group_name group_name , ");
		sql.append("info.project_name_ project_name,info.area_name_ area_name  ");
		sql.append("FROM yjwy_patrol_task task ");
		sql.append("LEFT JOIN yjwy_pub_user user ON task.executor_id = user.pk_user_ ");
		sql.append("JOIN ( ");
		sql.append("SELECT plan.plan_id,plan.plan_name,plan.pk_area,plan.pk_project,plan.group_id,gro.group_name, ");
		sql.append("pro.project_name_,area.area_name_  ");
		sql.append(" FROM yjwy_patrol_plan plan ");
		sql.append("JOIN yjwy_pub_area area ON plan.pk_area = area.pk_area_ ");
		sql.append("JOIN yjwy_pub_project pro ON plan.pk_project = pro.pk_project_ ");
		sql.append("JOIN yjwy_executor_group gro ON plan.group_id = gro.pk_group ");
		sql.append(" ) info  ");
		sql.append("ON task.plan_id = info.plan_id ");
		// 计划类型
		if (!DeviceUtil.stringIsEmpty(task_type)) {
			sql.append(" where task.task_type = '" + task_type + "' ");
		}
		// 人员选择
		if (null != pk_user && !"default".equals(pk_user)) {
			sql.append(" AND task.executor_id = '" + pk_user + "' ");
		}
		// 任务状态
		if (null != task_state && "" != task_state && !"-1".equals(task_state)) {
			sql.append(" AND task.task_state = '" + task_state + "' ");
		}
		// 开始时间
		if (null != start_time && "" != start_time && null != end_time && "" != end_time) {
			sql.append(" AND task.begin_time <= '" + end_time + "' AND task.begin_time >= '" + start_time + "' ");
		}
		// 查询当前用户的项目
		Set<String> project_ids = params.getProject_ids();
		String projectids = "";
		if (project_ids != null) {
			String[] ids = project_ids.toArray(new String[] {});
			for (int i = 0; i < ids.length; i++) {
				projectids += ("'" + ids[i] + "'");
				if (i != ids.length - 1) {
					projectids += ",";
				}
			}
		}
		if (project_ids != null && project_ids.size() > 0) {
			sql.append(" AND info.pk_project in (" + projectids + ")");
		} else {
			sql.append(" AND 1=2");
		}
				
		sql.append(" and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		sql.append(" ORDER BY task.task_id ");
		if (flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}

	/**
	 * 判断过期任务，并修改状态
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public void judgeExpired() {
		List<YJWYTaskModel> taskList = getExpiredTask();
		if (null == taskList || taskList.size() == 0) {
			return;
		}
		YJWYTaskModel[] taskModels = new YJWYTaskModel[taskList.size()];
		for (int i = 0; i < taskList.size(); i++) {
			YJWYTaskModel yjwyTaskModel = taskList.get(i);
			// 修改状态
			yjwyTaskModel.setTask_state(2);
			DeviceUtil.setUpdateTime(yjwyTaskModel);
			taskModels[i] = yjwyTaskModel;
		}
		taskDao.updateModels(taskModels);
	}

	/**
	 * 获取过期任务集
	 */
	public List<YJWYTaskModel> getExpiredTask() {
		Query taskQuery = Query.from(YJWYTaskModel.META_ID);
		// 结束日期等于当前日期
		String now_time = DeviceUtil.date2String(new Date(), "yyyy-MM-dd");
		taskQuery.where(new Condition("end_time", QueryContents.TYPE_EQ, now_time));
		taskQuery.and(new Condition("task_state", QueryContents.TYPE_IN, new Integer[] { 0, 6 }));
		return taskDao.queryListByCondition(taskQuery);
	}

}
