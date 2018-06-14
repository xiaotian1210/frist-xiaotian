package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * FM项目表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSiteBusinessService extends BusinessService<YJWYSiteModel> {

	String ID = "yJWYSiteBusinessService";
	
	/**
	 * 查询FM项目表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载FM项目表
	 */
	YJWYSiteModel[] load(YJWYSiteModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存FM项目表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel[] save(YJWYSiteModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM项目表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel[] update(YJWYSiteModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除FM项目表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel[] delete(YJWYSiteModel[] models) throws ShareworxServiceException;
}
