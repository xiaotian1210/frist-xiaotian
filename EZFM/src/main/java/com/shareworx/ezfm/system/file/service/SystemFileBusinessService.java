package com.shareworx.ezfm.system.file.service;

import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 附件表业务操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface SystemFileBusinessService extends BusinessService<SystemFileModel> {

	String ID = "systemFileBusinessService";
	
	/**
	 * 查询附件表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载附件表
	 */
	SystemFileModel[] load(SystemFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel[] save(SystemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel[] update(SystemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel[] delete(SystemFileModel[] models) throws ShareworxServiceException;
}
