package com.shareworx.ezfm.baseinfo.role.service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 角色人员关系业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleUserBusinessService extends BusinessService<YJWYRoleUserModel> {

	String ID = "yjwyRoleUserBusinessService";
	
	/**
	 * 查询角色人员关系
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载角色人员关系
	 */
	YJWYRoleUserModel[] load(YJWYRoleUserModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存角色人员关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel[] save(YJWYRoleUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色人员关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel[] update(YJWYRoleUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除角色人员关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel[] delete(YJWYRoleUserModel[] models) throws ShareworxServiceException;
}
