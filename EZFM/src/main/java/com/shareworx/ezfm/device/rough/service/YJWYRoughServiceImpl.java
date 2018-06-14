package com.shareworx.ezfm.device.rough.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.device.rough.dao.YJWYRoughDao;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 设备概况业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYRoughService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYRoughServiceImpl implements YJWYRoughService {
	@Autowired
	@Qualifier(YJWYRoughDao.ID)
	private YJWYRoughDao yjwyRoughDao;

	public void setYjwyRoughDao(YJWYRoughDao yjwyRoughDao) {
		this.yjwyRoughDao = yjwyRoughDao;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProjectService;

	public void setSiteProjectService(YJWYSiteProjectService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 查询设备概况数值
	 * 
	 * @param paramEntity
	 * @return
	 */
	@Override
	public Map<String, Object> queryRatio(ParamEntity paramEntity) {
		String pk_area = paramEntity.getPk_area();
		String pk_project = paramEntity.getPk_project();
		String start_time = paramEntity.getStart_time();
		String end_time = paramEntity.getEnd_time();
		StringBuilder siteSql = null;
		StringBuilder projectSql = null;
		StringBuilder areaSql = null;
		StringBuilder allTaskSql = null;
		StringBuilder finishTaskSql = null;
		// 如果项目不为空，则根据项目查询
		if (null != pk_project && "" != pk_project && !"default".equals(pk_project)) {

//			String[] siteIds = siteProjectService.queryIds(new String[] { pk_project });
			String[] siteIds = new String[] { pk_project };
//			// 获取fm项目sql语句
			if (DeviceUtil.arrayIsNotEmpty(siteIds)) {
				siteSql = new StringBuilder(DeviceUtil.getInNotInSql("site_id", QueryContents.TYPE_IN, siteIds));
			}
			projectSql = new StringBuilder(" t.pk_project='" + pk_project + "' ");
			// 如果项目为空，区域不为空，则根据区域查询
		} else if (null != pk_area && "" != pk_area && !"default".equals(pk_area)) {
			// 根据区域获取项目
			YJWYProjectModel[] projectModels = deviceService.queryProjectModelsByPkArea(pk_area);
			String[] proIds = DeviceUtil.getPrimaryKeyValue(projectModels);
//			String[] siteIds = siteProjectService.queryIds(proIds);
//			if (DeviceUtil.arrayIsNotEmpty(siteIds)) {
//				siteSql = new StringBuilder(DeviceUtil.getInNotInSql("site_id", QueryContents.TYPE_IN, siteIds));
//			}
			if (DeviceUtil.arrayIsNotEmpty(proIds)) {
				siteSql = new StringBuilder(DeviceUtil.getInNotInSql("site_id", QueryContents.TYPE_IN, proIds));
			}
			areaSql = new StringBuilder(" t.pk_area = '" + pk_area + "' ");
		}
		// 设置时间筛选sql
		if (null != start_time && null != end_time && "" != start_time && "" != end_time) {
			allTaskSql = new StringBuilder(" t.begin_time >= '" + start_time + "' " + "AND t.end_time <= '" + end_time + "' ");
			finishTaskSql = new StringBuilder(" t.finish_time >= '" + start_time + "' " + "AND t.finish_time <= '" + end_time + "' ");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.intactRatio,b.checkRatio,c.maintRatio from ");
		sql.append(" (select CAST(b.num/a.num*100 as decimal(6, 2)) intactRatio from ");
		sql.append(" (select count(*) num from yjwy_fmdata_eq where pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 设备筛选条件
		if (null != siteSql) {
			sql.append(" and " + siteSql);
		}
		sql.append(" ) a , (select count(*) num from yjwy_fmdata_eq where status = 1 and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 设备筛选条件
		if (null != siteSql) {
			sql.append(" and " + siteSql);
		}
		sql.append(" ) b ) a, ");
		sql.append(" (select CAST(b.num/a.num*100 as decimal(6, 2)) checkRatio from ");
		sql.append("(select count(*) num from (select task.task_id task_id,task.plan_id plan_id,");
		sql.append("task.task_type task_type,task.task_state task_state,plan.pk_area pk_area,");
		sql.append("task.begin_time begin_time,task.end_time end_time,task.pk_crop pk_crop,task.finish_time finish_time,");
		sql.append("plan.pk_project pk_project from yjwy_patrol_task task ");
		sql.append("LEFT JOIN yjwy_patrol_plan plan ON task.plan_id = plan.plan_id ) t ");
		sql.append("where t.task_type = 0 and t.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 全部巡检任务筛选条件
		if (null != projectSql) {
			sql.append(" and " + projectSql);
		}
		if (null != areaSql) {
			sql.append(" and " + areaSql);
		}
		if (null != allTaskSql) {
			sql.append(" and " + allTaskSql);
		}
		sql.append(" ) a,(select count(*) num from (select task.task_id task_id,task.plan_id plan_id, ");
		sql.append("task.task_type task_type,task.task_state task_state,plan.pk_area pk_area,");
		sql.append("task.begin_time begin_time,task.end_time end_time,task.finish_time finish_time,task.pk_crop pk_crop,");
		sql.append("plan.pk_project pk_project from yjwy_patrol_task task ");
		sql.append("LEFT JOIN yjwy_patrol_plan plan ON task.plan_id = plan.plan_id ) t ");
		sql.append("where t.task_type = 0 AND t.task_state IN (1,3) and t.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 完成巡检任务筛选条件
		if (null != projectSql) {
			sql.append(" and " + projectSql);
		}
		if (null != areaSql) {
			sql.append(" and " + areaSql);
		}
		if (null != allTaskSql) {
			sql.append(" and " + allTaskSql);
		}
		if (null != finishTaskSql) {
			sql.append(" and " + finishTaskSql);
		}
		sql.append(" ) b ) b, ");
		sql.append(" (select CAST(b.num/a.num*100 as decimal(6, 2)) maintRatio from  ");
		sql.append("(select count(*) num from (select task.task_id task_id,task.plan_id plan_id,");
		sql.append("task.task_type task_type,task.task_state task_state,plan.pk_area pk_area,");
		sql.append("task.begin_time begin_time,task.end_time end_time,task.finish_time finish_time,task.pk_crop pk_crop,");
		sql.append("plan.pk_project pk_project from yjwy_patrol_task task ");
		sql.append("JOIN yjwy_patrol_plan plan ON task.plan_id = plan.plan_id ) t ");
		sql.append("where t.task_type = 1 and t.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 全部维保任务筛选条件
		if (null != projectSql) {
			sql.append(" and " + projectSql);
		}
		if (null != areaSql) {
			sql.append(" and " + areaSql);
		}
		if (null != allTaskSql) {
			sql.append(" and " + allTaskSql);
		}
		sql.append(" ) a,(select count(*) num from (select task.task_id task_id,task.plan_id plan_id,");
		sql.append("task.task_type task_type,task.task_state task_state,plan.pk_area pk_area,");
		sql.append("task.begin_time begin_time,task.end_time end_time,task.finish_time finish_time,task.pk_crop pk_crop,");
		sql.append("plan.pk_project pk_project from yjwy_patrol_task task ");
		sql.append("JOIN yjwy_patrol_plan plan ON task.plan_id = plan.plan_id ) t ");
		sql.append("where t.task_type = 1 AND t.task_state IN (1,3)  and t.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 完成维保任务筛选条件
		if (null != projectSql) {
			sql.append(" and " + projectSql);
		}
		if (null != areaSql) {
			sql.append(" and " + areaSql);
		}
		if (null != allTaskSql) {
			sql.append(" and " + allTaskSql);
		}
		if (null != finishTaskSql) {
			sql.append(" and " + finishTaskSql);
		}
		sql.append(" ) b ) c ");
		return yjwyRoughDao.queryRatio(sql.toString());
	}

}
