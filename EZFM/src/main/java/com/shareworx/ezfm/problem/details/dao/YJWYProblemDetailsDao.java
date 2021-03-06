package com.shareworx.ezfm.problem.details.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 报事主类持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYProblemDetailsDao {

	String ID = "yJWYProblemDetailsDao";
	
	/**
	 * 保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事主类
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProblemDetailsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改报事主类
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事主类
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事主类
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询报事主类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询报事主类条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事主类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
