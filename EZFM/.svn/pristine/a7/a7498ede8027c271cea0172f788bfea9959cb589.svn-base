package com.shareworx.ezfm.worktask.areauser.service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.ezfm.worktask.areauser.vo.AreaUserQueryVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 片区与人员关系表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaUserNexusBusinessService extends BusinessService<YJWYWorkTaskAreaUserNexusModel> {

	String ID = "yJWYWorkTaskAreaUserNexusBusinessService";
	
	/**
	 * 查询片区与人员关系表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询全部用户
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] queryUser(AreaUserQueryVo vo,String whrSql) throws ShareworxServiceException;
	
	/**
	 * 查询维修人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaPersonnelModel[] queryUserRepair(AreaUserQueryVo vo,String whrSql) throws ShareworxServiceException;
	/**
	 * 查询维修人员
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYUserModel[] queryUserRepairAll(String areaId) throws ShareworxServiceException;
	/**
	 * 加载片区与人员关系表
	 */
	YJWYWorkTaskAreaUserNexusModel[] load(YJWYWorkTaskAreaUserNexusModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存片区与人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel[] save(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区与人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel[] update(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除片区与人员关系表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel[] delete(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException;
	
}
