package com.shareworx.ezfm.worktask.classpro.service;

import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 种类项目关联业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskRepairClassProjectBusinessService extends BusinessService<YJWYWorkTaskRepairClassProjectModel> {

	String ID = "yJWYWorkTaskRepairClassProjectBusinessService";
	
	/**
	 * 查询种类项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载种类项目关联
	 */
	YJWYWorkTaskRepairClassProjectModel[] load(YJWYWorkTaskRepairClassProjectModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel[] save(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel[] update(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除种类项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassProjectModel[] delete(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException;
}
