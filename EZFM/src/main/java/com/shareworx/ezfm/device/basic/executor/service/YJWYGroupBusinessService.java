package com.shareworx.ezfm.device.basic.executor.service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 巡检/维保人员分组业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYGroupBusinessService extends BusinessService<YJWYGroupModel> {

	String ID = "yJWYGroupBusinessService";
	
	/**
	 * 查询巡检/维保人员分组
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检/维保人员分组
	 */
	YJWYGroupModel[] load(YJWYGroupModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel[] save(YJWYGroupModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel[] update(YJWYGroupModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel[] delete(YJWYGroupModel[] models) throws ShareworxServiceException;
}
