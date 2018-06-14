package com.shareworx.ezfm.baseinfo.org.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;

/**
 * 组织架构业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface DefaultOrgBusinessService extends BusinessService<DefaultOrgModel> {

	String ID = "defaultOrgBusinessService";
	
	/**
	 * 查询组织架构
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载组织架构
	 */
	DefaultOrgModel[] load(DefaultOrgModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel[] save(DefaultOrgModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel[] update(DefaultOrgModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel[] delete(DefaultOrgModel[] models) throws ShareworxServiceException;
}
