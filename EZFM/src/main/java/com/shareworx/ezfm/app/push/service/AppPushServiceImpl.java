package com.shareworx.ezfm.app.push.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.push.PushUtils;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;

@Service(AppPushService.ID)
public class AppPushServiceImpl implements AppPushService{

	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 工单推送
	 */
	@Override
	public void sendWorkTaskPush(YJWYWorkTaskDetailsModel workTaskDetailsModel) {
		
		if (!AppEmptyUtils.isEmpty(workTaskDetailsModel)) {
			
			String dutyUserId = workTaskDetailsModel.getDuty_user_id();
			//没有跟进人的 将推送发给给多个人
			if (AppEmptyUtils.isEmpty(dutyUserId)) {
				//工单池推送
				sendWorkTaskPush_Pool(workTaskDetailsModel);
			}else{
				//单个推送（推送给个人）
				sendWorkTaskPush_Personal(workTaskDetailsModel);
			}
			
		}
		
	}
	
	/**
	 * 任务推送
	 */
	@Override
	public void sendInspectTaskPush(InspectTaskModel inspectTaskModel,String type) {
		if (!AppEmptyUtils.isEmpty(inspectTaskModel)) {
			sendInspectTaskPush_Personal(inspectTaskModel, type);
		}
		
	}
	
	/**
	 * 报事推送
	 */
	@Override
	public void sendProbelmPush(YJWYProblemDetailsModel probelmModel) {
		if (!AppEmptyUtils.isEmpty(probelmModel)) {
			sendProblemPush_Personal(probelmModel);
		}
		
	}
	
	/**
	 * 工单池推送（新增工单的时候）
	 * @param workTaskDetailsModel
	 */
	public void sendWorkTaskPush_Pool(YJWYWorkTaskDetailsModel workTaskDetailsModel){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//工单内容
		String repairContent = workTaskDetailsModel.getRepair_content();
		
		if (AppEmptyUtils.isEmpty(repairContent)) {
			repairContent = "有抢单消息!";
		}else{
			repairContent = "有抢单消息："+repairContent+"（工单内容）。";
		}
		
		String mobileIdsSql = getMobileIdsSql(workTaskDetailsModel.getFk_project_id());
		List<Map<String, Object>> mobileIdsMap = readJdbcTemplate.queryForList(mobileIdsSql);
		if (!AppEmptyUtils.isEmpty(mobileIdsMap)) {
			String ios_mobile_ids = String.valueOf(mobileIdsMap.get(0).get("ios_mobile_ids"));
			String android_mobile_ids = String.valueOf(mobileIdsMap.get(0).get("android_mobile_ids"));
			String reserve_str = repairContent;
			//设置pm 值 
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_WORKTASK); //模块编号
			pmMap.put("pm_function", AppConstant.WorkTaskFunction.FUNCTION_201);//功能编号
			//多个设备推送信息
			PushUtils.pushListcast(ios_mobile_ids, android_mobile_ids, reserve_str,pmMap);
		}
		
	}
	
	/**
	 * 工单 单个推送
	 * @param workTaskDetailsModel
	 */
	public void sendWorkTaskPush_Personal(YJWYWorkTaskDetailsModel workTaskDetailsModel){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String dutyUserId = workTaskDetailsModel.getDuty_user_id();
		//通过根据人 获取跟进人的设备号
		
		List<Map<String, Object>> mobileIdsMap = readJdbcTemplate.queryForList(getMobileInfo(dutyUserId));
		if (!AppEmptyUtils.isEmpty(mobileIdsMap)) {
			String mobile_id = String.valueOf(mobileIdsMap.get(0).get("mobile_id"));
			String mobile_platform = String.valueOf(mobileIdsMap.get(0).get("mobile_platform"));
			String reserve_str = "您有工单号为："+workTaskDetailsModel.getDatails_code()+"的工单需要处理";
			//单个设备 推送信息
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_WORKTASK); //模块编号
			pmMap.put("pm_function", AppConstant.WorkTaskFunction.FUNCTION_202);//功能编号
			Map<String, Object> extMap = new HashMap<String, Object>();
			extMap.put("task_id", workTaskDetailsModel.getPk_details_id());
			pmMap.put("pm_ext", extMap);
			//单个设备发送
			PushUtils.pushUnicast(mobile_id, mobile_platform, reserve_str,pmMap);
		}
	}
	
	/**
	 * 任务单个推送
	 * @param inspectTaskModel
	 * @param type
	 */
	public void sendInspectTaskPush_Personal(InspectTaskModel inspectTaskModel, String type){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String pushUserId = "";
		/*
		 * 如果  type="1" 表示 待整改 需要发送的推送（推送给任务当前人）；
		 * 如果  type="2" 表示 整改确认 需要发送的推送（推送给任务所属人）；
		 */
		if ("1".equals(type)) {
			pushUserId = inspectTaskModel.getTask_currentuser_pk();
		}else if("2".equals(type)){
			pushUserId = inspectTaskModel.getFk_taskuser();
		}
		List<Map<String, Object>> mobileIdsMap = readJdbcTemplate.queryForList(getMobileInfo(pushUserId));
		
		if (!AppEmptyUtils.isEmpty(mobileIdsMap)) {
			
			String content = "";
			if ("1".equals(type)) {
				//获取任务所属人
				YJWYUserModel taskUser = userService.queryById(inspectTaskModel.getFk_taskuser());
				content = "请注意，您有1条由"+taskUser.getUser_name()+"发送来的待整改任务";
			}else if("2".equals(type)){
				//获取任务当前人
				YJWYUserModel currentUser = userService.queryById(inspectTaskModel.getTask_currentuser_pk());
				content = "请注意，"+currentUser.getUser_name()+"有一条需要您确认的待整改任务。";
			}
			
			String mobile_id = String.valueOf(mobileIdsMap.get(0).get("mobile_id"));
			String mobile_platform = String.valueOf(mobileIdsMap.get(0).get("mobile_platform"));
			String reserve_str = content;
			
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_QUALITYINSPECT); //模块编号
			pmMap.put("pm_function", AppConstant.QualityInspectFunction.FUNCTION_101);//功能编号
			Map<String, Object> extMap = new HashMap<String, Object>();
			extMap.put("task_id", inspectTaskModel.getPk_task());
			pmMap.put("pm_ext", extMap);
			//单个设备 推送信息
			PushUtils.pushUnicast(mobile_id, mobile_platform, reserve_str,pmMap);
		}
	}
	
	/**
	 * 报事单个推送
	 * @param inspectTaskModel
	 * @param type
	 */
	public void sendProblemPush_Personal(YJWYProblemDetailsModel probelmModel){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//获取跟进人设备信息  给跟进人推送消息
		String pushUserId = probelmModel.getFk_duty_user_id();
		List<Map<String, Object>> mobileIdsMap = readJdbcTemplate.queryForList(getMobileInfo(pushUserId));
		
		if (!AppEmptyUtils.isEmpty(mobileIdsMap)) {
			
			String mobile_id = String.valueOf(mobileIdsMap.get(0).get("mobile_id"));
			String mobile_platform = String.valueOf(mobileIdsMap.get(0).get("mobile_platform"));
			String reserve_str = "请注意，您有一条编号为 "+probelmModel.getDetails_number()+" 的报事需要处理。";
			
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_PROBLEM); //模块编号
			pmMap.put("pm_function", AppConstant.ProblemFunction.FUNCTION_301);//功能编号
			Map<String, Object> extMap = new HashMap<String, Object>();
			extMap.put("task_id", probelmModel.getPk_details_id());
			pmMap.put("pm_ext", extMap);
			//单个设备 推送信息
			PushUtils.pushUnicast(mobile_id, mobile_platform, reserve_str,pmMap);
		}
	}
	
	/**
	 * 根据项目id 获取设备号列表 sql
	 * @param projectId
	 * @return
	 */
	public static String getMobileIdsSql(String projectId){
		String sql = "select n.android_mobile_ids, m.ios_mobile_ids "
				+ "from "
				+ "(select IFNULL(GROUP_CONCAT(DISTINCT mobile_id SEPARATOR ','),'') AS ios_mobile_ids,"
				+ "'"+projectId+"' AS ios_project_id "
				+ "from yjwy_pub_user_mobile "
				+ "where fk_user in "
					+ "(select ywaun.user_id from "
					+ "yjwy_worktask_area_user_nexus ywaun "
					+ "left join yjwy_pub_user ypu "
					+ "on ywaun.user_id = ypu.pk_user_ "
					+ "where ywaun.area_id in "
						+ "(select area_id from yjwy_worktask_area_project_nexus "
						+ "where project_id = '"+projectId+"') "
						+ "and ypu.is_sign_ = '1' and ypu.is_able_ = '1' "
					+ ") and mobile_platform = 'IOS' "
				+ ") m "
				+ "left join "
				+ "(select IFNULL(GROUP_CONCAT(DISTINCT mobile_id SEPARATOR ','),'') AS android_mobile_ids,"
				+ "'"+projectId+"' AS android_project_id "
				+ "from yjwy_pub_user_mobile "
				+ "where fk_user in "
					+ "(select ywaun.user_id from "
					+ "yjwy_worktask_area_user_nexus ywaun "
					+ "left join yjwy_pub_user ypu "
					+ "on ywaun.user_id = ypu.pk_user_ "
					+ "where ywaun.area_id in "
						+ "(select area_id from yjwy_worktask_area_project_nexus "
						+ "where project_id = '"+projectId+"') "
						+ "and ypu.is_sign_ = '1' and ypu.is_able_ = '1' "
					+ ") and mobile_platform = 'Android' "
				+ ") n "
				+ "ON m.ios_project_id = n.android_project_id";
		
		return sql;
	}
	
	/**
	 * 根据用户id 获取用户的设备号以及设备类型
	 * @param userId
	 * @return
	 */
	public static String getMobileInfo(String userId){
		String sql = "select mobile_id,mobile_platform "
				+ "from yjwy_pub_user_mobile "
				+ "where fk_user = '"+userId+"'";
		
		return sql;
	}

}
