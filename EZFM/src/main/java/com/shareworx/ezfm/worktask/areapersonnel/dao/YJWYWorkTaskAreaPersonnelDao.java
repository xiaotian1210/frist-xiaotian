package com.shareworx.ezfm.worktask.areapersonnel.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 片区维修人员持久化操作接口
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYWorkTaskAreaPersonnelDao {

	String ID = "yJWYWorkTaskAreaPersonnelDao";
	
	/**
	 * 保存片区维修人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区维修人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改片区维修人员
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYWorkTaskAreaPersonnelModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改片区维修人员
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除片区维修人员
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区维修人员
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区维修人员
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询片区维修人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询片区维修人员条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区维修人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
