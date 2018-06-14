package com.shareworx.ezfm.baseinfo.type.service;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 资源类型业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface ResourceTypeBusinessService extends BusinessService<ResourcetypeModel> {

	String ID = "resourcetypeBusinessService";
	
	/**
	 * 查询资源类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源类型
	 */
	ResourcetypeModel[] load(ResourcetypeModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel[] save(ResourcetypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel[] update(ResourcetypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel[] delete(ResourcetypeModel[] models) throws ShareworxServiceException;
}
