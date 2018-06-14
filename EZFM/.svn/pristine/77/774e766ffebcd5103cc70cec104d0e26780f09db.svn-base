package com.shareworx.ezfm.system.file.service;

import java.util.List;

import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 附件表领域操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface SystemFileDomainService {

	
	String ID = "systemFileDomainService";
	
	/**
	 * 新增保存附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<SystemFileModel> save(SystemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<SystemFileModel> update(SystemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存附件表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<SystemFileModel> update(List<String> editFields, SystemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(SystemFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除附件表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询附件表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	SystemFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<SystemFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载附件表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<SystemFileModel> queryByExample(SystemFileModel model) throws ShareworxServiceException;
}
