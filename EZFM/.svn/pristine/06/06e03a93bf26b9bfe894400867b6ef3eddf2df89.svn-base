package com.shareworx.ezfm.problem.record.service;

import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 报事记录业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProblemRecordBusinessService extends BusinessService<YJWYProblemRecordModel> {

	String ID = "yJWYProblemRecordBusinessService";
	
	/**
	 * 查询报事记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事记录
	 */
	YJWYProblemRecordModel[] load(YJWYProblemRecordModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存报事记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel[] save(YJWYProblemRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel[] update(YJWYProblemRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除报事记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel[] delete(YJWYProblemRecordModel[] models) throws ShareworxServiceException;
}
