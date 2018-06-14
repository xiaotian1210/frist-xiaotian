package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

import java.util.List;

/**
 * 房间位置信息业务操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoomBusinessService extends BusinessService<YJWYRoomModel> {

	String ID = "yJWYRoomBusinessService";
	
	/**
	 * 查询房间位置信息
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载房间位置信息
	 */
	YJWYRoomModel[] load(YJWYRoomModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel[] save(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel[] update(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel[] delete(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	String getRoomPlaceByResourceId(String resourceid);
	
	String getRoomPlaceByRoomId(String roomid);


	List<String>  getRoomIdsByResources(List<YJWYResourcesModel> list );
	
}
