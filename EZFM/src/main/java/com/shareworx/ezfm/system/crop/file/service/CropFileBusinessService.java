package com.shareworx.ezfm.system.crop.file.service;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 企业文件存储业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface CropFileBusinessService extends BusinessService<CropFileModel> {

	String ID = "cropFileBusinessService";
	
	/**
	 * 查询企业文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业文件存储
	 */
	CropFileModel[] load(CropFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel[] save(CropFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel[] update(CropFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除企业文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	CropFileModel[] delete(CropFileModel[] models) throws ShareworxServiceException;
}
