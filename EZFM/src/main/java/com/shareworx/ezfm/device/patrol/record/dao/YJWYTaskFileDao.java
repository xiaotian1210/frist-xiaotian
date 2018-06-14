package com.shareworx.ezfm.device.patrol.record.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 巡检维保附件表持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYTaskFileDao {

	String ID = "yJWYTaskFileDao";
	
	/**
	 * 保存巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYTaskFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTaskFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改巡检维保附件表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYTaskFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改巡检维保附件表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保附件表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYTaskFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保附件表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保附件表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询巡检维保附件表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询巡检维保附件表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保附件表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
