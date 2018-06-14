package com.shareworx.ezfm.problem.nexus.proandclass.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 项目与报事类型关系表持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYProjectInfoClassNexusDao {

	String ID = "yJWYProjectInfoClassNexusDao";
	
	/**
	 * 保存项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改项目与报事类型关系表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProjectInfoClassNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改项目与报事类型关系表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除项目与报事类型关系表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询项目与报事类型关系表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询项目与报事类型关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询项目与报事类型关系表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询项目与报事类型关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
