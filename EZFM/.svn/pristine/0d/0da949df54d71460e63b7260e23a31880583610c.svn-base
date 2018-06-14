package com.shareworx.ezfm.device.patrol.plan.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;

/**
 * 巡检维保计划业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPlanBusinessService extends BusinessService<YJWYPlanModel> {

	String ID = "yJWYPlanBusinessService";
	
	/**
	 * 查询巡检维保计划
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保计划
	 */
	YJWYPlanModel[] load(YJWYPlanModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel[] save(YJWYPlanModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel[] update(YJWYPlanModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel[] delete(YJWYPlanModel[] models) throws ShareworxServiceException;
}
