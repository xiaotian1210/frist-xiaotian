package com.shareworx.ezfm.worktask.areapersonnel.service;

import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 片区人员业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaPersonnelBusinessService extends BusinessService<YJWYWorkTaskAreaPersonnelModel> {

	String ID = "yJWYWorkTaskAreaPersonnelBusinessService";
	
	/**
	 * 查询片区人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区人员
	 */
	YJWYWorkTaskAreaPersonnelModel[] load(YJWYWorkTaskAreaPersonnelModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存片区人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel[] save(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel[] update(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除片区人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel[] delete(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
}
