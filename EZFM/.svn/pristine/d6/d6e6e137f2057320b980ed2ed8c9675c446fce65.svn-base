package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 角色人员关系领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoleUserDomainService {

	
	String ID = "yjwyRoleUserDomainService";
	
	/**
	 * 新增保存角色人员关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleUserModel> save(YJWYRoleUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色人员关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleUserModel> update(YJWYRoleUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存角色人员关系领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleUserModel> update(List<String> editFields, YJWYRoleUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除角色人员关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYRoleUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除角色人员关系领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除角色人员关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询角色人员关系领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条角色人员关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询角色人员关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载角色人员关系对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleUserModel> queryByExample(YJWYRoleUserModel model) throws ShareworxServiceException;
}
