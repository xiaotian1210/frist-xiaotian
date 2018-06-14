package com.shareworx.ezfm.baseinfo.station.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 岗位管理领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYStationDomainService {

	
	String ID = "yJWYStationDomainService";
	
	/**
	 * 新增保存岗位管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> save(YJWYStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存岗位管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> update(YJWYStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存岗位管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> update(List<String> editFields, YJWYStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除岗位管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除岗位管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除岗位管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询岗位管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条岗位管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询岗位管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载岗位管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYStationModel> queryByExample(YJWYStationModel model) throws ShareworxServiceException;
}
