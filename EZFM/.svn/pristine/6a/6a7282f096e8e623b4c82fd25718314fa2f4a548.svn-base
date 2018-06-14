package com.shareworx.ezfm.system.dict.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 数据字典持久化操作接口
 *
 * @author ying.chen
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYDictDao {

	String ID = "yJWYDictDao";
	
	/**
	 * 保存数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改数据字典
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYDictModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改数据字典
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除数据字典
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询数据字典
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询数据字典
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询数据字典条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询数据字典
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
