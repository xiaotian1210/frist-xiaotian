package com.shareworx.ezfm.worktask.areadetails.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 片区详情持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskAreaDetailsDao {

	String ID = "yJWYWorkTaskAreaDetailsDao";
	
	/**
	 * 保存片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区详情
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaDetailsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改片区详情
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除片区详情
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区详情
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区详情
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询片区详情
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询片区详情条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区详情
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
