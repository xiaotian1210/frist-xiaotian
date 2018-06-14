package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工艺程序主表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPmpBusinessService extends BusinessService<YJWYPmpModel> {

	String ID = "yJWYPmpBusinessService";
	
	/**
	 * 查询工艺程序主表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工艺程序主表
	 */
	YJWYPmpModel[] load(YJWYPmpModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存工艺程序主表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel[] save(YJWYPmpModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序主表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel[] update(YJWYPmpModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工艺程序主表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel[] delete(YJWYPmpModel[] models) throws ShareworxServiceException;
}
