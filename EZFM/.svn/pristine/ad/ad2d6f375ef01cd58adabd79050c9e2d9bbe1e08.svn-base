package com.shareworx.ezfm.device.patrol.task.service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保任务表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTaskBusinessService extends BusinessService<YJWYTaskModel> {

	String ID = "yJWYTaskBusinessService";
	
	/**
	 * 查询巡检维保任务表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保任务表
	 */
	YJWYTaskModel[] load(YJWYTaskModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel[] save(YJWYTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel[] update(YJWYTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel[] delete(YJWYTaskModel[] models) throws ShareworxServiceException;
}
