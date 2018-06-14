package com.shareworx.ezfm.quality.file.service;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 品质核查业务操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface QualityFileBusinessService extends BusinessService<QualityFileModel> {

	String ID = "qualityFileBusinessService";
	
	/**
	 * 查询品质核查
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载品质核查
	 */
	QualityFileModel[] load(QualityFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel[] save(QualityFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel[] update(QualityFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除品质核查
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	QualityFileModel[] delete(QualityFileModel[] models) throws ShareworxServiceException;
}
