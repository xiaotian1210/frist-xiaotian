package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 核查标准人员中间表业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardUserBusinessService extends BusinessService<StandardUserModel> {

	String ID = "standardUserBusinessService";
	
	/**
	 * 查询核查标准人员中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查标准人员中间表
	 */
	StandardUserModel[] load(StandardUserModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel[] save(StandardUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel[] update(StandardUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel[] delete(StandardUserModel[] models) throws ShareworxServiceException;
}
