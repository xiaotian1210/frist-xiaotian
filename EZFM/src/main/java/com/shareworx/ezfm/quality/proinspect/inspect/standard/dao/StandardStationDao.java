package com.shareworx.ezfm.quality.proinspect.inspect.standard.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 标准与岗位中间表持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface StandardStationDao {

	String ID = "standardStationDao";
	
	/**
	 * 保存标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(StandardStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改标准与岗位中间表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改标准与岗位中间表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除标准与岗位中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(StandardStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除标准与岗位中间表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询标准与岗位中间表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询标准与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询标准与岗位中间表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询标准与岗位中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
