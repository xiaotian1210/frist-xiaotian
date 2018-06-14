package com.shareworx.ezfm.device.siteproject.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * FM与YJWY项目关联表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSiteProjectBusinessService extends BusinessService<YJWYSiteProjectModel> {

	String ID = "yJWYSiteProjectBusinessService";
	
	/**
	 * 查询FM与YJWY项目关联表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载FM与YJWY项目关联表
	 */
	YJWYSiteProjectModel[] load(YJWYSiteProjectModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel[] save(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel[] update(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel[] delete(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
}
