package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 核查标准领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface InspectStandardDomainService {

	
	String ID = "inspectStandardDomainService";
	
	/**
	 * 新增保存核查标准领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectStandardModel> save(InspectStandardModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectStandardModel> update(InspectStandardModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存核查标准领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectStandardModel> update(List<String> editFields, InspectStandardModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除核查标准领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(InspectStandardModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除核查标准领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除核查标准领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询核查标准领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条核查标准领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	InspectStandardModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询核查标准领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectStandardModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载核查标准对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<InspectStandardModel> queryByExample(InspectStandardModel model) throws ShareworxServiceException;

	/**
	 * 获取标准列表（包含条件查询）
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryMap(String param);
	
	/**
	 * 查询条数
	 * @param param
	 * @return
	 */
	Long queryCount(String param);
}
