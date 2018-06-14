package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保计划领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPlanDomainService {

	
	String ID = "yJWYPlanDomainService";
	
	/**
	 * 新增保存巡检维保计划领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> save(YJWYPlanModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保计划领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> update(YJWYPlanModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保计划领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> update(List<String> editFields, YJWYPlanModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保计划领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYPlanModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除巡检维保计划领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保计划领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保计划领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条巡检维保计划领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保计划领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保计划对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanModel> queryByExample(YJWYPlanModel model) throws ShareworxServiceException;
}
