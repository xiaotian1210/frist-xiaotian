package com.shareworx.ezfm.app.leave.service;

import java.text.SimpleDateFormat;
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
import com.shareworx.ezfm.app.util.AppDateUtils;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.performance.leave.service.YJWYLeaveDomainService;
import com.shareworx.ezfm.system.file.model.SystemFileModel;
/**
 * 休假备案 接口实现
 * @author lingwei.li
 *
 */

@Service(AppLeaveService.ID)
public class AppLeaveServiceImpl implements AppLeaveService{

	final static Logger logger = Logger.getLogger(AppLeaveServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	@Autowired
	@Qualifier(YJWYStationDomainService.ID)
	private YJWYStationDomainService stationService;
	@Autowired
	@Qualifier(YJWYLeaveDomainService.ID)
	private YJWYLeaveDomainService leaveService;
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	/**
	 * 通过userid 获取休假列表
	 */
	@Override
	public JSONObject getAllLeaveByUserId(HttpServletRequest reqParam) throws Exception {
		logger.info("leaveService/getAllLeaveByUserId");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String lt = reqParam.getParameter("lt");
		String crop = reqParam.getParameter("crop");
		
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(lt) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//根据用户id、crop、lt 获取休假列表
		List<Map<String, Object>> leaveListMap = getLeaveList(userId, crop, lt);
		
		logger.info("result:success");
		
		return AppJsonMessage.toJsonMsgTrue(leaveListMap);
	}

	/**
	 * 新增休假
	 */
	@Override
	public JSONObject addLeave(HttpServletRequest reqParam) throws Exception {
		logger.info("leaveService/addLeave");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String projectId = reqParam.getParameter("projectId");
		String lvDetails = reqParam.getParameter("lvDetails");
		String lvType = reqParam.getParameter("lvType");
		String lvStartTime = reqParam.getParameter("lvStartTime");
		String lvEndTime = reqParam.getParameter("lvEndTime");
		String crop = reqParam.getParameter("crop");
		String stationId = reqParam.getParameter("stationId");
		
		//当前时间
		Date currentDate = new Date();
		
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(lvType)
				|| AppEmptyUtils.isEmpty(lvStartTime)
				|| AppEmptyUtils.isEmpty(lvEndTime) 
				|| AppEmptyUtils.isEmpty(stationId) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		//构建新的 leaveModel 
		YJWYLeaveModel leaveModel = new YJWYLeaveModel();
		
		if(!AppEmptyUtils.isEmpty(projectId)){
			//检查项目id
			YJWYProjectModel projectModel = projectService.queryById(projectId);
			if (AppEmptyUtils.isEmpty(projectModel)) {
				String msg = "项目不存在";
				logger.info("result:error parameter");
				return AppJsonMessage.toJsonMsgFalse(2,msg);
			}
			leaveModel.setFk_region(projectModel.getPk_area());
		}
		
		//计算天数
		String lv_days = String.valueOf(AppDateUtils.daysBetween(AppConstant.df_YMD.parse(lvStartTime), 
				AppConstant.df_YMD.parse(lvEndTime)));
		SimpleDateFormat startDate=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat endDate=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String startTime = startDate.format(AppConstant.df_YMD.parse(lvStartTime));
		String endTime = endDate.format(AppConstant.df_YMD.parse(lvEndTime));
		
		leaveModel.setLv_submitter(userId);
		leaveModel.setFk_project(projectId);
		leaveModel.setFk_job(stationId);
		leaveModel.setLv_commitTime(AppConstant.df_YMDHMS.format(currentDate));
		leaveModel.setLv_startTime(startTime);
		leaveModel.setLv_endTime(endTime);
		leaveModel.setLv_days(lv_days);
		leaveModel.setFk_type(lvType);
		leaveModel.setLv_details(lvDetails);
		leaveModel.setLv_operation(AppConstant.LeaveOperation.PENDING);//待审批
		leaveModel.setCreate_time(new Date().getTime()+"");
		leaveModel.setCreate_user(userId);
		leaveModel.setUpdate_user(userId);
		leaveModel.setUpdate_time(new Date().getTime()+"");
		leaveModel.setPk_crop(crop);
		
		List<YJWYLeaveModel> leaves = leaveService.save(new YJWYLeaveModel[]{leaveModel});
		
		if (AppEmptyUtils.isEmpty(leaves)) {
			logger.info("result:error add");
			String msg = "新增休假失败！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		//获取 休假id 并设置到 leaveModel 中
		leaveModel.setPk_leave(leaves.get(0).getPk_leave());
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(leaveModel);
	}

	/**
	 * 根据参数获取 休假列表
	 * @param userId
	 * @param crop
	 * @param lt
	 * @return
	 */
	public List<Map<String,Object>> getLeaveList(String userId, String crop, String lt){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		/*String leaveListSQL = "select lv.pk_leave_ as pk_leave, "
				+ "lv.lv_submitter_ as lv_submitter, "
				+ "lv.lv_commitTime_ as lv_commitTime, "
				+ "lv.lv_startTime_ as lv_startTime, "
				+ "lv.lv_endTime_ as lv_endTime, "
				+ "lv.lv_days_ as lv_days, lv.fk_type_ as lv_type, "
				+ "lv.lv_details_ as lv_details, lv.lv_operation_ as lv_operation, "
				+ "lv.lv_approvalDesc_ as lv_approvalDesc,lv.create_time_ as create_time, "
				+ "lv.pk_crop_ as pk_crop,"
				+ " file.fileurls as lv_file_urls "
				+ " from yjwy_performance_leave lv"
				+ " left join "
				+ " (select IFNULL(GROUP_CONCAT(file_path_ SEPARATOR ','),'') as fileurls,record_id_ from yjwy_system_file GROUP BY record_id_) file"
				+ " on file.record_id_=lv.pk_leave_"
				+ " where lv_submitter_ = '"+userId+"' "
				+ " and pk_crop_ = '"+crop+"' "  IFNULL(service_type,'') service_type,
				+ " and update_time_ >= '"+lt+"'";*/
		
		String leaveListSQL1 = "select lv.pk_leave_ as pk_leave, "
				+ "lv.lv_submitter_ as lv_submitter, "
				+ "lv.lv_commitTime_ as lv_commitTime, "
				+ "lv.lv_startTime_ as lv_startTime, "
				+ "lv.lv_endTime_ as lv_endTime, "
				+ "lv.lv_days_ as lv_days, lv.fk_type_ as lv_type, "
				+ "IFNULL(lv.lv_details_,'') as lv_details, lv.lv_operation_ as lv_operation, "
				+ "IFNULL(lv.lv_approvalDesc_,'') as lv_approvalDesc,lv.create_time_ as create_time, "
				+ "lv.pk_crop_ as pk_crop"
				+ " from yjwy_performance_leave lv"
				+ " where lv_submitter_ = '"+userId+"' "
				+ " and pk_crop_ = '"+crop+"' "
				+ " and update_time_ >= '"+lt+"'";
		List<Map<String, Object>> leaveList = readJdbcTemplate.queryForList(leaveListSQL1);
		if(leaveList.size()>0){
			Set<Object> recordIds = new HashSet<>();
			for (int i = 0; i < leaveList.size(); i++) {
				recordIds.add(leaveList.get(i).get("pk_leave"));
			}
			
			Query query = Query.from(SystemFileModel.META_ID);
			query.where(new Condition("record_id_", QueryContents.TYPE_IN,recordIds));
			List<SystemFileModel> fileList = service.queryListByCondition(query);
			if(fileList.size()>0){
				Map<String,Object> map = null;
				StringBuilder stringBuilder = null;
				for (int j = 0; j < leaveList.size(); j++) {
					map=leaveList.get(j);
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
		return leaveList;
		
	}
}
