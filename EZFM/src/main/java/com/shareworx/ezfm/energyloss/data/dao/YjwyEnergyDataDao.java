package com.shareworx.ezfm.energyloss.data.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 水表电表抄表持久化操作接口
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
public interface YjwyEnergyDataDao {

	String ID = "yjwyEnergyDataDao";
	
	/**
	 * 保存水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改水表电表抄表
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YjwyEnergyDataModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改水表电表抄表
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除水表电表抄表
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询水表电表抄表
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询水表电表抄表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询水表电表抄表条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询水表电表抄表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
