package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 角色领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleDomainService {

	
	String ID = "yjwyRoleDomainService";
	
	/**
	 * 新增保存角色领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> save(YJWYRoleModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> update(YJWYRoleModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> update(List<String> editFields, YJWYRoleModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除角色领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYRoleModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除角色领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除角色领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询角色领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条角色领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询角色领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载角色对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> queryByExample(YJWYRoleModel model) throws ShareworxServiceException;
}
