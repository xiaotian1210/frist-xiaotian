package com.shareworx.ezfm.problem.classadmin.service;

import java.util.List;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 测试用的领域操作接口
 * @author jiangwei.peng
 * @version since Shareworx platform 3.0
 *
 */
public interface yjwyjwtest_csDomainService {

	
	String ID = "yjwyjwtest_csDomainService";
	
	/**
	 * 新增保存测试用的领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> save(yjwyjwtest_csModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试用的领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> update(yjwyjwtest_csModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存测试用的领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> update(List<String> editFields, yjwyjwtest_csModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除测试用的领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(yjwyjwtest_csModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除测试用的领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除测试用的领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询测试用的领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条测试用的领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询测试用的领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载测试用的对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> queryByExample(yjwyjwtest_csModel model) throws ShareworxServiceException;
}
