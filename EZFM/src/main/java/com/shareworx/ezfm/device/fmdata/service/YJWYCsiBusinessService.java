package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 设备分类信息业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYCsiBusinessService extends BusinessService<YJWYCsiModel> {

	String ID = "yJWYCsiBusinessService";
	
	/**
	 * 查询设备分类信息
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载设备分类信息
	 */
	YJWYCsiModel[] load(YJWYCsiModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存设备分类信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel[] save(YJWYCsiModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备分类信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel[] update(YJWYCsiModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除设备分类信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel[] delete(YJWYCsiModel[] models) throws ShareworxServiceException;
}
