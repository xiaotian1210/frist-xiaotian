package com.shareworx.ezfm.baseinfo.resourceslog.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 记录资源空间日志表领域操作接口
 * @author kl
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourcesLogDomainService {

	
	String ID = "yJWYResourcesLogDomainService";
	
	/**
	 * 新增保存记录资源空间日志表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> save(YJWYResourcesLogModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存记录资源空间日志表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> update(YJWYResourcesLogModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存记录资源空间日志表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> update(List<String> editFields, YJWYResourcesLogModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除记录资源空间日志表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYResourcesLogModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除记录资源空间日志表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除记录资源空间日志表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询记录资源空间日志表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条记录资源空间日志表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesLogModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询记录资源空间日志表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载记录资源空间日志表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesLogModel> queryByExample(YJWYResourcesLogModel model) throws ShareworxServiceException;
}
