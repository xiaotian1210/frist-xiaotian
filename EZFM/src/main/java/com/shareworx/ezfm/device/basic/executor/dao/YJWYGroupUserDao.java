package com.shareworx.ezfm.device.basic.executor.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 分组和人员关系表持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYGroupUserDao {

	String ID = "yJWYGroupUserDao";
	
	/**
	 * 保存分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYGroupUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYGroupUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改分组和人员关系表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYGroupUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改分组和人员关系表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除分组和人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYGroupUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除分组和人员关系表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询分组和人员关系表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询分组和人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询分组和人员关系表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询分组和人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
