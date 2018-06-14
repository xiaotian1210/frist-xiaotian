package com.shareworx.ezfm.system.function.service;

import java.util.List;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 系统功能领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYFunctionDomainService {

	
	String ID = "yJWYFunctionDomainService";
	
	/**
	 * 新增保存系统功能领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> save(YJWYFunctionModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统功能领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> update(YJWYFunctionModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统功能领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> update(List<String> editFields, YJWYFunctionModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除系统功能领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYFunctionModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除系统功能领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除系统功能领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询系统功能领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条系统功能领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询系统功能领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载系统功能对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> queryByExample(YJWYFunctionModel model) throws ShareworxServiceException;
}
