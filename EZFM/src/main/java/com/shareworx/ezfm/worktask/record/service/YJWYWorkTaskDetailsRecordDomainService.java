package com.shareworx.ezfm.worktask.record.service;

import java.util.List;

import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 工单详情表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskDetailsRecordDomainService {

	
	String ID = "yJWYWorkTaskDetailsRecordDomainService";
	
	/**
	 * 新增保存工单详情表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskDetailsRecordModel> save(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单详情表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskDetailsRecordModel> update(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单详情表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskDetailsRecordModel> update(List<String> editFields, YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除工单详情表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除工单详情表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工单详情表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工单详情表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条工单详情表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工单详情表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskDetailsRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单详情表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskDetailsRecordModel> queryByExample(YJWYWorkTaskDetailsRecordModel model) throws ShareworxServiceException;
}
