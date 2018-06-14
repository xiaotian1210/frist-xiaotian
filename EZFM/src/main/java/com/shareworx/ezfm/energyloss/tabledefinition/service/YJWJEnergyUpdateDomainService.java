package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.util.List;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 能耗数据修改表领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWJEnergyUpdateDomainService {

	
	String ID = "yJWJEnergyUpdateDomainService";
	
	/**
	 * 新增保存能耗数据修改表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> save(YJWJEnergyUpdateModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存能耗数据修改表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> update(YJWJEnergyUpdateModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存能耗数据修改表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> update(List<String> editFields, YJWJEnergyUpdateModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除能耗数据修改表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWJEnergyUpdateModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除能耗数据修改表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除能耗数据修改表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询能耗数据修改表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条能耗数据修改表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询能耗数据修改表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载能耗数据修改表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWJEnergyUpdateModel> queryByExample(YJWJEnergyUpdateModel model) throws ShareworxServiceException;
}
