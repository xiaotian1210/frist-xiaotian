package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 核查与整改记录持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface InspectRecordDao {

	String ID = "inspectRecordDao";
	
	/**
	 * 保存核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(InspectRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(InspectRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改核查与整改记录
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(InspectRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改核查与整改记录
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除核查与整改记录
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(InspectRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除核查与整改记录
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询核查与整改记录
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询核查与整改记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询核查与整改记录条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询核查与整改记录
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
