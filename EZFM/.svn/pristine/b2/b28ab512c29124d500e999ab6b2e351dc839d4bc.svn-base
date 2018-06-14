package com.shareworx.ezfm.app.sign.service;

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
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;

/**
 * 签到、签退 接口实现
 * @author lingwei.li
 *
 */
@Service(AppWorkTrackService.ID)
public class AppWorkTrackServiceImpl implements AppWorkTrackService{

	final static Logger logger = Logger.getLogger(AppWorkTrackServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 获取工作轨迹
	 */
	@Override
	public JSONObject getWorkTrack(HttpServletRequest reqParam) throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		logger.info("appSignService/saveSign");
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String userId = reqParam.getParameter("userId");
		
		//先判断必填参数
		if(AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(mobilePlatform)){
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1,msg); //参数错误！
		}
		
		//获取核查任务的工作轨迹
		
		//获取当天签到签退的轨迹
		
		JSONObject data = new JSONObject();
		//核查任务工作轨迹
		data.put("task", getQualityTaskList(userId, crop));
		//签到签退轨迹
		data.put("sign", getUserSignList(userId, crop));
		
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(data);
		
	}
	
	/**
	 * 获取当前用户当天签到状态下的核查任务的工作轨迹
	 * @return
	 */
	public List<Map<String, Object>> getQualityTaskList(String userId, String crop){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//当前时间 年-月-日
		String currentTime = AppConstant.df_YMD.format(new Date());  
		//拼接的需要比较的开始时间 和 结束时间
		String currentStartTime = currentTime+" 00:00:00";
		String currentEndTime = currentTime+" 23:59:59";
		
		String sql = "select * from "
				+ "("
				//整改的需要判断 整改开始时间
				+ "select yqit.pk_task, yqit.is_rectify, yqit.specialty, "
				+ " yqis.inspstan_category, yqit.record_finish_lon, "
				+ " yqit.record_finish_lat, yqit.task_rectify_starttime AS task_time "
				+ " from yjwy_quality_inspecttask yqit "
				+ " left join yjwy_quality_inspectstandard yqis "
				+ " on yqit.fk_standard = yqis.pk_inspstan "
				+ " where yqit.is_rectify = '1' "
				+ " and yqit.is_sign = '1'"
				+ " and yqit.fk_taskuser = '"+userId+"' "
				+ " and yqit.pk_crop = '"+crop+"' "
				+ " and yqit.task_rectify_starttime >= '"+currentStartTime+"' "
				+ " and yqit.task_rectify_starttime <= '"+currentEndTime+"' "
				+ " UNION "
				//没有的整改的需要判断 任务结束时间
				+ " select yqit.pk_task, yqit.is_rectify, yqit.specialty, "
				+ " yqis.inspstan_category, yqit.record_finish_lon, "
				+ " yqit.record_finish_lat, yqit.task_end_time AS task_time "
				+ " from yjwy_quality_inspecttask yqit "
				+ " left join yjwy_quality_inspectstandard yqis "
				+ " on yqit.fk_standard = yqis.pk_inspstan "
				+ " where yqit.is_rectify = '0' "
				+ " and yqit.is_sign = '1'"
				+ " and yqit.fk_taskuser = '"+userId+"' "
				+ " and yqit.pk_crop = '"+crop+"' "
				+ " and yqit.task_end_time >= '"+currentStartTime+"' "
				+ " and yqit.task_end_time <= '"+currentEndTime+"' "
				+ " ) m ORDER BY m.task_time ASC";
		List<Map<String, Object>> qualityTaskListMap = readJdbcTemplate.queryForList(sql);
		return qualityTaskListMap;
		
	}

	/**
	 * 获取当前用户当天的签到签退轨迹
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getUserSignList(String userId, String crop){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//当前时间 年-月-日
		String currentTime = AppConstant.df_YMD.format(new Date());  
		//拼接的需要比较的开始时间 和 结束时间
		String currentStartTime = currentTime+" 00:00:00";
		String currentEndTime = currentTime+" 23:59:59";
		String sql ="select sign_commitTime_ as sign_commitTime,sign_action_ as sign_action,sign_location_ as sign_location"
				+ " from yjwy_performance_sign"
				+ " WHERE sign_person_='"+userId+"'"
				+ " and sign_commitTime_ >='"+currentStartTime+"' "
				+ " and sign_commitTime_ <='"+currentEndTime+"'"
				+ " and pk_crop_='"+crop+"'	";
		List<Map<String, Object>> userSignListMap = readJdbcTemplate.queryForList(sql);
		return userSignListMap;
	}
}
