package com.shareworx.ezfm.system.crop.pre.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 企业信息预采集实体持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface PreCropDao {

	String ID = "preCropDao";
	
	/**
	 * 保存企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(PreCropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PreCropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改企业信息预采集实体
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PreCropModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改企业信息预采集实体
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(PreCropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除企业信息预采集实体
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询企业信息预采集实体
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询企业信息预采集实体
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询企业信息预采集实体条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询企业信息预采集实体
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 多表联合查询
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<Map<String,Object>> queryMap(String sql);
	
}
