package com.shareworx.ezfm.baseinfo.resourceslog.service;

import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 记录资源空间日志表业务操作接口
 * @author kimguo
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourcesLogBusinessService extends BusinessService<YJWYResourcesLogModel> {

	String ID = "yJWYResourcesLogBusinessService";
	
	/**
	 * 查询记录资源空间日志表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载记录资源空间日志表
	 */
	YJWYResourcesLogModel[] load(YJWYResourcesLogModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel[] save(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel[] update(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel[] delete(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据接口传过来的日期返回保存或者更新的数据
	 */
	String SaUpRecords(String date) throws ShareworxServiceException;
	
	
	/**
	 * 根据接口传过来的日期返回删除的数据
	 */
	String deleteRecords(String date) throws ShareworxServiceException;
	
	
}
