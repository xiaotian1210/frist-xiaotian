package com.shareworx.ezfm.energyloss.tabledefinition.service;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 能耗数据修改表业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWJEnergyUpdateBusinessService extends BusinessService<YJWJEnergyUpdateModel> {

	String ID = "yJWJEnergyUpdateBusinessService";
	
	/**
	 * 查询能耗数据修改表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载能耗数据修改表
	 */
	YJWJEnergyUpdateModel[] load(YJWJEnergyUpdateModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel[] save(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel[] update(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除能耗数据修改表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWJEnergyUpdateModel[] delete(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException;
}
