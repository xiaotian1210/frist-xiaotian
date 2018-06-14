package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 设备设施基本信息业务操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEqBusinessService extends BusinessService<YJWYEqModel> {

	String ID = "yJWYEqBusinessService";
	
	/**
	 * 查询设备设施基本信息
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载设备设施基本信息
	 */
	YJWYEqModel[] load(YJWYEqModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存设备设施基本信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel[] save(YJWYEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备设施基本信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel[] update(YJWYEqModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除设备设施基本信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel[] delete(YJWYEqModel[] models) throws ShareworxServiceException;
}
