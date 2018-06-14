package com.shareworx.ezfm.performance.sign.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 签到管理持久化操作接口
 *
 * @author lingwei.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYSignDao {

	String ID = "yJWYSignDao";
	
	/**
	 * 保存签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYSignModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYSignModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改签到管理
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYSignModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改签到管理
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除签到管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYSignModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除签到管理
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询签到管理
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询签到管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询签到管理条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询签到管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
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
