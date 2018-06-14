package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 版本与岗位中间表领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface EditionStationDomainService {

	
	String ID = "editionStationDomainService";
	
	/**
	 * 新增保存版本与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> save(EditionStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存版本与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> update(EditionStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存版本与岗位中间表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> update(List<String> editFields, EditionStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除版本与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(EditionStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除版本与岗位中间表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除版本与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询版本与岗位中间表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条版本与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	EditionStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询版本与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载版本与岗位中间表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<EditionStationModel> queryByExample(EditionStationModel model) throws ShareworxServiceException;
}
