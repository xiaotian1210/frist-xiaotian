package com.shareworx.ezfm.system.crop.pre.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.ezfm.system.crop.pre.vo.PreCropVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 企业信息预采集实体业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface PreCropBusinessService extends BusinessService<PreCropModel> {

	String ID = "preCropBusinessService";
	
	/**
	 * 查询企业信息预采集实体
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载企业信息预采集实体
	 */
	PreCropModel[] load(PreCropModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel[] save(PreCropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel[] update(PreCropModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除企业信息预采集实体
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel[] delete(PreCropModel[] models) throws ShareworxServiceException;
	/**
	 * 企业信息预先申请
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	PreCropModel[] apply(PreCropVo model) throws ShareworxServiceException;


}
