package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 设备设施基本信息领域操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEqDomainService {

	
	String ID = "yJWYEqDomainService";
	
	/**
	 * 新增保存设备设施基本信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqModel> save(YJWYEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备设施基本信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqModel> update(YJWYEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备设施基本信息领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqModel> update(List<String> editFields, YJWYEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除设备设施基本信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除设备设施基本信息领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除设备设施基本信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询设备设施基本信息领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条设备设施基本信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询设备设施基本信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载设备设施基本信息对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEqModel> queryByExample(YJWYEqModel model) throws ShareworxServiceException;
}
