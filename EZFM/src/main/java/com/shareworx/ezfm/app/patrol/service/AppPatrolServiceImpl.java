package com.shareworx.ezfm.app.patrol.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService;
/**
 * 巡检保养 接口实现
 * @author lingwei.li
 *
 */
@Service(AppPatrolService.ID)
public class AppPatrolServiceImpl implements AppPatrolService{

	final static Logger logger = Logger.getLogger(AppPatrolServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(YJWYTaskDomainService.ID)
	private YJWYTaskDomainService taskService;
	
	@Autowired
	@Qualifier(YJWYTaskEqDomainService.ID)
	private YJWYTaskEqDomainService taskEqService;
	
	@Autowired
	@Qualifier(YJWYTaskEqBusinessService.ID)
	private YJWYTaskEqBusinessService taskEqBusinessService;

	public void setTaskEqBusinessService(YJWYTaskEqBusinessService taskEqBusinessService) {
		this.taskEqBusinessService = taskEqBusinessService;
	}
	/**
	 * 通过用户id 获取可以领取的任务列表
	 */
	@Override
	public JSONObject getAllPatrolByUserId(HttpServletRequest reqParam) throws Exception {
		logger.info("AppPatrolServiceImpl/getAllPatrolByUserId");
		
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(lt)
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		List<Map<String, Object>> planTaskMap = new ArrayList<Map<String, Object>>();
		
		//1、根据用户id 获取 所属 组 ids
		List<Map<String, Object>> groupMapList = getGroupList(userId);
		
		if (AppEmptyUtils.isEmpty(groupMapList)) {
			logger.info("result:groupMapList is null");
			return AppJsonMessage.toJsonMsgTrue(planTaskMap);
		}
		
		//2、根据 组ids 获取 计划 ids
		String groupIds = String.valueOf(groupMapList.get(0).get("pk_groups"));
		List<Map<String, Object>> planMapList = getPlanList(groupIds);

		if (AppEmptyUtils.isEmpty(planMapList)) {
			logger.info("result:planMapList is null");
			return AppJsonMessage.toJsonMsgTrue(planTaskMap);
		}
		
		//3、根据 计划ids 获取 巡检任务列表
		String planIds = String.valueOf(planMapList.get(0).get("plan_ids"));
		planTaskMap = getPlanTask(planIds, lt);
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(planTaskMap);
	}

	/**
	 * 领取 巡检任务
	 */
	@Override
	public JSONObject getPatrol(HttpServletRequest reqParam) throws Exception {
		logger.info("AppPatrolServiceImpl/getPatrol");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String patrolTaskId = reqParam.getParameter("patrolTaskId"); //巡检任务id
		
		Date currentDate = new Date();
		
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(patrolTaskId)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		//判断 巡检任务是否存在
		YJWYTaskModel patrolModel = taskService.queryById(patrolTaskId);
		if (AppEmptyUtils.isEmpty(patrolModel)) {
			logger.info("result:error patrolTaskId is not exist");
			return AppJsonMessage.toJsonMsgFalse(2,"该任务不存在");
		}
		//判断状态是否是未派单状态
		if(Integer.valueOf(AppConstant.PatrolTaskState.UNDISPATCH)!=patrolModel.getTask_state()){
			logger.info("result:error patrolTaskId is not exist");
			return AppJsonMessage.toJsonMsgFalse(2,"该任务非可领取任务，请刷新后重试！");
		}
		
		patrolModel.setExecutor_id(userId);
		patrolModel.setTask_state(Integer.valueOf(AppConstant.PatrolTaskState.UNFINISH));
		patrolModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		patrolModel.setUpdate_user(userId);
		
		List<YJWYTaskModel> taskModels = taskService.update(new YJWYTaskModel[]{patrolModel});
		if (taskModels.size()==0) {
			logger.info("result:error update patrolTask");
			String msg = "修改任务状态失败，请重试";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	/**
	 * 获取我的巡检任务列表
	 */
	@Override
	public JSONObject getMyPatrolList(HttpServletRequest reqParam) throws Exception {
		logger.info("AppPatrolServiceImpl/getMyPatrolList");
		
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		
		//判断必填字段
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(lt)
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//获取 我的 巡检任务列表
		List<Map<String, Object>> planTaskMap = getMyPlanTask(userId, lt);
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(planTaskMap);
	}

	/**
	 * 提交我的 巡检任务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject saveSubmitMyPatrol(HttpServletRequest reqParam) throws Exception {
		logger.info("AppPatrolServiceImpl/saveSubmitMyPatrol");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String details = reqParam.getParameter("details");
		String taskId = reqParam.getParameter("taskId");
		String isComplete = reqParam.getParameter("isComplete");
		
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(details)
				|| AppEmptyUtils.isEmpty(taskId)
				|| AppEmptyUtils.isEmpty(isComplete)){
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		//跟进任务id获取任务，如果当前提交是完成提交，判断任务是否超期，不超期就将状态改为已完成，超期不做状态改变。
		YJWYTaskModel task = taskService.queryById(taskId);
		if(null!=task && "1".equals(isComplete)){
			//如果任务状态已过期，则不再改变任务状态
			if(!AppConstant.PatrolTaskState.EXPIRE.equals(task.getTask_state())){
				task.setTask_state(Integer.valueOf(AppConstant.PatrolTaskState.FINISH));
			}
			//如果任务处理情况不为0，则改为1正常
			if(0!=task.getTask_dealt()){
				task.setTask_dealt(1);
			}
			task.setFinish_time(AppConstant.df_YMDHMS.format(new Date()));
			task.setTask_result(1);//任务完成设置结果为正常1
			task.setUpdate_time(new Date().getTime()+"");
			task.setUpdate_user(userId);
		}
		
		//先查询出所有需要更新的taskeq 得到taskEqModels
		JSONArray  detailsArray = JSONArray.parseArray(details); 
		String[] recordIds = new String[detailsArray.size()];
		for(int i =0;i<detailsArray.size();i++){
			Map<String, String> mapModel = (Map<String, String>)detailsArray.get(i);
			recordIds[i]= mapModel.get("recordId");
		}
		
		Query taskEqQuery = Query.from(YJWYTaskEqModel.META_ID);
		taskEqQuery.where(new Condition("pk_id", QueryContents.TYPE_IN, recordIds));
		YJWYTaskEqModel[] taskEqModels = taskEqBusinessService.query(taskEqQuery);
		
		for (int i = 0; i < detailsArray.size(); i++) {
			Map<String, String> mapModel = (Map<String, String>)detailsArray.get(i);
			String eqDesc = String.valueOf(mapModel.get("eqDesc"));
			String eqState = String.valueOf(mapModel.get("eqState"));
			String recordId = String.valueOf(mapModel.get("recordId"));
			//如果设备有异常，任务状态也为异常0
			if("0".equals(eqState)){
				task.setTask_result(0);
			}
			//taskeqModel需要更新的字段
			for(int j = 0; j<taskEqModels.length;j++){
				if(recordId.equals(taskEqModels[j].getPk_id())){
					taskEqModels[j].setEq_state(Integer.valueOf(eqState));
					taskEqModels[j].setEq_desc(eqDesc);
					taskEqModels[j].setUpdate_time(new Date().getTime()+"");
					taskEqModels[j].setUpdate_user(userId);
				}
			}
		}
		//更新所有taskeq
		taskEqService.update(taskEqModels);
		//更新任务
		taskService.update(task);
		
		logger.info("result:success");
		//提交后接口返回数据；TODO
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	/**
	 * 获取 巡检任务详情
	 */
	@Override
	public JSONObject getPatrolDetails(HttpServletRequest reqParam) throws Exception {
		logger.info("AppPatrolServiceImpl/getPatrolDetails");
		
		String patrolTaskId = reqParam.getParameter("patrolTaskId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userId = reqParam.getParameter("userId");
		
		if (AppEmptyUtils.isEmpty(patrolTaskId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		//根据任务id 获取 任务详情
		List<Map<String, Object>> planTaskList = getPlanDetails(patrolTaskId);
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(planTaskList);
	}

	/**
	 * 根据用户id 获取该用户所在 的 分组
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getGroupList(String userId){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String groupSql = "select "
				+ "IFNULL(GROUP_CONCAT(pk_group SEPARATOR \"', '\"),'') as pk_groups "
				+ "from yjwy_executor_group_user "
				+ "where pk_user = '"+userId+"' "
				+ "group by pk_user";
		
		List<Map<String, Object>> groupMapList = readJdbcTemplate.queryForList(groupSql);
		
		return groupMapList;
		
	}
	
	/**
	 * 根据所在组的 ids 获取计划 数组
	 * @param groupIds
	 * @return
	 */
	public List<Map<String, Object>> getPlanList(String groupIds){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String planSql = "select "
				+ "IFNULL(GROUP_CONCAT(plan_id SEPARATOR \"', '\"),'') as plan_ids "
				+ "from yjwy_patrol_plan "
				+ "where group_id in ('"+groupIds+"')";
		List<Map<String, Object>> planMapList = readJdbcTemplate.queryForList(planSql);
		
		return planMapList;
		
	}
	
	public List<Map<String, Object>> getPlanTask(String planIds,String lt){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//3、根据 计划ids 获取 巡检任务列表
		String patrolSql = "select ypt.task_id, ypt.end_time, "
				+ "ypt.task_state, ypt.task_type, ypt.update_time, "
				+ "ypp.plan_id, ypp.plan_name, "
				+ "ypp.pk_project,ypp.frequency, "
				+ "yppro.project_name_ as project_name, "
				+ "case when yptep.count IS NULL THEN 0 ELSE yptep.count end check_eq_count "
				+ " from yjwy_patrol_task ypt "
				+ "left join yjwy_patrol_plan ypp "
				+ "on ypp.plan_id = ypt.plan_id "
				+ "left join yjwy_pub_project yppro "
				+ "on yppro.pk_project_ = ypp.pk_project "
				+ " left join (select task_id,count(*) count from yjwy_patrol_taskeq GROUP BY task_id) yptep"
				+ " on ypt.task_id = yptep.task_id"
				+ " where ypt.executor_id is null "
				+ " and ypt.task_state = '"+AppConstant.PatrolTaskState.UNDISPATCH+"' "
				//+ " and ypt.update_time >= '"+lt+"'"(注释最后更新时间)
				+ " and ypp.plan_id in ('"+planIds+"')";
		List<Map<String, Object>> planTaskMapList = readJdbcTemplate.queryForList(patrolSql);
		
		return planTaskMapList;
	}
	
	/**
	 * 根据用户id 、lt  获取我的任务列表
	 * @param userId
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getMyPlanTask(String userId, String lt){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String myPatrolSql = "select ypt.task_id, ypt.end_time, "
				+ "ypt.task_state, ypt.task_type, ypt.update_time, "
				+ "ypp.plan_id, ypp.plan_name, "
				+ "ypp.pk_project,ypp.frequency, "
				+ "yppro.project_name_ as project_name, "
				+ "case when yptep.count IS NULL THEN 0 ELSE yptep.count end check_eq_count "
				+ " from yjwy_patrol_task ypt "
				+ " left join yjwy_patrol_plan ypp "
				+ " on ypp.plan_id = ypt.plan_id "
				+ " left join (select task_id,count(*) count from yjwy_patrol_taskeq GROUP BY task_id) yptep"
				+ " on ypt.task_id = yptep.task_id"
				+ " left join yjwy_pub_project yppro "
				+ " on yppro.pk_project_ = ypp.pk_project "
				+ " where ypt.executor_id = '"+userId+"'"
				+ " and ypt.update_time >= '"+lt+"'";
		List<Map<String, Object>> planTaskMapList = readJdbcTemplate.queryForList(myPatrolSql);
		
		return planTaskMapList;
	}
	
	/**
	 * 获取任务详情
	 * @param planId
	 * @return
	 */
	public List<Map<String, Object>> getPlanDetails(String patrolTaskId){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String patrolSql = "select pk_id,task_id, eq_id, plan_id, csi_id, "
				+ "pmp_id, rm_id, eq_state, eq_desc, pk_crop, update_time "
				+ "from yjwy_patrol_taskeq "
				+ "where task_id = '"+patrolTaskId+"'";
		List<Map<String, Object>> planTaskMapList = readJdbcTemplate.queryForList(patrolSql);
		
		return planTaskMapList;
	}
}
