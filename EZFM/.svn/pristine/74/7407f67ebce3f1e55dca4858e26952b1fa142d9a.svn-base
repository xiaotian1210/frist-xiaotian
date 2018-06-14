package com.shareworx.ezfm.problem.details.service;

import java.util.List;

import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 报事主类领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProblemDetailsDomainService {

	
	String ID = "yJWYProblemDetailsDomainService";
	
	/**
	 * 新增保存报事主类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> save(YJWYProblemDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事主类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> update(YJWYProblemDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事主类领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> update(List<String> editFields, YJWYProblemDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除报事主类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYProblemDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除报事主类领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事主类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事主类领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条报事主类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事主类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事主类对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYProblemDetailsModel> queryByExample(YJWYProblemDetailsModel model) throws ShareworxServiceException;
	/**
	 * 根据项目ID获取报事单号
	 * @param projectId 项目ID
	 * @return 报事单号     BS-+项目编号前四位+年月日+四位流水号
	 * @throws ShareworxServiceException
	 */
	String getCodeByProjectId(String projectId) throws ShareworxServiceException;
}
