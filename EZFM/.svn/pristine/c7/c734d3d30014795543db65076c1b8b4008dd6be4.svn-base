package com.shareworx.ezfm.baseinfo.pub.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.ezfm.utils.UserUtil;

@Service(IBaseInfoQueryService.ID)
public class BaseInfoQueryServiceImp implements IBaseInfoQueryService {
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaService;

	public void setAreaService(YJWYAreaBusinessService areaService) {
		this.areaService = areaService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService orgService;

	public void setOrgService(DefaultOrgBusinessService orgService) {
		this.orgService = orgService;
	}

	@Autowired
	@Qualifier(DefaultOrgDomainService.ID)
	private DefaultOrgDomainService orgMainService;

	public void setOrgMainService(DefaultOrgDomainService orgMainService) {
		this.orgMainService = orgMainService;
	}

	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService stationService;

	public void setStationService(YJWYStationBusinessService stationService) {
		this.stationService = stationService;
	}

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userService;

	public void setUserService(YJWYUserBusinessService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier(YJWYDictBusinessService.ID)
	private YJWYDictBusinessService dictService;

	public void setDictService(YJWYDictBusinessService dictService) {
		this.dictService = dictService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	
	
	@Override
	public Map<String, String> queryDictionaryForMap(String code, int state) {
		YJWYDictModel[] models = queryDictionaryForArray(code, state);
		Map<String, String> map = new HashMap<String, String>();
		for (YJWYDictModel model : models) {
			map.put(model.getDict_code(), model.getDict_name());
		}
		return map;
	}

	@Override
	public Map<String, String> queryDictionaryNameToCodeForMap(String code, int state) {
		YJWYDictModel[] models = queryDictionaryForArray(code, state);
		Map<String, String> map = new HashMap<String, String>();
		for (YJWYDictModel model : models) {
			map.put(model.getDict_name(), model.getDict_code());
		}
		return map;
	}

	
	@Override
	public YJWYDictModel[] queryDictionaryForArray(String code, int state) {
		Query query = Query.from(YJWYDictModel.META_ID);
		query.addSelect(new String[] { "pk_dict_", "dict_code_", "dict_name_" });
		if (state > 0) {
			query.and(Condition.create("dict_parent_", code));
		} else {
			Query sQuery = Query.from(YJWYDictModel.META_ID);
			sQuery.and(Condition.create("dict_code_", code));
			YJWYDictModel[] fModel = dictService.query(sQuery);
			query.and(Condition.create("dict_parent_", fModel[0].getDict_parent()));
		}
		YJWYDictModel[] models = dictService.query(query);
		return models;
	}

	
	@Override
	public YJWYAreaModel[] queryForArea() {
		Query query = Query.from(YJWYAreaModel.META_ID);
		query.and(Condition.neq("delete_flag_", "1"));
		query.and(Condition.eq("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		query.addSelect(new String[] { "pk_area_", "area_name_" });
		YJWYAreaModel[] models = areaService.query(query);
		return models;
	}

	
	@Override
	public YJWYProjectModel[] queryForProject(String areaId) {
		return findProjectByAreaId(areaId);
	}

	
	@Override
	public YJWYStationModel[] queryForStation(String projectId) {
		JdbcTemplate read = dao.getReadTemplate();
		String andSql = "";
		if (!StringUtils.isEmpty(projectId)) {
			andSql = andSql + " and org_project_ = '" + projectId + "' ";
		}
		String sql = " select pk_station_ as pk_station,station_name_ as station_name from yjwy_pub_station where pk_station_ in (select pk_station_ from view_yjwy_project_station where 1=1 "
				+ andSql + ") and delete_flag_ <> '1'";
		List<YJWYStationModel> list = read.query(sql, new RowMapper<YJWYStationModel>() {

			@Override
			public YJWYStationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYStationModel model = new YJWYStationModel();
				model.setPk_station(rs.getString("pk_station"));
				model.setStation_name(rs.getString("station_name"));
				return model;
			}

		});
		return list.toArray(new YJWYStationModel[] {});
	}

	
	@Override
	public YJWYUserModel[] queryForUser(String areaId,String projectId,String stationId) {
		if (!StringUtils.isEmpty(stationId)) {
			return findUserByStationId(stationId);
		} else if (!StringUtils.isEmpty(projectId)) {
			List<YJWYUserModel> list = deviceService.queryUsermodelsByPkProject(projectId);
			if (list!=null&&list.size()>0) {
				return list.toArray(new YJWYUserModel[]{});
			}
			return new YJWYUserModel[]{};
//			return findUserByProjectId(projectId);
		} else if (!StringUtils.isEmpty(areaId)) {
			return findUserByAreaId(areaId);
		} else {
			Query query = Query.from(YJWYUserModel.META_ID);
			query.and(Condition.neq("delete_flag_", "1"));
			query.and(Condition.eq("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
			return userService.query(query);
		}
	}

	// 通过区域ID查询项目
	public YJWYProjectModel[] findProjectByAreaId(String areaId) {
		Query query = Query.from(YJWYProjectModel.META_ID);
		query.and(Condition.neq("delete_flag_", "1"));
		query.and(Condition.eq("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		if (!StringUtils.isEmpty(areaId)) {
			query.and(Condition.create("pk_area_", areaId));
		}
		query.addSelect(new String[] { "pk_project_", "project_name_","pk_area_" });
		YJWYProjectModel[] models = projectService.query(query);
		return models;
	}

	// 通过区域ID查询人员
	public YJWYUserModel[] findUserByAreaId(String areaId) {
		JdbcTemplate read = dao.getReadTemplate();
		YJWYProjectModel[] pros = findProjectByAreaId(areaId);
		if (pros == null || pros.length < 1) {
			return new YJWYUserModel[]{};
			// return null;
			// return new ModelAndResult(false, "该区域下无人员");
		}
		String inSql = "";
		for (YJWYProjectModel pro : pros) {
			inSql = inSql + ",'" + pro.getPk_project() + "'";
		}
		inSql = inSql.substring(1);
		String sql = " select pk_user_ as pk_user,user_name_ as user_name from yjwy_pub_user where pk_user_ in(select pk_user_ from view_yjwy_project_user where pk_project_ in ("
				+ inSql + ")) and delete_flag_ <> '1' ";
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>() {

			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.setPk_user(rs.getString("pk_user"));
				model.setUser_name(rs.getString("user_name"));
				return model;
			}

		});
		if(null!=list&&list.size()>0){
			return list.toArray(new YJWYUserModel[]{});
		}
		return new YJWYUserModel[]{};
	}

	// 通过项目ID查询人员
	public YJWYUserModel[] findUserByProjectId(String projectId) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " select pk_user_ as pk_user,user_name_ as user_name,user_code_ as user_code,em_code_ as em_code,email_ as email,phone_ as phone from yjwy_pub_user where pk_user_ in(select pk_user_ from view_yjwy_project_user where pk_project_ = '"
				+ projectId + "') and delete_flag_ <> '1' ";
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>() {

			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.setPk_user(rs.getString("pk_user"));
				model.setUser_name(rs.getString("user_name"));
				model.setEm_code(rs.getString("em_code"));
				model.setEm_code(rs.getString("user_code"));
				model.setEmail(rs.getString("email"));
				model.setPhone(rs.getString("phone"));
				return model;
			}

		});
		if(null!=list&&list.size()>0){
			return list.toArray(new YJWYUserModel[]{});
		}
		return new YJWYUserModel[]{};
	}

	// 通过岗位ID查询人员
	public YJWYUserModel[] findUserByStationId(String stationId) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select pk_user_ as pk_user,user_name_ as user_name from yjwy_pub_user where pk_user_ in (select pk_user_ from yjwy_pub_user_station where pk_station_ = '"
				+ stationId + "') and delete_flag_ <> '1' ";
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>() {

			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.setPk_user(rs.getString("pk_user"));
				model.setUser_name(rs.getString("user_name"));
				return model;
			}

		});
		if(null!=list&&list.size()>0){
			return list.toArray(new YJWYUserModel[]{});
		}
		return new YJWYUserModel[]{};
	}
	
	@Override
	public DefaultOrgModel[] queryForOrgDept(String areaId,String projectId) {
		Query query = Query.from(DefaultOrgModel.META_ID);
		query.and(Condition.neq("delete_flag_", "1"));
		if (!StringUtils.isEmpty(projectId)) {
			List<DefaultOrgModel> projectOrgs = orgMainService
					.queryListByCondition(query.and(Condition.create("org_project_", projectId)));
			List<DefaultOrgModel> queryOrgs = new ArrayList<DefaultOrgModel>();
			circleQueryForOrgs(projectOrgs, queryOrgs, Condition.create("org_type_", 3));
			return queryOrgs.toArray(new DefaultOrgModel[] {});
		} else if (!StringUtils.isEmpty(areaId)) {
			List<DefaultOrgModel> areaOrgs = orgMainService
					.queryListByCondition(query.and(Condition.create("org_area_", areaId)));
			List<DefaultOrgModel> queryOrgs = new ArrayList<DefaultOrgModel>();
			circleQueryForOrgs(areaOrgs, queryOrgs, Condition.create("org_type_", 3));
			return queryOrgs.toArray(new DefaultOrgModel[] {});
		} else {
			query.addSelect("pk_org_", "org_name_");
			List<DefaultOrgModel> areaOrgs = orgMainService
					.queryListByCondition(query.and(Condition.create("org_type_", 3)));
			return areaOrgs.toArray(new DefaultOrgModel[] {});
		}
	}
	
	/**
	 * 递归查询ORG子孙节点
	 * 
	 * @param models
	 *            父对象
	 * @param list
	 *            最后递归对象
	 * @return
	 */
	public void circleQueryForOrgs(List<DefaultOrgModel> models, List<DefaultOrgModel> list, Condition... conditions) {
		circleQueryForOrgs(models, list, null, conditions);
	}
	
	/**
	 * 递归查询ORG子孙节点
	 * 
	 * @param models
	 *            父对象
	 * @param list
	 *            最后递归对象
	 * @param orgType 递归结束类型
	 * @return
	 */
	public void circleQueryForOrgs(List<DefaultOrgModel> models, List<DefaultOrgModel> list,String orgType, Condition... conditions) {
		if (null != models && models.size() > 0) {
			Query query = Query.from(DefaultOrgModel.META_ID);
			query.and(Condition.neq("delete_flag_", "1"));
			if (null != conditions && conditions.length > 0) {
				query.and(conditions);
			}
			for (DefaultOrgModel org : models) {
				if(null!=orgType){
					if("project".equals(orgType) && "2".equals(org.getOrg_type())){
						continue;
					}
				}
				query.and(Condition.create("pk_parent_", org.getPk_org()));
				List<DefaultOrgModel> childs = orgMainService.queryListByCondition(query);
				if (null != childs && childs.size() > 0) {
					list.addAll(childs);
					circleQueryForOrgs(childs, list,orgType, conditions);
				}
			}
		}
	}
	
	@Override
	public List<YJWYProjectModel> queryProjectsByUserId(String userId){
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select * from yjwy_pub_project where pk_project_ in(select pk_project_ from view_yjwy_user where pk_user_ = '"+userId+"') and delete_flag_ <> '1'";
		List<YJWYProjectModel> list = read.query(sql, new RowMapper<YJWYProjectModel>(){

			@Override
			public YJWYProjectModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYProjectModel model = new YJWYProjectModel();
				model.setPk_crop(rs.getString("pk_crop_"));
				model.setPk_project(rs.getString("pk_project_"));
				model.setProject_name(rs.getString("project_name_"));
				model.setProject_code(rs.getString("project_code_"));
				return model;
			}
			
		});
		return list == null?new ArrayList<YJWYProjectModel>():list;
	}
	
	
	@Override
	public List<YJWYStationModel> queryStationsByUserId(String userId){
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select * from yjwy_pub_station where pk_station_ in(select pk_station_ from view_yjwy_user where pk_user_ = '"+userId+"') and delete_flag_ <> '1'";
		List<YJWYStationModel> list = read.query(sql, new RowMapper<YJWYStationModel>(){

			@Override
			public YJWYStationModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYStationModel model = new YJWYStationModel();
				model.setPk_crop(rs.getString("pk_crop_"));
				model.setPk_station(rs.getString("pk_station_"));
				model.setStation_name(rs.getString("station_name_"));
				model.setStation_code(rs.getString("station_code_"));
				return model;
			}
			
		});
		return list == null?new ArrayList<YJWYStationModel>():list;
	}
	
	//查询城市根据parent_id
	@Override
	public List<Map<String,Object>> queryCityForList(int pid) {
		String sql = " select * from pub_city where parent_id = "+pid+" order by sort";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String,Object>> list =read.queryForList(sql);
		if(null==list||list.size()<1){
			String sql2 =  " select * from pub_city where id = "+pid+" order by sort";
			list =read.queryForList(sql2);
		}
		return list;
	}
	
	//查询城市根据id
	@Override
	public Map<String,Object> queryCityForMap(int id) {
		String sql = " select * from pub_city where id = "+id;
		JdbcTemplate read = dao.getReadTemplate();
		Map<String,Object> map =read.queryForMap(sql);
		return map;
	}
	/**
	 * 通过用户Id 查询用户所属组织旗下的所有项目IDS
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> queryProjectIdsByUserId(String userId){
		Set<String> set = new HashSet<>();
		String orgSql = "select * from (yjwy_pub_org) where pk_org_ in (select distinct(pk_org_) from view_yjwy_user where pk_user_ = '"+userId+"') and delete_flag_ <> 1 "; 
		JdbcTemplate read = dao.getReadTemplate();
		List<DefaultOrgModel> orgList =read.query(orgSql, new RowMapper<DefaultOrgModel>(){
			@Override
			public DefaultOrgModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				DefaultOrgModel org = new DefaultOrgModel();
				org.setPk_crop(rs.getString("pk_crop_"));
				org.setPk_org(rs.getString("pk_org_"));
				org.setPk_parent(rs.getString("pk_parent_"));
				org.setOrg_area(rs.getString("org_area_"));
				org.setOrg_project(rs.getString("org_project_"));
				org.setOrg_type(rs.getString("org_type_"));
				org.setOrg_code(rs.getString("org_code_"));
				org.setDelete_flag(rs.getString("delete_flag_"));
				return org;
			}});
		if(null == orgList || orgList.size()<1){
			return set;
		}
		List<DefaultOrgModel> queryOrgs = new ArrayList<DefaultOrgModel>();
		circleQueryForOrgs(orgList, queryOrgs,"project", null);
		queryOrgs.addAll(orgList);
		for(DefaultOrgModel org : queryOrgs){
			if("2".equals(org.getOrg_type())){
				set.add(org.getOrg_project());
			}
		}
		return set;
	}
}
