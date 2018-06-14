package com.shareworx.ezfm.app.quality.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ModelUtils;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.push.service.AppPushService;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService;
/**
 * 任务 接口实现
 * @author lingwei.li
 *
 */
@Service(AppInspectTaskService.ID)
public class AppInspectTaskServiceImpl implements AppInspectTaskService{

	final static Logger logger = Logger.getLogger(AppInspectTaskServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(InspectTaskDomainService.ID)
	private InspectTaskDomainService inspectTaskService;
	
	@Autowired
	@Qualifier(InspectRecordDomainService.ID)
	private InspectRecordDomainService inspectRecordService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	@Autowired
	@Qualifier(ProblemTypeDomainService.ID)
	private ProblemTypeDomainService problemTypeService;
	
	@Autowired
	@Qualifier(AppPushService.ID)
	private AppPushService appPushService;
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;



	
	/**
	 * 获取所有的任务列表(包括：待办任务、待整改、已办任务、问题跟踪)
	 */
	@Override
	public JSONObject getAllTask(HttpServletRequest reqParam) throws Exception {
		logger.info("InspectTaskService/getAllTask");
		//页面上传过来的参数
		String userId = reqParam.getParameter("userId");//获取用户id
		String lastTime = reqParam.getParameter("lt");//获取时间
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		
		//判断必填字段
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(lastTime)
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		//获取任务列表
		List<Map<String,Object>> inspectTaskList = getTaskList(userId, lastTime, crop);
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(inspectTaskList);
	}

	/**
	 * 根据任务id获取任务详情+历史记录
	 */
	@Override
	public JSONObject getInspectTaskDetail(HttpServletRequest reqParam ) throws Exception {
		logger.info("service: InspectTaskService/getInspectTaskDetail");
		//页面上传过来的参数
		String userId = reqParam.getParameter("userId");//获取用户id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String inspectTaskId = reqParam.getParameter("inspectTaskId");
		
		//判断必填字段
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(inspectTaskId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//获取任务
		List<Map<String,Object>> inspectTaskList = getTaskDetails(inspectTaskId);
		if(AppEmptyUtils.isEmpty(inspectTaskList)){
			String msg="不存在该核查任务！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//获取任务记录
		List<Map<String,Object>> recordList = getTaskRecordList(inspectTaskId);
		inspectTaskList.get(0).put("inspectrecords", recordList);
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(inspectTaskList.get(0));
		
		
	}
	
	/**
	 * 保存要 完成的任務,同时新增任务记录表
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveTaskFinish(HttpServletRequest reqParam) throws Exception {
		logger.info("InspectTaskService/saveTaskFinish");
		//页面传过来的参数
		String userId = reqParam.getParameter("userId"); //核查人id N
		String inspectTaskId = reqParam.getParameter("inspectTaskId"); //核查任务id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult"); //核查结论 N
		String remarks = reqParam.getParameter("remarks"); //备注
		String lng = reqParam.getParameter("lng"); //经度 N
		String lat = reqParam.getParameter("lat"); //纬度 N
		String inspectLocaleName = reqParam.getParameter("inspectLocaleName"); //核查地点名称
		String inspectLocaleId = reqParam.getParameter("inspectLocaleId"); //核查地点id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String isSign = reqParam.getParameter("isSign");
		
		Date currentDate = new Date();
		
		//先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(inspectTaskId) 
				|| AppEmptyUtils.isEmpty(recordInspectResult) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(isSign)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		
		//核查人
		YJWYUserModel taskUser = userService.queryById(userId);
		
		//检查任务id
		InspectTaskModel task = inspectTaskService.queryById(inspectTaskId);//根据id获取到当前任务
		if(AppEmptyUtils.isEmpty(task)){
			String msg = "不存在该任务！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!AppConstant.InspectTaskState.DB.equals(task.getTask_state())){
			String msg = "该条任务非待办任务，请刷新任务列表后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!userId.equals(task.getTask_currentuser_pk())){
			String msg="该任务的当前任务人不是此用户:"+taskUser.getUser_name();
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//修改任务状态
		task.setRecord_finish_lat(lat);//经度
		task.setRecord_finish_lon(lng);//纬度
		task.setUpdate_user(userId);//更新人
		task.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		task.setTask_state(AppConstant.InspectTaskState.COMPLETE);//任务状态
		task.setTask_end_time(AppConstant.df_YMDHMS.format(currentDate));//任务结束时间
		task.setIs_sign(isSign);
		
		List<InspectTaskModel> taskResults = inspectTaskService.update(new InspectTaskModel[]{task});
		
		if(AppEmptyUtils.isEmpty(taskResults)){
			String msg="任务更新失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//核查任务修改成功后，再保存记录
		InspectRecordModel record = new InspectRecordModel();
		record.setRecord_sample_desc(remarks);//描述
		record.setRecord_inspect_result(recordInspectResult);//核查结论
		record.setTask_phonetype(mobilePlatform);//移动平台来源
		record.setCreate_user(userId);//创建人即检查人
		record.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));//创建时间
		record.setUpdate_user(userId);//更新人
		record.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		record.setFk_task(inspectTaskId);//任务id外键
		record.setRecord_status(AppConstant.InspectRecordState.COMPLETE);//记录状态
		record.setPk_crop(taskUser.getPk_crop());
		
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{record});
		
		if(AppEmptyUtils.isEmpty(recordList)){
			String msg="记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		record.setPk_record(recordList.get(0).getPk_record());
		return AppJsonMessage.toJsonMsgTrue(record);
	}

	/**
	 * 保存要 整改的任務,同时新增任务记录表
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveTaskRectification(HttpServletRequest reqParam) throws Exception {
		logger.info("service: InspectTaskService/saveTaskRectification");
		//页面上传过来的参数
		String userId = reqParam.getParameter("userId"); //核查人id N
		String inspectTaskId = reqParam.getParameter("inspectTaskId"); //核查任务id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult"); //核查结论 N
		String remarks = reqParam.getParameter("remarks"); //备注
		String lng = reqParam.getParameter("lng"); //经度 N
		String lat = reqParam.getParameter("lat"); //纬度 N
		String inspectLocaleName = reqParam.getParameter("inspectLocaleName"); //核查地点名称
		String inspectLocaleId = reqParam.getParameter("inspectLocaleId"); //核查地点id
		String proId = reqParam.getParameter("proId"); //问题分类id N
		String followUserId = reqParam.getParameter("followUserId"); //跟进人id N
		String deductionScore = reqParam.getParameter("deductionScore"); //扣罚分值
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String isSign = reqParam.getParameter("isSign");
		String taskRectifyDeadlineTime = reqParam.getParameter("taskRectifyDeadlineTime"); //任务整改截止日期
		
		Date currentDate = new Date();
		
		//先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(inspectTaskId) 
				|| AppEmptyUtils.isEmpty(recordInspectResult) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat) 
				|| AppEmptyUtils.isEmpty(proId) 
				|| AppEmptyUtils.isEmpty(followUserId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(taskRectifyDeadlineTime)
				|| AppEmptyUtils.isEmpty(isSign)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		
		//检查核查人
		YJWYUserModel taskUser = userService.queryById(userId);
		
		//检查跟进人
		YJWYUserModel followUser = userService.queryById(followUserId);
		if(AppEmptyUtils.isEmpty(followUser)){
			String msg = "不存在该跟进人！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if (!followUser.getIs_able()) {
			String msg = "该跟进人没有被启用！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		
		//检查任务id
		InspectTaskModel task = inspectTaskService.queryById(inspectTaskId);//根据id获取到当前任务
		if(AppEmptyUtils.isEmpty(task)){
			String msg = "不存在该任务！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!AppConstant.InspectTaskState.DB.equals(task.getTask_state())){
			String msg = "该条任务非待办任务，请刷新任务列表后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!userId.equals(task.getTask_currentuser_pk())){
			String msg="该任务的当前任务人不是此用户:"+taskUser.getUser_name();
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		//判断问题类型是否存在
		ProblemTypeModel problemType = problemTypeService.queryById(proId);
		if(AppEmptyUtils.isEmpty(problemType)){
			String msg="不存在该问题类型，请同步问题类型数据后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//设置需要修改的值
		task.setRecord_finish_lat(lat);//经度
		task.setRecord_finish_lon(lng);//纬度
		task.setUpdate_user(userId);//更新人
		task.setUpdate_time(new Date().getTime()+"");//更新时间
		task.setTask_state(AppConstant.InspectTaskState.DZG);//任务状态 2整改
		task.setTask_rectifyuser_pk(followUserId);//整改人id
		task.setTask_currentuser_pk(followUserId);//任务当前人id
		task.setTask_subtract_score(new BigDecimal(deductionScore));//扣罚分值
		task.setTask_rectify_starttime(AppConstant.df_YMDHMS.format(currentDate));//整改开始时间
		task.setTask_rectify_deadline_time(taskRectifyDeadlineTime);
		task.setIs_sign(isSign);
		task.setIs_rectify("1");//是否整改，1是，0否
		
		List<InspectTaskModel> taskResultList = inspectTaskService.update(new InspectTaskModel[]{task});
		if(AppEmptyUtils.isEmpty(taskResultList)){
			String msg="任务更新失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//任务更新成功后，插入一条历史记录
		InspectRecordModel record = new InspectRecordModel();
		record.setRecord_sample_desc(remarks);//描述
		record.setRecord_inspect_result(recordInspectResult);//核查结论
		record.setTask_phonetype(mobilePlatform);//移动平台来源
		record.setCreate_user(userId);//创建人即检查人
		record.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));//创建时间
		record.setUpdate_user(userId);//更新人
		record.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		record.setFk_task(inspectTaskId);//任务id外键
		record.setRecord_status(AppConstant.InspectRecordState.PFZG);//记录状态
		record.setFollow_user_pk(followUserId);//跟进人id
		record.setPk_crop(taskUser.getPk_crop());
		record.setFk_problem_id(proId);//问题分类id
		
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{record});
		if(AppEmptyUtils.isEmpty(recordList)){
			String msg="记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		record.setPk_record(recordList.get(0).getPk_record());
		
		//整改任務（待整改）  推送
		appPushService.sendInspectTaskPush(task, "1");
		
		return AppJsonMessage.toJsonMsgTrue(record);
		
	}

	/**
	 * 保存要 整改完成的任務,同时新增任务记录表
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveTaskRecFinish(HttpServletRequest reqParam) throws Exception {
		logger.info("service: InspectTaskService/saveTaskRecFinish");
		//页面上传过来的参数
		String userId = reqParam.getParameter("userId"); //整改人id N
		String inspectTaskId = reqParam.getParameter("inspectTaskId"); //核查任务id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult"); //核查结论 N
		String remarks = reqParam.getParameter("remarks"); //备注
		String inspectLocaleName = reqParam.getParameter("inspectLocaleName"); //核查地点名称
		String inspectLocaleId = reqParam.getParameter("inspectLocaleId"); //核查地点id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		
		Date currentDate = new Date();
		
		//先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(inspectTaskId) 
				|| AppEmptyUtils.isEmpty(recordInspectResult) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		
		//检查整改人
		YJWYUserModel taskUser = userService.queryById(userId);
		
		//检查任务id
		InspectTaskModel task = inspectTaskService.queryById(inspectTaskId);//根据id获取到当前任务
		if(AppEmptyUtils.isEmpty(task)){
			String msg = "不存在该任务！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!AppConstant.InspectTaskState.DZG.equals(task.getTask_state())){
			String msg = "该条任务非待整改任务，请刷新任务列表后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
		}
		if(!userId.equals(task.getTask_currentuser_pk())){
			String msg="该任务的当前任务人不是此用户:"+taskUser.getUser_name();
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//设置需要修改的字段
		task.setUpdate_user(userId);//更新人
		task.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		task.setTask_currentuser_pk(task.getFk_taskuser());//任务当前人id
		task.setTask_rectify_finishtime(AppConstant.df_YMDHMS.format(currentDate));//整改完成时间
		
		List<InspectTaskModel> taskResults = inspectTaskService.update(new InspectTaskModel[]{task});
		if(AppEmptyUtils.isEmpty(taskResults)){
			String msg="任务更新失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		//更新任务成功后，插入历史记录
		InspectRecordModel record = new InspectRecordModel();
		record.setRecord_sample_desc(remarks);//描述
		record.setRecord_inspect_result(recordInspectResult);//核查结论
		record.setTask_phonetype(mobilePlatform);//移动平台来源
		record.setCreate_user(userId);//创建人即检查人
		record.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));//创建时间
		record.setUpdate_user(userId);//更新人
		record.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		record.setFk_task(inspectTaskId);//任务id外键
		record.setRecord_status(AppConstant.InspectRecordState.ZGWC);//记录状态,2整改完成
		record.setFollow_user_pk(task.getFk_taskuser());//跟进人id
		record.setPk_crop(taskUser.getPk_crop());
		
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{record});
		if(AppEmptyUtils.isEmpty(recordList)){
			String msg="记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		record.setPk_record(recordList.get(0).getPk_record());
		
		//整改完成的任務（整改确认）  推送
		appPushService.sendInspectTaskPush(task, "2");
		
		return AppJsonMessage.toJsonMsgTrue(record);
	}

	/**
	 * 保存要 整改确认的任務,同时新增任务记录表
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveTaskRecConfirm(HttpServletRequest reqParam) throws Exception {
		logger.info("service: InspectTaskService/saveTaskRecConfirm");
		//页面上传过来的参数
		String userId = reqParam.getParameter("userId"); //核查人id N
		String inspectTaskId = reqParam.getParameter("inspectTaskId"); //核查任务id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult"); //核查结论 N
		String remarks = reqParam.getParameter("remarks"); //备注
		String inspectLocaleName = reqParam.getParameter("inspectLocaleName"); //核查地点名称
		String inspectLocaleId = reqParam.getParameter("inspectLocaleId"); //核查地点id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		
		Date currentDate = new Date();
		
		//先判断必填参数
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(inspectTaskId) 
				|| AppEmptyUtils.isEmpty(recordInspectResult) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,"参数有误");
		}
		//检查核查人
		YJWYUserModel taskUser = userService.queryById(userId);
		
		//检查任务id
		InspectTaskModel task = inspectTaskService.queryById(inspectTaskId);//根据id获取到当前任务
		if(AppEmptyUtils.isEmpty(task)){
			String msg = "不存在该任务！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		if(!AppConstant.InspectTaskState.DZG.equals(task.getTask_state())){
			String msg = "该条任务非待整改任务，请刷新任务列表后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		if(!userId.equals(task.getTask_currentuser_pk())){
			String msg="该任务的当前任务人不是此用户:"+taskUser.getUser_name();
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//设置需要修改的值
		task.setUpdate_user(userId);//更新人
		task.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		task.setTask_state(AppConstant.InspectTaskState.COMPLETE);//任务状态 1完成
		task.setTask_end_time(AppConstant.df_YMDHMS.format(currentDate));//任务完成
		//判断整改是否超期
		Date deadlineTime = AppConstant.df_YMD.parse(task.getTask_rectify_deadline_time());
		if (deadlineTime.getTime() <= currentDate.getTime()) {
			task.setTask_iscq("0");//0:否，不超期
		}else{
			task.setTask_iscq("1");//1:是，超期
		}
		
		List<InspectTaskModel> taskResults = inspectTaskService.update(new InspectTaskModel[]{task});
		if(AppEmptyUtils.isEmpty(taskResults)){
			String msg="任务更新失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		//更新任务成功后，插入历史记录
		InspectRecordModel record = new InspectRecordModel();
		record.setRecord_sample_desc(remarks);//描述
		record.setRecord_inspect_result(recordInspectResult);//核查结论
		record.setTask_phonetype(mobilePlatform);//移动平台来源
		record.setCreate_user(userId);//创建人即检查人
		record.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));//创建时间
		record.setUpdate_user(userId);//更新人
		record.setUpdate_time(String.valueOf(currentDate.getTime()));//更新时间
		record.setFk_task(inspectTaskId);//任务id外键
		record.setRecord_status(AppConstant.InspectRecordState.ZGQR);//记录状态,2整改确认
		record.setPk_crop(taskUser.getPk_crop());
		
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{record});
		if(AppEmptyUtils.isEmpty(recordList)){
			String msg="记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		record.setPk_record(recordList.get(0).getPk_record());
		return AppJsonMessage.toJsonMsgTrue(record);
	}
	
	
	/**
	 * 根据 userid、lastTime、crop 获取任务列表
	 * @param userId
	 * @param lastTime
	 * @param crop
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getTaskList(String userId, String lastTime, String crop) throws Exception {
		logger.info("InspectTaskService/getAllTaskSQL");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select it.pk_task,"
				+ "IFNULL(it.task_deadline_date,'') task_deadline_date, it.task_rectifyuser_pk, "
				+ "user1.user_name_ as task_rectifyuser_name, "
				+ "it.task_currentuser_pk, "
				+ "user2.user_name_ as task_currentuser_name, "
				+ "it.task_score, it.task_state, it.task_subtract_score,"
				+ "it.fk_taskuser, it.fk_project,it.task_start_time,it.task_rectify_starttime,"
				+ "ist.inspstan_code, ist.specialty, ist.inspstan_category,ist.inspstan_category_description, "
				+" project.project_name_ project_name, it.update_time, "
				+ "IFNULL(it.task_pc_name,'') task_pc_name, it.task_rectify_deadline_time "
				+" from yjwy_quality_inspecttask it "
				+" LEFT JOIN yjwy_quality_inspectstandard ist "
				+" ON it.fk_standard = ist.pk_inspstan"
				+" left join yjwy_pub_project project"
				+" on it.fk_project=project.pk_project_"
				+" left join yjwy_pub_user user1 on user1.pk_user_=it.task_rectifyuser_pk"
				+" left join yjwy_pub_user user2 on user2.pk_user_=it.task_currentuser_pk"
				+" where 1=1"
				+" and it.update_time >= '"+lastTime+"'"
				+" and it.pk_crop = '"+crop+"'"
//				+" and it.task_state in ('10','20','30')"
				+" and it.fk_taskuser= '"+userId+"' "
				+" OR it.task_rectifyuser_pk='"+userId+"'"
				+" and it.update_time >='"+lastTime+"'"
				);
		
		List<Map<String,Object>> inspectTaskList = readJdbcTemplate.queryForList(sql.toString());
		
		return inspectTaskList;
	}
	
	/**
	 * 根据任务id 获取任务详情
	 * @param inspectTaskId
	 * @return
	 */
	public List<Map<String,Object>> getTaskDetails(String inspectTaskId){
		
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//核查任务详情sql
		StringBuffer taskSQL = new StringBuffer();
		taskSQL.append("SELECT it.pk_task, it.fk_project, "
				+ "it.fk_taskuser, it.task_currentuser_pk, "
				+ "it.task_rectifyuser_pk, user1.user_name_ as task_rectifyuser_name, "
				+ "it.task_start_time, it.task_end_time, it.task_score, it.task_subtract_score,"
				+ "IFNULL(it.task_deadline_date,'') task_deadline_date, it.task_iscq, it.task_state, "
				+ "it.update_time,IFNULL(it.task_pc_name,'') task_pc_name, it.task_rectify_deadline_time,it.task_rectify_starttime,"
				+ "user.user_name_ as task_currentuser_name, "
				+ "ist.inspstan_code, ist.specialty, ist.inspstan_category,ist.inspstan_category_description,"
				+ "ist.inspstan_performance_norm,ist.inspstan_inpectmethod,"
				+ "project.project_name_ project_name "
				+ "FROM yjwy_quality_inspecttask it "
				+ "left join yjwy_quality_inspectstandard ist "
				+ "on it.fk_standard = ist.pk_inspstan "
				+ "left join yjwy_pub_project project "
				+ "on project.pk_project_ = it.fk_project "
				+ "left join yjwy_pub_user user "
				+ "on user.pk_user_ = it.task_currentuser_pk "
				+ " left join yjwy_pub_user user1 on user1.pk_user_=it.task_rectifyuser_pk"
				+ " where it.pk_task = '"+inspectTaskId+"' ");
		//核查任务
		List<Map<String,Object>> inspectTaskList = readJdbcTemplate.queryForList(taskSQL.toString());
		
		return inspectTaskList;
	}
	
	/**
	 * 根据任务id 获取任务记录列表
	 * @param inspectTaskId
	 * @return
	 */
	public List<Map<String, Object>> getTaskRecordList(String inspectTaskId){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//核查任务的历史记录sql
		String recordSQL=" select ir.pk_record, ir.create_time, "
				+" ir.record_sample_desc, ir.record_inspect_result, "
				+" ir.update_time, ir.record_status,"
				+" pu1.user_name_ create_user, pu2.user_name_ follow_user"
				+" from yjwy_quality_inspectrecord ir "
				+" left join yjwy_pub_user pu1 on ir.create_user=pu1.pk_user_ "
				+" left join yjwy_pub_user pu2 on ir.follow_user_pk=pu2.pk_user_ "
				+" where ir.fk_task='"+inspectTaskId+"'";
		
		List<Map<String,Object>> recordList = readJdbcTemplate.queryForList(recordSQL);
		if(recordList.size()>0){
			Set<Object> recordIds = new HashSet<>();
			for (int i = 0; i < recordList.size(); i++) {
				recordIds.add(recordList.get(i).get("pk_record"));
			}
			
			Query query = Query.from(QualityFileModel.META_ID);
			query.where(new Condition("record_id", QueryContents.TYPE_IN,recordIds));
			List<QualityFileModel> fileList = service.queryListByCondition(query);
			if(fileList.size()>0){
				Map<String,Object> map = null;
				StringBuilder stringBuilder = null;
				for (int j = 0; j < recordList.size(); j++) {
					map=recordList.get(j);
					stringBuilder = new StringBuilder();
					for (int i = 0; i < fileList.size(); i++) {
							if(map.get("pk_record").equals(fileList.get(i).get("record_id"))){
								stringBuilder.append(fileList.get(i).get("file_path")+",");
							}
					}
					if(stringBuilder.length()>0){
						map.put("record_file_urls", stringBuilder.toString().substring(0, stringBuilder.length()-1));
					}else{
						map.put("record_file_urls","");
					}
				}
			}
			
		}
		return recordList;
		
	}
	
}
