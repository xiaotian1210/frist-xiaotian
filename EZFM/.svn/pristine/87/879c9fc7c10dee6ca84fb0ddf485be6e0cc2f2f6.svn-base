package com.shareworx.ezfm.baseinfo.role.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 角色持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYRoleDao {

	String ID = "yjwyRoleDao";
	
	/**
	 * 保存角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYRoleModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoleModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改角色
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoleModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改角色
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除角色
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYRoleModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除角色
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询角色
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询角色
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询角色条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询角色
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
