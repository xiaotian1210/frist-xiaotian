package com.shareworx.ezfm.baseinfo.userstation.service;

import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 人员岗位关系业务操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface UserStationBusinessService extends BusinessService<UserStationModel> {

	String ID = "userStationBusinessService";
	
	/**
	 * 查询人员岗位关系
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载人员岗位关系
	 */
	UserStationModel[] load(UserStationModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel[] save(UserStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel[] update(UserStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel[] delete(UserStationModel[] models) throws ShareworxServiceException;
}
