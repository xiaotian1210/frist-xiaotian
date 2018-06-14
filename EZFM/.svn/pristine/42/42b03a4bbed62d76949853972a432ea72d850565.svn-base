package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 工艺程序主表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPmpDomainService {

	
	String ID = "yJWYPmpDomainService";
	
	/**
	 * 新增保存工艺程序主表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpModel> save(YJWYPmpModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序主表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpModel> update(YJWYPmpModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工艺程序主表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpModel> update(List<String> editFields, YJWYPmpModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除工艺程序主表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYPmpModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除工艺程序主表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工艺程序主表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工艺程序主表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条工艺程序主表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPmpModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工艺程序主表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工艺程序主表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPmpModel> queryByExample(YJWYPmpModel model) throws ShareworxServiceException;
}
