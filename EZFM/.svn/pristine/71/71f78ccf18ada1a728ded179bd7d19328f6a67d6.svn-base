package com.shareworx.ezfm.baseinfo.usermobile.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 用户与设备关系表持久化操作接口
 *
 * @author yuting.wang
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYUserMobileDao {

	String ID = "yJWYUserMobileDao";
	
	/**
	 * 保存用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYUserMobileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYUserMobileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改用户与设备关系表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYUserMobileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改用户与设备关系表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除用户与设备关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYUserMobileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除用户与设备关系表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询用户与设备关系表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询用户与设备关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询用户与设备关系表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询用户与设备关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
