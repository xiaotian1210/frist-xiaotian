package com.shareworx.ezfm.worktask.classpro.service;

import java.util.List;

import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 种类项目关联领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskRepairClassProjectDomainService {

	
	String ID = "yJWYWorkTaskRepairClassProjectDomainService";
	
	/**
	 * 新增保存种类项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> save(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存种类项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> update(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存种类项目关联领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> update(List<String> editFields, YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除种类项目关联领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除种类项目关联领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除种类项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询种类项目关联领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条种类项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询种类项目关联领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载种类项目关联对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassProjectModel> queryByExample(YJWYWorkTaskRepairClassProjectModel model) throws ShareworxServiceException;
}
