package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 资源属性与资源关联领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourceAttributePkResourceDomainService {

	
	String ID = "yjwyResourceAttributePkResourceDomainService";
	
	/**
	 * 新增保存资源属性与资源关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> save(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源属性与资源关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> update(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源属性与资源关联领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> update(List<String> editFields, YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除资源属性与资源关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除资源属性与资源关联领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源属性与资源关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源属性与资源关联领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条资源属性与资源关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceAttributePkResourceModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源属性与资源关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源属性与资源关联对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceAttributePkResourceModel> queryByExample(YJWYResourceAttributePkResourceModel model) throws ShareworxServiceException;
}
