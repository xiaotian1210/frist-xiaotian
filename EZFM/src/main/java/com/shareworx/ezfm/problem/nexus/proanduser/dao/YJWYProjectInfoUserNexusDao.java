package com.shareworx.ezfm.problem.nexus.proanduser.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 接口人员关系表持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYProjectInfoUserNexusDao {

	String ID = "yJWYProjectInfoUserNexusDao";
	
	/**
	 * 保存接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改接口人员关系表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYProjectInfoUserNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改接口人员关系表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除接口人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除接口人员关系表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询接口人员关系表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询接口人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询接口人员关系表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询接口人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
