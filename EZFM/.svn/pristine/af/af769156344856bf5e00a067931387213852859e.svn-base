package com.shareworx.ezfm.system.crop.service;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.system.crop.model.CropModel;

/**
 * 企业管理业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface CropBusinessService extends BusinessService<CropModel> {

	String ID = "cropBusinessService";
	
	/**
	 * 查询企业管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业管理
	 */
	CropModel[] load(CropModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存企业管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel[] save(CropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel[] update(CropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除企业管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropModel[] delete(CropModel[] models) throws ShareworxServiceException;
	
	YJWYUserModel[] userSave(YJWYUserModel[] models) throws ShareworxServiceException;
	YJWYUserModel[] userUpdate(YJWYUserModel[] models) throws ShareworxServiceException;
}
