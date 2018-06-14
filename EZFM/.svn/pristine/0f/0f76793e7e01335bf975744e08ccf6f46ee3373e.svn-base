package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 基于角色的功能权限领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleFuncDomainService {

	
	String ID = "yJWYRoleFuncDomainService";
	
	/**
	 * 新增保存基于角色的功能权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> save(YJWYRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基于角色的功能权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> update(YJWYRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存基于角色的功能权限领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> update(List<String> editFields, YJWYRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除基于角色的功能权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除基于角色的功能权限领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除基于角色的功能权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询基于角色的功能权限领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条基于角色的功能权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询基于角色的功能权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载基于角色的功能权限对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> queryByExample(YJWYRoleFuncModel model) throws ShareworxServiceException;
}
