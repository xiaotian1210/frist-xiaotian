package com.shareworx.ezfm.worktask.classpro.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 种类项目关联持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskRepairClassProjectDao {

	String ID = "yJWYWorkTaskRepairClassProjectDao";
	
	/**
	 * 保存种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改种类项目关联
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskRepairClassProjectModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改种类项目关联
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除种类项目关联
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询种类项目关联
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询种类项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询种类项目关联条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询种类项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
