package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 巡检维保附件表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYTaskFileDomainService {

	
	String ID = "yJWYTaskFileDomainService";
	
	/**
	 * 新增保存巡检维保附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> save(YJWYTaskFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> update(YJWYTaskFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存巡检维保附件表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> update(List<String> editFields, YJWYTaskFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除巡检维保附件表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYTaskFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除巡检维保附件表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除巡检维保附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询巡检维保附件表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条巡检维保附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYTaskFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询巡检维保附件表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载巡检维保附件表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYTaskFileModel> queryByExample(YJWYTaskFileModel model) throws ShareworxServiceException;
}
