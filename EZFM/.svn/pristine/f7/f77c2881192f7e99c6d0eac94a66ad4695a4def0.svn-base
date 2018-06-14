package com.shareworx.ezfm.worktask.record.service;

import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工单详情表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskDetailsRecordBusinessService extends BusinessService<YJWYWorkTaskDetailsRecordModel> {

	String ID = "yJWYWorkTaskDetailsRecordBusinessService";
	
	/**
	 * 查询工单详情表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单详情表
	 */
	YJWYWorkTaskDetailsRecordModel[] load(YJWYWorkTaskDetailsRecordModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel[] save(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel[] update(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel[] delete(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException;
}
