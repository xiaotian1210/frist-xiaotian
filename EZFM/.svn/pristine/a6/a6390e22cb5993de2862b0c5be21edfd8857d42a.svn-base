package com.shareworx.ezfm.system.crop.file.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 企业文件存储持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface CropFileDao {

	String ID = "cropFileDao";
	
	/**
	 * 保存企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(CropFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(CropFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改企业文件存储
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(CropFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改企业文件存储
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(CropFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除企业文件存储
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询企业文件存储
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询企业文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询企业文件存储条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询企业文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
