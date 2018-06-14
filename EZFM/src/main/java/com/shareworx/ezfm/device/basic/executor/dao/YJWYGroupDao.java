package com.shareworx.ezfm.device.basic.executor.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 巡检/维保人员分组持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYGroupDao {

	String ID = "yJWYGroupDao";
	
	/**
	 * 保存巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYGroupModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYGroupModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检/维保人员分组
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYGroupModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改巡检/维保人员分组
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除巡检/维保人员分组
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYGroupModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检/维保人员分组
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检/维保人员分组
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询巡检/维保人员分组
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询巡检/维保人员分组条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检/维保人员分组
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
