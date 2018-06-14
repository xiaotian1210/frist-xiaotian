package com.shareworx.ezfm.timer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shareworx.ezfm.constants.DVConstants;
import com.shareworx.ezfm.utils.VersionUtil;
import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.persist.DatabaseConnections;

/**
 * dv 大屏定时统计任务
 * @author lihui
 *
 */
@Component
public class DVDataStatisticsTimer {
	
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("cacheManager")
	private IDmsCacheManager cacheManager;

	/**
	 * 统计近七日工单情况
	 * 每天凌晨1点
	 */
	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void last7WorkTask() {
		if("0".equals(env.getProperty("dv.enable", "0"))) {
			return;
		}
		//根据项目id，工单状态统计数量
		String sql = "select a.fk_project_id as project,DATE_FORMAT(a.create_time, '%Y-%m-%d') as date,a.task_state as status, count(*) as count from yjwy_worktask_details a "+
					" where a.create_time>=DATE_SUB(CURDATE(), INTERVAL 7 DAY) and  a.create_time<=DATE_SUB(CURDATE(), INTERVAL 0 DAY) "+
					" group by a.fk_project_id,DATE_FORMAT(a.create_time, '%Y-%m-%d'), a.task_state order by a.fk_project_id,a.create_time,a.task_state";
		JdbcTemplate jdbcTemplate = DatabaseConnections.getReadTemplate();
		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		for(int i=0;i<listMap.size();i++) {
			Map<String, Object> map = listMap.get(i);
			String project = map.get("project")+"";
			String date = map.get("date")+"";
			String status = map.get("status")+"";
			status = getWrokTaskStatus(status);
			String count = map.get("count")+"";
			Map<String, String> dataMap = resultMap.get(project);
			if(dataMap==null) {
				dataMap = new HashMap<String, String>();
			}
			dataMap.put(date+"|"+status, count);
			resultMap.put(project, dataMap);
			
		}
		cacheManager.put(DVConstants.DV_DATA_STATISTICS, DVConstants.METHOD_LAST7WORKTASK, resultMap);
	}
	
	private String getWrokTaskStatus(String code) {
		switch(code) {
			case "0":
				return "未派单";
			case "1":
				return "待接单";
			case "2":
				return "维修中";
			case "3":
				return "完成";
			case "4":
				return "已拒单";
			case "5":
				return "已取消";
			case "6":
				return "已关闭";
			default:
				return "";
		}
	}
	
	/**
	 * 统计近一年工单完成情况
	 * 每天凌晨1点
	 */
	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void lastYearWorkTaskComplete() {
		if("0".equals(env.getProperty("dv.enable", "0"))) {
			return;
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -11);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(c.getTime());
		//根据项目id，工单状态统计数量
		//对于不存在的状态可能需要进行补0操作
		String sql = "select a.fk_project_id as project,DATE_FORMAT(a.finish_time, '%Y-%m') as date, a.dispatch_type as type, count(*) as count "+
					" from yjwy_worktask_details a "+
					" where a.finish_time>='"+time+"' "+
					" and a.task_state=3 "+
					" group by a.fk_project_id,DATE_FORMAT(a.finish_time, '%Y-%m'),a.dispatch_type "+
					" order by a.fk_project_id,DATE_FORMAT(a.finish_time, '%Y-%m'),a.dispatch_type";
		JdbcTemplate jdbcTemplate = DatabaseConnections.getReadTemplate();
		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql);
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		for(int i=0;i<listMap.size();i++) {
			Map<String, Object> map = listMap.get(i);
			String project = map.get("project")+"";
			String date = map.get("date")+"";
			String type = map.get("type")+"";
			type = getWrokTaskType(type);
			String count = map.get("count")+"";
			Map<String, String> dataMap = resultMap.get(project);
			if(dataMap==null) {
				dataMap = new HashMap<String, String>();
			}
			dataMap.put(date+"|"+type, count);
			resultMap.put(project, dataMap);
		}
		cacheManager.put(DVConstants.DV_DATA_STATISTICS, DVConstants.METHOD_LASTYEARWORKTASKCOMPLETE, resultMap);
	}
	
	private String getWrokTaskType(String type) {
		switch(type) {
			case "1":
				return "抢单";
			case "2":
				return "派单";
			default:
				return "";
		}
	}
	
}
