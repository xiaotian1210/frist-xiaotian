package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.ezfm.device.list.service.YJWYListService;
import com.shareworx.ezfm.device.patrol.plan.dao.YJWYPlanDao;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanService;
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskDao;
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskEqDao;

/**
 * 巡检维保任务service实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYTaskFileService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYTaskFileServiceImpl implements YJWYTaskFileService {
	private String querySql = null;

	@Autowired
	@Qualifier(YJWYListService.ID)
	private YJWYListService listService;

	public void setListService(YJWYListService listService) {
		this.listService = listService;
	}

	@Autowired
	@Qualifier(YJWYPlanBusinessService.ID)
	private YJWYPlanBusinessService planBusinessService;

	public void setPlanBusinessService(YJWYPlanBusinessService planBusinessService) {
		this.planBusinessService = planBusinessService;
	}


	@Autowired
	@Qualifier(YJWYPlanService.ID)
	private YJWYPlanService planService;

	public void setPlanService(YJWYPlanService planService) {
		this.planService = planService;
	}

	@Autowired
	@Qualifier(YJWYPlanEqBusinessService.ID)
	private YJWYPlanEqBusinessService planEqBusinessService;

	public void setPlanEqBusinessService(YJWYPlanEqBusinessService planEqBusinessService) {
		this.planEqBusinessService = planEqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYTaskDao.ID)
	private YJWYTaskDao taskDao;

	public void setTaskDao(YJWYTaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Autowired
	@Qualifier(YJWYTaskEqDao.ID)
	private YJWYTaskEqDao taskEqDao;

	public void setTaskEqDao(YJWYTaskEqDao taskEqDao) {
		this.taskEqDao = taskEqDao;
	}

	@Autowired
	@Qualifier(YJWYPlanDao.ID)
	private YJWYPlanDao planDao;

	public void setPlanDao(YJWYPlanDao planDao) {
		this.planDao = planDao;
	}


	/**
	 * 任务查询列表
	 */
	public List<Map<String, Object>> queryMap(String task_id,String eq_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		this.querySql = sql.toString();
		return taskDao.queryMap(this.querySql);
	}
}
