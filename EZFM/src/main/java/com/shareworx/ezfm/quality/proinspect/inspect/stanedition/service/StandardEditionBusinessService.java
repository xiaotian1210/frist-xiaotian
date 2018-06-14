package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 标准版本业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardEditionBusinessService extends BusinessService<StandardEditionModel> {

	String ID = "standardEditionBusinessService";
	
	/**
	 * 查询标准版本
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载标准版本
	 */
	StandardEditionModel[] load(StandardEditionModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel[] save(StandardEditionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel[] update(StandardEditionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel[] delete(StandardEditionModel[] models) throws ShareworxServiceException;
}
