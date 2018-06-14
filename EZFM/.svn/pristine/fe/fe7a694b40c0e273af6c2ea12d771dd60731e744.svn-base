package com.shareworx.ezfm.device.siteproject.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * FM与YJWY项目关联表持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYSiteProjectDao {

	String ID = "yJWYSiteProjectDao";
	
	/**
	 * 查询FM项目id集合
	 * @param sql
	 * @return
	 */
	List<String> queryIds(String sql);
	
	/**
	 * 查询map
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryMap(String sql);
	
	/**
	 * 保存FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改FM与YJWY项目关联表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYSiteProjectModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改FM与YJWY项目关联表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除FM与YJWY项目关联表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除FM与YJWY项目关联表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询FM与YJWY项目关联表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询FM与YJWY项目关联表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询FM与YJWY项目关联表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询FM与YJWY项目关联表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
