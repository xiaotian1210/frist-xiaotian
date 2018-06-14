package com.shareworx.ezfm.baseinfo.area.service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 区域管理业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYAreaBusinessService extends BusinessService<YJWYAreaModel> {

	String ID = "yJWYAreaBusinessService";
	
	/**
	 * 查询区域管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载区域管理
	 */
	YJWYAreaModel[] load(YJWYAreaModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel[] save(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel[] update(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel[] delete(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	YJWYAreaModel queryById(String id) throws ShareworxServiceException;
}
