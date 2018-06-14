package com.shareworx.ezfm.app.worktask.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.push.PushUtils;
/**
 * 工单超时推送 实现
 * @author lingwei.li
 *
 */
@Service(AppWorkTaskPushService.ID)
public class AppWorkTaskPushServiceImpl implements AppWorkTaskPushService{

	final static Logger logger = Logger.getLogger(AppWorkTaskPushServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 未接单 超时推送
	 */
	@Override
	public void notOrdersOverTimePush() throws Exception {
		logger.info("appWorkTaskPushService/NotOrdersOverTimePush");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//当前时间的前15分钟
		long currentTime = System.currentTimeMillis() - 15 * 60 * 1000;
 	   	Date date = new Date(currentTime);
		
		String sql = "select "
				+ "ypum.mobile_id, "
				+ "ypum.mobile_platform,"
				+ "ywad.area_name,"
				+ "ywap.area_id,"
				+ "ywaun.user_id,"
				+ "count(ywd.pk_details_id) as pk_details_num,"
				+ "ywd.task_state,"
				+ "ywd.service_type,"
				+ "ywd.dispatch_time "
				+ "from yjwy_worktask_details ywd "
				+ "left join yjwy_worktask_area_project_nexus ywap "
				+ "on ywd.fk_project_id = ywap.project_id "
				+ "left join yjwy_worktask_area_user_nexus ywaun "
				+ "on ywaun.area_id = ywap.area_id "
				+ "left join yjwy_pub_user_mobile ypum "
				+ "on ypum.fk_user = ywaun.user_id "
				+ "LEFT JOIN yjwy_pub_user ypu " 
				+ "ON ypum.fk_user = ypu.pk_user_ " 
				+ "left join yjwy_worktask_area_details ywad "
				+ "on ywad.pk_area_id = ywaun.area_id "
				+ "where ywd.service_type = 'serviceCateOne' "
				+ "and ywd.task_state = '1' "
				+ "and ywaun.user_type = '1' "
				+ "and ywd.dispatch_time < '"+AppConstant.df_YMDHMS.format(date)+"' "
						+ "and ypu.is_sign_ = '1' AND ypu.is_able_ = '1' "
						+ "group by ywap.area_id,ywaun.user_id";
		
		List<Map<String,Object>> list = readJdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> map : list) {
			String mobile_id = String.valueOf(map.get("mobile_id"));
			//String mobile_id = "f5f28b2472e4f20dc8bc97adfaddbd4fd8e9116cd4f43c5e8e45d0f450e88dac";
			String mobile_platform = String.valueOf(map.get("mobile_platform"));
			String reserve_str = "请注意,片区（"+map.get("area_name")+"）内"
					+ "已有"+map.get("pk_details_num")+"条接单超时工单";
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_WORKTASK); //模块编号
			pmMap.put("pm_function", AppConstant.WorkTaskFunction.FUNCTION_203);//功能编号
			PushUtils.pushUnicast(mobile_id, mobile_platform, reserve_str,pmMap);
		}
		
	}

	/**
	 * 维修 超时工单
	 */
	@Override
	public void repairOverTimePush() throws Exception {
		logger.info("appWorkTaskPushService/RepairOverTimePush");
		
		//当前时间的前30分钟
		long currentTime = System.currentTimeMillis() - 30 * 60 * 1000;
 	   	Date date = new Date(currentTime);
 	   	
 	   String sql = "select "
				+ "ypum.mobile_id, "
				+ "ypum.mobile_platform,"
				+ "ywad.area_name,"
				+ "ywap.area_id,"
				+ "ywaun.user_id,"
				+ "count(ywd.pk_details_id) as pk_details_num,"
				+ "ywd.task_state,"
				+ "ywd.service_type,"
				+ "ywd.orders_time "
				+ "from yjwy_worktask_details ywd "
				+ "left join yjwy_worktask_area_project_nexus ywap "
				+ "on ywd.fk_project_id = ywap.project_id "
				+ "left join yjwy_worktask_area_user_nexus ywaun "
				+ "on ywaun.area_id = ywap.area_id "
				+ "left join yjwy_pub_user_mobile ypum "
				+ "on ypum.fk_user = ywaun.user_id "
				+ "LEFT JOIN yjwy_pub_user ypu " 
				+ "ON ypum.fk_user = ypu.pk_user_ " 
				+ "left join yjwy_worktask_area_details ywad "
				+ "on ywad.pk_area_id = ywaun.area_id "
				+ "where ywd.service_type = 'serviceCateOne' "
				+ "and ywd.task_state = '2' "
				+ "and ywaun.user_type = '1' "
				+ "and ywd.orders_time < '"+AppConstant.df_YMDHMS.format(date)+"' "
						+ "and ypu.is_sign_ = '1' AND ypu.is_able_ = '1' "
						+ "group by ywap.area_id,ywaun.user_id";
 	   	JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		List<Map<String,Object>> list = readJdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> map : list) {
			String mobile_id = String.valueOf(map.get("mobile_id"));
			//String mobile_id = "f5f28b2472e4f20dc8bc97adfaddbd4fd8e9116cd4f43c5e8e45d0f450e88dac";
			String mobile_platform = String.valueOf(map.get("mobile_platform"));
			String reserve_str = "请注意,片区（"+map.get("area_name")+"）内"
					+ "已有"+map.get("pk_details_num")+"条维修超时工单";
			Map<String, Object> pmMap = new HashMap<String,Object>();
			pmMap.put("pm_module", AppConstant.MODULE_WORKTASK); //模块编号
			pmMap.put("pm_function", AppConstant.WorkTaskFunction.FUNCTION_203);//功能编号
			PushUtils.pushUnicast(mobile_id, mobile_platform, reserve_str,pmMap);
		}
		
	}

}
