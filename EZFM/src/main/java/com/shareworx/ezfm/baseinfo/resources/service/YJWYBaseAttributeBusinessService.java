package com.shareworx.ezfm.baseinfo.resources.service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 基础属性表业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYBaseAttributeBusinessService extends BusinessService<YJWYBaseAttributeModel> {

	String ID = "yjwyBaseAttributeBusinessService";
	
	/**
	 * 查询基础属性表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载基础属性表
	 */
	YJWYBaseAttributeModel[] load(YJWYBaseAttributeModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel[] save(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel[] update(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel[] delete(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
}
