package com.shareworx.ezfm.quality.file.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 品质核查持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface QualityFileDao {

	String ID = "qualityFileDao";
	
	/**
	 * 保存品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(QualityFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(QualityFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改品质核查
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(QualityFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改品质核查
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(QualityFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除品质核查
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询品质核查
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询品质核查
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询品质核查条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询品质核查
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
