package com.shareworx.ezfm.worktask.sonclass.service;

import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工单维修种类记录表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskSonClassRecordBusinessService extends BusinessService<YJWYWorkTaskSonClassRecordModel> {

	String ID = "yJWYWorkTaskSonClassRecordBusinessService";
	
	/**
	 * 查询工单维修种类记录表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单维修种类记录表
	 */
	YJWYWorkTaskSonClassRecordModel[] load(YJWYWorkTaskSonClassRecordModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存工单维修种类记录表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel[] save(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单维修种类记录表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel[] update(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工单维修种类记录表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel[] delete(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException;
}
