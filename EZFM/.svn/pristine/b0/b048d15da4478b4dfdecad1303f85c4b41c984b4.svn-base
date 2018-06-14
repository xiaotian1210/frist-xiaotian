package com.shareworx.ezfm.device.patrol.plan.service;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 计划设备中间表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPlanEqBusinessService extends BusinessService<YJWYPlanEqModel> {

	String ID = "yJWYPlanEqBusinessService";
	
	/**
	 * 查询计划设备中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载计划设备中间表
	 */
	YJWYPlanEqModel[] load(YJWYPlanEqModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存计划设备中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel[] save(YJWYPlanEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存计划设备中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel[] update(YJWYPlanEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除计划设备中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel[] delete(YJWYPlanEqModel[] models) throws ShareworxServiceException;
}
