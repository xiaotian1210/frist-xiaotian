package com.shareworx.ezfm.baseinfo.area.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 区域管理持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYAreaDao {

	String ID = "yJWYAreaDao";
	
	/**
	 * 保存区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改区域管理
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYAreaModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改区域管理
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除区域管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYAreaModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除区域管理
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询区域管理
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询区域管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询区域管理条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询区域管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
