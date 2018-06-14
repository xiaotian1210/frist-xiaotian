package com.shareworx.ezfm.baseinfo.rolefunc.service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 基于角色的功能权限业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleFuncBusinessService extends BusinessService<YJWYRoleFuncModel> {

	String ID = "yJWYRoleFuncBusinessService";
	
	/**
	 * 查询基于角色的功能权限
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载基于角色的功能权限
	 */
	YJWYRoleFuncModel[] load(YJWYRoleFuncModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel[] save(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel[] update(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel[] delete(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
}
