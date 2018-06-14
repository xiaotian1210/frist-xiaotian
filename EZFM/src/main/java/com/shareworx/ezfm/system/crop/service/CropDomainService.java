package com.shareworx.ezfm.system.crop.service;

import java.util.List;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.system.crop.model.CropModel;

/**
 * 企业管理领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface CropDomainService {

	
	String ID = "cropDomainService";
	List<YJWYRoleFuncModel> funcSave(YJWYRoleFuncModel... models) throws ShareworxServiceException;
	/**
	 * 该功能已经作废
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYUserModel> userSave(YJWYUserModel... models) throws ShareworxServiceException;
	List<YJWYUserModel> userUpdate(YJWYUserModel... models) throws ShareworxServiceException;
	List<YJWYRoleModel> roleSave(YJWYRoleModel... models) throws ShareworxServiceException;
	List<YJWYRoleUserModel> roleUserSave(YJWYRoleUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 新增保存企业管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropModel> save(CropModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropModel> update(CropModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropModel> update(List<String> editFields, CropModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除企业管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(CropModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除企业管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除企业管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询企业管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条企业管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询企业管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropModel> queryByExample(CropModel model) throws ShareworxServiceException;
}
