package com.shareworx.ezfm.baseinfo.resources.file.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 资源附件领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourceFileDomainService {

	
	String ID = "yJWYResourceFileDomainService";
	
	/**
	 * 新增保存资源附件领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> save(YJWYResourceFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源附件领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> update(YJWYResourceFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源附件领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> update(List<String> editFields, YJWYResourceFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除资源附件领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYResourceFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除资源附件领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源附件领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源附件领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条资源附件领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourceFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源附件领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源附件对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourceFileModel> queryByExample(YJWYResourceFileModel model) throws ShareworxServiceException;
}
