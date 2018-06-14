package com.shareworx.ezfm.device.timer.service;

import java.util.List;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 定时任务执行记录领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTimerDomainService {

	
	String ID = "yJWYTimerDomainService";
	
	/**
	 * 新增保存定时任务执行记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> save(YJWYTimerModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存定时任务执行记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> update(YJWYTimerModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存定时任务执行记录领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> update(List<String> editFields, YJWYTimerModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除定时任务执行记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYTimerModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除定时任务执行记录领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除定时任务执行记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询定时任务执行记录领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条定时任务执行记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTimerModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询定时任务执行记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载定时任务执行记录对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTimerModel> queryByExample(YJWYTimerModel model) throws ShareworxServiceException;
}
