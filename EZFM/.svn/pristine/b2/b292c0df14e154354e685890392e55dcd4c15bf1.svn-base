package com.shareworx.ezfm.performance.leave.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;

/**
 * 休假备案业务操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYLeaveBusinessService extends BusinessService<YJWYLeaveModel> {

	String ID = "yJWYLeaveBusinessService";
	
	/**
	 * 查询休假备案
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载休假备案
	 */
	YJWYLeaveModel[] load(YJWYLeaveModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel[] save(YJWYLeaveModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel[] update(YJWYLeaveModel[] models) throws ShareworxServiceException;
	YJWYLeaveModel[] updateOperation(YJWYLeaveModel[] models) throws ShareworxServiceException;
	/**
	 * 删除休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel[] delete(YJWYLeaveModel[] models) throws ShareworxServiceException;
	
}
