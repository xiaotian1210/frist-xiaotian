package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 版本与岗位中间表业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface EditionStationBusinessService extends BusinessService<EditionStationModel> {

	String ID = "editionStationBusinessService";
	
	/**
	 * 查询版本与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载版本与岗位中间表
	 */
	EditionStationModel[] load(EditionStationModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel[] save(EditionStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel[] update(EditionStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel[] delete(EditionStationModel[] models) throws ShareworxServiceException;
}
