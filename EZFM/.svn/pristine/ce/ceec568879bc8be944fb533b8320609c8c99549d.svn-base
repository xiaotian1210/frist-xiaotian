package com.shareworx.ezfm.app.problem.service;

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
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpClient;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpService;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 报事处理 实现类
 * @author lingwei.li
 *
 */
@Service(AppProblemService.ID)
public class AppProblemServiceImpl implements AppProblemService{

	final static Logger logger = Logger.getLogger(AppProblemServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService problemDetailsService;
	
	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService problemRecordService;
	
	@Autowired
	@Qualifier(cloudHttpService.ID)
	private cloudHttpService cloudhttpservice;  
	
	public void setCloudhttpservice(cloudHttpService cloudhttpservice) {
		this.cloudhttpservice = cloudhttpservice;
	}

	/**
	 * 通过用户id（跟进人）获取报事列表
	 */
	@Override
	public JSONObject getAllProblemByUserId(HttpServletRequest reqParam) throws Exception {
		logger.info("appProblem/getAllProblemByUserId");
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
		
		List<Map<String, Object>> problemListMap = getProblemList(userId, crop, lt);
		
		logger.info("result:success");
		
		return AppJsonMessage.toJsonMsgTrue(problemListMap);
	}

	/**
	 * 根据报事id 查询报事详情
	 */
	@Override
	public JSONObject getProblemDetailById(HttpServletRequest reqParam) throws Exception {
		logger.info("appProblem/getProblemDetailById");
		String problemDetailsId = reqParam.getParameter("problemDetailsId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userId = reqParam.getParameter("userId");
		
		if (AppEmptyUtils.isEmpty(problemDetailsId)
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		List<Map<String, Object>> problemDetailMap = getProblemDetails(problemDetailsId);
		if (AppEmptyUtils.isEmpty(problemDetailMap)) {
			logger.info("result:error parameter");
			String msg = "该报事不存在，请刷新后再尝试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(problemDetailMap.get(0));
	}

	/**
	 * 完成报事
	 */
	@Override
	public JSONObject saveProblemFinishById(HttpServletRequest reqParam) throws Exception {
		logger.info("appProblem/saveProblemFinishById");
		String problemDetailsId = reqParam.getParameter("proDetailsId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userId = reqParam.getParameter("userId");
		String detailsContent = reqParam.getParameter("detailsContent");
		
		//当前时间
		Date date = new Date();
		
		//判断必填字段
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(problemDetailsId)
				|| AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//获取报事，进行修改
		YJWYProblemDetailsModel problemDetail = problemDetailsService.queryById(problemDetailsId);
		if (AppEmptyUtils.isEmpty(problemDetail)) {
			logger.info("result:error problemDetail is null");
			String msg = "该报事不存在";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		problemDetail.setState(3);
		problemDetail.setUpdate_time(String.valueOf(date.getTime()));
		problemDetail.setUpdate_user_id(userId);
		problemDetail.setDetails_content(detailsContent);
		problemDetail.setComplete_time(AppConstant.df_YMDHMS.format(date));

		//修改报事状态
		List<YJWYProblemDetailsModel> newProblemDetails = 
				problemDetailsService.update(new YJWYProblemDetailsModel[]{problemDetail});
		
		if (AppEmptyUtils.isEmpty(newProblemDetails)) {
			logger.info("result:error newProblemDetails is null");
			String msg = "修改报事状态错误";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}else{
			// 2017-1-11 kim start
			// 根据工单id查找出problemdetails表的workstaskid
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			if (!AppEmptyUtils.isEmpty(problemDetail.getYcq_worktaskid())) {
				// 推送"完成状态"--“finished”
				String worktaskid = problemDetail.getYcq_worktaskid();
				String ycq_status = "finished";
				String pem_id = userId;
				cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
			}
			// 2017-1-11 kim end
			// 添加工单记录  
		}
		
		//添加报事记录
		YJWYProblemRecordModel recordModel = new YJWYProblemRecordModel();
		recordModel.setFk_details_id(problemDetail.getPk_details_id());
		recordModel.setRecord_content(detailsContent);
		recordModel.setRecord_time(AppConstant.df_YMDHMS.format(date));
		recordModel.setRecord_user_id(userId);
		recordModel.setDuty_user_id(problemDetail.getFk_duty_user_id());
		recordModel.setOperate_state(Integer.valueOf(AppConstant.PROBLEM_STATE_3));
		List<YJWYProblemRecordModel> newRecordModel = problemRecordService.save(new YJWYProblemRecordModel[]{recordModel});
		
		//判断报事记录是否添加成功
		if (AppEmptyUtils.isEmpty(newRecordModel)) {
			logger.info("result:error parameter");
			String msg = "新添加报事记录错误！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		recordModel.setPk_record_id(newRecordModel.get(0).getPk_record_id());
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(recordModel);
	}

	
	/**
	 * 根据userid、crop、lt 获取报事列表
	 * @param userId
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String, Object>> getProblemList(String userId, String crop, String lt){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String problemSQL = "select ypd.pk_details_id, "
				+ "ypp.project_name_ as project_name, "
				+ "IFNULL(ypc.class_name,'') class_name, "
				+ "ypd.create_time, ypd.update_time, "
				+ "ypd.state, ypd.pk_crop "
				+ "from yjwy_problem_details ypd "
				+ "left join yjwy_pub_project ypp "
				+ "on ypp.pk_project_ = ypd.fk_project_id "
				+ "left join yjwy_problem_class ypc "
				+ "on ypc.pk_class_id = ypd.fk_class_id "
				+ "where ypd.fk_duty_user_id = '"+userId+"' "
				+ "and ypd.update_time >= '"+lt+"' "
				+ "and ypd.pk_crop = '"+crop+"'"
				+ " and ypd.fk_details_id is NULL"
				+ " and ypd.state in (2,3)";
		
		List<Map<String, Object>> ProblemMapList = readJdbcTemplate.queryForList(problemSQL);
		
		return ProblemMapList;
		
	}
	
	/**
	 * 根据报事id 获取报事详情
	 * @param problemDetailsId
	 * @return
	 */
	public List<Map<String, Object>> getProblemDetails(String problemDetailsId){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String sql = "select ypd.pk_details_id, "
				+ "ypp.project_name_ as project_name, "
				+ "ypc.class_name, ypd.details_number, "
				+ "ypd.fk_project_id, IFNULL(ypd.customer_name,'') customer_name, "
				+ "IFNULL(ypd.customer_number,'') customer_number, ypd.create_user_id, "
				+ "ypu.user_name_ as create_user_name, "
				+ "ypd.create_time, "
				+ "ypu2.user_name_ as duty_user_name, ypd.fk_duty_user_id, "
				+ "IFNULL(ypd.details_content,'') details_content, ypd.update_time, "
				+ "ypd.state, ypd.pk_crop, ypd.complete_time "
				+ "from yjwy_problem_details ypd "
				+ "left join yjwy_problem_class ypc "
				+ "on ypc.pk_class_id = ypd.fk_class_id "
				+ "left join yjwy_pub_project ypp "
				+ "on ypp.pk_project_ = ypd.fk_project_id "
				+ "left join yjwy_pub_user ypu "
				+ "on ypd.create_user_id = ypu.pk_user_ "
				+ " left join yjwy_pub_user ypu2 "
				+ " on ypd.fk_duty_user_id= ypu2.pk_user_ "
				+ " where ypd.pk_details_id = '"+problemDetailsId+"'";
		
		List<Map<String, Object>> problemDetailMap = readJdbcTemplate.queryForList(sql);
		
		return problemDetailMap;
		
	}
}
