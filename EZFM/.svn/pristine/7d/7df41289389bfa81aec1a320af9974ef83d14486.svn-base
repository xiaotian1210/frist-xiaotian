package com.shareworx.ezfm.quality.proinspect.inspect.standard.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 核查标准人员中间表持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface StandardUserDao {

	String ID = "standardUserDao";
	
	/**
	 * 保存核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(StandardUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改核查标准人员中间表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改核查标准人员中间表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除核查标准人员中间表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(StandardUserModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除核查标准人员中间表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询核查标准人员中间表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询核查标准人员中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询核查标准人员中间表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询核查标准人员中间表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
