package com.shareworx.ezfm.worktask.areadetails.service;

import java.util.List;

import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 片区详情领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaDetailsDomainService {

	
	String ID = "yJWYWorkTaskAreaDetailsDomainService";
	
	/**
	 * 新增保存片区详情领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> save(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区详情领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> update(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区详情领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> update(List<String> editFields, YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除片区详情领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除片区详情领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区详情领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区详情领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条片区详情领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区详情领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区详情对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaDetailsModel> queryByExample(YJWYWorkTaskAreaDetailsModel model) throws ShareworxServiceException;
}
