package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * APP手机权限领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYAPPRoleFuncDomainService {

	
	String ID = "yJWYAPPRoleFuncDomainService";
	
	/**
	 * 新增保存APP手机权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAPPRoleFuncModel> save(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存APP手机权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAPPRoleFuncModel> update(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存APP手机权限领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAPPRoleFuncModel> update(List<String> editFields, YJWYAPPRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除APP手机权限领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除APP手机权限领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除APP手机权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询APP手机权限领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条APP手机权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAPPRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询APP手机权限领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAPPRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载APP手机权限对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAPPRoleFuncModel> queryByExample(YJWYAPPRoleFuncModel model) throws ShareworxServiceException;
}
