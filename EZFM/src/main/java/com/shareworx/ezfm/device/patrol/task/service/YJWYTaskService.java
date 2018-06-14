package com.shareworx.ezfm.device.patrol.task.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 巡检维保任务service
 * @author jin.li
 *
 */
public interface YJWYTaskService {
	String ID = "yJWYTaskService";
	
	/**
	 * 获取过期任务集
	 */
	List<YJWYTaskModel> getExpiredTask();
	
	/**
	 * 判断过期任务，并修改状态
	 */
	void judgeExpired();
	
	/**
	 * 根据计划生成任务
	 * @return
	 */
	void getTaskModels();
	
	/**
	 * 任务列表查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryTaskMap(ParamEntity params);


	/**
	 * 任务数量查询
	 * @param params
	 * @return
	 */
	Long queryTaskCount(ParamEntity params);

	
	/**
	 * 查询计划设备关系集
	 * @param id
	 * @return
	 */
	YJWYPlanEqModel[] queryPlanEqModels(Serializable id);

	/**
	 * 获取计划下一次任务生成时间
	 * @param model
	 * @return
	 */
	String getNext_time(YJWYPlanModel model);

}
