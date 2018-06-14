package com.shareworx.ezfm.device.util.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;

/**
 * 设备概况业务操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYDeviceService {
	String ID = "yJWYDeviceService";

	/**
	 * 根据项目id查询报事分类（三级、二级、一级）model集合
	 * @param pk_project
	 * @return
	 */
	Set<ProblemClassModel> queryProblemClassModelsByPkProject(String pk_project);

	/**
	 * 根据项目级组织id集合获取项目级组织下子级包含的所有人员models
	 * 
	 * @param pk_project
	 * @return
	 */
	List<YJWYUserModel> queryUsermodelsByPkOrgList(List<String> pk_org_list);

	/**
	 * 根据项目id获取项目下所有人员models
	 * 
	 * @param pk_project
	 * @return
	 */
	List<YJWYUserModel> queryUsermodelsByPkProject(String pk_project);

	/**
	 * 根据用户id查询所属组织集合（使用sql，结果已去重）
	 * 
	 * @param pk_user
	 * @return
	 */
	List<DefaultOrgModel> queryPkOrgsByPkUserUseSql(String pk_user);

	/**
	 * 根据用户id查询所属组织（所属组织可能为多个）
	 * 
	 * @param pk_user
	 * @return
	 */
	List<DefaultOrgModel> queryOrgModelsByPkUser(String pk_user);

	/**
	 * 根据用户id查询所属岗位models
	 * 
	 * @param pk_user
	 * @return
	 */
	List<YJWYStationModel> queryStationModelsByPkUser(String pk_user);

	/**
	 * 通过用户id 查询用户具有权限的项目id集合
	 * 
	 * @param pk_user
	 * @return
	 */
	Set<String> queryProjectIdsByPkUser(String pk_user);

	/**
	 * 重新设置session有效期
	 * 
	 * @param request
	 * @param response
	 * @param maxInactiveInterval
	 * @return
	 */
	ModelAndResult configSessionUser(HttpServletRequest request, HttpServletResponse response, int maxInactiveInterval);

	/**
	 * 根据计划id获取设备分类集合（包含工艺步骤信息）
	 * 
	 * @param plan_id
	 * @return
	 */
	List<Map<String, Object>> queryCsiPmpByPlanId(String plan_id);

	/**
	 * 根据巡检/维保计划id获取机房集合
	 * 
	 * @param plan_id
	 * @return
	 */
	Set<String> queryRoomIdsByPlanId(String plan_id);

	/**
	 * 根据YJWY项目id获取机房列表
	 * 
	 * @param pk_project
	 * @return
	 */
	List<Map<String, Object>> queryRoomsByPkProject(String pk_project);

	/**
	 * 根据公司id获取人员model集合
	 * 
	 * @param projectIds
	 * @return
	 */
	YJWYUserModel[] queryUserModelsByPkCrop(String pk_crop);

	/**
	 * 根据项目id获取机房model
	 * 
	 * @param pk_project
	 * @return
	 */
	YJWYRoomModel[] getRoomModelsByPkProject(String pk_project);

	/**
	 * 根据区域id获取项目model
	 * 
	 * @param pk_area
	 * @return
	 */
	List<YJWYProjectModel> getProjectModelsByPkArea(String pk_area);

	/**
	 * 根据项目id获取所有区域model
	 * 
	 * @param projectIds
	 * @return
	 */
	YJWYAreaModel[] getAreaModelsByPorjectIds(Set<String> projectIds);

	/**
	 * 根据区域id获取YJWY项目id集合
	 * 
	 * @param pk_area
	 * @return
	 */
	String[] getProjectIdsByPkArea(String pk_area);

	/**
	 * 根据区域id获取项目models
	 * 
	 * @param pk_area
	 * @return
	 */
	YJWYProjectModel[] queryProjectModelsByPkArea(String pk_area);

	/**
	 * 根据用户id查询所属角色model
	 * 
	 * @param pk_user
	 * @return
	 */
	YJWYRoleModel[] getRoleModelsByPkUser(String pk_user);

	/**
	 * 通过项目id查询房间
	 * @param pk_project
	 * @return
	 */
	YJWYRoomModel[] getRoomModelsByPkProjectId(String pk_project);
}
