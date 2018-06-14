package com.shareworx.ezfm.performance.leave.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;

/**
 * 休假备案持久化操作接口
 *
 * @author ying.chen
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYLeaveDao {

	String ID = "yJWYLeaveDao";
	
	/**
	 * 保存休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYLeaveModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYLeaveModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改休假备案
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYLeaveModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改休假备案
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除休假备案
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYLeaveModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除休假备案
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询休假备案
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询休假备案
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询休假备案条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询休假备案
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	
	/**
	 * sql语句联合查询
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryMap(String sql);
	
	/**
	 * 任务数量查询
	 * @param params
	 * @return
	 */
	Long queryTaskCount(String sql);
}
