package com.shareworx.ezfm.pub.test.plms.service;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 测试plms业务操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface PlmsBusinessService extends BusinessService<PlmsModel> {

	String ID = "plmsBusinessService";
	
	/**
	 * 查询测试plms
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载测试plms
	 */
	PlmsModel[] load(PlmsModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel[] save(PlmsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel[] update(PlmsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel[] delete(PlmsModel[] models) throws ShareworxServiceException;
}
