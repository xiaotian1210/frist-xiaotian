package com.shareworx.ezfm.baseinfo.resources.file.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 资源附件持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYResourceFileDao {

	String ID = "yJWYResourceFileDao";
	
	/**
	 * 保存资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYResourceFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourceFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改资源附件
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYResourceFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改资源附件
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除资源附件
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYResourceFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源附件
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源附件
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询资源附件
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询资源附件条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源附件
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
