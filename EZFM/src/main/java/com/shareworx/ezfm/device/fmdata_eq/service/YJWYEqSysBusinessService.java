package com.shareworx.ezfm.device.fmdata_eq.service;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 设备所属系统业务操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYEqSysBusinessService extends BusinessService<YJWYEqSysModel> {

	String ID = "yJWYEqSysBusinessService";
	
	/**
	 * 查询设备所属系统
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel[] query(Query query) throws ShareworxServiceException;



	/**
	 * 加载设备所属系统
	 */
	YJWYEqSysModel[] load(YJWYEqSysModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel[] save(YJWYEqSysModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel[] update(YJWYEqSysModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除设备所属系统
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYEqSysModel[] delete(YJWYEqSysModel[] models) throws ShareworxServiceException;

	/**
	 * 新增和修改时检查名称和编码
	 * @param eqSysId
	 * @param name
	 * @param code
	 * @return
	 */
	String checkNameAndCodeUnique(String parentId,String name,String code,String eqSysId);



}
