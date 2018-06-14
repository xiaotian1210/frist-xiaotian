package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 资源属性与资源关联持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYResourceAttributePkResourceDao {

	String ID = "yjwyResourceAttributePkResourceDao";
	
	/**
	 * 保存资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源属性与资源关联
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourceAttributePkResourceModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改资源属性与资源关联
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除资源属性与资源关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException;
	int excuteUpdateSql(String sql);
	/**
	 * 根据条件删除资源属性与资源关联
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源属性与资源关联
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询资源属性与资源关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询资源属性与资源关联条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源属性与资源关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
