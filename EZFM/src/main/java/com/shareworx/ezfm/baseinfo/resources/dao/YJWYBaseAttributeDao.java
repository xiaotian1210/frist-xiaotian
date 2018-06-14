package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 基础属性表持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYBaseAttributeDao {

	String ID = "yjwyBaseAttributeDao";
	
	/**
	 * 保存基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改基础属性表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYBaseAttributeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改基础属性表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除基础属性表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除基础属性表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询基础属性表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询基础属性表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询基础属性表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询基础属性表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
