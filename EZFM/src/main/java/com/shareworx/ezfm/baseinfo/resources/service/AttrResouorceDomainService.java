package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 资源_属性领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface AttrResouorceDomainService {

	
	String ID = "attrResouorceDomainService";
	
	/**
	 * 新增保存资源_属性领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttrResouorceModel> save(AttrResouorceModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源_属性领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttrResouorceModel> update(AttrResouorceModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源_属性领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttrResouorceModel> update(List<String> editFields, AttrResouorceModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除资源_属性领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(AttrResouorceModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除资源_属性领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源_属性领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	int deleteByCondition(String sql) throws ShareworxServiceException;
	/**
	 * 根据主键查询资源_属性领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttrResouorceModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条资源_属性领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	AttrResouorceModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源_属性领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttrResouorceModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源_属性对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<AttrResouorceModel> queryByExample(AttrResouorceModel model) throws ShareworxServiceException;
}
