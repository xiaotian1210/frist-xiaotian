package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工艺程序步骤子表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPmpsBusinessService extends BusinessService<YJWYPmpsModel> {

	String ID = "yJWYPmpsBusinessService";
	
	/**
	 * 查询工艺程序步骤子表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工艺程序步骤子表
	 */
	YJWYPmpsModel[] load(YJWYPmpsModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel[] save(YJWYPmpsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel[] update(YJWYPmpsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel[] delete(YJWYPmpsModel[] models) throws ShareworxServiceException;
}
