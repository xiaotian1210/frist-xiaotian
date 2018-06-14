package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 基础属性表领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYBaseAttributeDomainService {

	
	String ID = "yjwyBaseAttributeDomainService";
	
	/**
	 * 新增保存基础属性表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> save(YJWYBaseAttributeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基础属性表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> update(YJWYBaseAttributeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基础属性表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> update(List<String> editFields, YJWYBaseAttributeModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除基础属性表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYBaseAttributeModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除基础属性表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除基础属性表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询基础属性表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条基础属性表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYBaseAttributeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询基础属性表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载基础属性表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYBaseAttributeModel> queryByExample(YJWYBaseAttributeModel model) throws ShareworxServiceException;
}
