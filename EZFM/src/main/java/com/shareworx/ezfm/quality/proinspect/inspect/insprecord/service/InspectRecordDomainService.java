package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 核查与整改记录领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface InspectRecordDomainService {

	
	String ID = "inspectRecordDomainService";
	
	/**
	 * 新增保存核查与整改记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> save(InspectRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查与整改记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> update(InspectRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查与整改记录领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> update(List<String> editFields, InspectRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除核查与整改记录领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(InspectRecordModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除核查与整改记录领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除核查与整改记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询核查与整改记录领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条核查与整改记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectRecordModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询核查与整改记录领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查与整改记录对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectRecordModel> queryByExample(InspectRecordModel model) throws ShareworxServiceException;
}
