package com.shareworx.ezfm.worktask.projectuser.service;

import java.util.List;

import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 项目接口人员领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskProjectUserDomainService {

	
	String ID = "yJWYWorkTaskProjectUserDomainService";
	
	/**
	 * 新增保存项目接口人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskProjectUserModel> save(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目接口人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskProjectUserModel> update(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目接口人员领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskProjectUserModel> update(List<String> editFields, YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除项目接口人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除项目接口人员领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除项目接口人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询项目接口人员领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条项目接口人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskProjectUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询项目接口人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskProjectUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载项目接口人员对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskProjectUserModel> queryByExample(YJWYWorkTaskProjectUserModel model) throws ShareworxServiceException;
}
