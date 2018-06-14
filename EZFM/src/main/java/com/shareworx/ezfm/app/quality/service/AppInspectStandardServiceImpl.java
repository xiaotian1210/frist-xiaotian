package com.shareworx.ezfm.app.quality.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.push.service.AppPushService;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService;
import com.shareworx.ezfm.timer.service.QualityTaskService;
/**
 * 标准实现类
 * @author lingwei.li
 *
 */
@Service(AppInspectStandardService.ID)
public class AppInspectStandardServiceImpl implements AppInspectStandardService{

	final static Logger logger = Logger.getLogger(AppInspectStandardServiceImpl.class);

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
	@Qualifier(InspectStandardDomainService.ID)
	private InspectStandardDomainService inspectStandardService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	
	@Autowired
	@Qualifier(ProblemTypeDomainService.ID)
	private ProblemTypeDomainService problemTypeService;
	
	@Autowired
	@Qualifier(QualityTaskService.ID)
	private QualityTaskService qualityTaskService;
	
	@Autowired
	@Qualifier(AppPushService.ID)
	private AppPushService appPushService;
	
	/**
	 * 获取标准列表
	 */
	@Override
	public JSONObject getAllStandard(HttpServletRequest reqParam) throws Exception {
		
		//页面上面传过来的参数
		String lastTime = reqParam.getParameter("lt");//获取时间
		String userId = reqParam.getParameter("userId");//用户id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		
		if (AppEmptyUtils.isEmpty(lastTime)
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		List<Map<String,Object>> inspectStandardList = getStandardList(userId);
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(inspectStandardList);
		
	}

	/**
	 * 保存 标准完成
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveStandardFinish(HttpServletRequest reqParam) throws Exception {
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");// N
		String userId = reqParam.getParameter("userId"); //核查人id N
		String inspstandardId = reqParam.getParameter("inspstandardId");//核查标准id N
		String projectId = reqParam.getParameter("projectId");//项目id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult");//核查结论
		String remarks = reqParam.getParameter("remarks");//备注
		String lng =  reqParam.getParameter("lng");//经度 N
		String lat =  reqParam.getParameter("lat");//纬度 N
		String inspectlocalename =  reqParam.getParameter("inspectlocalename");//核查地点名称 
		String inspectLocaleId =  reqParam.getParameter("inspectLocaleId");//核查地点
		String isSign = reqParam.getParameter("isSign");//是否签到 N
		String crop = reqParam.getParameter("crop");//是否签到 N
		
		Date currentDate = new Date();
		
		//判断必填参数
		if (AppEmptyUtils.isEmpty(userId)
				|| AppEmptyUtils.isEmpty(inspstandardId) 
				|| AppEmptyUtils.isEmpty(projectId) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(isSign) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//检查项目id
		YJWYProjectModel projectModel =projectService.queryById(projectId);
		if (AppEmptyUtils.isEmpty(projectModel)) {
			String msg="不存在该项目，请同步项目数据后再操作！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//判断标准是否存在
		InspectStandardModel standard = inspectStandardService.queryById(inspstandardId);
		if (AppEmptyUtils.isEmpty(standard)) {
			String msg="不存在该核查标准，请同步标准列表后再试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//添加任务表
		InspectTaskModel inspectTaskModel = new InspectTaskModel();
		inspectTaskModel.setFk_project(projectId);
		inspectTaskModel.setTask_code(qualityTaskService.taskCodeGenerator());
		inspectTaskModel.setSpecialty(standard.getSpecialty());
		inspectTaskModel.setFk_standard(inspstandardId);
		inspectTaskModel.setTask_currentuser_pk(userId);
		inspectTaskModel.setFk_taskuser(userId);
		inspectTaskModel.setTask_inspectresult(recordInspectResult);
		inspectTaskModel.setTask_state(AppConstant.InspectTaskState.COMPLETE);
		inspectTaskModel.setTask_generate_mode(AppConstant.CreateTaskBy.MOBILE);
		inspectTaskModel.setTask_start_time(AppConstant.df_YMD.format(currentDate));//年月日
		inspectTaskModel.setTask_end_time(AppConstant.df_YMDHMS.format(currentDate));
		inspectTaskModel.setRecord_finish_lat(lat);
		inspectTaskModel.setRecord_finish_lon(lng);
		inspectTaskModel.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));
		inspectTaskModel.setCreate_user(userId);
		inspectTaskModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		inspectTaskModel.setUpdate_user(userId);
		inspectTaskModel.setPk_crop(crop);
		inspectTaskModel.setIs_sign(isSign);
		inspectTaskModel.setIs_rectify("0");
		
		List<InspectTaskModel> taskList = inspectTaskService.save(new InspectTaskModel[]{inspectTaskModel});
		if(AppEmptyUtils.isEmpty(taskList)){
			String msg="任务插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//添加 任务记录到 任务记录表中
		InspectRecordModel inspectRecordModel = new InspectRecordModel();
		inspectRecordModel.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));
		inspectRecordModel.setCreate_user(userId);
		inspectRecordModel.setRecord_sample_desc(remarks);
		inspectRecordModel.setRecord_inspect_result(recordInspectResult);
		inspectRecordModel.setRecord_status(AppConstant.InspectRecordState.COMPLETE);
		inspectRecordModel.setFk_task(taskList.get(0).getPk_task());
		inspectRecordModel.setTask_phonetype(mobilePlatform);
		inspectRecordModel.setPk_crop(crop);
		inspectRecordModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		inspectRecordModel.setUpdate_user(userId);
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{inspectRecordModel});//saveModels(new InspectTaskModel[]{inspectTaskModel});
		if(AppEmptyUtils.isEmpty(recordList)){
			String msg="任务记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		JSONObject data = new JSONObject();
		data.put("pk_record", recordList.get(0).getPk_record());
		
		return AppJsonMessage.toJsonMsgNoSt(0,"标准完成成功！",data);
		
	}

	/**
	 * 保存 标准整改
	 */
	@SuppressWarnings("unused")
	@Override
	public JSONObject saveStandardRectification(HttpServletRequest reqParam) throws Exception {
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");// N
		String userId = reqParam.getParameter("userId"); //核查人id N
		String inspstandardId = reqParam.getParameter("inspstandardId");//核查标准id N
		String projectId = reqParam.getParameter("projectId");//项目id N
		String recordInspectResult = reqParam.getParameter("recordInspectResult");//核查结论
		String remarks = reqParam.getParameter("remarks");//备注
		String lng =  reqParam.getParameter("lng");//经度 N
		String lat =  reqParam.getParameter("lat");//纬度 N
		String inspectlocalename =  reqParam.getParameter("inspectlocalename");//核查地点名称 
		String inspectLocaleId =  reqParam.getParameter("inspectLocaleId");//核查地点
		String proId = reqParam.getParameter("proId");//问题分类id
		String followUserId = reqParam.getParameter("followUserId");//跟进人
		String deductionScore = reqParam.getParameter("deductionScore");//扣罚分值
		String isSign = reqParam.getParameter("isSign");//是否签到 N
		String crop = reqParam.getParameter("crop");//是否签到 N  
		String taskRectifyDeadlineTime = reqParam.getParameter("taskRectifyDeadlineTime");//任务整改截止日期
		
		Date currentDate = new Date();
		
		//判断必填参数
		if (AppEmptyUtils.isEmpty(userId)
				|| AppEmptyUtils.isEmpty(inspstandardId) 
				|| AppEmptyUtils.isEmpty(projectId) 
				|| AppEmptyUtils.isEmpty(recordInspectResult) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat) 
				|| AppEmptyUtils.isEmpty(followUserId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(isSign) 
				|| AppEmptyUtils.isEmpty(proId) 
				|| AppEmptyUtils.isEmpty(crop) 
				|| AppEmptyUtils.isEmpty(taskRectifyDeadlineTime)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//判断项目id 是否存在
		YJWYProjectModel project = projectService.queryById(projectId);
		if (AppEmptyUtils.isEmpty(project)) {
			String msg="不存在该项目ID！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//判断标准是否存在
		InspectStandardModel standard = inspectStandardService.queryById(inspstandardId);
		if (AppEmptyUtils.isEmpty(standard)) {
			String msg="不存在该核查标准，请同步标准列表后再试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//判断问题类型是否存在
		ProblemTypeModel problemType = problemTypeService.queryById(proId);
		if(AppEmptyUtils.isEmpty(problemType)){
			String msg="不存在该问题类型，请同步问题类型数据后重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//添加任务表
		InspectTaskModel inspectTaskModel = new InspectTaskModel();
		inspectTaskModel.setFk_project(projectId);
		inspectTaskModel.setSpecialty(standard.getSpecialty());
		inspectTaskModel.setTask_code(qualityTaskService.taskCodeGenerator());
		inspectTaskModel.setFk_standard(inspstandardId);
		inspectTaskModel.setTask_currentuser_pk(followUserId);
		inspectTaskModel.setTask_rectifyuser_pk(followUserId);//保存整改人
		inspectTaskModel.setFk_taskuser(userId);
		inspectTaskModel.setTask_inspectresult(recordInspectResult);
		inspectTaskModel.setTask_state(AppConstant.InspectTaskState.DZG);
		inspectTaskModel.setTask_generate_mode(AppConstant.CreateTaskBy.MOBILE);
		inspectTaskModel.setTask_start_time(AppConstant.df_YMD.format(currentDate));
		inspectTaskModel.setRecord_finish_lat(lat);
		inspectTaskModel.setRecord_finish_lon(lng);
		inspectTaskModel.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));
		inspectTaskModel.setCreate_user(userId);
		inspectTaskModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		inspectTaskModel.setUpdate_user(userId);
		inspectTaskModel.setPk_crop(crop);
		inspectTaskModel.setIs_sign(isSign);
		inspectTaskModel.setTask_rectify_deadline_time(taskRectifyDeadlineTime);
		inspectTaskModel.setIs_rectify("1");//是否整改，1是，0否
		
		List<InspectTaskModel> taskList = inspectTaskService.save(new InspectTaskModel[]{inspectTaskModel});//saveModels(new InspectTaskModel[]{inspectTaskModel});
		
		if(taskList.size()<=0){
			String msg="任务插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		
		//添加 任务记录到 任务记录表中
		InspectRecordModel inspectRecordModel = new InspectRecordModel();
		inspectRecordModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
		inspectRecordModel.setCreate_user(userId);
		//检阅时间有后台自己处理 不有手机APP接口生成inspectRecordModel.setRecord_check_time(AppConstant.df_YMDHMS.format(new Date()));
		inspectRecordModel.setRecord_sample_desc(remarks);
		inspectRecordModel.setRecord_inspect_result(recordInspectResult);
		inspectRecordModel.setRecord_status(AppConstant.InspectRecordState.PFZG);
		inspectRecordModel.setFk_task(taskList.get(0).getPk_task());
		inspectRecordModel.setTask_phonetype(mobilePlatform);
		inspectRecordModel.setFollow_user_pk(followUserId);
		inspectRecordModel.setFk_problem_id(proId);
		inspectRecordModel.setPk_crop(crop);
		inspectRecordModel.setUpdate_time(String.valueOf(new Date().getTime()));
		inspectRecordModel.setUpdate_user(userId);
		List<InspectRecordModel> recordList = inspectRecordService.save(new InspectRecordModel[]{inspectRecordModel});
		if(recordList.size()<=0){
			String msg="任务记录插入失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg); 
		}
		JSONObject data = new JSONObject();
		data.put("pk_record", recordList.get(0).getPk_record());
		
		//标准整改 推送
		appPushService.sendInspectTaskPush(inspectTaskModel, "1");
		
		
		return AppJsonMessage.toJsonMsgNoSt(0,"标准整改成功！",data);
		
	}
	
	/**
	 * 根据userid 获取标准列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getStandardList(String userId){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String sql = "select "
				+ "yqis.pk_inspstan,"
				+ "yqis.inspstan_code,"
				+ "yqis.specialty,"
				+ "yqis.inspstan_scorevalue,"
				+ "yqis.inspstan_category,"
				+ "yqis.inspstan_category_description,"
				+ "yqis.inspstan_performance_norm,"
				+ "yqis.inspstan_inpectmethod,"
				+ "yqis.project_category,"
				+ "yqis.inspstan_usingscope,"
				+ "yqis.inspstan_secretinquiries,"
				+ "yqis.inspstan_dkzg_pc,"
				+ "yqis.inspstan_bmjl_pc,"
				+ "yqis.inspstan_xmjl_pc,"
				+ "yqis.update_time,"
				+ "yqis.pk_crop"
				+ " from yjwy_quality_inspectstandard yqis"
				+ " LEFT JOIN yjwy_quality_standard_station qss ON yqis.pk_inspstan=qss.pk_standard"
				+ " WHERE qss.pk_station IN"
				+ " (select pk_station_ from yjwy_pub_user_station WHERE pk_user_='"+userId+"')";
		
		List<Map<String,Object>> inspectStandardList = readJdbcTemplate.queryForList(sql);
		
		return inspectStandardList;
		
	}

}
