package com.shareworx.ezfm.worktask.assistuser.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 工单协助人表持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskAssistUserRecordDao {

	String ID = "yJWYWorkTaskAssistUserRecordDao";
	
	/**
	 * 保存工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改工单协助人表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAssistUserRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改工单协助人表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除工单协助人表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工单协助人表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工单协助人表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询工单协助人表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询工单协助人表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工单协助人表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
