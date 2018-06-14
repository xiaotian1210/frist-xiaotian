package com.shareworx.ezfm.worktask.assistuser.service;

import java.util.List;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 工单协助人表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAssistUserRecordDomainService {

	
	String ID = "yJWYWorkTaskAssistUserRecordDomainService";
	
	/**
	 * 新增保存工单协助人表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> save(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单协助人表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> update(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单协助人表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> update(List<String> editFields, YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除工单协助人表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除工单协助人表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除工单协助人表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询工单协助人表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条工单协助人表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAssistUserRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询工单协助人表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载工单协助人表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAssistUserRecordModel> queryByExample(YJWYWorkTaskAssistUserRecordModel model) throws ShareworxServiceException;
}
