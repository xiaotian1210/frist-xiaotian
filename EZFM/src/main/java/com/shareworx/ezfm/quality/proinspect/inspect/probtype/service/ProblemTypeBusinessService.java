package com.shareworx.ezfm.quality.proinspect.inspect.probtype.service;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 问题类型业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemTypeBusinessService extends BusinessService<ProblemTypeModel> {

	String ID = "problemTypeBusinessService";
	
	/**
	 * 查询问题类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载问题类型
	 */
	ProblemTypeModel[] load(ProblemTypeModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel[] save(ProblemTypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel[] update(ProblemTypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel[] delete(ProblemTypeModel[] models) throws ShareworxServiceException;
}
