package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 工艺程序步骤子表持久化操作接口
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYPmpsDao {

	String ID = "yJWYPmpsDao";
	
	/**
	 * 保存工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYPmpsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYPmpsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改工艺程序步骤子表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYPmpsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改工艺程序步骤子表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除工艺程序步骤子表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYPmpsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工艺程序步骤子表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工艺程序步骤子表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询工艺程序步骤子表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询工艺程序步骤子表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工艺程序步骤子表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
