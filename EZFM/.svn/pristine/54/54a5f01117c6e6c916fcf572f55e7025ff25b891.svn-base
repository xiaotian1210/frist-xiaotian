package com.shareworx.ezfm.system.crop.file.service;

import java.util.List;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 企业文件存储领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface CropFileDomainService {

	
	String ID = "cropFileDomainService";
	
	/**
	 * 新增保存企业文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> save(CropFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> update(CropFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业文件存储领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> update(List<String> editFields, CropFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除企业文件存储领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(CropFileModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除企业文件存储领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除企业文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询企业文件存储领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条企业文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询企业文件存储领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业文件存储对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<CropFileModel> queryByExample(CropFileModel model) throws ShareworxServiceException;
}
