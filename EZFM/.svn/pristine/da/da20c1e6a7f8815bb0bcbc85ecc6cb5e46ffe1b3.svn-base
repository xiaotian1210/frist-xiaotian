package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * FM项目表领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSiteDomainService {

	
	String ID = "yJWYSiteDomainService";
	
	/**
	 * 新增保存FM项目表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteModel> save(YJWYSiteModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM项目表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteModel> update(YJWYSiteModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存FM项目表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteModel> update(List<String> editFields, YJWYSiteModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除FM项目表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYSiteModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除FM项目表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除FM项目表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询FM项目表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条FM项目表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSiteModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询FM项目表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载FM项目表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSiteModel> queryByExample(YJWYSiteModel model) throws ShareworxServiceException;
}
