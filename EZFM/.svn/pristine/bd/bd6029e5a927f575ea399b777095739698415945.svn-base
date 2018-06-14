package com.shareworx.ezfm.baseinfo.type.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 资源类型持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface ResourceTypeDao {

	String ID = "resourcetypeDao";
	
	/**
	 * 保存资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(ResourcetypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ResourcetypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源类型
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ResourcetypeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改资源类型
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除资源类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(ResourcetypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源类型
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源类型
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询资源类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询资源类型条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
