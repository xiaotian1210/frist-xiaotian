package com.shareworx.ezfm.device.timer.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 定时任务执行记录持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYTimerDao {

	String ID = "yJWYTimerDao";
	
	/**
	 * 保存定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYTimerModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTimerModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改定时任务执行记录
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTimerModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改定时任务执行记录
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除定时任务执行记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYTimerModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除定时任务执行记录
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询定时任务执行记录
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询定时任务执行记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询定时任务执行记录条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询定时任务执行记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
