package com.shareworx.ezfm.baseinfo.rolefunc.service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * APP手机权限业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYAPPRoleFuncBusinessService extends BusinessService<YJWYAPPRoleFuncModel> {

	String ID = "yJWYAPPRoleFuncBusinessService";
	
	/**
	 * 查询APP手机权限
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载APP手机权限
	 */
	YJWYAPPRoleFuncModel[] load(YJWYAPPRoleFuncModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存APP手机权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel[] save(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存APP手机权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel[] update(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除APP手机权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel[] delete(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException;
}
