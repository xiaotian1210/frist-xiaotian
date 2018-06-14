package com.shareworx.ezfm.system.function.service;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 系统功能业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYFunctionBusinessService extends BusinessService<YJWYFunctionModel> {

	String ID = "yJWYFunctionBusinessService";
	
	/**
	 * 查询系统功能
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载系统功能
	 */
	YJWYFunctionModel[] load(YJWYFunctionModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel[] save(YJWYFunctionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel[] update(YJWYFunctionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel[] delete(YJWYFunctionModel[] models) throws ShareworxServiceException;
}
