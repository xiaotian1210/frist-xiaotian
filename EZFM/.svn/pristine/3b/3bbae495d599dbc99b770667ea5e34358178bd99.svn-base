package com.shareworx.ezfm.pub.feedback.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.pub.feedback.model.YJWYFeedbackModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * app的意见反馈持久化操作接口
 *
 * @author yuting.wang
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYFeedbackDao {

	String ID = "yJWYFeedbackDao";
	
	/**
	 * 保存app的意见反馈
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYFeedbackModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改app的意见反馈
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYFeedbackModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改app的意见反馈
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYFeedbackModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改app的意见反馈
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除app的意见反馈
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYFeedbackModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除app的意见反馈
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询app的意见反馈
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFeedbackModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询app的意见反馈
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFeedbackModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询app的意见反馈条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询app的意见反馈
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFeedbackModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
