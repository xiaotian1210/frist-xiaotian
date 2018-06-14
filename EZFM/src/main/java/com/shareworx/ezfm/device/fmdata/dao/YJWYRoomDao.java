package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 房间位置信息持久化操作接口
 *
 * @author lihui
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYRoomDao {

	String ID = "yJWYRoomDao";
	
	/**
	 * 保存房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改房间位置信息
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoomModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改房间位置信息
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除房间位置信息
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYRoomModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除房间位置信息
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询房间位置信息
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询房间位置信息
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询房间位置信息条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询房间位置信息
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
