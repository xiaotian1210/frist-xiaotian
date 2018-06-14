package com.shareworx.ezfm.problem.file.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 报事报修文件存储持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface ProblemFileDao {

	String ID = "problemFileDao";
	
	/**
	 * 保存报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(ProblemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事报修文件存储
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改报事报修文件存储
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(ProblemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事报修文件存储
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事报修文件存储
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询报事报修文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询报事报修文件存储条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事报修文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
