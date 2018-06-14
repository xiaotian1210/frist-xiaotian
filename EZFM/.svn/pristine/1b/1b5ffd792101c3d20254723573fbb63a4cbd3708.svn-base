package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 计划设备中间表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYPlanEqDomainService {

	
	String ID = "yJWYPlanEqDomainService";
	
	/**
	 * 新增保存计划设备中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanEqModel> save(YJWYPlanEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存计划设备中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanEqModel> update(YJWYPlanEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存计划设备中间表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanEqModel> update(List<String> editFields, YJWYPlanEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除计划设备中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYPlanEqModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除计划设备中间表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除计划设备中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询计划设备中间表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条计划设备中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYPlanEqModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询计划设备中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanEqModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载计划设备中间表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYPlanEqModel> queryByExample(YJWYPlanEqModel model) throws ShareworxServiceException;
}
