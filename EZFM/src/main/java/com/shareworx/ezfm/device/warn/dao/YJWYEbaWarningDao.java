package com.shareworx.ezfm.device.warn.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;

/**
 * eba设备报警信息持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYEbaWarningDao {

	String ID = "yJWYEbaWarningDao";

	/**
	 * 查询数据集合
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryList(String sql);

	/**
	 * 查询总数
	 * 
	 * @param sql
	 * @return
	 */
	Long queryCount(String sql);

	/**
	 * 保存eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException;

	/**
	 * 修改eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException;

	/**
	 * 修改eba设备报警信息
	 * 
	 * @param models
	 * @param include
	 *            {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude
	 *            {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEbaWarningModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;

	/**
	 * 根据条件修改eba设备报警信息
	 * 
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;

	/**
	 * 删除eba设备报警信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException;

	/**
	 * 根据条件删除eba设备报警信息
	 * 
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;

	/**
	 * 根据主键查询eba设备报警信息
	 * 
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询eba设备报警信息
	 * 
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> queryListByCondition(Query query) throws ShareworxServiceException;

	/**
	 * 查询eba设备报警信息条数
	 * 
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;

	/**
	 * 根据条件查询eba设备报警信息
	 * 
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel queryOneByCondition(Query query) throws ShareworxServiceException;

}
