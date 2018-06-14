package com.shareworx.ezfm.baseinfo.user.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
/**
 * 用户关联关系接口
 * @author qiang.gu
 *
 */
public interface YJWYUserRelationService {
	String ID = "YJWYUserRelationService";
	/**
	 * 根据组织id查找该组织下所有用户
	 * @return
	 */
	List<YJWYUserModel> queryUserByOrg(String org_id);
	/**
	 * 根据用户id查询所属组织（所属组织可能为多个）
	 * @param user_id
	 * @return
	 */
	List<DefaultOrgModel> queryOrgByUser(String user_id);
	/**
	 * 根据用户id查询已分配岗位信息
	 * @param user_id
	 * @return
	 */
	List<YJWYStationModel> queryStationByUser(String user_id);
	/**
	 * 根据岗位id查询用户
	 * @param station_id
	 * @return
	 */
	List<YJWYUserModel> queryUserByStation(String station_id);
	
	/**
	 *根据项目查人
	 * @param station_id
	 * @return
	 */
	List<YJWYUserModel> queryUserByProject(String pk_project);
	/**
	 *根据人查项目
	 * @param station_id
	 * @return
	 */
	List<YJWYProjectModel> queryProjectByUser(String pk_user);
}
