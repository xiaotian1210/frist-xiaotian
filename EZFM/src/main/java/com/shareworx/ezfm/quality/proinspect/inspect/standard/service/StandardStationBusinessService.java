package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 标准与岗位中间表业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardStationBusinessService extends BusinessService<StandardStationModel> {

	String ID = "standardStationBusinessService";
	
	/**
	 * 查询标准与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载标准与岗位中间表
	 */
	StandardStationModel[] load(StandardStationModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel[] save(StandardStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel[] update(StandardStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel[] delete(StandardStationModel[] models) throws ShareworxServiceException;
}
