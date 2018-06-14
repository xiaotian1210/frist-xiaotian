package com.shareworx.ezfm.quality.proinspect.inspect.insptask.service;

import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 核查与整改任务业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface InspectTaskBusinessService extends BusinessService<InspectTaskModel> {

	String ID = "inspectTaskBusinessService";
	
	/**
	 * 查询核查与整改任务
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectTaskModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查与整改任务
	 */
	InspectTaskModel[] load(InspectTaskModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存核查与整改任务
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectTaskModel[] save(InspectTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查与整改任务
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectTaskModel[] update(InspectTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除核查与整改任务
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectTaskModel[] delete(InspectTaskModel[] models) throws ShareworxServiceException;
}
