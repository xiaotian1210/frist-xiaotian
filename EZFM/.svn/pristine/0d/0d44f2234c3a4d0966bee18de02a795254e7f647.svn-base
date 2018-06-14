package com.shareworx.ezfm.baseinfo.resources.service;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 属性名称表业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface AttributeNameBusinessService extends BusinessService<AttributeNameModel> {

	String ID = "attributeNameBusinessService";
	
	/**
	 * 查询属性名称表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载属性名称表
	 */
	AttributeNameModel[] load(AttributeNameModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel[] save(AttributeNameModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel[] update(AttributeNameModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel[] delete(AttributeNameModel[] models) throws ShareworxServiceException;
}
