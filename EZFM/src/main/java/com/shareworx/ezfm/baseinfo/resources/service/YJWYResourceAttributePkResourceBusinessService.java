package com.shareworx.ezfm.baseinfo.resources.service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 资源属性与资源关联业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourceAttributePkResourceBusinessService extends BusinessService<YJWYResourceAttributePkResourceModel> {

	String ID = "yjwyResourceAttributePkResourceBusinessService";
	
	/**
	 * 查询资源属性与资源关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源属性与资源关联
	 */
	YJWYResourceAttributePkResourceModel[] load(YJWYResourceAttributePkResourceModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel[] save(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel[] update(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel[] delete(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
}
