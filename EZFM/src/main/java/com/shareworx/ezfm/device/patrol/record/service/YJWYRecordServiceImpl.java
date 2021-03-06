package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskDao;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保记录service实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYRecordService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYRecordServiceImpl implements YJWYRecordService {

	@Autowired
	@Qualifier(YJWYTaskDao.ID)
	private YJWYTaskDao taskDao;

	public void setTaskDao(YJWYTaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService baseInfoQueryService;

	public void setBaseInfoQueryService(IBaseInfoQueryService baseInfoQueryService) {
		this.baseInfoQueryService = baseInfoQueryService;
	}

	/**
	 * 任务查询列表
	 */
	public List<Map<String, Object>> queryRecordMap(ParamEntity params) {

		return taskDao.queryMap(this.getSqlString(params, true));
	}

	/**
	 * 任务列表数量查询
	 */
	public Long queryRecordCount(ParamEntity params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(task.task_id) from (" + this.getSqlString(params, false) + ") task");
		return taskDao.queryCount(sql.toString());
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString(ParamEntity params, boolean flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String task_type = params.getTask_type();
		String pk_area = params.getPk_area();
		String pk_project = params.getPk_project();
		String pk_user = params.getPk_user();
		String task_dealt = params.getTask_dealt();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("t1.task_result task_result,t1.task_dealt task_dealt,t1.task_desc task_desc,");
		sql.append("t1.task_id task_id,t1.plan_id plan_id,t1.plan_name plan_name,t1.executor_id executor_id,t1.begin_time begin_time, ");
		sql.append("t1.end_time end_time,t1.finish_time finish_time,t1.task_state task_state,t1.task_type task_type,t1.pk_crop pk_crop, ");
		sql.append("t1.create_time create_time,t1.create_user create_user,t1.update_time update_time,t1.update_user update_user, ");
		sql.append("t2.user_name_ executor_name,t3.pk_area pk_area,t3.pk_project pk_project,t3.group_id group_id,t3.group_name group_name , ");
		sql.append("t3.project_name_ project_name,t3.area_name_ area_name, ");
		sql.append("case when t4.file_count IS NULL THEN 0 ELSE t4.file_count END file_count ");
		sql.append("FROM yjwy_patrol_task t1 ");
		sql.append("LEFT JOIN yjwy_pub_user t2 ON t1.executor_id = t2.pk_user_ ");
		sql.append("LEFT JOIN ( ");
		sql.append("SELECT plan.plan_id,plan.plan_name,plan.pk_area,plan.pk_project,plan.group_id,gro.group_name, ");
		sql.append("pro.project_name_,area.area_name_  ");
		sql.append(" FROM yjwy_patrol_plan plan ");
		sql.append("JOIN yjwy_pub_area area ON plan.pk_area = area.pk_area_ ");
		sql.append("JOIN yjwy_pub_project pro ON plan.pk_project = pro.pk_project_ ");
		sql.append("JOIN yjwy_executor_group gro ON plan.group_id = gro.pk_group ");
		sql.append(" ) t3  ");
		sql.append("ON t1.plan_id = t3.plan_id ");
		sql.append(" LEFT JOIN ( ");
		sql.append("select count(s1.file_id) file_count,s2.task_id task_id from yjwy_patrol_task_file s1  ");
		sql.append("left join yjwy_patrol_taskeq s2 on s1.record_id = s2.pk_id ");
		sql.append("where s1.file_path <> '' ");
		sql.append("and s1.file_path is not null ");
		sql.append("and s1.file_name <> '' ");
		sql.append("and s1.file_name is not null ");
		sql.append("GROUP BY s2.task_id ");
		sql.append(" ) t4 ");
		sql.append("ON t1.task_id = t4.task_id ");
		sql.append(" where t1.task_state = 1 and t1.pk_crop= '" + DeviceUtil.getPkCrop() + "' ");
		// 计划类型
		if (null != task_type && "" != task_type) {
			sql.append(" AND t1.task_type = '" + task_type + "' ");
		}
		// 人员选择
		if (null != pk_user && !"default".equals(pk_user)) {
			sql.append(" AND t1.executor_id = '" + pk_user + "' ");
		}
		// 任务状态
		if (null != task_dealt && "" != task_dealt && !"-1".equals(task_dealt)) {
			sql.append(" AND t1.task_dealt = " + task_dealt + " ");
		}
		// 开始时间
		if (null != start_time && "" != start_time && null != end_time && "" != end_time) {
			sql.append(" AND t1.begin_time <= '" + end_time + "' AND t1.begin_time >= '" + start_time + "' ");
		} else {
//			sql.append(" AND t1.begin_time <= '" + DeviceUtil.date2String(new Date(), DeviceUtil.YMD) + "' AND t1.begin_time >= '" + DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMD) + "' ");
		}
		// 区域
		if (null != pk_project && !"default".equals(pk_project)) {
			sql.append(" AND t3.pk_project = '" + pk_project + "'");
			// 项目
		} else if (null != pk_area && !"default".equals(pk_area)) {
			String[] projectIds = DeviceUtil.getPrimaryKeyValue((DomainModel[]) deviceService.getProjectModelsByPkArea(pk_area).toArray(new YJWYProjectModel[] {}));
			if (projectIds.length != 0) {
				sql.append(" AND " + DeviceUtil.getInNotInSql("t3.pk_project", QueryContents.TYPE_IN, projectIds));
			} else {
				sql.append(" AND 1=2");
			}
		} else {
			// 默认查询当前人所属项目所有数据
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
			if (projectIds.size() != 0) {
				sql.append(" AND " + DeviceUtil.getInNotInSql("t3.pk_project", QueryContents.TYPE_IN, projectIds.toArray(new String[] {})));
			} else {
				sql.append(" AND 1=2");
			}
		}
		sql.append(" ORDER BY t1.task_id ");
		if (flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}

	@Override
	public List<Map<String, Object>> queryRecordMap2(ParamEntity params) {
		// TODO Auto-generated method stub
		return taskDao.queryMap(this.getSqlString2(params, true));
	}
	
	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString2(ParamEntity params, boolean flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String task_type = params.getTask_type();

		String pk_user = params.getPk_user();
		String task_dealt = params.getTask_dealt();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("t1.task_result task_result,t1.task_dealt task_dealt,t1.task_desc task_desc,");
		sql.append("t1.task_id task_id,t1.plan_id plan_id,t1.plan_name plan_name,t1.executor_id executor_id,t1.begin_time begin_time, ");
		sql.append("t1.end_time end_time,t1.finish_time finish_time,t1.task_state task_state,t1.task_type task_type,t1.pk_crop pk_crop, ");
		sql.append("t1.create_time create_time,t1.create_user create_user,t1.update_time update_time,t1.update_user update_user, ");
		sql.append("t2.user_name_ executor_name,t3.pk_area pk_area,t3.pk_project pk_project,t3.group_id group_id,t3.group_name group_name , ");
		sql.append("t3.project_name_ project_name,t3.area_name_ area_name, ");
		sql.append("case when t4.file_count IS NULL THEN 0 ELSE t4.file_count END file_count ");
		sql.append("FROM yjwy_patrol_task t1 ");
		sql.append("LEFT JOIN yjwy_pub_user t2 ON t1.executor_id = t2.pk_user_ ");
		sql.append("LEFT JOIN ( ");
		sql.append("SELECT plan.plan_id,plan.plan_name,plan.pk_area,plan.pk_project,plan.group_id,gro.group_name, ");
		sql.append("pro.project_name_,area.area_name_  ");
		sql.append(" FROM yjwy_patrol_plan plan ");
		sql.append("JOIN yjwy_pub_area area ON plan.pk_area = area.pk_area_ ");
		sql.append("JOIN yjwy_pub_project pro ON plan.pk_project = pro.pk_project_ ");
		sql.append("JOIN yjwy_executor_group gro ON plan.group_id = gro.pk_group ");
		sql.append(" ) t3  ");
		sql.append("ON t1.plan_id = t3.plan_id ");
		sql.append(" LEFT JOIN ( ");
		sql.append("select count(s1.file_id) file_count,s2.task_id task_id from yjwy_patrol_task_file s1  ");
		sql.append("left join yjwy_patrol_taskeq s2 on s1.record_id = s2.pk_id ");
		sql.append("where s1.file_path <> '' ");
		sql.append("and s1.file_path is not null ");
		sql.append("and s1.file_name <> '' ");
		sql.append("and s1.file_name is not null ");
		sql.append("GROUP BY s2.task_id ");
		sql.append(" ) t4 ");
		sql.append("ON t1.task_id = t4.task_id ");
		sql.append(" where t1.task_state = 1 and t1.pk_crop= '" + DeviceUtil.getPkCrop() + "' ");
		// 计划类型
		if (null != task_type && "" != task_type) {
			sql.append(" AND t1.task_type = '" + task_type + "' ");
		}
		// 人员选择
		if (null != pk_user && !"default".equals(pk_user)) {
			sql.append(" AND t1.executor_id = '" + pk_user + "' ");
		}
		// 任务状态
		if (null != task_dealt && "" != task_dealt && !"-1".equals(task_dealt)) {
			sql.append(" AND t1.task_dealt = " + task_dealt + " ");
		}
		// 开始时间
		if (null != start_time && "" != start_time && null != end_time && "" != end_time) {
			sql.append(" AND t1.begin_time <= '" + end_time + "' AND t1.begin_time >= '" + start_time + "' ");
		} else {
//			sql.append(" AND t1.begin_time <= '" + DeviceUtil.date2String(new Date(), DeviceUtil.YMD) + "' AND t1.begin_time >= '" + DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMD) + "' ");
		}

		// 默认查询当前人所属项目所有数据
		Set<String> project_ids = params.getProject_ids();
		String projectids = "";
		if (project_ids != null) {
			String[] ids = project_ids.toArray(new String[] {});
			for (int i = 0; i < ids.length; i++) {
				projectids += ("'" + ids[i] + "'");
				if (i != ids.length - 1) {
					projectids += ",";
				}
			}
		}
		if (project_ids != null && project_ids.size() > 0) {
			sql.append(" AND t3.pk_project in (" + projectids + ")");
		} else {
			sql.append(" AND 1=2");
		}

		sql.append(" ORDER BY t1.task_id ");
		if (flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}
}
