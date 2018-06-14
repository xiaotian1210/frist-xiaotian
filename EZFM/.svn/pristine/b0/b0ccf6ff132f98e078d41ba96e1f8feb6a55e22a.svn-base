package com.shareworx.ezfm.worktask.projectuser.service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 项目接口人员业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskProjectUserBusinessService extends BusinessService<YJWYWorkTaskProjectUserModel> {

	String ID = "yJWYWorkTaskProjectUserBusinessService";
	
	/**
	 * 查询项目接口人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel[] query(Query query) throws ShareworxServiceException;
	
	
	/**
	 * 查询项目接口人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] findUserByProjectId(String projectId,String userName,int start,int limit) throws ShareworxServiceException;
	
	/**
	 * 查询所有项目接口人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] findUserAllByProjectId(String projectId) throws ShareworxServiceException;
	/**
	 * 加载项目接口人员
	 */
	YJWYWorkTaskProjectUserModel[] load(YJWYWorkTaskProjectUserModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存项目接口人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel[] save(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目接口人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel[] update(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除项目接口人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel[] delete(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException;
}
