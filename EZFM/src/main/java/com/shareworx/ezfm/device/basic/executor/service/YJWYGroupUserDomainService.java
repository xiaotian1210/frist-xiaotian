package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 分组和人员关系表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYGroupUserDomainService {

	
	String ID = "yJWYGroupUserDomainService";
	
	/**
	 * 新增保存分组和人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> save(YJWYGroupUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存分组和人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> update(YJWYGroupUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存分组和人员关系表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> update(List<String> editFields, YJWYGroupUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除分组和人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYGroupUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除分组和人员关系表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除分组和人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询分组和人员关系表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条分组和人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询分组和人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载分组和人员关系表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupUserModel> queryByExample(YJWYGroupUserModel model) throws ShareworxServiceException;
}
