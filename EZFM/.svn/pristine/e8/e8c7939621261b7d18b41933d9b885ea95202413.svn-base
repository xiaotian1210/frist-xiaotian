package com.shareworx.ezfm.pub.test.plms.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 测试plms持久化操作接口
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
public interface PlmsDao {

	String ID = "plmsDao";
	
	/**
	 * 保存测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(PlmsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PlmsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改测试plms
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PlmsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改测试plms
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除测试plms
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(PlmsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除测试plms
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询测试plms
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询测试plms
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询测试plms条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询测试plms
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
