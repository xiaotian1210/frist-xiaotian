package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 属性名称表领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface AttributeNameDomainService {

	
	String ID = "attributeNameDomainService";
	
	/**
	 * 新增保存属性名称表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> save(AttributeNameModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存属性名称表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> update(AttributeNameModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存属性名称表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> update(List<String> editFields, AttributeNameModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除属性名称表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(AttributeNameModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除属性名称表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除属性名称表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询属性名称表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条属性名称表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttributeNameModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询属性名称表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载属性名称表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttributeNameModel> queryByExample(AttributeNameModel model) throws ShareworxServiceException;
}
