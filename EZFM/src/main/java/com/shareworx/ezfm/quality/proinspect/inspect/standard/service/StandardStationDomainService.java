package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 标准与岗位中间表领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardStationDomainService {

	
	String ID = "standardStationDomainService";
	
	/**
	 * 新增保存标准与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> save(StandardStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> update(StandardStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准与岗位中间表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> update(List<String> editFields, StandardStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除标准与岗位中间表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(StandardStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除标准与岗位中间表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除标准与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询标准与岗位中间表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条标准与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询标准与岗位中间表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载标准与岗位中间表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardStationModel> queryByExample(StandardStationModel model) throws ShareworxServiceException;
}
