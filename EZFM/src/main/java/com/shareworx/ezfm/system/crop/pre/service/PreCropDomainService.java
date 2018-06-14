package com.shareworx.ezfm.system.crop.pre.service;

import java.util.List;

import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 企业信息预采集实体领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface PreCropDomainService {

	
	String ID = "preCropDomainService";
	
	/**
	 * 新增保存企业信息预采集实体领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> save(PreCropModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业信息预采集实体领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> update(PreCropModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业信息预采集实体领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> update(List<String> editFields, PreCropModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除企业信息预采集实体领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(PreCropModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除企业信息预采集实体领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除企业信息预采集实体领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询企业信息预采集实体领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条企业信息预采集实体领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询企业信息预采集实体领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业信息预采集实体对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PreCropModel> queryByExample(PreCropModel model) throws ShareworxServiceException;
}
