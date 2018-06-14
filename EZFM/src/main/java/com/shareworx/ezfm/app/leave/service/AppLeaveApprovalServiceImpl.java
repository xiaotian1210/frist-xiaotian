package com.shareworx.ezfm.app.leave.service;

import java.util.ArrayList;
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
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.performance.leave.service.YJWYLeaveDomainService;
import com.shareworx.ezfm.system.file.model.SystemFileModel;
/**
 * 休假审批 接口实现类
 * @author lingwei.li
 *
 */
@Service(AppLeaveApprovalService.ID)
public class AppLeaveApprovalServiceImpl implements AppLeaveApprovalService{

	final static Logger logger = Logger.getLogger(AppLeaveApprovalServiceImpl.class);
	
	@Autowired
	@Qualifier(YJWYLeaveDomainService.ID)
	private YJWYLeaveDomainService leaveService;
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	/**
	 * 获取审批列表
	 */
	@Override
	public JSONObject getAllLeaveApprovalByUserId(HttpServletRequest reqParam) throws Exception {
		logger.info("AppLeaveApprovalServiceImpl/getAllLeaveApproveByUserId");
		
		//页面必填参数
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String lt = reqParam.getParameter("lt");
		
		//检查参数是否必填
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)
				|| AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(lt)){
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		List<Map<String, Object>> leaveApprovalMap = new ArrayList<Map<String, Object>>();
		//根据用户id 获取岗位信息
		List<Map<String, Object>> stationMapList = getStationList(userId);
		//如果用户不是项目总 则直接返回一个空的 审批列表
		if (AppEmptyUtils.isEmpty(stationMapList)) {
			logger.info("result:stationMapList is null");
			return AppJsonMessage.toJsonMsgTrue(leaveApprovalMap);
		}
		
		//重组岗位list
		List<String> orgStr = new ArrayList<String>();
		for (Map<String, Object> mapStr : stationMapList) {
			String str = String.valueOf(mapStr.get("pk_org"));
			if (!AppEmptyUtils.isEmpty(str)) {
				orgStr.add(str);
			}
		}
		
		//通过组织， 获取组织下的所有人 如果为空 直接跳出
		if (AppEmptyUtils.isEmpty(orgStr)) {
			logger.info("result:orgStr is null");
			return AppJsonMessage.toJsonMsgTrue(leaveApprovalMap);
		}
		
		List<YJWYUserModel> userModels = deviceService.queryUsermodelsByPkOrgList(orgStr);
		String userIds = "";
		for (YJWYUserModel user : userModels) {
			if (!AppEmptyUtils.isEmpty(user.getPk_user())) {
				userIds = userIds + "'"+user.getPk_user()+"',"; 
			}
		}
		userIds = userIds.substring(0, userIds.length()-1);
		
		/*String stationIds =String.valueOf(stationMapList.get(0).get("pk_station"));
		List<Map<String, Object>> stationLowerMapList = getStationLowerList(stationIds, crop);
		
		if (AppEmptyUtils.isEmpty(stationLowerMapList)) {
			logger.info("stationLowerMapList is null");
			return AppJsonMessage.toJsonMsgTrue(leaveApprovalMap);
		}*/
		
		//通过 组织下面的人 获取 审批列表 
		if (AppEmptyUtils.isEmpty(userIds)) {
			logger.info("result:userIds is null");
			return AppJsonMessage.toJsonMsgTrue(leaveApprovalMap);
		}
		
		leaveApprovalMap = getLeaveApprovalList(userIds, crop, lt);
		
		logger.info("result:success");
		
		return AppJsonMessage.toJsonMsgTrue(leaveApprovalMap);
	}

	/**
	 * 休假审批 通过
	 */
	@Override
	public JSONObject saveLeaveApprovalPass(HttpServletRequest reqParam) throws Exception {
		logger.info("AppLeaveApproveService/saveLeaveApprovalPass");
		
		String lvApprovalDesc = reqParam.getParameter("lvApprovalDesc");
		String leaveId = reqParam.getParameter("leaveId");
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		
		//当前时间
		Date currentDate = new Date();
		
		//判断参数必填项
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(leaveId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//判断休假id
		YJWYLeaveModel leaveModel = leaveService.queryById(leaveId);
		if (AppEmptyUtils.isEmpty(leaveModel)) {
			logger.info("result:error leaveModel is null");
			String msg = "休假备案不存在";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		if (!"0".equals(leaveModel.getLv_operation())) {
			logger.info("result:error leaveModel operation");
			String msg = "请选择待审批的休假！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		leaveModel.setLv_approval(userId);
		leaveModel.setLv_approvalDesc(lvApprovalDesc);
		leaveModel.setLv_operation(AppConstant.LeaveOperation.PASS);
		leaveModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		leaveModel.setUpdate_user(userId);
		leaveModel.setLv_confirmTime(AppConstant.df_YMDHMS.format(currentDate));
		
		List<YJWYLeaveModel> leaveModels = leaveService.update(new YJWYLeaveModel[]{leaveModel});
		if (AppEmptyUtils.isEmpty(leaveModels)) {
			String msg = "修改休假审批错误，请重试！";
			return AppJsonMessage.toJsonMsg(2,msg);
		}
		logger.info("result:success");
		return AppJsonMessage.toJsonMsg(0,"success");
	}

	/**
	 * 休假审批 拒绝
	 */
	@Override
	public JSONObject saveLeaveApprovalReject(HttpServletRequest reqParam) throws Exception {
		logger.info("AppLeaveApproveService/saveLeaveApprovalReject");
		String lvApprovalDesc = reqParam.getParameter("lvApprovalDesc");
		String leaveId = reqParam.getParameter("leaveId");
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		
		//当前时间
		Date currentDate = new Date();
		
		//判断必填项
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(leaveId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//判断休假id
		YJWYLeaveModel leaveModel = leaveService.queryById(leaveId);
		if (AppEmptyUtils.isEmpty(leaveModel)) {
			logger.info("result:error parameter");
			String msg = "休假备案id不存在";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		if (!"0".equals(leaveModel.getLv_operation())) {
			String msg = "请选择待审批的休假！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		leaveModel.setLv_approval(userId);
		leaveModel.setLv_approvalDesc(lvApprovalDesc);
		leaveModel.setLv_operation(AppConstant.LeaveOperation.REJECT);
		leaveModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		leaveModel.setUpdate_user(userId);
		leaveModel.setLv_confirmTime(AppConstant.df_YMDHMS.format(currentDate));
		
		List<YJWYLeaveModel> leaveModels=leaveService.update(new YJWYLeaveModel[]{leaveModel});
		if (AppEmptyUtils.isEmpty(leaveModels)) {
			String msg = "修改休假审批错误，请重试！";
			return AppJsonMessage.toJsonMsg(2,msg);
		}
		logger.info("result:success");
		return AppJsonMessage.toJsonMsg(0,"success");
	}
	
	/**
	 * 根据用户id 获取岗位
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getStationList(String userId) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String stationSQL = "select "
				+ "yps.pk_org_ as pk_org "
				+ "from yjwy_pub_user_station ypus "
				+ "LEFT JOIN yjwy_pub_station yps ON ypus.pk_station_ = yps.pk_station_ "
				+ "LEFT JOIN yjwy_pub_org ypo ON yps.pk_org_ = ypo.pk_org_ "
				+ "where ypus.pk_user_ = '"+userId+"' and ypo.org_type_ = '2' ";
		
		List<Map<String, Object>> stationMapList = readJdbcTemplate.queryForList(stationSQL);
		
		return stationMapList;
		
	}
	
	/**
	 * 根据岗位ids和crop 获取下级岗位数组
	 * @param stationIds
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getStationLowerList(String stationIds,String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//通过userId获取的岗位数组 ，获取所有岗位的下级岗位
		String stationLowerSQL = "SELECT "
				+ "IFNULL(GROUP_CONCAT(pk_station_ SEPARATOR \"', '\"),'') as pk_station_ "
				+ "FROM yjwy_pub_station "
				+ "where pk_parent_ IN ('"+stationIds+"') "
				+ "and pk_crop_ = '"+crop+"'";
		List<Map<String, Object>> stationLowerMapList = readJdbcTemplate.queryForList(stationLowerSQL);
		
		return stationLowerMapList;
		
	}
	
	/**
	 * 根据用户id 获取岗位
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getLeaveApprovalList(String userIds,String crop,String lt) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//通过 岗位 获取 审批列表 
		/*String leaveApprovalSQL = "select lv.pk_leave_ as pk_leave, "
				+ "lv.lv_submitter_ as lv_submitter, lv.lv_commitTime_ as lv_commitTime, "
				+ "lv.lv_startTime_ as lv_startTime, lv.lv_endTime_ as lv_endTime, "
				+ "lv.lv_days_ as lv_days, lv.lv_details_ as lv_details, "
				+ "lv.lv_operation_ as lv_operation,lv.create_time_ as create_time, lv.fk_type_ as fk_type,"
				+ " puser.user_name_ as lv_submitter_name, file.fileurls as lv_file_urls"
				+ " from yjwy_performance_leave lv"
				+ " left join yjwy_pub_user puser on puser.pk_user_ = lv.lv_submitter_"
				+ " left join "
				+ " (select IFNULL(GROUP_CONCAT(file_path_ SEPARATOR ','),'') as fileurls,record_id_ from yjwy_system_file GROUP BY record_id_) file"
				+ " on file.record_id_=lv.pk_leave_"
				+ " where lv.lv_submitter_ in ("+userIds+") "
				+ " and lv.lv_operation_ = '"+AppConstant.LeaveOperation.PENDING+"' "
				+ " and lv.pk_crop_ = '"+crop+"' "
				+ " and lv.update_time_ >= '"+lt+"'";*/
		
		String leaveApprovalSQL1 = "select lv.pk_leave_ as pk_leave, "
				+ "lv.lv_submitter_ as lv_submitter, lv.lv_commitTime_ as lv_commitTime, "
				+ "lv.lv_startTime_ as lv_startTime, lv.lv_endTime_ as lv_endTime, "
				+ "lv.lv_days_ as lv_days, "
				+ "IFNULL(lv.lv_details_,'') as lv_details, "
				+ "lv.lv_operation_ as lv_operation,lv.create_time_ as create_time, lv.fk_type_ as fk_type,"
				+ " puser.user_name_ as lv_submitter_name"
				+ " from yjwy_performance_leave lv"
				+ " left join yjwy_pub_user puser on puser.pk_user_ = lv.lv_submitter_"
				+ " where lv.lv_submitter_ in ("+userIds+") "
				+ " and lv.lv_operation_ = '"+AppConstant.LeaveOperation.PENDING+"' "
				+ " and lv.pk_crop_ = '"+crop+"' "
				+ " and lv.update_time_ >= '"+lt+"'";
		
		List<Map<String, Object>> leaveApprovalList = readJdbcTemplate.queryForList(leaveApprovalSQL1);
		if(leaveApprovalList.size()>0){
			Set<Object> recordIds = new HashSet<>();
			for (int i = 0; i < leaveApprovalList.size(); i++) {
				recordIds.add(leaveApprovalList.get(i).get("pk_leave"));
			}
			
			Query query = Query.from(SystemFileModel.META_ID);
			query.where(new Condition("record_id_", QueryContents.TYPE_IN,recordIds));
			List<SystemFileModel> fileList = service.queryListByCondition(query);
			if(fileList.size()>0){
				Map<String,Object> map = null;
				StringBuilder stringBuilder = null;
				for (int j = 0; j < leaveApprovalList.size(); j++) {
					map=leaveApprovalList.get(j);
					stringBuilder = new StringBuilder();
					for (int i = 0; i < fileList.size(); i++) {
							if(map.get("pk_leave").equals(fileList.get(i).get("record_id"))){
								stringBuilder.append(fileList.get(i).get("file_path")+",");
							}
					}
					if(stringBuilder.length()>0){
						map.put("lv_file_urls", stringBuilder.toString().substring(0, stringBuilder.length()-1));
					}else{
						map.put("lv_file_urls","");
					}
				}
			}
		}
		return leaveApprovalList;
		
	}

}
