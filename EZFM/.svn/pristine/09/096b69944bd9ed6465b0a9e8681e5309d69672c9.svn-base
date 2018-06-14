package com.shareworx.ezfm.baseinfo.station.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 岗位管理持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYStationDao {

	String ID = "yJWYStationDao";
	
	/**
	 * 保存岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改岗位管理
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改岗位管理
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除岗位管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除岗位管理
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询岗位管理
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询岗位管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询岗位管理条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询岗位管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
