package com.shareworx.ezfm.device.basic.executor.service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 分组和人员关系表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYGroupUserBusinessService extends BusinessService<YJWYGroupUserModel> {

	String ID = "yJWYGroupUserBusinessService";
	
	/**
	 * 查询分组和人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载分组和人员关系表
	 */
	YJWYGroupUserModel[] load(YJWYGroupUserModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel[] save(YJWYGroupUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel[] update(YJWYGroupUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel[] delete(YJWYGroupUserModel[] models) throws ShareworxServiceException;
}
