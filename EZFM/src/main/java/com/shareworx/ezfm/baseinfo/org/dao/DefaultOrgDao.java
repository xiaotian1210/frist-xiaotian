package com.shareworx.ezfm.baseinfo.org.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 组织架构持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface DefaultOrgDao {

	String ID = "defaultOrgDao";
	
	/**
	 * 保存组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(DefaultOrgModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(DefaultOrgModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改组织架构
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(DefaultOrgModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改组织架构
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除组织架构
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(DefaultOrgModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除组织架构
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询组织架构
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询组织架构
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询组织架构条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询组织架构
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
