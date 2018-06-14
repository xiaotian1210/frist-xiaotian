package com.shareworx.ezfm.energyloss.data.service;

import java.util.List;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 水表电表抄表领域操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YjwyEnergyDataDomainService {

	
	String ID = "yjwyEnergyDataDomainService";
	
	/**
	 * 新增保存水表电表抄表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> save(YjwyEnergyDataModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存水表电表抄表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> update(YjwyEnergyDataModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存水表电表抄表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> update(List<String> editFields, YjwyEnergyDataModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除水表电表抄表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YjwyEnergyDataModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除水表电表抄表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除水表电表抄表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询水表电表抄表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条水表电表抄表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询水表电表抄表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载水表电表抄表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YjwyEnergyDataModel> queryByExample(YjwyEnergyDataModel model) throws ShareworxServiceException;


}
