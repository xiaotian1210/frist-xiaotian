package com.shareworx.ezfm.app.basedata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ArrayELResolver;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.quality.service.AppProblemTypeService;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;

@Service(AppBaseDataDownService.ID)
public class AppBaseDataDownServiceImpl implements AppBaseDataDownService {

	final static Logger logger = Logger.getLogger(AppBaseDataDownServiceImpl.class);

	@Autowired
	@Qualifier(AppProblemTypeService.ID)
	private AppProblemTypeService problemTypeService;

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	YJWYDeviceService deviceService;

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectBusinessService;

	public void setProjectBusinessService(YJWYProjectBusinessService projectBusinessService) {
		this.projectBusinessService = projectBusinessService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/**
	 * 获取基础数据
	 */
	@Override
	public JSONObject doDownData(HttpServletRequest reqParam) throws Exception {
		logger.info("AppBaseDataDownServiceImpl/doDownData");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String userId = reqParam.getParameter("userId");
		String lt = reqParam.getParameter("lt");

		// 判断必填字段
		if (AppEmptyUtils.isEmpty(crop) || AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误
		}

		JSONObject json = new JSONObject();
		// 获取问题分类
		json.put("proType", problemTypeService.getAllProblemType(crop, lt));
		// 数据字典
		json.put("dictList", getAllDict(crop, lt));
		// 如果用户有工单权限再做维修种类的查询
		if (isHaveAppWorktask(userId, crop)) {
			// 维修种类
			json.put("repairClassList", getAllRepairClass(crop, lt));
		} else {
			List<Map<String, Object>> repairClassList = new ArrayList<>();
			json.put("repairClassList", repairClassList);
		}

		/*
		 * // 项目与维修种类关系 json.put("repairclassProject",
		 * getAllRepairClassProject(crop,lt));
		 */

		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(json);

	}

	/**
	 * 根据用户 获取用户 相关的 用户基础数据
	 * 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject doForUserData(HttpServletRequest reqParam) throws Exception {
		logger.info("BaseDataSyncService/doForUserData");

		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String userId = reqParam.getParameter("userId");

		// 判断必填字段
		if (AppEmptyUtils.isEmpty(crop) || AppEmptyUtils.isEmpty(mobilePlatform) || AppEmptyUtils.isEmpty(userId)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1, msg); // 参数错误
		}

		JSONObject json = new JSONObject();
		// 岗位list
		json.put("stationList", getStation(userId, crop));
		// 项目岗位关系list
		json.put("projectStation", getProjectStation(userId, crop));
		
		// 项目列表
		json.put("projectList", getProject(userId, crop));

		// 项目用户关系list
		json.put("projectUser", getProjectUser(userId, crop));

		// 如果用户有工单权限再做片区之类的查询
		if (isHaveAppWorktask(userId, crop)) {
			// 片区与片区人员关系表
			json.put("wtaUserNexus", getWtaUserNexus(userId, crop));
			// 片区与项目关系
			json.put("wtaProjectNexus", getWtaProjectNexus(userId, crop));
			// 维修种类与项目关系表
			json.put("repairclassProject", getRepairclassProject(userId, crop));
		} else {
			List<Map<String, Object>> wtaNexusList = new ArrayList<>();
			// 片区与片区人员关系表
			json.put("wtaUserNexus", wtaNexusList);
			// 片区与项目关系
			json.put("wtaProjectNexus", wtaNexusList);
			// 维修种类与项目关系表
			json.put("repairclassProject", wtaNexusList);
		}
		logger.info("result:success");
		return AppJsonMessage.toJsonMsgTrue(json);

	}

	/**
	 * 根據用戶id判斷用戶是否有app工單權限
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isHaveAppWorktask(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String AppPermissionSql = "select dict_code_ " + " from yjwy_pub_dict "
				+ " where dict_parent_ = 'AppPermission' " + " and pk_crop_ = '" + crop + "' " + " and pk_dict_ in "
				+ " (select pk_func_ from yjwy_app_role_func " + " where pk_crop_='" + crop + "' "
				+ " and  pk_role_ in " + " (select pk_role_ from yjwy_pub_role_user " + " where  pk_crop_='" + crop
				+ "' and pk_user_  = '" + userId + "') ) ;";
		List<Map<String, Object>> AppPermissionList = readJdbcTemplate.queryForList(AppPermissionSql);
		for (int i = 0; i < AppPermissionList.size(); i++) {
			if ("worktask".equals(AppPermissionList.get(i).get("dict_code_"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前用户有关维修种类与项目关系表
	 * 
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getRepairclassProject(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		// 通过用户获取项目ids
		List<Map<String, Object>> repairclassProjectMap = new ArrayList<>();
		String sql = "select IFNULL(GROUP_CONCAT(wtapn.project_id SEPARATOR \"', '\"),'') as project_ids from yjwy_worktask_area_project_nexus wtapn "
				+ "where wtapn.area_id in " + "( select area_id from yjwy_worktask_area_user_nexus "
				+ "where user_id ='" + userId + "' group by area_id )";

		List<Map<String, Object>> repairclassProjectId = readJdbcTemplate.queryForList(sql);
		if (repairclassProjectId.size() == 0) {
			return repairclassProjectMap;
		}

		String repairclassProjectSQL = "select * from yjwy_worktask_repair_class_project where project_id in" + " ('"
				+ repairclassProjectId.get(0).get("project_ids") + "')";

		repairclassProjectMap = readJdbcTemplate.queryForList(repairclassProjectSQL);
		return repairclassProjectMap;
	}

	/**
	 * 获取当前用户有关的片区用户关系表
	 * 
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getWtaUserNexus(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String wtaUserNexusSQL = "select wtaun.*,pu.user_name_ as user_name"
				+ " from yjwy_worktask_area_user_nexus wtaun"
				+ " left join yjwy_pub_user pu on wtaun.user_id=pu.pk_user_ where wtaun.area_id in "
				+ " (select area_id from yjwy_worktask_area_user_nexus where user_id='" + userId
				+ "' group by area_id)";
		List<Map<String, Object>> wtaUserNexusMap = readJdbcTemplate.queryForList(wtaUserNexusSQL);
		return wtaUserNexusMap;
	}

	/**
	 * 获取当前用户有关的片区项目关系表
	 * 
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getWtaProjectNexus(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String wtaProjectNexusSQL = "select * " + " from yjwy_worktask_area_project_nexus wtapn"
				+ " WHERE wtapn.area_id in" + " (select area_id from yjwy_worktask_area_user_nexus where user_id='"
				+ userId + "' GROUP BY area_id)";
		List<Map<String, Object>> wtaProjectNexusMap = readJdbcTemplate.queryForList(wtaProjectNexusSQL);
		return wtaProjectNexusMap;
	}

	/**
	 * 获取当前用户所在项目 所有项目人员
	 * 
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getProjectUserList(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String userSQL = "select pu.pk_user_ as pk_user,pu.pk_crop_ as crop,us.user_name_ as user_name"
				+ " from  view_yjwy_project_user pu" + " left join yjwy_pub_user us on pu.pk_user_ = us.pk_user_"
				+ " where pu.pk_project_ in (select pk_project_ from view_yjwy_project_user where pk_user_='" + userId
				+ "')" + " and pu.pk_crop_='" + crop + "'";
		List<Map<String, Object>> userMap = readJdbcTemplate.queryForList(userSQL);
		return userMap;
	}

	/**
	 * 根据用户和crop获取项目和岗位关系表
	 * 
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getProjectStation(String userId, String crop) {
		/*JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String projectStationSql = "select  ps.pk_station_ as pk_station,ps.org_project_ as pk_project,"
				+ " ps.pk_org_ as pk_org,ps.pk_crop_ as pk_crop" + " from view_yjwy_project_station ps"
				+ " left join 	yjwy_pub_user_station us ON ps.pk_station_=us.pk_station_" + " where us.pk_user_='"
				+ userId + "'";
		List<Map<String, Object>> projectStationList = readJdbcTemplate.queryForList(projectStationSql);*/
		List<Map<String, Object>> projectStationList = new ArrayList<>();
		return projectStationList;
	}

	/**
	 * 获取用户的岗位列表
	 * 
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getStation(String userId, String crop) {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String stationSQL = "select yps.pk_station_ as pk_station, " + "yps.station_name_ as station_name, "
				+ "yps.pk_crop_ as pk_crop " + "from yjwy_pub_station yps " + "left join yjwy_pub_user_station ypus "
				+ "on ypus.pk_station_ = yps.pk_station_ " + "where ypus.pk_user_ = '" + userId + "' "
				+ "and ypus.pk_crop_ ='" + crop + "'";
		List<Map<String, Object>> stationMap = readJdbcTemplate.queryForList(stationSQL);
		return stationMap;
	}

	/**
	 * 根据用户Id获取所有项目IDS
	 * 
	 * @param userId
	 * @return
	 */
	public String getProjectIds(String userId) {
		Set<String> projectIdSet = deviceService.queryProjectIdsByPkUser(userId);
		String projectIds = "";
		if (projectIdSet.size() > 0) {
			for (String projectId : projectIdSet) {
				projectIds += "'" + projectId + "',";
			}
			projectIds = projectIds.substring(0, projectIds.length() - 1);
		}
		return projectIds;
	}

	/**
	 * 获取与当前用户相关的项目用户关系表数据
	 * 
	 * @param crop
	 * @return
	 */
	// public List<Map<String, Object>> getProjectUser(String userId, String
	// crop) {
	// JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
	// // 项目用户关系 通过视图找出 指定公司的 人员；页面上用来 通过项目 找人员的 下拉框
	// String projectUserSQL = "select prou.pk_user_ as pk_user,prou.pk_station_
	// as pk_station,prou.pk_project_ as pk_project,"
	// + " prou.pk_org_ as pk_org, prou.pk_crop_ as pk_crop,pubu.user_name_ as
	// user_name"
	// + " from view_yjwy_project_user prou" + " left join yjwy_pub_user pubu on
	// prou.pk_user_ = pubu.pk_user_"
	// + " where prou.pk_project_ in ("+getProjectIds(userId)+")"
	// + " and prou.pk_crop_='" + crop + "'";
	// List<Map<String, Object>> projectUserMap =
	// readJdbcTemplate.queryForList(projectUserSQL);
	// return projectUserMap;
	// }
	public List<Map<String, Object>> getProjectUser(String userId, String crop) {
		List<Map<String, Object>> projectUserMap = new ArrayList<Map<String, Object>>();
		if (AppEmptyUtils.isEmpty(getProjectIds(userId))) {
			return projectUserMap;
		};
		String sql = "select * from ( "
				+ "select t1.pk_user_ pk_user,t1.pk_station_ pk_station,t2.station_name_ station_name,"
				+ " t3.org_name_ org_name,t3.org_project_ pk_project,t4.user_name_ user_name "
				+ " from yjwy_pub_user_station t1 "
				+ " left join yjwy_pub_station t2 on t1.pk_station_ = t2.pk_station_ "
				+ " left join yjwy_pub_org t3 on t2.pk_org_ = t3.pk_org_ "
				+ " left join yjwy_pub_user t4 on t4.pk_user_ = t1.pk_user_ ) t " 
				+ " where t.pk_project in ("+ getProjectIds(userId) + ")"
				+ " GROUP BY t.pk_user,t.pk_project";
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		projectUserMap = readJdbcTemplate.queryForList(sql);
		return projectUserMap;
	}

	/**
	 * 获取当前用户的项目列表
	 * 
	 * @param userId
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getProject(String userId, String crop) {
		List<Map<String, Object>> projectList = new ArrayList<>();
		Set<String> projectIdSet = deviceService.queryProjectIdsByPkUser(userId);
		
		if (AppEmptyUtils.isEmpty(projectIdSet)) {
			return projectList;
		}
		
		
		Query projectQuery = Query.from(YJWYProjectModel.META_ID);
		projectQuery.where(new Condition("pk_project_", QueryContents.TYPE_IN, projectIdSet));
		YJWYProjectModel[] projectModels = projectBusinessService.query(projectQuery);
		
		for (int i = 0; i < projectModels.length; i++) {
			Map<String, Object> project = new HashMap<>();
			project.put("pk_project", projectModels[i].get("pk_project"));
			project.put("project_name", projectModels[i].get("project_name"));
			project.put("pk_crop", projectModels[i].get("pk_crop"));
			projectList.add(project);
		}
		// JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		// // 项目列表 通过项目人员视图 指定 公司和指定用户；页面上用来 通过登录人 获取所有的属于该用户的 项目列表
		// String projectSQL = "select " + "ypp.pk_project_ as pk_project, " +
		// "ypp.project_name_ as project_name, "
		// + "ypp.pk_crop_ as pk_crop " + "from yjwy_pub_project ypp " + "left
		// join view_yjwy_project_user vypu "
		// + "on ypp.pk_project_ = vypu.pk_project_ " + "where ypp.pk_crop_ = '"
		// + crop + "' "
		// + "and vypu.pk_user_ = '" + userId + "'";
		// List<Map<String, Object>> projectMap =
		// readJdbcTemplate.queryForList(projectSQL);
		return projectList;
	}

	/**
	 * 获取所有字典列表
	 * 
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getAllDict(String crop, String lt) {
		logger.info("BaseDataSyncService/getAllDict");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();

		// 数据字典列表
		String dicSQL = "select pk_dict_ as pk_dict, " + " dict_code_ as dict_code, " + " dict_name_ as dict_name,  "
				+ " dict_sort_ as dict_sort, " + " dict_node_ as dict_node, " + " dict_parent_ as dict_parent, "
				+ " dict_modificationTime_ as dict_modificationTime " + " from yjwy_pub_dict " + " where pk_crop_ = '"
				+ crop + "'";
		
		List<Map<String, Object>> dicList = new ArrayList<>();
		if("0".equals(lt)){
			dicList = readJdbcTemplate.queryForList(dicSQL);
		}else{
			String updateSql = dicSQL+ " and dict_modificationTime_ > '"+lt+"'";
			dicList = readJdbcTemplate.queryForList(updateSql);
			if(dicList.size()>0){
				dicList = readJdbcTemplate.queryForList(dicSQL);
			}
		}
		return dicList;
	}

	/**
	 * 根据 crop 获取 维修种类
	 * 
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getAllRepairClass(String crop, String lt) {
		logger.info("BaseDataSyncService/getAllRepairClass");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String repairClassSql = "select pk_class_id, class_name, class_code, "
				+ "rated_worktime, labor_cost, material_cost, mechanics_cost, " + "sort,"
				+ "parent_id, whether_root,update_time,pk_crop " + "from yjwy_worktask_repair_class "
				+ "where pk_crop = '" + crop + "'";
		List<Map<String, Object>> repairClassList = new ArrayList<>();
		
		if("0".equals(lt)){
			repairClassList = readJdbcTemplate.queryForList(repairClassSql);
		}else{
			String updateSql =repairClassSql+ " and update_time > '"+lt+"'";
			repairClassList = readJdbcTemplate.queryForList(updateSql);
			if(repairClassList.size()>0){
				repairClassList = readJdbcTemplate.queryForList(repairClassSql);
			}
		}
		return repairClassList;
	}

	/**
	 * 项目和维修种类的 关系
	 * 
	 * @param crop
	 * @return
	 */
	public List<Map<String, Object>> getAllRepairClassProject(String crop, String lt) {
		logger.info("BaseDataSyncService/getAllRepairClassProject");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		String repairClassSql = "select pk_nexus_id, project_id, " + "class_id, create_time, "
				+ "create_user_id, update_time, " + "update_user_id, pk_crop "
				+ "from yjwy_worktask_repair_class_project " + "where pk_crop = '" + crop + "'";

		if (!"0".equals(lt)) {
			repairClassSql += " and update_time > '" + lt + "'";
		}

		List<Map<String, Object>> repairClassList = readJdbcTemplate.queryForList(repairClassSql);
		return repairClassList;
	}
}
