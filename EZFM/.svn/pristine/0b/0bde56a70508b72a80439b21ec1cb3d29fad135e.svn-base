package com.shareworx.ezfm.problem.nexus.proanduser.service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 接口人员关系表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectInfoUserNexusBusinessService extends BusinessService<YJWYProjectInfoUserNexusModel> {

	String ID = "yJWYProjectInfoUserNexusBusinessService";
	
	/**
	 * 查询接口人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询项目接口人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] findUserByProjectId(String projectId,String userName,int start,int limit) throws ShareworxServiceException;
	/**
	 * 加载接口人员关系表
	 */
	YJWYProjectInfoUserNexusModel[] load(YJWYProjectInfoUserNexusModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel[] save(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel[] update(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel[] delete(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
}
