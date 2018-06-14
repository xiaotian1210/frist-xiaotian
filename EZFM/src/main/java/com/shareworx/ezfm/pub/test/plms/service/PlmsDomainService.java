package com.shareworx.ezfm.pub.test.plms.service;

import java.util.List;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 测试plms领域操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface PlmsDomainService {

	
	String ID = "plmsDomainService";
	
	/**
	 * 新增保存测试plms领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> save(PlmsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试plms领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> update(PlmsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试plms领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> update(List<String> editFields, PlmsModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除测试plms领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(PlmsModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除测试plms领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除测试plms领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询测试plms领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条测试plms领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	PlmsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询测试plms领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载测试plms对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PlmsModel> queryByExample(PlmsModel model) throws ShareworxServiceException;
}
