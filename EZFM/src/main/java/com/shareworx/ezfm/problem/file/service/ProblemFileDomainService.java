package com.shareworx.ezfm.problem.file.service;

import java.util.List;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 报事报修文件存储领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemFileDomainService {

	
	String ID = "problemFileDomainService";
	
	/**
	 * 新增保存报事报修文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> save(ProblemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事报修文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> update(ProblemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事报修文件存储领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> update(List<String> editFields, ProblemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除报事报修文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(ProblemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除报事报修文件存储领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除报事报修文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询报事报修文件存储领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条报事报修文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询报事报修文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事报修文件存储对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemFileModel> queryByExample(ProblemFileModel model) throws ShareworxServiceException;
}
