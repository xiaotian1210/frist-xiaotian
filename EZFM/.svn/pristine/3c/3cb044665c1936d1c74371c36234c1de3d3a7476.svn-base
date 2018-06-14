package com.shareworx.ezfm.quality.file.service;

import java.util.List;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 品质核查领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface QualityFileDomainService {

	
	String ID = "qualityFileDomainService";
	
	/**
	 * 新增保存品质核查领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> save(QualityFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存品质核查领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> update(QualityFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存品质核查领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> update(List<String> editFields, QualityFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除品质核查领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(QualityFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除品质核查领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除品质核查领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询品质核查领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条品质核查领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询品质核查领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载品质核查对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<QualityFileModel> queryByExample(QualityFileModel model) throws ShareworxServiceException;
}
