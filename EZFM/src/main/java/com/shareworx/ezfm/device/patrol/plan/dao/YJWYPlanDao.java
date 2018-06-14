package com.shareworx.ezfm.device.patrol.plan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 巡检维保计划持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYPlanDao {

	String ID = "yJWYPlanDao";
	
	/**
	 * 根据sql查询models
	 * @param sql
	 * @return
	 */
	List<YJWYPlanModel> queryModels(String sql);
	
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
	 * 保存巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYPlanModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYPlanModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保计划
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYPlanModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改巡检维保计划
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保计划
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYPlanModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保计划
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保计划
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询巡检维保计划
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询巡检维保计划条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保计划
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
