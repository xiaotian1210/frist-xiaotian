package com.shareworx.ezfm.problem.record.service;

import java.util.List;

import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 报事记录领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProblemRecordDomainService {

	
	String ID = "yJWYProblemRecordDomainService";
	
	/**
	 * 新增保存报事记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemRecordModel> save(YJWYProblemRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemRecordModel> update(YJWYProblemRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事记录领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemRecordModel> update(List<String> editFields, YJWYProblemRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除报事记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYProblemRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除报事记录领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事记录领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条报事记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事记录对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemRecordModel> queryByExample(YJWYProblemRecordModel model) throws ShareworxServiceException;
}
