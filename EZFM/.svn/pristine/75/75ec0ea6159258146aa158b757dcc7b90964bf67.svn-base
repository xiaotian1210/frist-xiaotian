package com.shareworx.ezfm.baseinfo.rolefunc.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 基于角色的功能权限持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYRoleFuncDao {

	String ID = "yJWYRoleFuncDao";
	
	/**
	 * 保存基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改基于角色的功能权限
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYRoleFuncModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改基于角色的功能权限
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除基于角色的功能权限
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除基于角色的功能权限
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询基于角色的功能权限
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询基于角色的功能权限
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询基于角色的功能权限条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询基于角色的功能权限
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
