package com.shareworx.ezfm.baseinfo.role.service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 角色业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleBusinessService extends BusinessService<YJWYRoleModel> {

	String ID = "yjwyRoleBusinessService";
	
	/**
	 * 查询角色
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载角色
	 */
	YJWYRoleModel[] load(YJWYRoleModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel[] save(YJWYRoleModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel[] update(YJWYRoleModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel[] delete(YJWYRoleModel[] models) throws ShareworxServiceException;
}
