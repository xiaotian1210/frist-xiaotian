package com.shareworx.ezfm.baseinfo.type.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 资源类型领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface ResourceTypeDomainService {

	
	String ID = "resourcetypeDomainService";
	
	/**
	 * 新增保存资源类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> save(ResourcetypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> update(ResourcetypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源类型领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> update(List<String> editFields, ResourcetypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除资源类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(ResourcetypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除资源类型领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源类型领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条资源类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	ResourcetypeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源类型对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ResourcetypeModel> queryByExample(ResourcetypeModel model) throws ShareworxServiceException;
}
