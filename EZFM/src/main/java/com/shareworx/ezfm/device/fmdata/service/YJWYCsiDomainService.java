package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 设备分类信息领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYCsiDomainService {

	
	String ID = "yJWYCsiDomainService";
	
	/**
	 * 新增保存设备分类信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYCsiModel> save(YJWYCsiModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备分类信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYCsiModel> update(YJWYCsiModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备分类信息领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYCsiModel> update(List<String> editFields, YJWYCsiModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除设备分类信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYCsiModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除设备分类信息领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除设备分类信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询设备分类信息领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条设备分类信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYCsiModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询设备分类信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYCsiModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载设备分类信息对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYCsiModel> queryByExample(YJWYCsiModel model) throws ShareworxServiceException;
}
