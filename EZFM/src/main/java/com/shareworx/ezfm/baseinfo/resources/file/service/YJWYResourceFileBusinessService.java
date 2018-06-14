package com.shareworx.ezfm.baseinfo.resources.file.service;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 资源附件业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourceFileBusinessService extends BusinessService<YJWYResourceFileModel> {

	String ID = "yJWYResourceFileBusinessService";
	
	/**
	 * 查询资源附件
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源附件
	 */
	YJWYResourceFileModel[] load(YJWYResourceFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel[] save(YJWYResourceFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel[] update(YJWYResourceFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel[] delete(YJWYResourceFileModel[] models) throws ShareworxServiceException;
}
