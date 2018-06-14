package com.shareworx.ezfm.energyloss.tabledefinition.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 电表持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYEnergyElectricDao {

	String ID = "yJWYEnergyElectricDao";
	
	/**
	 * 保存电表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改电表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改电表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEnergyElectricModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改电表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除电表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除电表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询电表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEnergyElectricModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询电表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询电表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询电表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEnergyElectricModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
