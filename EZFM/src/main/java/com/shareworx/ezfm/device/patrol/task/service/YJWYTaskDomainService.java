package com.shareworx.ezfm.device.patrol.task.service;

import java.util.List;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保任务表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTaskDomainService {

	
	String ID = "yJWYTaskDomainService";
	
	/**
	 * 新增保存巡检维保任务表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> save(YJWYTaskModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保任务表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> update(YJWYTaskModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保任务表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> update(List<String> editFields, YJWYTaskModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保任务表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYTaskModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除巡检维保任务表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保任务表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保任务表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条巡检维保任务表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保任务表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保任务表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskModel> queryByExample(YJWYTaskModel model) throws ShareworxServiceException;
}
