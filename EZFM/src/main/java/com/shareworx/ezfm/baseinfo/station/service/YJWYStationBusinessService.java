package com.shareworx.ezfm.baseinfo.station.service;

import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 岗位管理业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYStationBusinessService extends BusinessService<YJWYStationModel> {

	String ID = "yJWYStationBusinessService";
	
	/**
	 * 查询岗位管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载岗位管理
	 */
	YJWYStationModel[] load(YJWYStationModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel[] save(YJWYStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel[] update(YJWYStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel[] delete(YJWYStationModel[] models) throws ShareworxServiceException;
}
