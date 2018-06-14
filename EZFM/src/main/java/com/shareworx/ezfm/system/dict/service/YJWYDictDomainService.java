package com.shareworx.ezfm.system.dict.service;

import java.util.List;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 数据字典领域操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYDictDomainService {

	
	String ID = "yJWYDictDomainService";
	
	/**
	 * 新增保存数据字典领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> save(YJWYDictModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存数据字典领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> update(YJWYDictModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存数据字典领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> update(List<String> editFields, YJWYDictModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除数据字典领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYDictModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除数据字典领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除数据字典领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询数据字典领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条数据字典领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询数据字典领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载数据字典对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYDictModel> queryByExample(YJWYDictModel model) throws ShareworxServiceException;
	
	//共用方法
	YJWYDictModel getDict(String code) throws ShareworxServiceException;
}
