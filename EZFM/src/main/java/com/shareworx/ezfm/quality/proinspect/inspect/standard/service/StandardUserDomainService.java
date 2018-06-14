package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 核查标准人员中间表领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardUserDomainService {

	
	String ID = "standardUserDomainService";
	
	/**
	 * 新增保存核查标准人员中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> save(StandardUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准人员中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> update(StandardUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准人员中间表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> update(List<String> editFields, StandardUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除核查标准人员中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(StandardUserModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除核查标准人员中间表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除核查标准人员中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询核查标准人员中间表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条核查标准人员中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardUserModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询核查标准人员中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查标准人员中间表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardUserModel> queryByExample(StandardUserModel model) throws ShareworxServiceException;
}
