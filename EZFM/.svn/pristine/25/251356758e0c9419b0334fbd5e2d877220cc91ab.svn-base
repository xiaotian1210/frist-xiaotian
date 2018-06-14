package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.util.List;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 电表领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEnergyElectricDomainService {

	
	String ID = "yJWYEnergyElectricDomainService";
	
	/**
	 * 新增保存电表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> save(YJWYEnergyElectricModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存电表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> update(YJWYEnergyElectricModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存电表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> update(List<String> editFields, YJWYEnergyElectricModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除电表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYEnergyElectricModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除电表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除电表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询电表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEnergyElectricModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条电表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEnergyElectricModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询电表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载电表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEnergyElectricModel> queryByExample(YJWYEnergyElectricModel model) throws ShareworxServiceException;
}
