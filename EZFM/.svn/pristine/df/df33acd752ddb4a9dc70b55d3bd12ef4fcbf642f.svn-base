package com.shareworx.ezfm.baseinfo.user.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;

/**
 * 系统用户业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYUserBusinessService extends BusinessService<YJWYUserModel> {

	String ID = "yjwyUserBusinessService";
	
	/**
	 * 查询系统用户
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载系统用户
	 */
	YJWYUserModel[] load(YJWYUserModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] save(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] update(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] delete(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 启用用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] execEnable(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 停用用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] execDisable(YJWYUserModel[] models) throws ShareworxServiceException;
	
	YJWYAreaModel[] getUserArea(String pk_user);
	
	YJWYProjectModel[] getUserProject(String pk_user);
	
	YJWYProjectModel[] getUserProject(String pk_user, String areaid);
	
}
