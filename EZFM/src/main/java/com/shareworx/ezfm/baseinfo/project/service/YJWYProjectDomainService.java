package com.shareworx.ezfm.baseinfo.project.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 项目管理领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectDomainService {

	
	String ID = "yJWYProjectDomainService";
	
	/**
	 * 新增保存项目管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectModel> save(YJWYProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectModel> update(YJWYProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectModel> update(List<String> editFields, YJWYProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除项目管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除项目管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除项目管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询项目管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条项目管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询项目管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载项目管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProjectModel> queryByExample(YJWYProjectModel model) throws ShareworxServiceException;
}
