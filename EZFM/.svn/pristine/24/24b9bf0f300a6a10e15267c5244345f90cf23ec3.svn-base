package com.shareworx.ezfm.baseinfo.org.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 组织架构领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface DefaultOrgDomainService {

	
	String ID = "defaultOrgDomainService";
	
	/**
	 * 新增保存组织架构领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> save(DefaultOrgModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存组织架构领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> update(DefaultOrgModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存组织架构领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> update(List<String> editFields, DefaultOrgModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除组织架构领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(DefaultOrgModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除组织架构领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除组织架构领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询组织架构领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条组织架构领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	DefaultOrgModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询组织架构领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载组织架构对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<DefaultOrgModel> queryByExample(DefaultOrgModel model) throws ShareworxServiceException;
}
