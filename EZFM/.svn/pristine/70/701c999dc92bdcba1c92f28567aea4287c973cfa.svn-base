package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 巡检/维保人员分组领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYGroupDomainService {

	
	String ID = "yJWYGroupDomainService";
	
	/**
	 * 新增保存巡检/维保人员分组领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> save(YJWYGroupModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检/维保人员分组领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> update(YJWYGroupModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检/维保人员分组领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> update(List<String> editFields, YJWYGroupModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检/维保人员分组领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYGroupModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除巡检/维保人员分组领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检/维保人员分组领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检/维保人员分组领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条巡检/维保人员分组领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYGroupModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检/维保人员分组领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检/维保人员分组对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYGroupModel> queryByExample(YJWYGroupModel model) throws ShareworxServiceException;
}
