package com.shareworx.ezfm.baseinfo.resourceslog.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 记录资源空间日志表持久化操作接口
 *
 * @author kimguo
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYResourcesLogDao {

	String ID = "yJWYResourcesLogDao";
	
	/**
	 * 保存记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改记录资源空间日志表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourcesLogModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改记录资源空间日志表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除记录资源空间日志表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除记录资源空间日志表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询记录资源空间日志表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询记录资源空间日志表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询记录资源空间日志表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询记录资源空间日志表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
