package com.shareworx.ezfm.baseinfo.project.service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 项目管理业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectBusinessService extends BusinessService<YJWYProjectModel> {

	String ID = "yJWYProjectBusinessService";
	
	/**
	 * 查询项目管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载项目管理
	 */
	YJWYProjectModel[] load(YJWYProjectModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存项目管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel[] save(YJWYProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel[] update(YJWYProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除项目管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel[] delete(YJWYProjectModel[] models) throws ShareworxServiceException;
	
	YJWYProjectModel queryById(String id) throws ShareworxServiceException;;
}
