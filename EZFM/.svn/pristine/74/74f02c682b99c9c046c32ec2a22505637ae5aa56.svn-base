package com.shareworx.ezfm.problem.nexus.proanduser.service;

import java.util.List;

import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 接口人员关系表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectInfoUserNexusDomainService {

	
	String ID = "yJWYProjectInfoUserNexusDomainService";
	
	/**
	 * 新增保存接口人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> save(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存接口人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> update(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存接口人员关系表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> update(List<String> editFields, YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除接口人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除接口人员关系表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除接口人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询接口人员关系表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条接口人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询接口人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载接口人员关系表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoUserNexusModel> queryByExample(YJWYProjectInfoUserNexusModel model) throws ShareworxServiceException;
}
