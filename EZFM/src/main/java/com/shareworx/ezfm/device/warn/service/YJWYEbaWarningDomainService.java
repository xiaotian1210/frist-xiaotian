package com.shareworx.ezfm.device.warn.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * eba设备报警信息领域操作接口
 * 
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEbaWarningDomainService {

	String ID = "yJWYEbaWarningDomainService";

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryList(ParamEntity params);

	/**
	 * 查询总数
	 * 
	 * @return
	 */
	Long queryCount(ParamEntity params);

	/**
	 * 新增保存eba设备报警信息领域对象
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> save(YJWYEbaWarningModel... models) throws ShareworxServiceException;

	/**
	 * 修改保存eba设备报警信息领域对象
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> update(YJWYEbaWarningModel... models) throws ShareworxServiceException;

	/**
	 * 修改保存eba设备报警信息领域对象
	 * 
	 * @param editFields
	 *            {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> update(List<String> editFields, YJWYEbaWarningModel... models) throws ShareworxServiceException;

	/**
	 * 删除eba设备报警信息领域对象
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYEbaWarningModel... models) throws ShareworxServiceException;

	/**
	 * 根据主键删除eba设备报警信息领域对象
	 * 
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;

	/**
	 * 根据条件删除eba设备报警信息领域对象
	 * 
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;

	/**
	 * 根据主键查询eba设备报警信息领域对象
	 * 
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel queryById(String id) throws ShareworxServiceException;

	/**
	 * 根据条件查询一条eba设备报警信息领域对象
	 * 
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEbaWarningModel queryOneByCondition(Query query) throws ShareworxServiceException;

	/**
	 * 根据条件查询eba设备报警信息领域对象
	 * 
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> queryListByCondition(Query query) throws ShareworxServiceException;

	/**
	 * 加载eba设备报警信息对象
	 * 
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYEbaWarningModel> queryByExample(YJWYEbaWarningModel model) throws ShareworxServiceException;
}
