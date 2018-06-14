package com.shareworx.ezfm.worktask.repairclass.service;

import java.util.List;

import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 维修种类领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskRepairClassDomainService {

	
	String ID = "yJWYWorkTaskRepairClassDomainService";
	
	/**
	 * 新增保存维修种类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> save(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存维修种类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> update(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存维修种类领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> update(List<String> editFields, YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除维修种类领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除维修种类领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除维修种类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询维修种类领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条维修种类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询维修种类领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载维修种类对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskRepairClassModel> queryByExample(YJWYWorkTaskRepairClassModel model) throws ShareworxServiceException;
}
