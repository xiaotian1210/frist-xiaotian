package com.shareworx.ezfm.worktask.areaproject.service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.ezfm.worktask.areaproject.vo.WorkTaskAreaProVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;

/**
 * 片区项目关联业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaProjectNexusBusinessService extends BusinessService<YJWYWorkTaskAreaProjectNexusModel> {

	String ID = "yJWYWorkTaskAreaProjectNexusBusinessService";
	
	/**
	 * 查询片区项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询片区项目关联
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProjectModel[] queryProject(String wheSql,WorkTaskAreaProVo queryvo) throws ShareworxServiceException;
	
	/**
	 * 加载片区项目关联
	 */
	YJWYWorkTaskAreaProjectNexusModel[] load(YJWYWorkTaskAreaProjectNexusModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel[] save(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel[] update(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除片区项目关联
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaProjectNexusModel[] delete(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException;
}
