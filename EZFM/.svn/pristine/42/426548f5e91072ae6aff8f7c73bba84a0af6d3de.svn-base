package com.shareworx.ezfm.device.timer.service;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 定时任务执行记录业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTimerBusinessService extends BusinessService<YJWYTimerModel> {

	String ID = "yJWYTimerBusinessService";
	
	/**
	 * 查询定时任务执行记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载定时任务执行记录
	 */
	YJWYTimerModel[] load(YJWYTimerModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel[] save(YJWYTimerModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel[] update(YJWYTimerModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel[] delete(YJWYTimerModel[] models) throws ShareworxServiceException;
}
