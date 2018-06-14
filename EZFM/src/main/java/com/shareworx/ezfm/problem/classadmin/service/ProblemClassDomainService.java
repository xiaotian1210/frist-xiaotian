package com.shareworx.ezfm.problem.classadmin.service;

import java.util.List;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 报事分类管理领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemClassDomainService {

	
	String ID = "problemClassDomainService";
	
	/**
	 * 新增保存报事分类管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> save(ProblemClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事分类管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> update(ProblemClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事分类管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> update(List<String> editFields, ProblemClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除报事分类管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(ProblemClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除报事分类管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事分类管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事分类管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条报事分类管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事分类管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事分类管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemClassModel> queryByExample(ProblemClassModel model) throws ShareworxServiceException;
}
