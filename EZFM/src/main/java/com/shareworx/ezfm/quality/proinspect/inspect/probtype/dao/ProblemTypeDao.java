package com.shareworx.ezfm.quality.proinspect.inspect.probtype.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 问题类型持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface ProblemTypeDao {

	String ID = "problemTypeDao";
	
	/**
	 * 保存问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(ProblemTypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemTypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改问题类型
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(ProblemTypeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改问题类型
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除问题类型
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(ProblemTypeModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除问题类型
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询问题类型
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询问题类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询问题类型条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询问题类型
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
