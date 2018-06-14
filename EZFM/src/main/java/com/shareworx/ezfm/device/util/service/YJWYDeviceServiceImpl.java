package com.shareworx.ezfm.device.util.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.interceptor.YJWYWebAuthSessionListener;
import com.shareworx.platform.exception.BusinessException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.mvc.Session;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ModelUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleBusinessService;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleUserBusinessService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 设备概况业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYDeviceService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYDeviceServiceImpl implements YJWYDeviceService {

	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();

	@Autowired
	@Qualifier(SessionFactory.ID)
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectBusinessService;

	public void setProjectBusinessService(YJWYProjectBusinessService projectBusinessService) {
		this.projectBusinessService = projectBusinessService;
	}

	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaBusinessService;

	public void setAreaBusinessService(YJWYAreaBusinessService areaBusinessService) {
		this.areaBusinessService = areaBusinessService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService roomService;

	public void setRoomService(YJWYRoomBusinessService roomService) {
		this.roomService = roomService;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProjectService;

	public void setSiteProjectService(YJWYSiteProjectService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Autowired
	@Qualifier(YJWYRoleUserBusinessService.ID)
	private YJWYRoleUserBusinessService roleUserBusinessService;

	public void setRoleUserBusinessService(YJWYRoleUserBusinessService roleUserBusinessService) {
		this.roleUserBusinessService = roleUserBusinessService;
	}

	@Autowired
	@Qualifier(YJWYRoleBusinessService.ID)
	private YJWYRoleBusinessService roleBusinessService;

	public void setRoleBusinessService(YJWYRoleBusinessService roleBusinessService) {
		this.roleBusinessService = roleBusinessService;
	}

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;

	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	@Autowired
	@Qualifier(YJWYPlanEqBusinessService.ID)
	private YJWYPlanEqBusinessService planEqBusinessService;

	public void setPlanEqBusinessService(YJWYPlanEqBusinessService planEqBusinessService) {
		this.planEqBusinessService = planEqBusinessService;
	}

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;

	@Autowired
	@Qualifier(DefaultOrgDomainService.ID)
	private DefaultOrgDomainService orgDomainService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectDomainService;
	
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaDomainService;
	

	@Override
	public Set<ProblemClassModel> queryProblemClassModelsByPkProject(String pk_project) {
		if (StringUtils.isEmpty(pk_project)) {
			return new HashSet<>();
		}
		Query query = Query.from(YJWYProjectInfoClassNexusModel.META_ID);
		query.where(new Condition("project_id", QueryContents.TYPE_EQ, pk_project));
		List<YJWYProjectInfoClassNexusModel> list = service.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new HashSet<>();
		}
		List<Object> class_ids = ModelUtils.getModelFieldValues(list, "class_id");
		Query query2 = Query.from(ProblemClassModel.META_ID);
		List<ProblemClassModel> list2 = service.queryListByCondition(query2);
		if (ArrayUtils.isEmpty(list2)) {
			return new HashSet<>();
		}
		Set<ProblemClassModel> set = new HashSet<>();
		Set<String> twoSet = new HashSet<>();
		Set<String> oneSet = new HashSet<>();
		Set<ProblemClassModel> removeSet = new HashSet<>();
		String temp = null;
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : class_ids) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					twoSet.add(problemClassModel.getParent_id());
					removeSet.add(problemClassModel);
					break;
				}
			}
		}
		list2.removeAll(removeSet);
		removeSet.clear();
		// 查二级
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : twoSet) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					oneSet.add(problemClassModel.getParent_id());
					removeSet.add(problemClassModel);
					break;
				}
			}
		}
		list2.removeAll(removeSet);
		// 查一级
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : oneSet) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					break;
				}
			}
		}
		return set;
	}

	@Override
	public List<YJWYUserModel> queryUsermodelsByPkOrgList(List<String> pk_org_list) {
//		Query query = Query.from(DefaultOrgModel.META_ID);
//		query.where();
//		query.and(new Condition("pk_parent_", QueryContents.TYPE_IN, pk_org_list));
//		query.or(new Condition("pk_org_", QueryContents.TYPE_IN, pk_org_list));
//		List<DefaultOrgModel> org_list = service.queryListByCondition(query);
//		Query query2 = Query.from(YJWYStationModel.META_ID);
//		query2.where(new Condition("delete_flag_", QueryContents.TYPE_NEQ, 1));
//		query2.and(new Condition("pk_org_", QueryContents.TYPE_IN, ModelUtils.getModelPrimarys(org_list)));
//		List<YJWYStationModel> station_list = service.queryListByCondition(query2);
//		Query query3 = Query.from(UserStationModel.META_ID);
//		query3.where(new Condition("pk_station_", QueryContents.TYPE_IN, ModelUtils.getModelPrimarys(station_list)));
//		List<YJWYStationModel> userstation_list = service.queryListByCondition(query3);
		if (ArrayUtils.isEmpty(pk_org_list)) {
			return new ArrayList<>();
		}
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append("PK_USER_ PK_USER,PK_CROP_ PK_CROP,EM_CODE_ EM_CODE,USER_NAME_ USER_NAME,USER_CODE_ USER_CODE," + "PASSWORD_ PASSWORD,EMAIL_ EMAIL,PHONE_ PHONE,IS_PRE_ IS_PRE,");
		sql.append("IS_ABLE_ IS_ABLE,HEADER_IMG_ HEADER_IMG,CREATE_USER_ CREATE_USER," + "CREATE_TIME_ CREATE_TIME,LAST_MODIFY_USER_ LAST_MODIFY_USER,LAST_MODIFY_TIME_ LAST_MODIFY_TIME,");
		sql.append("UPDATE_TIME_ UPDATE_TIME,DELETE_FLAG_ DELETE_FLAG,IS_SIGN_ IS_SIGN FROM YJWY_PUB_USER ");
		sql.append("WHERE DELETE_FLAG_ <> 1 AND PK_USER_ IN (");
		sql.append("SELECT PK_USER_ FROM YJWY_PUB_USER_STATION WHERE PK_STATION_ IN (");
		sql.append("SELECT PK_STATION_ FROM YJWY_PUB_STATION WHERE DELETE_FLAG_ <> 1 AND PK_ORG_ IN (");
		sql.append("SELECT PK_ORG_ FROM YJWY_PUB_ORG WHERE DELETE_FLAG_ <> 1 ");
		sql.append("AND (");
		sql.append(DeviceUtil.getInNotInSql("pk_parent_", QueryContents.TYPE_IN, pk_org_list.toArray(new String[] {})));
		sql.append(" or ");
		sql.append(DeviceUtil.getInNotInSql("pk_org_", QueryContents.TYPE_IN, pk_org_list.toArray(new String[] {})));
		sql.append("))))");
		List<YJWYUserModel> userModels = readTemplate.query(sql.toString(), new Object[] {}, new BeanPropertyRowMapper<YJWYUserModel>(YJWYUserModel.class));
		return userModels;
	}

	@Override
	public List<YJWYUserModel> queryUsermodelsByPkProject(String pk_project) {
		Query query = Query.from(DefaultOrgModel.META_ID).where(new Condition("org_project_", QueryContents.TYPE_EQ, pk_project));
		List<DefaultOrgModel> org_list = service.queryListByCondition(query);
		if (ArrayUtils.isEmpty(org_list)) {
			return new ArrayList<>();
		}
		Query query2 = Query.from(YJWYStationModel.META_ID).where(new Condition("pk_org_", QueryContents.TYPE_IN, DeviceUtil.getPrimaryKeyValue(org_list.toArray(new DefaultOrgModel[] {}))));
		List<YJWYStationModel> station_list = service.queryListByCondition(query2);
		if (ArrayUtils.isEmpty(station_list)) {
			return new ArrayList<>();
		}
		Query query3 = Query.from(UserStationModel.META_ID).where(new Condition("pk_station_", QueryContents.TYPE_IN, DeviceUtil.getPrimaryKeyValue(station_list.toArray(new YJWYStationModel[] {}))));
		List<UserStationModel> userstation_list = service.queryListByCondition(query3);
		if (ArrayUtils.isEmpty(userstation_list)) {
			return new ArrayList<>();
		}
		Query query4 = Query.from(YJWYUserModel.META_ID).where(new Condition("pk_user_", QueryContents.TYPE_IN, DeviceUtil.getPrimaryKeyValue(userstation_list.toArray(new UserStationModel[] {}))));
		List<YJWYUserModel> user_list = service.queryListByCondition(query4);
		return user_list;
	}

	@Override
	public List<DefaultOrgModel> queryOrgModelsByPkUser(String pk_user) {
		List<YJWYStationModel> stationModels = this.queryStationModelsByPkUser(pk_user);
		if (ArrayUtils.isEmpty(stationModels)) {
			return new ArrayList<>();
		}
		Set<String> pk_org_set = new HashSet<>();
		for (YJWYStationModel stationModel : stationModels) {
			pk_org_set.add(stationModel.getPk_org());
		}
		Query query = Query.from(DefaultOrgModel.META_ID).where(Condition.create("pk_org_").in(pk_org_set.toArray(new String[pk_org_set.size()])));
		query.and(Condition.neq("delete_flag_", "1"));
		return service.queryListByCondition(query);
	}

	@Override
	public List<YJWYStationModel> queryStationModelsByPkUser(String pk_user) {
		Query query = Query.from(UserStationModel.META_ID).where(Condition.create("pk_user_").eq(pk_user));
		List<UserStationModel> userStationModels = service.queryListByCondition(query);
		if (ArrayUtils.isEmpty(userStationModels)) {
			return new ArrayList<>();
		}
		Set<String> pk_station_set = new HashSet<>();
		for (UserStationModel userStationModel : userStationModels) {
			pk_station_set.add(userStationModel.getPk_station());
		}

		Query query2 = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_station_").in(pk_station_set.toArray(new String[pk_station_set.size()])));
		query2.and(Condition.neq("delete_flag_", "1"));
		return service.queryListByCondition(query2);
	}

	@Override
	public List<DefaultOrgModel> queryPkOrgsByPkUserUseSql(String pk_user) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT T2.PK_ORG_ PK_ORG,T3.ORG_TYPE_ ORG_TYPE,T3.ORG_AREA_ ORG_AREA,T3.ORG_PROJECT_ ORG_PROJECT FROM (");
		sql.append("SELECT PK_USER_,PK_STATION_,PK_CROP_ FROM YJWY_PUB_USER_STATION ");
		sql.append("WHERE PK_USER_ = '" + pk_user + "') T1 ");
		sql.append("LEFT JOIN YJWY_PUB_STATION T2 ON T1.PK_STATION_ = T2.PK_STATION_ ");
		sql.append("LEFT JOIN YJWY_PUB_ORG T3 ON T2.PK_ORG_ = T3.PK_ORG_ ");
		return readTemplate.query(sql.toString(), new Object[] {}, new BeanPropertyRowMapper<DefaultOrgModel>(DefaultOrgModel.class));
	}

	@Override
	public Set<String> queryProjectIdsByPkUser(String pk_user) {
		List<DefaultOrgModel> orgModels = this.queryPkOrgsByPkUserUseSql(pk_user);
		if (ArrayUtils.isEmpty(orgModels)) {
			return new HashSet<>();
		}
		List<DefaultOrgModel> sonOrgModels = new ArrayList<>();
		queryAllSonOrgModels(orgModels, sonOrgModels);
		Set<String> pk_project_set = new HashSet<>();
		Set<String> pk_area_set = new HashSet<>();
		if (!ArrayUtils.isEmpty(sonOrgModels)) {
			for (DefaultOrgModel orgModel : sonOrgModels) {
				if (StringUtils.isEmpty(orgModel.getOrg_project())) {
					if (StringUtils.isEmpty(orgModel.getOrg_area())) {
						continue;
					} else {
						pk_area_set.add(orgModel.getOrg_area());
					}
				} else {
					pk_project_set.add(orgModel.getOrg_project());
				}
			}
			if (!ArrayUtils.isEmpty(pk_area_set)) {
				Query projectQuery = Query.from(YJWYProjectModel.META_ID);
				projectQuery.where(Condition.neq("delete_flag_", "1"));
				projectQuery.and(new Condition("pk_area_", QueryContents.TYPE_IN, pk_area_set));
				List<YJWYProjectModel> projectModels = service.queryListByCondition(projectQuery);
				for (YJWYProjectModel yjwyProjectModel : projectModels) {
					pk_project_set.add(yjwyProjectModel.getPk_project());
				}
			}
		}
		return pk_project_set;
	}

	/**
	 * 根据层级字段查询所有子节点
	 * 
	 * @param orgModels
	 * @param sonOrgModels
	 * @param conditions
	 */
	private void queryAllSonOrgModels(List<DefaultOrgModel> orgModels, List<DefaultOrgModel> sonOrgModels, Condition... conditions) {
		for (DefaultOrgModel model : orgModels) {
			String pk_org = model.getPk_org();
			Query query = Query.from(DefaultOrgModel.META_ID).where(Condition.neq("delete_flag_", "1"));
			if (null != conditions && conditions.length > 0) {
				query.and(conditions);
			}
			// 组织类型为项目，不进行子节点查询
			if ("2".equals(model.getOrg_type()) || "3".equals(model.getOrg_type())) {
				sonOrgModels.add(model);
				continue;
			}
			query.and(Condition.like("hierarchy_ids_", "%|" + pk_org + "|%"));
			sonOrgModels.addAll(orgDomainService.queryListByCondition(query));
		}
	}

	@Override
	public ModelAndResult configSessionUser(HttpServletRequest request, HttpServletResponse response, int maxInactiveInterval) {
		YJWYUserModel model = UserUtil.getCurrentUser();
		String sessionId = sessionFactory.createSession(model.getUser_code(), LoginCommons.DOMAINSYS);
		model.setAttribute("token", sessionId);
		if (StringUtils.isEmpty(sessionId)) {
			throw new BusinessException("无法获取sessionId");
		}
		request.getSession().setMaxInactiveInterval(maxInactiveInterval);
		request.getSession().setAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY, sessionId);

		Session session = sessionFactory.getSession(sessionId);
		session.setPk_user(model.getPk_user());
		session.setUserObject(model);
		session.setRemoteHost(request.getRemoteHost());
		sessionFactory.updateSession(sessionId, session);
		SessionContext context = new SessionContext(sessionId);
		ThreadLocalContextHolder.setContext(context);
		ModelAndResult result = new ModelAndResult(model);
		return result;
	}

	@Override
	public List<Map<String, Object>> queryCsiPmpByPlanId(String plan_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t1.csi_id csi_id,t1.pmp_id pmp_id,t2.dict_name_ dict_name,");
		sql.append("t3.description pmp_description,t2.pk_dict_ pk_dict,t2.pk_crop_ pk_crop ");
		sql.append("from yjwy_patrol_plan_eq t1 ");
		sql.append("left join yjwy_pub_dict t2 on t1.csi_id = t2.pk_dict_ ");
		sql.append("left join yjwy_fmdata_pmp t3 on t1.pmp_id = t3.pmp_id ");
		if (!DeviceUtil.stringIsEmpty(plan_id)) {
			sql.append("where t1.plan_id ='" + plan_id + "' ");
		}
		return readTemplate.queryForList(sql.toString());
	}

	@Override
	public Set<String> queryRoomIdsByPlanId(String plan_id) {
		Set<String> idSet = new HashSet<>();
		if (!DeviceUtil.stringIsEmpty(plan_id)) {
			YJWYPlanEqModel[] planEqModels = planEqBusinessService.query(Query.from(YJWYPlanEqModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_EQ, plan_id)));
			for (YJWYPlanEqModel yjwyPlanEqModel : planEqModels) {
				idSet.add(yjwyPlanEqModel.getRm_id());
			}
		}
		return idSet;
	}

	@Override
	public List<Map<String, Object>> queryRoomsByPkProject(String pk_project) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.rm_id rm_id,t1.fm_code fm_code,t1.name name,t1.site_id site_id,");
		sql.append("t1.pk_crop pk_crop,t1.flag flag,t2.pk_project_ pk_project,t2.pk_area_ pk_area,");
		sql.append("t2.project_name_ project_name,t2.project_code_ project_code ");
		sql.append("from yjwy_fmdata_room t1 ");
//		sql.append("left join yjwy_fmdata_site_project t2 on t1.site_id = t2.site_id ");
//		sql.append("left join yjwy_pub_project t3 on t2.pk_project = t3.pk_project_ ");
		sql.append("left join yjwy_pub_project t2 on t2.pk_project_ = t1.site_id ");
		sql.append("where t1.flag = 1 ");
		sql.append("and t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		if (!DeviceUtil.stringIsEmpty(pk_project)) {
			sql.append("and t2.pk_project_ = '" + pk_project + "' ");
		}
		return readTemplate.queryForList(sql.toString());
	}

	@Override
	public YJWYRoomModel[] getRoomModelsByPkProject(String pk_project) {
		// 根据YJWY项目id获取FM项目id集合
		String[] siteIds = siteProjectService.queryIds(new String[] { pk_project });
		if (siteIds.length != 0) {
			// 查询机房model集合
			YJWYRoomModel[] roomModels = roomService.query(Query.from(YJWYRoomModel.META_ID).where(new Condition("site_id", QueryContents.TYPE_IN, siteIds)));
			return roomModels;
		}
		return new YJWYRoomModel[] {};
	}

	@Override
	public List<YJWYProjectModel> getProjectModelsByPkArea(String pk_area) {
		// 获取当前用户所属的所有项目id
		Set<String> projectIds = this.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
		// 获取项目model
		
		YJWYProjectModel[] projectModels = null;
		//2017-1-19 kim start:修复projectId为空报错的bug
		if(projectIds.size()==0){
			projectModels =  projectBusinessService.query(Query.from(YJWYProjectModel.META_ID).where(new Condition("pk_project_", QueryContents.TYPE_EQ,null).and(new Condition("pk_area_", QueryContents.TYPE_EQ, pk_area))));
		}else{
			projectModels = projectBusinessService.query(Query.from(YJWYProjectModel.META_ID).where(new Condition("pk_project_", QueryContents.TYPE_IN, projectIds).and(new Condition("pk_area_", QueryContents.TYPE_EQ, pk_area))));
		}
		return ArrayUtils.objectToList(projectModels);
	}

	@Override
	public YJWYAreaModel[] getAreaModelsByPorjectIds(Set<String> projectIds) {
		// 获取项目model集合
		YJWYProjectModel[] projectModels = projectBusinessService.query(Query.from(YJWYProjectModel.META_ID).where(new Condition("pk_project_", QueryContents.TYPE_IN, projectIds)));
		// 声明区域id集合
		Set<String> areaIds = new HashSet<>();
		// 获取区域id装入集合
		for (YJWYProjectModel projectModel : projectModels) {
			areaIds.add(projectModel.getPk_area());
		}
		// 获取区域model集合
		YJWYAreaModel[] areaModels = areaBusinessService.query(Query.from(YJWYAreaModel.META_ID).where(new Condition("pk_area_", QueryContents.TYPE_IN, areaIds)));
		return areaModels;
	}

	@Override
	public String[] getProjectIdsByPkArea(String pk_area) {
		Query query = Query.from(YJWYProjectModel.META_ID);
		query.where(new Condition("pk_area_", QueryContents.LIKE, pk_area));
		query.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		YJWYProjectModel[] models = projectService.query(query);
		int len = models.length;
		String[] projectIds = new String[len];
		for (int i = 0; i < len; i++) {
			projectIds[i] = models[i].getPk_project();
		}
		return projectIds;
	}

	@Override
	public YJWYProjectModel[] queryProjectModelsByPkArea(String pk_area) {
		Query query = Query.from(YJWYProjectModel.META_ID);
		query.where(new Condition("pk_area_", QueryContents.LIKE, pk_area));
		query.and(new Condition("pk_crop_", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		return projectBusinessService.query(query);
	}

	@Override
	public YJWYRoleModel[] getRoleModelsByPkUser(String pk_user) {
		YJWYRoleModel[] roleModels = null;
		// 获取所属角色id
		YJWYRoleUserModel[] roleUserModels = roleUserBusinessService.query(Query.from(YJWYRoleUserModel.META_ID).where(new Condition("pk_user_", QueryContents.TYPE_EQ, pk_user)));
		if (DeviceUtil.arrayIsNotEmpty(roleUserModels)) {
			Set<String> roleIds = new HashSet<>();
			for (YJWYRoleUserModel yjwyRoleUserModel : roleUserModels) {
				roleIds.add(yjwyRoleUserModel.getPk_role());
			}
			Query roleQuery = Query.from(YJWYRoleModel.META_ID);
			roleQuery.where(new Condition("pk_role_", QueryContents.TYPE_IN, roleIds));
			roleQuery.and(new Condition("pk_crop_", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
			roleModels = roleBusinessService.query(roleQuery);
		}
		return roleModels;
	}

	@Override
	public YJWYUserModel[] queryUserModelsByPkCrop(String pk_crop) {
		return userBusinessService.query(Query.from(YJWYUserModel.META_ID).where(new Condition("pk_crop_", QueryContents.TYPE_EQ, pk_crop)));
	}
	
	
	@Override
	public YJWYRoomModel[] getRoomModelsByPkProjectId(String projectId) {
		String queryRoomCondition = "";
		YJWYProjectModel projectModel = projectDomainService.queryById(projectId);
		if(projectModel!=null){
			String projectName = projectModel.getProject_name();
			if(!StringUtils.isEmpty(projectModel.getPk_area())){
				queryRoomCondition = areaDomainService.queryById(projectModel.getPk_area()).getArea_name()+"|"+projectName;
			}
		}
		
		if (!StringUtils.isEmpty(queryRoomCondition)) {
			// 查询机房model集合
			YJWYRoomModel[] roomModels = roomService.query(Query.from(YJWYRoomModel.META_ID).where(new Condition("name", QueryContents.TYPE_LEFT_LIKE, queryRoomCondition)));
			return roomModels;
		}
		return new YJWYRoomModel[] {};
	}

}
