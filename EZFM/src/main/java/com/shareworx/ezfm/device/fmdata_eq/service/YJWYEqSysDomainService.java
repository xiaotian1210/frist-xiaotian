package com.shareworx.ezfm.device.fmdata_eq.service;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

import java.util.List;

/**
 * 设备所属系统领域操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEqSysDomainService {

	
	String ID = "yJWYEqSysDomainService";
	
	/**
	 * 新增保存设备所属系统领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> save(YJWYEqSysModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备所属系统领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> update(YJWYEqSysModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备所属系统领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> update(List<String> editFields, YJWYEqSysModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除设备所属系统领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYEqSysModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除设备所属系统领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除设备所属系统领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询设备所属系统领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条设备所属系统领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询设备所属系统领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载设备所属系统对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqSysModel> queryByExample(YJWYEqSysModel model) throws ShareworxServiceException;
}
