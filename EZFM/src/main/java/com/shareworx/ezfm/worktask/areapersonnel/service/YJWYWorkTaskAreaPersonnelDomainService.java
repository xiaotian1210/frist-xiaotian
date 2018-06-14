package com.shareworx.ezfm.worktask.areapersonnel.service;

import java.util.List;

import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 片区人员领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaPersonnelDomainService {

	
	String ID = "yJWYWorkTaskAreaPersonnelDomainService";
	
	/**
	 * 新增保存片区人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> save(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> update(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区人员领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> update(List<String> editFields, YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除片区人员领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除片区人员领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区人员领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条片区人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区人员领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区人员对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaPersonnelModel> queryByExample(YJWYWorkTaskAreaPersonnelModel model) throws ShareworxServiceException;
}
