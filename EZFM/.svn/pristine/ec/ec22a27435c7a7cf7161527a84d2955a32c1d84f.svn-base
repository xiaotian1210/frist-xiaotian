package com.shareworx.ezfm.device.siteproject.service;

import java.util.List;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * FM与YJWY项目关联表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSiteProjectDomainService {

	
	String ID = "yJWYSiteProjectDomainService";
	
	/**
	 * 新增保存FM与YJWY项目关联表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> save(YJWYSiteProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM与YJWY项目关联表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> update(YJWYSiteProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM与YJWY项目关联表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> update(List<String> editFields, YJWYSiteProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除FM与YJWY项目关联表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYSiteProjectModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除FM与YJWY项目关联表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除FM与YJWY项目关联表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询FM与YJWY项目关联表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条FM与YJWY项目关联表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteProjectModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询FM与YJWY项目关联表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载FM与YJWY项目关联表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteProjectModel> queryByExample(YJWYSiteProjectModel model) throws ShareworxServiceException;
}
