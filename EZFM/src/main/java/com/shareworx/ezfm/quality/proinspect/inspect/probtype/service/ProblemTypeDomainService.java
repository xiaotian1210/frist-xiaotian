package com.shareworx.ezfm.quality.proinspect.inspect.probtype.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 问题类型领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemTypeDomainService {

	
	String ID = "problemTypeDomainService";
	
	/**
	 * 新增保存问题类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> save(ProblemTypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存问题类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> update(ProblemTypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存问题类型领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> update(List<String> editFields, ProblemTypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除问题类型领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(ProblemTypeModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除问题类型领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除问题类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询问题类型领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条问题类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemTypeModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询问题类型领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载问题类型对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<ProblemTypeModel> queryByExample(ProblemTypeModel model) throws ShareworxServiceException;
}
