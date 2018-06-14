package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 工艺程序步骤子表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPmpsDomainService {

	
	String ID = "yJWYPmpsDomainService";
	
	/**
	 * 新增保存工艺程序步骤子表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> save(YJWYPmpsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序步骤子表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> update(YJWYPmpsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序步骤子表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> update(List<String> editFields, YJWYPmpsModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除工艺程序步骤子表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYPmpsModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除工艺程序步骤子表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工艺程序步骤子表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工艺程序步骤子表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条工艺程序步骤子表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工艺程序步骤子表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工艺程序步骤子表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpsModel> queryByExample(YJWYPmpsModel model) throws ShareworxServiceException;
}
