package com.shareworx.ezfm.baseinfo.area.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 区域管理领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYAreaDomainService {

	
	String ID = "yJWYAreaDomainService";
	
	/**
	 * 新增保存区域管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> save(YJWYAreaModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存区域管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> update(YJWYAreaModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存区域管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> update(List<String> editFields, YJWYAreaModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除区域管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYAreaModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除区域管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除区域管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询区域管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条区域管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYAreaModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询区域管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载区域管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYAreaModel> queryByExample(YJWYAreaModel model) throws ShareworxServiceException;
}
