package com.shareworx.ezfm.device.patrol.task.service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保任务表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTaskEqBusinessService extends BusinessService<YJWYTaskEqModel> {

	String ID = "yJWYTaskEqBusinessService";
	
	/**
	 * 查询巡检维保任务表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskEqModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保任务表
	 */
	YJWYTaskEqModel[] load(YJWYTaskEqModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskEqModel[] save(YJWYTaskEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskEqModel[] update(YJWYTaskEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskEqModel[] delete(YJWYTaskEqModel[] models) throws ShareworxServiceException;
}
