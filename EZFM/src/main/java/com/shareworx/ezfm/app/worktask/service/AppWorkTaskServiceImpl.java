package com.shareworx.ezfm.app.worktask.service;

import java.math.BigDecimal;
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
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.push.service.AppPushService;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService;
import com.shareworx.ezfm.pub.dictionary.OperationExpress;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpClient;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpService;
import com.shareworx.ezfm.webservice.eba.out.EbaWarningEliminateService;
import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService;
import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService;

@Service(AppWorkTaskService.ID)
public class AppWorkTaskServiceImpl implements AppWorkTaskService {

	final static Logger logger = Logger.getLogger(AppWorkTaskServiceImpl.class);

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;

	@Autowired
	@Qualifier(YJWYWorkTaskSonClassRecordDomainService.ID)
	private YJWYWorkTaskSonClassRecordDomainService workTaskSonService;

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordDomainService.ID)
	private YJWYWorkTaskDetailsRecordDomainService workTaskRecordService;

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsDomainService.ID)
	private YJWYWorkTaskDetailsDomainService workTaskService;

	@Autowired
	@Qualifier(YJWYWorkTaskAssistUserRecordDomainService.ID)
	private YJWYWorkTaskAssistUserRecordDomainService workTaskAssistService;

	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService problemService;

	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService problemRecordService;

	@Autowired
	@Qualifier(AppPushService.ID)
	private AppPushService pushService;

	@Autowired
	@Qualifier(YJWYTaskDomainService.ID)
	private YJWYTaskDomainService patrolTaskService;

	@Autowired
	@Qualifier(EbaWarningEliminateService.ID)
	private EbaWarningEliminateService ebaWarningEliminateService;
	
	@Autowired
	@Qualifier(cloudHttpService.ID)
	private cloudHttpService cloudhttpservice;  
	
	public void setCloudhttpservice(cloudHttpService cloudhttpservice) {
		this.cloudhttpservice = cloudhttpservice;
	}

	/**
	 * 通过userid 获取工单池
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getAllWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/getAllWorkTask");

		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(lt) || AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		List<Map<String, Object>> workTaskMap = new ArrayList<Map<String, Object>>();

		// 1、根据userid 查询属性那个片区
		List<Map<String, Object>> areaMapList = getAreaForUserId(userId);

		if (AppEmptyUtils.isEmpty(areaMapList)) {
			logger.info("result:areaMapList is null");
			return AppJsonMessage.toJsonMsgTrue(workTaskMap);
		}

		// 2、根据 片区 查询 该片区下的 项目
		String areaIds = String.valueOf(areaMapList.get(0).get("area_ids"));
		List<Map<String, Object>> projectMapList = getProjectIds(areaIds);

		if (AppEmptyUtils.isEmpty(projectMapList)) {
			logger.info("result:projectMapList is null");
			return AppJsonMessage.toJsonMsgTrue(workTaskMap);
		}

		// 3、获取项目ids 查询 抢单池 中的 工单
		String projectIds = String.valueOf(projectMapList.get(0).get("project_ids"));
		workTaskMap = getWorkTaskList(projectIds, crop, lt);

		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(workTaskMap);
	}

	/**
	 * 抢 工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/getWorkTask");

		String userId = reqParam.getParameter("userId");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		Date currentDate = new Date();

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		// 判断工单是否存在
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 判断工单状态是否是 未派单
		if (AppConstant.WorktaskState.NOTSEND != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			String msg = "该工单已被抢！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 设置工单 要修改的值
		workTaskDetailsModel.setTask_state(Integer.valueOf(AppConstant.WorktaskState.PENDING));
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);
		workTaskDetailsModel.setDuty_user_id(userId);
		workTaskDetailsModel.setDispatch_type(1);// 派单类型为抢单
		workTaskDetailsModel.setDispatch_time(AppConstant.df_YMDHMS.format(currentDate));
		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.PENDING);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_express(OperationExpress.GRAB); // 抢单
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error parameter");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		} else {
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, worktaskDetailsId));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			if (problemModel != null) {
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!AppEmptyUtils.isEmpty(worktask_id)) {
					// 推送"已响应"--“answered”
					String worktaskid = worktask_id;
					String ycq_status = "answered";
					String pem_id = userId;
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录
		}

		logger.info("result:success");
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	/**
	 * 我的工单列表
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getMyWorkTaskList(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/getMyWorkTaskList");
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(lt)
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 跟进人是我 状态是 1 或者 2 的 就可以是我的工单列表中的任务
		List<Map<String, Object>> workTaskMap = getMyWorkTaskList(userId, crop, lt);

		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(workTaskMap);
	}

	/**
	 * 接受我的工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveAcceptMyWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/acceptMyWorkTask");
		String userId = reqParam.getParameter("userId");
		String operationRemarks = reqParam.getParameter("operationRemarks");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		Date currentDate = new Date();
		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 判断工单是否存在
		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 判断工单是否是 待接单
		if (AppConstant.WorktaskState.PENDING != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			String msg = "该工单不是待接单状态，不可接单！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 修改工单状态
		workTaskDetailsModel.setTask_state(Integer.valueOf(AppConstant.WorktaskState.REPAIRING));
		workTaskDetailsModel.setRepair_user_id(userId);
		workTaskDetailsModel.setDuty_user_id(userId);
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);
		workTaskDetailsModel.setOrders_time(AppConstant.df_YMDHMS.format(currentDate));// 设置接单时间

		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });

		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.REPAIRING);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		workTaskDetailsRecord.setOperation_express(OperationExpress.ACCEPT); // 接单
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error parameter");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		} else {
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, worktaskDetailsId));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			if (problemModel != null) {
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!AppEmptyUtils.isEmpty(worktask_id)) {
					// 推送"已响应"--“processing”
					String worktaskid = worktask_id;
					String ycq_status = "processing";
					String pem_id = userId;
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录
		}
		return AppJsonMessage.toJsonMsgTrue(newWorkTaskDetailsRecord.get(0));
	}

	/**
	 * 拒绝我的工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveRejectMyWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/rejectMyWorkTask");
		String userId = reqParam.getParameter("userId");
		String operationRemarks = reqParam.getParameter("operationRemarks");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		String dictId = reqParam.getParameter("dictId");// 拒单原因id
		Date currentDate = new Date();
		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId) || AppEmptyUtils.isEmpty(dictId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 判断工单是否存在
		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 判断工单是否是 待接单
		if (AppConstant.WorktaskState.PENDING != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			String msg = "该工单不是待接单状态，不可拒单！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 抢单池来的回抢单池，派单来的回已拒单
		if (1 == workTaskDetailsModel.getDispatch_type()) {
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.NOTSEND);
		} else {
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.REFUSED);
		}
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);
		// 跟进人id置空
		workTaskDetailsModel.setDuty_user_id("");

		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });

		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.REFUSED);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		workTaskDetailsRecord.setOperation_express(OperationExpress.REFUSE); // 拒单
		workTaskDetailsRecord.setRefuse_reason(dictId);// 拒单原因
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error parameter");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		} else {
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, worktaskDetailsId));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			if (problemModel != null) {
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!AppEmptyUtils.isEmpty(worktask_id)) {
					// 推送"拒单"--“backed”
					String worktaskid = worktask_id;
					String ycq_status = "backed";
					String pem_id = userId;
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录
		}
		return AppJsonMessage.toJsonMsgTrue(newWorkTaskDetailsRecord.get(0));
	}

	/**
	 * 派发我的工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveDistributeMyWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/distributeMyWorkTask");
		String userId = reqParam.getParameter("userId");
		String operationRemarks = reqParam.getParameter("operationRemarks");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		String dutyUserId = reqParam.getParameter("dutyUserId"); // 接单人id
		Date currentDate = new Date();
		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId) || AppEmptyUtils.isEmpty(dutyUserId)) {
			logger.info("result:error parameter");
			String msg = "参数有误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 判断工单是否存在
		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 判断工单是否是 待接单
		if (AppConstant.WorktaskState.PENDING != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(2, "该工单不是待接单状态，不可派单！"); // 参数错误！
		}
		// 判断工单是派发给工单池还是具体人员
		if (!"0".equals(dutyUserId)) {
			// 核查 派发人
			YJWYUserModel dutyUser = userService.queryById(dutyUserId);
			if (AppEmptyUtils.isEmpty(dutyUser)) {
				logger.info("result:error parameter");
				return AppJsonMessage.toJsonMsgFalse(2, "派发人不存在！"); // 参数错误！
			}
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.PENDING);
			workTaskDetailsModel.setDuty_user_id(dutyUserId);
		} else {
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.NOTSEND);
		}
		workTaskDetailsModel.setDispatch_type(2);// 派单类型2：派单
		workTaskDetailsModel.setDispatch_time(AppConstant.df_YMDHMS.format(currentDate));// 设置派单时间
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);

		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.PENDING);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		workTaskDetailsRecord.setOperation_express(OperationExpress.SEND); // 派单
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error parameter");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		} else {
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, worktaskDetailsId));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			if (problemModel != null) {
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!AppEmptyUtils.isEmpty(worktask_id)) {
					// 推送"已分发"--“assigned”
					String worktaskid = worktask_id;
					String ycq_status = "assigned";
					String pem_id = userId;
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录
		}

		// 派单推送
		pushService.sendWorkTaskPush(workTaskDetailsModel);

		return AppJsonMessage.toJsonMsgTrue(newWorkTaskDetailsRecord.get(0));
	}

	/**
	 * 完成我的 工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public JSONObject saveFinishMyWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/distributeMyWorkTask");
		String userId = reqParam.getParameter("userId");
		String operationRemarks = reqParam.getParameter("operationRemarks");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		String rationDuration = reqParam.getParameter("rationDuration");// 额定工时
		String finishedDuration = reqParam.getParameter("finishedDuration");// 实际工时
		String materialCost = reqParam.getParameter("materialCost");// 材料费用
		String artificialCost = reqParam.getParameter("artificialCost");// 人工费用
		String countCost = reqParam.getParameter("countCost");// 实际总费用
		String repairClass = reqParam.getParameter("repairClass");
		String assistUser = reqParam.getParameter("assistUser");
		String feedbackContent = reqParam.getParameter("feedbackContent"); // 反馈详情
		String evaluate = reqParam.getParameter("evaluate"); // 评价满意度，必填

		Date currentDate = new Date();
		String default_value = "0.0";// 默认百分比

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(rationDuration) || AppEmptyUtils.isEmpty(finishedDuration)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 判断工单是否存在
		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 判断工单是否是 维修中
		if (AppConstant.WorktaskState.REPAIRING != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			String msg = "该工单不是维修中工单，不能完成！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 设置 工单状态
		workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.COMPLETE);
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);
		workTaskDetailsModel.setRation_duration(rationDuration);
		workTaskDetailsModel.setFinished_duration(finishedDuration);
		workTaskDetailsModel.setFinish_time(AppConstant.df_YMDHMS.format(currentDate));
		// 如果是户内维修，需要保存客户评价
		if ("serviceCateOne".equals(workTaskDetailsModel.getService_type())) {
			if (AppEmptyUtils.isEmpty(evaluate)) {
				logger.info("result:error parameter");
				return AppJsonMessage.toJsonMsgFalse(1, "评价满意度不可为空！"); // 参数错误！
			}
			workTaskDetailsModel.setFeedback_content(feedbackContent);
			workTaskDetailsModel.setEvaluate(evaluate);
		}

		if (AppEmptyUtils.isEmpty(materialCost)) {
			workTaskDetailsModel.setMaterial_cost(new BigDecimal(default_value));
		} else {
			workTaskDetailsModel.setMaterial_cost(new BigDecimal(materialCost));
		}
		if (AppEmptyUtils.isEmpty(artificialCost)) {
			workTaskDetailsModel.setArtificial_cost(new BigDecimal(default_value));
		} else {
			workTaskDetailsModel.setArtificial_cost(new BigDecimal(artificialCost));
		}
		if (AppEmptyUtils.isEmpty(countCost)) {
			workTaskDetailsModel.setCount_cost(new BigDecimal(default_value));
		} else {
			workTaskDetailsModel.setCount_cost(new BigDecimal(countCost));
		}

		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 判断维修种类是否为空
		if (!AppEmptyUtils.isEmpty(repairClass)) {
			JSONArray repairJson = JSONArray.parseArray(repairClass);
			if (!ArrayUtils.isEmpty(repairJson)) {
				YJWYWorkTaskSonClassRecordModel[] repClass = new YJWYWorkTaskSonClassRecordModel[repairJson.size()];
				for (int i = 0; i < repairJson.size(); i++) {
					Map<String, Object> mapModel = (Map<String, Object>) repairJson.get(i);
					String repairClassId = String.valueOf(mapModel.get("repairClassId"));
					String number = String.valueOf(mapModel.get("number"));
					String parentClassId = String.valueOf(mapModel.get("parentClassId"));
					if (AppEmptyUtils.isEmpty(number)) {
						number = "0";
					}

					YJWYWorkTaskSonClassRecordModel sonClassRecordModel = new YJWYWorkTaskSonClassRecordModel();
					sonClassRecordModel.setFk_details_id(worktaskDetailsId);
					sonClassRecordModel.setFk_class_id(repairClassId);
					sonClassRecordModel.setNumber(Integer.valueOf(number));
					sonClassRecordModel.setRecord_time(AppConstant.df_YMDHMS.format(currentDate));
					sonClassRecordModel.setRecord_user_id(userId);
					sonClassRecordModel.setFk_two_levelclass_id(parentClassId);
					repClass[i] = sonClassRecordModel;
				}

				List<YJWYWorkTaskSonClassRecordModel> newWorkTaskSonClassRecordModel = workTaskSonService
						.save(repClass);

				if (AppEmptyUtils.isEmpty(newWorkTaskSonClassRecordModel)) {
					logger.info("result:error parameter");
					String msg = "维修种类插入失败！";
					return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
				}
			}
		}
		// 判断协助人员是否为空 如果不为空 添加到工单协助人 表中
		if (!AppEmptyUtils.isEmpty(assistUser)) {
			JSONArray asUserJson = JSONArray.parseArray(assistUser);
			if (!ArrayUtils.isEmpty(asUserJson)) {
				YJWYWorkTaskAssistUserRecordModel[] asUser = new YJWYWorkTaskAssistUserRecordModel[asUserJson.size()];
				for (int i = 0; i < asUserJson.size(); i++) {
					Map<String, Object> mapModel = (Map<String, Object>) asUserJson.get(i);
					String assistUserId = String.valueOf(mapModel.get("assistUserId"));
					String workProportion = String.valueOf(mapModel.get("workProportion"));
					if (AppEmptyUtils.isEmpty(workProportion)) {
						workProportion = default_value;
					}
					YJWYWorkTaskAssistUserRecordModel newAsUser = new YJWYWorkTaskAssistUserRecordModel();
					newAsUser.setFk_details_id(worktaskDetailsId);
					newAsUser.setFk_user_id(assistUserId);
					newAsUser.setRecord_time(AppConstant.df_YMDHMS.format(currentDate));
					newAsUser.setWork_proportion(new BigDecimal(workProportion));
					newAsUser.setRecord_user_id(userId);
					asUser[i] = newAsUser;

				}

				List<YJWYWorkTaskAssistUserRecordModel> newWorkTaskAssistUserRecordModel = workTaskAssistService
						.save(asUser);

				if (AppEmptyUtils.isEmpty(newWorkTaskAssistUserRecordModel)) {
					logger.info("result:error parameter");
					String msg = "协助人员插入失败！";
					return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
				}
			}
		}
		// 完成工单，判断，如果是eba预警工单则调取消报警接口
		if (!AppEmptyUtils.isEmpty(workTaskDetailsModel.getEba_report_id())) {
			ebaWarningEliminateService.EbaWarningEliminate(workTaskDetailsModel.getEba_report_id());
		}

		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.COMPLETE);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		workTaskDetailsRecord.setOperation_express(OperationExpress.COMPLETE); // 接单
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });

		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error parameter");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		} else {
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, worktaskDetailsId));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			if (problemModel != null) {
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!AppEmptyUtils.isEmpty(worktask_id)) {
					// 推送"完成"--“completed”
					String worktaskid = worktask_id;
					String ycq_status = "completed";
					String pem_id = userId;
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录
		}
		workTaskDetailsRecord.setPk_record_id(newWorkTaskDetailsRecord.get(0).getPk_record_id());
		// 修改报事状态
		Query query = Query.from(YJWYProblemDetailsModel.META_ID);
		query.and(Condition.create("fk_details_id", worktaskDetailsId));
		YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
		// 判断报事是否存在
		if (AppEmptyUtils.isEmpty(problemModel)) {
			logger.info("result:error parameter");
			String msg = "该工单关联的报事不可以为空！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 修改报事 状态 设置为3 完成待回访
		problemModel.setState(3);
		problemService.update(problemModel);

		// 添加报事记录
		YJWYProblemRecordModel recordModel = new YJWYProblemRecordModel();
		recordModel.setFk_details_id(problemModel.getPk_details_id());
		recordModel.setRecord_content(operationRemarks);
		recordModel.setRecord_time(AppConstant.df_YMDHMS.format(currentDate));
		recordModel.setRecord_user_id(userId);
		recordModel.setDuty_user_id(problemModel.getFk_duty_user_id());
		recordModel.setDuty_user_name(problemModel.getDuty_user_name());
		recordModel.setOperate_state(Integer.valueOf(AppConstant.PROBLEM_STATE_3));
		List<YJWYProblemRecordModel> newRecordModel = problemRecordService
				.save(new YJWYProblemRecordModel[] { recordModel });

		// 判断报事记录是否添加成功
		if (AppEmptyUtils.isEmpty(newRecordModel)) {
			logger.info("result:error parameter");
			String msg = "新添加报事记录错误！";
			return AppJsonMessage.toJsonMsgFalse(2, msg);
		}

		return AppJsonMessage.toJsonMsgTrue(workTaskDetailsRecord);
	}

	/**
	 * 取消我的工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveCancelMyWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/distributeMyWorkTask");
		String userId = reqParam.getParameter("userId");
		String operationRemarks = reqParam.getParameter("operationRemarks");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		Date currentDate = new Date();
		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(worktaskDetailsId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 判断工单是否存在
		YJWYWorkTaskDetailsModel workTaskDetailsModel = workTaskService.queryById(worktaskDetailsId);
		if (AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			logger.info("result:error parameter");
			String msg = "任务id不存在！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 判断工单是否是 待接单
		if (AppConstant.WorktaskState.REPAIRING != workTaskDetailsModel.getTask_state()) {
			logger.info("result:error parameter");
			String msg = "该工单不是维修中工单，不能取消！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 设置 工单状态
		workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.CANCEL);
		workTaskDetailsModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);
		workTaskDetailsModel.setCancel_time(AppConstant.df_YMDHMS.format(currentDate));

		List<YJWYWorkTaskDetailsModel> newWorkTaskDetailsModel = workTaskService
				.update(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsModel)) {
			logger.info("result:error update");
			String msg = "工单修改失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(worktaskDetailsId);
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(AppConstant.WorktaskState.CANCEL);
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(currentDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		workTaskDetailsRecord.setOperation_express(OperationExpress.CANCEL); // 取消
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error newWorkTaskDetailsRecord add");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 修改报事状态
		Query query = Query.from(YJWYProblemDetailsModel.META_ID);
		query.and(Condition.create("fk_details_id", worktaskDetailsId));
		YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
		// 判断报事是否存在
		if (AppEmptyUtils.isEmpty(problemModel)) {
			logger.info("result:error parameter");
			String msg = "该工单关联的报事不可以为空！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}
		// 报事状态设置为1
		problemModel.setState(1);
		problemService.update(problemModel);

		// 添加报事记录
		YJWYProblemRecordModel recordModel = new YJWYProblemRecordModel();
		recordModel.setFk_details_id(problemModel.getPk_details_id());
		recordModel.setRecord_content(operationRemarks);
		recordModel.setRecord_time(AppConstant.df_YMDHMS.format(currentDate));
		recordModel.setRecord_user_id(userId);
		recordModel.setDuty_user_id(problemModel.getFk_duty_user_id());
		recordModel.setDuty_user_name(problemModel.getDuty_user_name());
		recordModel.setOperate_state(Integer.valueOf(AppConstant.PROBLEM_STATE_6));
		List<YJWYProblemRecordModel> newRecordModel = problemRecordService
				.save(new YJWYProblemRecordModel[] { recordModel });

		// 判断报事记录是否添加成功
		if (AppEmptyUtils.isEmpty(newRecordModel)) {
			logger.info("result:error parameter");
			String msg = "新添加报事记录错误！";
			return AppJsonMessage.toJsonMsgFalse(2, msg);
		}

		return AppJsonMessage.toJsonMsgTrue(newWorkTaskDetailsRecord.get(0));
	}

	/**
	 * 新增工单
	 */
	@Override
	public JSONObject addWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/addWorkTask");
		String userId = reqParam.getParameter("userId"); // userId N
		String mobilePlatform = reqParam.getParameter("mobilePlatform");// mobilePlatform
																		// N
		String crop = reqParam.getParameter("crop");// 公司 N
		String projectId = reqParam.getParameter("projectId");// 项目id N
		String repairDetails = reqParam.getParameter("repairDetails");// 详细地址 N
		String serviceType = reqParam.getParameter("serviceType");// 服务类型(数据字典code值)
																	// N
		String repairClassId = reqParam.getParameter("repairClassId");// 维修种类ID
																		// N
		String dutyUserId = reqParam.getParameter("dutyUserId");// 跟进人ID（接单人）
		String repairUser = reqParam.getParameter("repairUser");// 报修人（名称） N
		String contactNumber = reqParam.getParameter("contactNumber");// 联系电话 N
		String repairEqId = reqParam.getParameter("repairEqId");// 维修设备ID(关联eq_id)
		String fk_repair_equipment_room = reqParam.getParameter("fk_repair_equipment_room");// 维修机房ID(关联room)
		String repairContent = reqParam.getParameter("repairContent");// 维修内容(备注)
		// 获取巡检保养任务id，如果为非空，则该条工单是从巡检保养的设备提交的，需要去修改巡检保养任务的状态为派单
		String devicePatrolTaskId = reqParam.getParameter("devicePatrolTaskId");
		if (!AppEmptyUtils.isEmpty(devicePatrolTaskId)) {
			// 更新巡检保养任务
			updateTaskDealt(devicePatrolTaskId, userId);
		}
		// 创建时间
		Date createDate = new Date();
		// 判断必填项
		if (AppEmptyUtils.isEmpty(userId) || AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(projectId) || AppEmptyUtils.isEmpty(repairDetails)
				|| AppEmptyUtils.isEmpty(serviceType) || AppEmptyUtils.isEmpty(repairClassId)
				|| AppEmptyUtils.isEmpty(repairUser) || AppEmptyUtils.isEmpty(contactNumber)
				|| AppEmptyUtils.isEmpty(dutyUserId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误
		}

		YJWYWorkTaskDetailsModel workTaskDetailsModel = new YJWYWorkTaskDetailsModel();
		workTaskDetailsModel.setFk_project_id(projectId);

		// 如果有 跟进人 说明 是指定人 干这件事 工单状态为 未接单（进个人工单任务），否则为未派单（进工单池）
		if ("0".equals(dutyUserId)) {
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.NOTSEND);
		} else {
			// 核查人 跟进人
			// TODO跟进人需不需要判断是否启用
			YJWYUserModel dutyUser = userService.queryById(dutyUserId);
			if (AppEmptyUtils.isEmpty(dutyUser)) {
				logger.info("result:error parameter");
				String msg = "跟进人不存在！";
				return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
			}
			workTaskDetailsModel.setTask_state(AppConstant.WorktaskState.PENDING);
			workTaskDetailsModel.setDuty_user_id(dutyUserId);
		}

		workTaskDetailsModel.setDatails_code(workTaskService.getTaskCode(projectId));
		workTaskDetailsModel.setService_type(serviceType);
		workTaskDetailsModel.setRepair_class_id(repairClassId);
		// 工单来源，数据字典code值
		if ("IOS".equals(mobilePlatform)) {
			workTaskDetailsModel.setWorktask_type("taskSource3");
		}
		if ("Android".equals(mobilePlatform)) {
			workTaskDetailsModel.setWorktask_type("taskSource2");
		}
		workTaskDetailsModel.setRepair_details(repairDetails);
		workTaskDetailsModel.setFk_repair_equipment(repairEqId);
		workTaskDetailsModel.setRepair_user(repairUser);
		workTaskDetailsModel.setContact_number(contactNumber);
		workTaskDetailsModel.setRepair_content(repairContent);

		workTaskDetailsModel.setFk_repair_equipment_room(fk_repair_equipment_room);
		workTaskDetailsModel.setPk_crop(crop);
		workTaskDetailsModel.setCreate_user_id(userId);
		workTaskDetailsModel.setCreate_time(AppConstant.df_YMDHMS.format(createDate));
		workTaskDetailsModel.setUpdate_time(String.valueOf(createDate.getTime()));
		workTaskDetailsModel.setUpdate_user_id(userId);

		List<YJWYWorkTaskDetailsModel> newYJWYWorkTaskDetailsModel = workTaskService
				.save(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });

		if (AppEmptyUtils.isEmpty(newYJWYWorkTaskDetailsModel)) {
			logger.info("result:error add");
			String msg = "新增工单错误，请重试！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 添加工单记录
		YJWYWorkTaskDetailsRecordModel workTaskDetailsRecord = new YJWYWorkTaskDetailsRecordModel();
		workTaskDetailsRecord.setFk_details_id(newYJWYWorkTaskDetailsModel.get(0).getPk_details_id());
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setTask_state(newYJWYWorkTaskDetailsModel.get(0).getTask_state());
		workTaskDetailsRecord.setOperation_time(AppConstant.df_YMDHMS.format(createDate));
		workTaskDetailsRecord.setOperation_user_id(userId);
		workTaskDetailsRecord.setOperation_express(OperationExpress.ADD); // 开单

		// workTaskDetailsRecord.setOperation_remarks(operationRemarks);
		List<YJWYWorkTaskDetailsRecordModel> newWorkTaskDetailsRecord = workTaskRecordService
				.save(new YJWYWorkTaskDetailsRecordModel[] { workTaskDetailsRecord });
		if (AppEmptyUtils.isEmpty(newWorkTaskDetailsRecord)) {
			logger.info("result:error newWorkTaskDetailsRecord add");
			String msg = "工单记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 新增报事
		YJWYProblemDetailsModel problemDetail = new YJWYProblemDetailsModel();
		problemDetail.setDetails_number(problemService.getCodeByProjectId(projectId));
		problemDetail.setFk_project_id(projectId);
		problemDetail.setDetails_content(repairContent);
		problemDetail.setDetailed_address(repairDetails);
		problemDetail.setFk_duty_user_id(dutyUserId);
		problemDetail.setFk_details_id(newYJWYWorkTaskDetailsModel.get(0).getPk_details_id());
		problemDetail.setState(Integer.valueOf(AppConstant.PROBLEM_STATE_2));// 处理中报事
		problemDetail.setCreate_time(AppConstant.df_YMDHMS.format(createDate));
		problemDetail.setCreate_user_id(userId);
		problemDetail.setUpdate_time(String.valueOf(createDate.getTime()));
		problemDetail.setUpdate_user_id(userId);
		problemDetail.setPk_crop(crop);

		List<YJWYProblemDetailsModel> newProblemDetail = problemService
				.save(new YJWYProblemDetailsModel[] { problemDetail });

		if (AppEmptyUtils.isEmpty(newProblemDetail)) {
			logger.info("result:error newWorkTaskDetailsRecord add");
			String msg = "新增报事保存失败！";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 新增工单 推送
		pushService.sendWorkTaskPush(workTaskDetailsModel);

		return AppJsonMessage.toJsonMsgTrue(newWorkTaskDetailsRecord.get(0));
	}

	/**
	 * 我发出的 工单列表
	 */
	@Override
	public JSONObject getSendWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/getSendWorkTask");
		String crop = reqParam.getParameter("crop");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(crop) || AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(lt)
				|| AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		List<Map<String, Object>> workTaskMap = getMySendWorkTaskList(userId, crop, lt);

		return AppJsonMessage.toJsonMsgTrue(workTaskMap);
	}

	/**
	 * 获取超时工单
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getTimeOutWorkTask(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/getTimeOutWorkTask");
		String crop = reqParam.getParameter("crop");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(crop) || AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(lt)
				|| AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		List<Map<String, Object>> workTaskMap = getTimeOutWorkTaskList(userId, crop, lt);

		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(workTaskMap);

	}

	/**
	 * 工单详情
	 */
	@Override
	public JSONObject getWorkTaskDetails(HttpServletRequest reqParam) throws Exception {
		logger.info("worktaskService/distributeMyWorkTask");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String worktaskDetailsId = reqParam.getParameter("worktaskDetailsId");
		String userId = reqParam.getParameter("userId");

		// 先判断必填参数
		if (AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(worktaskDetailsId)
				|| AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误！
		}

		// 获取工单详情
		List<Map<String, Object>> workTaskMap = getWorkTaskDetailsList(worktaskDetailsId);

		if (AppEmptyUtils.isEmpty(workTaskMap)) {
			logger.info("result:error workTaskMap is null");
			String msg = "该工单不存在，请刷新后重试";
			return AppJsonMessage.toJsonMsgFalse(2, msg); // 参数错误！
		}

		// 通过工单id 查询工单记录
		List<Map<String, Object>> workTaskRecordMap = getWorkTaskRecordList(worktaskDetailsId);

		workTaskMap.get(0).put("worktask_details_records", workTaskRecordMap);

		return AppJsonMessage.toJsonMsgTrue(workTaskMap.get(0));
	}

	/**
	 * 根据用户id 获取所属区域的 数组
	 * 
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getAreaForUserId(String userId) {

		String areaSQL = "select " + "IFNULL(GROUP_CONCAT(a.area_id SEPARATOR \"', '\"),'') as area_ids " + "from "
				+ "(select area_id from yjwy_worktask_area_user_nexus " + "where user_id ='" + userId
				+ "' group by area_id) a";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> areaMapList = readJdbcTemplate.queryForList(areaSQL);

		return areaMapList;

	}

	/**
	 * 根据区域ids 获取项目ids
	 * 
	 * @param areaIds
	 * @return
	 */
	public List<Map<String, Object>> getProjectIds(String areaIds) {
		String projectSQL = "select " + "IFNULL(GROUP_CONCAT(a.project_id SEPARATOR \"', '\"),'') as project_ids "
				+ "from " + "(select project_id from yjwy_worktask_area_project_nexus " + "where area_id in ('"
				+ areaIds + "') group by project_id) a";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> projectMapList = readJdbcTemplate.queryForList(projectSQL);

		return projectMapList;
	}

	/**
	 * 根据projectIds crop lt 获取工单列表
	 * 
	 * @param projectIds
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getWorkTaskList(String projectIds, String crop, String lt) {
		// 3、获取项目ids 查询 抢单池 中的 工单 IFNULL(service_type,'') service_type,
		String workTaskSQL = "select pk_details_id, datails_code, "
				+ "fk_project_id, IFNULL(service_type,'') service_type, "
				+ "IFNULL(repair_class_id,'') repair_class_id, repair_user, "
				+ "contact_number, repair_details, bookings_time, "
				+ "create_user_name, create_time, update_time, task_state " + "from yjwy_worktask_details "
				+ "where fk_project_id in ('" + projectIds + "') " + "and task_state = '"
				+ AppConstant.WorktaskState.NOTSEND + "' " + "and pk_crop = '" + crop + "' ";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskList = new ArrayList<>();
		if ("0".equals(lt)) {
			workTaskList = readJdbcTemplate.queryForList(workTaskSQL);
		} else {
			String updateSql = workTaskSQL + "and update_time >= '" + lt + "'";
			workTaskList = readJdbcTemplate.queryForList(updateSql);
			if (workTaskList.size() > 0) {
				workTaskList = readJdbcTemplate.queryForList(workTaskSQL);
			}
		}
		return workTaskList;
	}

	/**
	 * 根据userId、crop、lt 获取我的工单列表
	 * 
	 * @param userId
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getMyWorkTaskList(String userId, String crop, String lt) {

		// 跟进人是我 状态是 1 或者 2 的 就可以是我的工单列表中的任务
		String workTaskSQL = "select pk_details_id, datails_code, "
				+ "fk_project_id, IFNULL(service_type,'') service_type, "
				+ "IFNULL(repair_class_id,'') repair_class_id, repair_user, contact_number, "
				+ "repair_details, bookings_time, " + "create_user_name, create_time, " + "update_time, task_state "
				+ "from yjwy_worktask_details " + "where duty_user_id = '" + userId + "' " + "and task_state in ('"
				+ AppConstant.WorktaskState.PENDING + "','" + AppConstant.WorktaskState.REPAIRING + "','"
				+ AppConstant.WorktaskState.COMPLETE + "') " + " and pk_crop = '" + crop + "' "
				+ " and update_time >= '" + lt + "'";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskList = readJdbcTemplate.queryForList(workTaskSQL);

		return workTaskList;
	}

	/**
	 * 根据项目id 获取设备号列表 sql
	 * 
	 * @param projectId
	 * @return
	 */
	public static String getMobileIdsSql(String projectId) {
		String sql = "select n.android_mobile_ids, m.ios_mobile_ids " + "from "
				+ "(select IFNULL(GROUP_CONCAT(DISTINCT mobile_id SEPARATOR ','),'') AS ios_mobile_ids," + "'"
				+ projectId + "' AS ios_project_id " + "from yjwy_pub_user_mobile " + "where fk_user in "
				+ "(select ywaun.user_id from " + "yjwy_worktask_area_user_nexus ywaun "
				+ "left join yjwy_pub_user ypu " + "on ywaun.user_id = ypu.pk_user_ " + "where ywaun.area_id = "
				+ "(select area_id from yjwy_worktask_area_project_nexus " + "where project_id = '" + projectId + "') "
				+ "and ypu.is_sign_ = '1'" + ") and mobile_platform = 'IOS' " + ") m " + "left join "
				+ "(select IFNULL(GROUP_CONCAT(DISTINCT mobile_id SEPARATOR ','),'') AS android_mobile_ids," + "'"
				+ projectId + "' AS android_project_id " + "from yjwy_pub_user_mobile " + "where fk_user in "
				+ "(select ywaun.user_id from " + "yjwy_worktask_area_user_nexus ywaun "
				+ "left join yjwy_pub_user ypu " + "on ywaun.user_id = ypu.pk_user_ " + "where ywaun.area_id = "
				+ "(select area_id from yjwy_worktask_area_project_nexus " + "where project_id = '" + projectId + "') "
				+ "and ypu.is_sign_ = '1'" + ") and mobile_platform = 'Android' " + ") n "
				+ "ON m.ios_project_id = n.android_project_id";

		return sql;
	}

	/**
	 * 获取 我发出的工单
	 * 
	 * @param userId
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getMySendWorkTaskList(String userId, String crop, String lt) {

		String workTaskSql = "select pk_details_id, fk_project_id, IFNULL(service_type,'') service_type, "
				+ "repair_class_id, repair_details, bookings_time, create_time, " + "update_time, task_state "
				+ "from yjwy_worktask_details " + "where create_user_id = '" + userId + "' " + "and update_time >= '"
				+ lt + "' " + "and pk_crop ='" + crop + "'";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskList = readJdbcTemplate.queryForList(workTaskSql);

		return workTaskList;

	}

	/**
	 * 获取超时工单
	 * 
	 * @param userId
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getTimeOutWorkTaskList(String userId, String crop, String lt) {

		// 当前时间的前半个小时
		long currentTime = System.currentTimeMillis() - 30 * 60 * 1000;
		Date date = new Date(currentTime);
		String nowTime = AppConstant.df_YMDHMS.format(date);

		// 拼接sql
		String querySql = " select pk_details_id, datails_code, fk_project_id, IFNULL(service_type,'') service_type, repair_class_id, repair_user,"
				+ " contact_number, repair_details, bookings_time, create_user_name, create_time, update_time, task_state"
				+ " from yjwy_worktask_details "
				+ " where fk_project_id in (select project_id from yjwy_worktask_area_project_nexus"
				+ " where area_id in (select area_id from yjwy_worktask_area_user_nexus where user_id ='" + userId
				+ "' and user_type='1'))" + " and task_state = '1' and pk_crop = '" + crop + "' "
				+ " and update_time >= '" + lt + "' and dispatch_time <= '" + nowTime + "'";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskMap = readJdbcTemplate.queryForList(querySql);
		return workTaskMap;
	}

	/**
	 * 获取工单详情
	 * 
	 * @param worktaskDetailsId
	 * @return
	 */
	public List<Map<String, Object>> getWorkTaskDetailsList(String worktaskDetailsId) {
		// 获取工单详情
		String workTaskSql = "select ywd.pk_details_id, ywd.datails_code, "
				+ "ywd.fk_project_id, IFNULL(ywd.service_type,'') service_type, "
				+ "ywd.repair_class_id, IFNULL(ywd.repair_user,'') repair_user, "
				+ " IFNULL(ywd.repair_content,'') repair_content,"
				+ "IFNULL(ywd.contact_number,'') contact_number, ywd.repair_details, ywd.bookings_time, "
				+ "IFNULL(pu.user_name_,'') create_user_name, ywd.create_time, ywd.update_time, "
				+ "ywd.repair_user_id, ywd.task_state," + "ypp.project_name_ as project_name "
				+ "from yjwy_worktask_details ywd " + "left join yjwy_pub_project ypp "
				+ "on ywd.fk_project_id = ypp.pk_project_ "
				+ " left join yjwy_pub_user pu on ywd.create_user_id=pu.pk_user_" + " where ywd.pk_details_id ='"
				+ worktaskDetailsId + "'";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskMap = readJdbcTemplate.queryForList(workTaskSql);
		return workTaskMap;
	}

	/**
	 * 根据工单号 查询工单详情列表
	 * 
	 * @param worktaskDetailsId
	 * @return
	 */
	public List<Map<String, Object>> getWorkTaskRecordList(String worktaskDetailsId) {

		// 通过工单id 查询工单记录
		String workTaskRecordSql = "select ywdr.pk_record_id, " + "ywdr.operation_express, " + "ywdr.operation_time, "
				+ "ywdr.operation_user_id, " + "IFNULL(ywdr.operation_remarks,'') operation_remarks, "
				+ "ywdr.refuse_reason," + "IFNULL(ypu.user_name_,'') operation_user "
				+ "from yjwy_worktask_details_record ywdr " + "left join yjwy_pub_user ypu "
				+ "on ywdr.operation_user_id = ypu.pk_user_ " + "where ywdr.fk_details_id ='" + worktaskDetailsId + "'";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> workTaskRecordMap = readJdbcTemplate.queryForList(workTaskRecordSql);
		return workTaskRecordMap;

	}

	/**
	 * 更新我的巡检维保任务的处理情况字段
	 * 
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskDealt(String taskId, String userId) {
		YJWYTaskModel task = patrolTaskService.queryById(taskId);
		if (null != task) {
			task.setTask_dealt(0);// 任务处理情况设置为0，派单；
			task.setUpdate_time(new Date().getTime() + "");
			task.setUpdate_user(userId);
			patrolTaskService.update(task);
		}
	}
}
