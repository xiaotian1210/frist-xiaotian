package com.shareworx.ezfm.problem.nexus.proandclass.service;

import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 项目与报事类型关系表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProjectInfoClassNexusBusinessService extends BusinessService<YJWYProjectInfoClassNexusModel> {

	String ID = "yJWYProjectInfoClassNexusBusinessService";
	
	/**
	 * 查询项目与报事类型关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel[] query(Query query) throws ShareworxServiceException;
	
	YJWYProjectInfoClassNexusModel[] queryAreaAndProject(String pk_crop) throws ShareworxServiceException;
	
	/**
	 * 加载项目与报事类型关系表
	 */
	YJWYProjectInfoClassNexusModel[] load(YJWYProjectInfoClassNexusModel model) throws ShareworxServiceException;
	
	
	/**
	 * 新增保存项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel[] save(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel[] update(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除项目与报事类型关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectInfoClassNexusModel[] delete(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException;
}
