package com.shareworx.ezfm.problem.nexus.proandclass.service;

import java.util.List;

import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 项目与报事类型关系表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectInfoClassNexusDomainService {

	
	String ID = "yJWYProjectInfoClassNexusDomainService";
	
	/**
	 * 新增保存项目与报事类型关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> save(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目与报事类型关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> update(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目与报事类型关系表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> update(List<String> editFields, YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除项目与报事类型关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除项目与报事类型关系表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除项目与报事类型关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询项目与报事类型关系表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条项目与报事类型关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询项目与报事类型关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载项目与报事类型关系表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectInfoClassNexusModel> queryByExample(YJWYProjectInfoClassNexusModel model) throws ShareworxServiceException;
}
