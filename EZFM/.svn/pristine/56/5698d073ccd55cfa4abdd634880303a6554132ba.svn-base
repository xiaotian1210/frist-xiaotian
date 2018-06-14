package com.shareworx.ezfm.baseinfo.user.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 系统用户持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYUserDao {

	String ID = "userDao";
	
	/**
	 * 保存系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改系统用户
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改系统用户
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除系统用户
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除系统用户
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询系统用户
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询系统用户
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询系统用户条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询系统用户
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
