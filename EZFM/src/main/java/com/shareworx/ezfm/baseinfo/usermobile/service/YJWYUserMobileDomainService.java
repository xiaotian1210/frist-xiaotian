package com.shareworx.ezfm.baseinfo.usermobile.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 用户与设备关系表领域操作接口
 * @author yuting.wang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYUserMobileDomainService {

	
	String ID = "yJWYUserMobileDomainService";
	
	/**
	 * 新增保存用户与设备关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> save(YJWYUserMobileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存用户与设备关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> update(YJWYUserMobileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存用户与设备关系表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> update(List<String> editFields, YJWYUserMobileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除用户与设备关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYUserMobileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除用户与设备关系表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除用户与设备关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询用户与设备关系表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条用户与设备关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserMobileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询用户与设备关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载用户与设备关系表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserMobileModel> queryByExample(YJWYUserMobileModel model) throws ShareworxServiceException;
}
