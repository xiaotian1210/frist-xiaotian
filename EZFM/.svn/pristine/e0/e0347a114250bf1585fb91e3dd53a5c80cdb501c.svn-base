package com.shareworx.ezfm.worktask.repairclass.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 维修种类持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskRepairClassDao {

	String ID = "yJWYWorkTaskRepairClassDao";
	
	/**
	 * 保存维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改维修种类
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskRepairClassModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改维修种类
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除维修种类
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询维修种类
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询维修种类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询维修种类条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询维修种类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
