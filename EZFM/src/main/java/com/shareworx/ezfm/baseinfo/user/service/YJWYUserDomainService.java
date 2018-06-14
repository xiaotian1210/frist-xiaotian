package com.shareworx.ezfm.baseinfo.user.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 系统用户领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYUserDomainService {

	
	String ID = "yJWYUserDomainService";
	
	/**
	 * 新增保存系统用户领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> save(YJWYUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统用户领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> update(YJWYUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存系统用户领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> update(List<String> editFields, YJWYUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除系统用户领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除系统用户领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除系统用户领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询系统用户领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel queryById(String id) throws ShareworxServiceException;




	/**
	 * 根据条件查询一条系统用户领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询系统用户领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载系统用户对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> queryByExample(YJWYUserModel model) throws ShareworxServiceException;
}
