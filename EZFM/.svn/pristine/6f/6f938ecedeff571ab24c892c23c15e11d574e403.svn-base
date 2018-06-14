package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 核查与整改记录业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface InspectRecordBusinessService extends BusinessService<InspectRecordModel> {

	String ID = "inspectRecordBusinessService";
	
	/**
	 * 查询核查与整改记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查与整改记录
	 */
	InspectRecordModel[] load(InspectRecordModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel[] save(InspectRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel[] update(InspectRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel[] delete(InspectRecordModel[] models) throws ShareworxServiceException;
}
