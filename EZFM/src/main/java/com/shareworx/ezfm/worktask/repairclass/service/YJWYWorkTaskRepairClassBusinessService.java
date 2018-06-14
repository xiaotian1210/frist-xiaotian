package com.shareworx.ezfm.worktask.repairclass.service;

import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 维修种类业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskRepairClassBusinessService extends BusinessService<YJWYWorkTaskRepairClassModel> {

	String ID = "yJWYWorkTaskRepairClassBusinessService";
	
	/**
	 * 查询维修种类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] query(Query query) throws ShareworxServiceException;
	
	
	
	/**
	 * 查询维修种类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] queryTree(String class_id) throws ShareworxServiceException;
	/**
	 * 查询项目关联种类
	 * @param projectId
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] queryClassByProject(String projectId,String pk_details_id) throws ShareworxServiceException;
	
	
	/**
	 * 查询树结构展示
	 * @param projectId
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] queryClassByTree(String pk_details_id) throws ShareworxServiceException;
	/**
	 * 加载维修种类
	 */
	YJWYWorkTaskRepairClassModel[] load(YJWYWorkTaskRepairClassModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] save(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] update(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除维修种类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] delete(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException;
}
