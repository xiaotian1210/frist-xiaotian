package com.shareworx.ezfm.worktask.areaproject.service;

import java.util.List;

import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 片区项目关联领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaProjectNexusDomainService {

	
	String ID = "yJWYWorkTaskAreaProjectNexusDomainService";
	
	/**
	 * 新增保存片区项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> save(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> update(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区项目关联领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> update(List<String> editFields, YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除片区项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除片区项目关联领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区项目关联领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条片区项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区项目关联对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaProjectNexusModel> queryByExample(YJWYWorkTaskAreaProjectNexusModel model) throws ShareworxServiceException;
}
