package com.shareworx.ezfm.device.warn.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;

/**
 * eba设备报警信息业务操作接口
 * 
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEbaWarningBusinessService extends BusinessService<YJWYEbaWarningModel> {

	String ID = "yJWYEbaWarningBusinessService";


	/**
	 * 查询eba设备报警信息
	 * 
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel[] query(Query query) throws ShareworxServiceException;

	/**
	 * 加载eba设备报警信息
	 */
	YJWYEbaWarningModel[] load(YJWYEbaWarningModel model) throws ShareworxServiceException;

	/**
	 * 新增保存eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel[] save(YJWYEbaWarningModel[] models) throws ShareworxServiceException;

	/**
	 * 修改保存eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel[] update(YJWYEbaWarningModel[] models) throws ShareworxServiceException;

	/**
	 * 删除eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel[] delete(YJWYEbaWarningModel[] models) throws ShareworxServiceException;
}
