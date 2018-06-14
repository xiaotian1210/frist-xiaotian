package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;
import java.util.Map;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;

/**
 * 核查标准业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface InspectStandardBusinessService extends BusinessService<InspectStandardModel> {

	String ID = "inspectStandardBusinessService";
	
	/**
	 * 查询核查标准
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel[] query(Query query) throws ShareworxServiceException;

	/**
	 * 加载核查标准
	 */
	InspectStandardModel[] load(InspectStandardModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存核查标准
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel[] save(InspectStandardModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel[] update(InspectStandardModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除核查标准
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel[] delete(InspectStandardModel[] models) throws ShareworxServiceException;
	
}
