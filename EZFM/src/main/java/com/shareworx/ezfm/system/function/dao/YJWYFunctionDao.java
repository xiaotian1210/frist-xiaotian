package com.shareworx.ezfm.system.function.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 系统功能持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYFunctionDao {

	String ID = "yJWYFunctionDao";
	
	/**
	 * 保存系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYFunctionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYFunctionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改系统功能
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYFunctionModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改系统功能
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除系统功能
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYFunctionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除系统功能
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询系统功能
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询系统功能
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYFunctionModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询系统功能条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询系统功能
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYFunctionModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
