package com.shareworx.ezfm.problem.classadmin.service;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 测试用的业务操作接口
 * @author jiangwei.peng
 * @version since Shareworx platform 3.0
 *
 */
public interface yjwyjwtest_csBusinessService extends BusinessService<yjwyjwtest_csModel> {

	String ID = "yjwyjwtest_csBusinessService";
	
	/**
	 * 查询测试用的
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载测试用的
	 */
	yjwyjwtest_csModel[] load(yjwyjwtest_csModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel[] save(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel[] update(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel[] delete(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
}
