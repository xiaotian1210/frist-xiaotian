package com.shareworx.ezfm.device.patrol.plan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 巡检维保计划业务操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYPlanService {

	String ID = "yJWYPlanService";

	/**
	 * 查询需要生成任务的计划集
	 * 
	 * @return
	 */
	YJWYPlanModel[] queryModelsByNextTime(String now_time);

	/**
	 * 查询设备列表
	 * 
	 * @param plan_id
	 * @return
	 */
	List<Map<String, Object>> queryEqMap(String plan_id);

	/**
	 * 删除多条数据
	 * 
	 * @param models
	 * @return
	 */
	YJWYPlanModel[] deleteModels(YJWYPlanModel[] models,String[] plan_ids);

	/**
	 * 根据主键查询单条数据
	 * 
	 * @param id
	 * @return
	 */
	YJWYPlanModel queryOne(Serializable id);

	/**
	 * 插入多条数据
	 * 
	 * @param models
	 * @return
	 */
	YJWYPlanModel[] saveModels(YJWYPlanModel[] models);

	/**
	 * 修改数据
	 * 
	 * @param models
	 * @return
	 */
	YJWYPlanModel[] updateModels(YJWYPlanModel[] models);

	/**
	 * 联合查询
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryMap(ParamEntity params);

	/**
	 * 联合数量查询
	 * 
	 * @param params
	 * @return
	 */
	Long queryCount(ParamEntity params);

	/**
	 * 获取下一次任务生成时间
	 * 
	 * @param model
	 * @return
	 */
	String getInitNext_time(YJWYPlanModel model);

}
