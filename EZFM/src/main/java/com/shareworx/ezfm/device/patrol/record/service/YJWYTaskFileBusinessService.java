package com.shareworx.ezfm.device.patrol.record.service;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保附件表业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTaskFileBusinessService extends BusinessService<YJWYTaskFileModel> {

	String ID = "yJWYTaskFileBusinessService";
	
	/**
	 * 查询巡检维保附件表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保附件表
	 */
	YJWYTaskFileModel[] load(YJWYTaskFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel[] save(YJWYTaskFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel[] update(YJWYTaskFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel[] delete(YJWYTaskFileModel[] models) throws ShareworxServiceException;
}
