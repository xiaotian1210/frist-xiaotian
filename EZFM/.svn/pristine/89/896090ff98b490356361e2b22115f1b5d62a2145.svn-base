package com.shareworx.ezfm.device.fmdata_eq.dao;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

import java.io.Serializable;
import java.util.List;

/**
 * 设备所属系统持久化操作接口
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
public interface YJWYEqSysDao {

	String ID = "yJWYEqSysDao";
	
	/**
	 * 保存设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YJWYEqSysModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEqSysModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改设备所属系统
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEqSysModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改设备所属系统
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YJWYEqSysModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除设备所属系统
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询设备所属系统
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询设备所属系统
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询设备所属系统条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询设备所属系统
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
