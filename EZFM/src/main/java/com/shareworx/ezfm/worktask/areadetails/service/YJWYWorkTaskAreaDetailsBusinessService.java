package com.shareworx.ezfm.worktask.areadetails.service;

import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 片区详情业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaDetailsBusinessService extends BusinessService<YJWYWorkTaskAreaDetailsModel> {

	String ID = "yJWYWorkTaskAreaDetailsBusinessService";
	
	/**
	 * 查询片区详情
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区详情
	 */
	YJWYWorkTaskAreaDetailsModel[] load(YJWYWorkTaskAreaDetailsModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel[] save(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel[] update(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel[] delete(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
}
