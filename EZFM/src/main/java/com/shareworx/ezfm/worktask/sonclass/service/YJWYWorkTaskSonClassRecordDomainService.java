package com.shareworx.ezfm.worktask.sonclass.service;

import java.util.List;

import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 工单维修种类记录表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskSonClassRecordDomainService {

	
	String ID = "yJWYWorkTaskSonClassRecordDomainService";
	
	/**
	 * 新增保存工单维修种类记录表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskSonClassRecordModel> save(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单维修种类记录表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskSonClassRecordModel> update(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单维修种类记录表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskSonClassRecordModel> update(List<String> editFields, YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除工单维修种类记录表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除工单维修种类记录表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工单维修种类记录表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工单维修种类记录表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条工单维修种类记录表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskSonClassRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工单维修种类记录表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskSonClassRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单维修种类记录表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskSonClassRecordModel> queryByExample(YJWYWorkTaskSonClassRecordModel model) throws ShareworxServiceException;
}
