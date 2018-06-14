package com.shareworx.ezfm.energyloss.tabledefinition.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 能耗数据修改表持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWJEnergyUpdateDao {

	String ID = "yJWJEnergyUpdateDao";
	
	/**
	 * 保存能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改能耗数据修改表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWJEnergyUpdateModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改能耗数据修改表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除能耗数据修改表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询能耗数据修改表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询能耗数据修改表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询能耗数据修改表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询能耗数据修改表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
