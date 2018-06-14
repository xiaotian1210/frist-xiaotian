package com.shareworx.ezfm.device.patrol.task.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 巡检维保任务表持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYTaskDao {

	String ID = "yJWYTaskDao";
	
	
	/**
	 * sql语句联合查询
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryMap(String sql);
	
	/**
	 * sql语句联合数据数量查询
	 * @param sql
	 * @return
	 */
	Long queryCount(String sql);
	
	/**
	 * 保存巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保任务表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTaskModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改巡检维保任务表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保任务表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYTaskModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保任务表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保任务表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询巡检维保任务表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询巡检维保任务表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保任务表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
