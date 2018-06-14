package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 资源管理领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYResourcesDomainService {

	
	String ID = "yJWYResourcesDomainService";
	
	/**
	 * 新增保存资源管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesModel> save(YJWYResourcesModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesModel> update(YJWYResourcesModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存资源管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesModel> update(List<String> editFields, YJWYResourcesModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除资源管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYResourcesModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除资源管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除资源管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询资源管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条资源管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYResourcesModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询资源管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载资源管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYResourcesModel> queryByExample(YJWYResourcesModel model) throws ShareworxServiceException;
	
	/**
	 * 根据id查询地址
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	String queryNameByResourcesId(String pk_resources) throws ShareworxServiceException;
}
