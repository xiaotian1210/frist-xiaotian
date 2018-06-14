package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 属性名称表持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface AttributeNameDao {

	String ID = "attributeNameDao";
	
	/**
	 * 保存属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(AttributeNameModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(AttributeNameModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改属性名称表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(AttributeNameModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改属性名称表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除属性名称表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(AttributeNameModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除属性名称表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询属性名称表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询属性名称表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询属性名称表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询属性名称表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
