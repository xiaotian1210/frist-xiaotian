package com.shareworx.ezfm.worktask.areaproject.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 片区项目关联持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskAreaProjectNexusDao {

	String ID = "yJWYWorkTaskAreaProjectNexusDao";
	
	/**
	 * 保存片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区项目关联
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaProjectNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改片区项目关联
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区项目关联
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区项目关联
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询片区项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询片区项目关联条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
