package com.shareworx.ezfm.performance.sign.service;

import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 签到管理业务操作接口
 * @author lingwei.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSignBusinessService extends BusinessService<YJWYSignModel> {

	String ID = "yJWYSignBusinessService";
	
	/**
	 * 查询签到管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载签到管理
	 */
	YJWYSignModel[] load(YJWYSignModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel[] save(YJWYSignModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel[] update(YJWYSignModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel[] delete(YJWYSignModel[] models) throws ShareworxServiceException;
}
