package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 版本与岗位中间表持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface EditionStationDao {

	String ID = "editionStationDao";
	
	/**
	 * 保存版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(EditionStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(EditionStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改版本与岗位中间表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(EditionStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改版本与岗位中间表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除版本与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(EditionStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除版本与岗位中间表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询版本与岗位中间表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询版本与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询版本与岗位中间表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询版本与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
