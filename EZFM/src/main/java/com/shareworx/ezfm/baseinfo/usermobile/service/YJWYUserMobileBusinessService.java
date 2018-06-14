package com.shareworx.ezfm.baseinfo.usermobile.service;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 用户与设备关系表业务操作接口
 * @author yuting.wang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYUserMobileBusinessService extends BusinessService<YJWYUserMobileModel> {

	String ID = "yJWYUserMobileBusinessService";
	
	/**
	 * 查询用户与设备关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载用户与设备关系表
	 */
	YJWYUserMobileModel[] load(YJWYUserMobileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel[] save(YJWYUserMobileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel[] update(YJWYUserMobileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel[] delete(YJWYUserMobileModel[] models) throws ShareworxServiceException;
}
