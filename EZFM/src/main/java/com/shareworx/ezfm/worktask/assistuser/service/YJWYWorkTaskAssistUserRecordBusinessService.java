package com.shareworx.ezfm.worktask.assistuser.service;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工单协助人表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAssistUserRecordBusinessService extends BusinessService<YJWYWorkTaskAssistUserRecordModel> {

	String ID = "yJWYWorkTaskAssistUserRecordBusinessService";
	
	/**
	 * 查询工单协助人表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单协助人表
	 */
	YJWYWorkTaskAssistUserRecordModel[] load(YJWYWorkTaskAssistUserRecordModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel[] save(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel[] update(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel[] delete(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
}
