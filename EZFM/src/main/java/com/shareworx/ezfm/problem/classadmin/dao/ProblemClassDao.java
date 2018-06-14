package com.shareworx.ezfm.problem.classadmin.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 报事分类管理持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface ProblemClassDao {

	String ID = "problemClassDao";
	
	/**
	 * 保存报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(ProblemClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改报事分类管理
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemClassModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改报事分类管理
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(ProblemClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事分类管理
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事分类管理
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询报事分类管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询报事分类管理条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事分类管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
