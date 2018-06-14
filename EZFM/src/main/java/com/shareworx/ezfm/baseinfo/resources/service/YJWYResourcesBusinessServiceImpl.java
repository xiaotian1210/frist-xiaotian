package com.shareworx.ezfm.baseinfo.resources.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.ezfm.baseinfo.type.service.ResourceTypeDomainService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.platform.persist.QueryContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourcesDao;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resourceslog.dao.YJWYResourcesLogDao;
import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 资源管理业务操作实现
 * 
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourcesBusinessService.ID)
public class YJWYResourcesBusinessServiceImpl implements YJWYResourcesBusinessService {

	@Autowired
	@Qualifier(YJWYResourcesDomainService.ID)
	private YJWYResourcesDomainService domainService;

	public void setDomainService(YJWYResourcesDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaService;

	public void setAreaService(YJWYAreaDomainService areaService) {
		this.areaService = areaService;
	}

	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
    @Qualifier(YJWYResourcesDao.ID)
	YJWYResourcesDao resourcesDao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;

	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier(YJWYResourcesLogBusinessService.ID)
	private YJWYResourcesLogBusinessService resourceLogService;

	@Autowired
	@Qualifier(ResourceTypeDomainService.ID)
	ResourceTypeDomainService resourceTypeDomainService;
	public void setResourcelogservice(YJWYResourcesLogBusinessService resourceLogService) {
		this.resourceLogService = resourceLogService;
	}

	@Autowired
	@Qualifier(YJWYResourcesLogDao.ID)
	private YJWYResourcesLogDao resourceLogDao;

	public void setResourceLogDao(YJWYResourcesLogDao resourceLogDao) {
		this.resourceLogDao = resourceLogDao;
	}
	
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resources.service.
	 * YJWYResourcesBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourcesModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYResourcesModel> list = domainService.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYResourcesModel[0];
		}
		return list.toArray(new YJWYResourcesModel[0]);
	}

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#pk_crop", key="#_methodSignature+','+#id+','+#type+','+#getsomeArea", timeout=1, timeUnit=TimeUnit.DAYS)
	public YJWYResourcesModel[] queryTree(String pk_crop, String id, int type,String getsomeArea,String keywords) throws ShareworxServiceException {
		// 返回树集合
		List<YJWYResourcesModel> list = new ArrayList<YJWYResourcesModel>();
		// 获取公司
		if (type == 1) {
			// 获取区域Query
			Query areaQuery = Query.from(YJWYAreaModel.META_ID);
			// 填充查询公司字段查询条件
			areaQuery.and(Condition.create("pk_crop_", pk_crop));
			areaQuery.and(Condition.create("delete_flag_", 0));
			if(!StringUtils.isEmpty(getsomeArea) && getsomeArea.equals("yes")){
				Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
				if (projectIds.size() != 0) {
					List<YJWYAreaModel> areaList = new ArrayList<>();
					areaList.addAll(Arrays.asList(deviceService.getAreaModelsByPorjectIds(projectIds)));
					String [] areaIds = new String[areaList.size()];
					int i = 0;
					for (YJWYAreaModel yjwyAreaModel : areaList) {
						areaIds[i++] = yjwyAreaModel.getPk_area();
					}
					if(areaIds!=null)
					areaQuery.and(Condition.in("pk_area_", areaIds));
				}
			}
			// 获取区域集合
			List<YJWYAreaModel> areaList = areaService.queryListByCondition(areaQuery);
			YJWYResourcesModel model = null;
			// 循环拼接树（区域）
			for (YJWYAreaModel areaModel : areaList) {
				model = new YJWYResourcesModel();
				model.put("id", areaModel.getPk_area());
				model.put("name", areaModel.getArea_name());
				model.put("isParent", true);
				model.put("isSilent", false);
				model.put("nocheck", false);
				model.put("type", 2);
				list.add(model);
			}
		} else if (type == 2) {
			// 获取项目Query
			Query projectQuery = Query.from(YJWYProjectModel.META_ID);
			// 填充查询公司字段查询条件
			projectQuery.and(Condition.create("pk_crop_", pk_crop));
			projectQuery.and(Condition.create("pk_area_", id));
			projectQuery.and(Condition.create("delete_flag_", 0));

			//默认查询当前人所属项目所有数据
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
			projectQuery.and(Condition.in("pk_project_",projectIds.toArray()));
			// 获取项目集合
			List<YJWYProjectModel> projectList = projectService.queryListByCondition(projectQuery);
			YJWYResourcesModel model = null;
			// 循环拼接树（区域）
			for (YJWYProjectModel projectModel : projectList) {
				model = new YJWYResourcesModel();
				model.put("id", projectModel.getPk_project());
				model.put("name", projectModel.getProject_name());
				model.put("pId", projectModel.getPk_area());
				model.put("project_id", projectModel.getPk_project());
				model.put("isParent", true);
				model.put("type", 3);
				model.put("bim_url",projectModel.getBim_url());
				list.add(model);
			}
		} else if (type == 3) {
			String sql = "select tab1.pk_resources,tab1.resources_name,tab1.resources_code,tab1.project_id,"
					+ "tab1.apartment_layout,tab1.resources_type,tab1.parent_id,tab1.create_time,"
					+ "tab2.user_name_,tab3.dict_name_, tab1.eq_code_,"
					+"tab1.resourcetype_name,tab1.resourcetype_code,tab1.pk_crop"
					+ " from (yjwy_pub_resources tab1 left join yjwy_pub_user tab2 on tab1.create_user=tab2.pk_user_) "
					+ " left join yjwy_pub_dict tab3 on tab1.resources_type= tab3.dict_code_"
					+ " where tab1.parent_id =0 and tab1.project_id='" + id  + "'"+ " " ;

					if(keywords != null && !"".equals(keywords)){
						sql += "and (tab1.resources_name like  '%"+keywords+"%' or tab1.resources_code like '%"+keywords+"%')";

					}
					sql+= " order by tab1.resources_name asc ";
			JdbcTemplate read = dao.getReadTemplate();
			list = read.query(sql, new RowMapper<YJWYResourcesModel>() {
				@Override
				public YJWYResourcesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYResourcesModel resourcesModel = new YJWYResourcesModel();
					resourcesModel.put("id", rs.getString("pk_resources"));
					resourcesModel.put("pk_resources", rs.getString("pk_resources"));
					// 判断资源名称
					if (!StringUtils.isEmpty(rs.getString("resources_name"))) {
						resourcesModel.put("name", rs.getString("resources_name"));
						resourcesModel.put("resources_name", rs.getString("resources_name"));
					} else {
						resourcesModel.put("name", rs.getString("dict_name_"));
						resourcesModel.put("resources_name", rs.getString("dict_name_"));
					}
					resourcesModel.put("pId", rs.getString("parent_id"));
					resourcesModel.put("pk_crop", rs.getString("pk_crop"));
					resourcesModel.put("project_id", rs.getString("project_id"));
					resourcesModel.put("resources_code", rs.getString("resources_code"));
					resourcesModel.put("resources_type", rs.getString("resources_type"));
					resourcesModel.put("resourcetype_name", rs.getString("resourcetype_name"));
					resourcesModel.put("resourcetype_code", rs.getString("resourcetype_code"));
					resourcesModel.put("resources_type_name", rs.getString("dict_name_"));

					if (!StringUtils.isEmpty(rs.getString("apartment_layout"))) {
						resourcesModel.put("apartment_layout", rs.getString("apartment_layout"));
					} else {
						resourcesModel.put("apartment_layout", "");
					}
					resourcesModel.put("create_user", rs.getString("user_name_"));
					resourcesModel.put("eq_code", rs.getString("eq_code_"));
					resourcesModel.put("create_time", rs.getString("create_time"));
					if(!StringUtils.isEmpty(rs.getString("parent_id"))&&!rs.getString("parent_id").equals("0")){
						//1 代表有上级的资源 0代表没有上级的资源
						resourcesModel.put("hasParent","1");
					}else{
						resourcesModel.put("hasParent","0");
					}
					// 获取资源Query
					Query sonResourcesQuery = Query.from(YJWYResourcesModel.META_ID);
					sonResourcesQuery.and(Condition.create("parent_id", rs.getString("pk_resources")));
					YJWYResourcesDao domainDao = SpringUtils.getBean(YJWYResourcesDao.ID);
					long count = domainDao.countListByCondition(sonResourcesQuery);
					if (count > 0) {
						resourcesModel.put("isParent", true);
					}
					resourcesModel.put("type", 4);
					return resourcesModel;
				}
			});
		} else {
			String sql = "select tab1.pk_resources,tab1.resources_name,tab1.resources_code,tab1.project_id,"
					+ "tab1.apartment_layout,tab1.resources_type,tab1.parent_id,tab1.create_time,"
					+ "tab2.user_name_,tab3.dict_name_,tab1.project_id,"
					+"tab1.resourcetype_name,tab1.resourcetype_code,tab1.pk_crop"
					+ " from (yjwy_pub_resources tab1 left join yjwy_pub_user tab2 on tab1.create_user=tab2.pk_user_) "
					+ " left join yjwy_pub_dict tab3 on tab1.resources_type= tab3.dict_code_"
					+ " where tab1.parent_id ='" + id  + "'";
			if(keywords != null && !"".equals(keywords)){
				sql += "and (tab1.resources_name like  '%"+keywords+"%' or tab1.resources_code like '%"+keywords+"%')";

			}
			sql+= " order by tab1.resources_name asc ";
			JdbcTemplate read = dao.getReadTemplate();
			list = read.query(sql, new RowMapper<YJWYResourcesModel>() {
				@Override
				public YJWYResourcesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYResourcesModel resourcesModel = new YJWYResourcesModel();
					resourcesModel.put("id", rs.getString("pk_resources"));
					resourcesModel.put("pk_resources", rs.getString("pk_resources"));
					// 判断资源名称
					if (!StringUtils.isEmpty(rs.getString("resources_name"))) {
						resourcesModel.put("name", rs.getString("resources_name"));
						resourcesModel.put("resources_name", rs.getString("resources_name"));
					} else {
						resourcesModel.put("name", rs.getString("dict_name_"));
						resourcesModel.put("resources_name", rs.getString("dict_name_"));
					}
					resourcesModel.put("pId", rs.getString("parent_id"));
					resourcesModel.put("project_id", rs.getString("project_id"));
					resourcesModel.put("resources_code", rs.getString("resources_code"));
					resourcesModel.put("resources_type", rs.getString("resources_type"));
					resourcesModel.put("pk_crop", rs.getString("pk_crop"));
					resourcesModel.put("resourcetype_name", rs.getString("resourcetype_name"));
					resourcesModel.put("resourcetype_code", rs.getString("resourcetype_code"));
					resourcesModel.put("resources_type_name", rs.getString("dict_name_"));
					if (!StringUtils.isEmpty(rs.getString("apartment_layout"))) {
						resourcesModel.put("apartment_layout", rs.getString("apartment_layout"));
					} else {
						resourcesModel.put("apartment_layout", "");
					}
					resourcesModel.put("create_user", rs.getString("user_name_"));
					resourcesModel.put("create_time", rs.getString("create_time"));
					if(!StringUtils.isEmpty(rs.getString("parent_id"))&&!rs.getString("parent_id").equals("0")){
						//1 代表有上级的资源 0代表没有上级的资源
						resourcesModel.put("hasParent","1");
					}else{
						resourcesModel.put("hasParent","0");
					}
					
					// 获取资源Query
					Query sonResourcesQuery = Query.from(YJWYResourcesModel.META_ID);
					sonResourcesQuery.and(Condition.create("parent_id", rs.getString("pk_resources")));
					YJWYResourcesDao domainDao = SpringUtils.getBean(YJWYResourcesDao.ID);
					long count = domainDao.countListByCondition(sonResourcesQuery);
					if (count > 0) {
						resourcesModel.put("isParent", true);
					}
					resourcesModel.put("type", 4);
					return resourcesModel;
				}
			});
		}
		// 循环拼接树（项目+资源）
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYResourcesModel[0];
		}
		return list.toArray(new YJWYResourcesModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resources.service.
	 * YJWYResourcesBusinessService#load(com.shareworx.ezfm.baseinfo.resources.
	 * model.YJWYResourcesModel)
	 */
	@Override
	public YJWYResourcesModel[] load(YJWYResourcesModel model) throws ShareworxServiceException {
		List<YJWYResourcesModel> list = domainService.queryByExample(model);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYResourcesModel[0];
		}
		return list.toArray(new YJWYResourcesModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resources.service.
	 * YJWYResourcesBusinessService#save(com.shareworx.ezfm.baseinfo.resources.
	 * model.YJWYResourcesModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
	public YJWYResourcesModel[] save(YJWYResourcesModel[] models) throws ShareworxServiceException {
		// 获取公司
		YJWYUserModel userModel = userService.queryById(UserUtil.getCurrentUserPk());
		for (YJWYResourcesModel model : models) {
			model.setCreate_time(this.obtainTime());
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setUpdate_user(UserUtil.getCurrentUserPk());
			model.setPk_crop(userModel.getPk_crop());
		}
		List<YJWYResourcesModel> list = domainService.save(models);
		// 2017-1-16 kim start
		// 保存“新增”空间资源操作日志数据到yjwy_resources_log表里面
		for (YJWYResourcesModel yjwyResourcesModel : list) {
			YJWYResourcesLogModel resourceLogModel = new YJWYResourcesLogModel();
			resourceLogModel.setPk_resources_log(yjwyResourcesModel.getPk_resources());
			// 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
			resourceLogModel.setOperate_type(1);
			resourceLogModel.setOperate_date(this.obtainTime().substring(0, 10));
			//判断log表是否存在此id记录，假如存在则更新，否则新增
			if(resourceLogDao.queryById(resourceLogModel.getPk_resources_log())!=null){
				resourceLogService.update(new YJWYResourcesLogModel[]{resourceLogModel});
			}else{
				resourceLogService.save(new YJWYResourcesLogModel[]{resourceLogModel});
			}
		}
		// 2017-1-16 kim end
		return list.toArray(new YJWYResourcesModel[0]);
	}

	@Override
	@DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
	public YJWYResourcesModel[] saveByDv(YJWYResourcesModel[] models, String pk_user,String pk_crop) throws ShareworxServiceException {
		// 获取公司
		YJWYUserModel userModel = userService.queryById(pk_user);
		for (YJWYResourcesModel model : models) {
			model.setCreate_time(this.obtainTime());
			model.setCreate_user(pk_user);
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setUpdate_user(pk_user);
			model.setPk_crop(pk_crop);
            ResourcetypeModel rm = new ResourcetypeModel();
            rm.setType_id(model.getResourcetype_code());
            List<ResourcetypeModel> resourcetypeModels = resourceTypeDomainService.queryByExample(rm);
            ResourcetypeModel resourcetypeModel = resourceTypeDomainService.queryById(model.getResourcetype_code());
            if(resourcetypeModel == null){
               throw new ShareworxServiceException("资源不存在");
            }
            model.setResourcetype_name(resourcetypeModel.getType_name());
        }

		List<YJWYResourcesModel> list = domainService.save(models);
		// 2017-1-16 kim start
		// 保存“新增”空间资源操作日志数据到yjwy_resources_log表里面
		for (YJWYResourcesModel yjwyResourcesModel : list) {
			YJWYResourcesLogModel resourceLogModel = new YJWYResourcesLogModel();
			resourceLogModel.setPk_resources_log(yjwyResourcesModel.getPk_resources());
			// 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
			resourceLogModel.setOperate_type(1);
			resourceLogModel.setOperate_date(this.obtainTime().substring(0, 10));
			//判断log表是否存在此id记录，假如存在则更新，否则新增
			if(resourceLogDao.queryById(resourceLogModel.getPk_resources_log())!=null){
				resourceLogService.update(new YJWYResourcesLogModel[]{resourceLogModel});
			}else{
				resourceLogService.save(new YJWYResourcesLogModel[]{resourceLogModel});
			}
		}
		// 2017-1-16 kim end
		return list.toArray(new YJWYResourcesModel[0]);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resources.service.
	 * YJWYResourcesBusinessService#update(com.shareworx.ezfm.baseinfo.resources
	 * .model.YJWYResourcesModel[])
	 */
	//#models.!['__RESOURCE_CACHA_NAMESPACE__'+pk_resources]
	@Override
	@DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
	public YJWYResourcesModel[] update(YJWYResourcesModel[] models) throws ShareworxServiceException {
		YJWYResourcesModel updateModel;
		YJWYResourcesModel[] updateModels = new YJWYResourcesModel[models.length];
		for (int i = 0; i < models.length; i++) {
			updateModel = domainService.queryById(models[i].get("pk_resources").toString());
			if (models[i].get("resources_name") != null
					&& !StringUtils.isEmpty(models[i].get("resources_name").toString())) {
				updateModel.setResources_name(models[i].get("resources_name").toString());
			}
			if (models[i].get("resources_code") != null
					&& !StringUtils.isEmpty(models[i].get("resources_code").toString())) {
				updateModel.setResources_code(models[i].get("resources_code").toString());
			}
			if (models[i].get("resources_type") != null
					&& !StringUtils.isEmpty(models[i].get("resources_type").toString())) {
				updateModel.setResources_type(models[i].get("resources_type").toString());
			}
			if(models[i].get("resourcetype_name") != null
					&& ! StringUtils.isEmpty(models[i].getResourcetype_name().toString())){
				updateModel.setResourcetype_name(models[i].getResourcetype_name().toString());
			}
			if(models[i].get("resourcetype_code") != null
					&& ! StringUtils.isEmpty(models[i].getResourcetype_code().toString())){
				updateModel.setResourcetype_code(models[i].getResourcetype_code().toString());
			}
			if (models[i].get("apartment_layout") != null
					&& !StringUtils.isEmpty(models[i].get("apartment_layout").toString())) {
				updateModel.setApartment_layout(models[i].get("apartment_layout").toString());
			}
			if (models[i].get("eq_code") != null
					&& !StringUtils.isEmpty(models[i].get("eq_code").toString())) {
				updateModel.setEq_code(models[i].get("eq_code").toString());
			}
			updateModel.setUpdate_time(System.currentTimeMillis() + "");
			updateModel.setUpdate_user(UserUtil.getCurrentUserPk());
			updateModel.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			updateModels[i] = updateModel;
		}
		List<YJWYResourcesModel> list = domainService.update(updateModels);
		// 2017-1-16 kim start
		// 保存“更新”空间资源操作日志数据到yjwy_resources_log表里面
		for (YJWYResourcesModel yjwyResourcesModel : list) {
			YJWYResourcesLogModel resourceLogModel = new YJWYResourcesLogModel();
			resourceLogModel.setPk_resources_log(yjwyResourcesModel.getPk_resources());
			resourceLogModel.setOperate_date(this.obtainTime().substring(0, 10));
		    YJWYResourcesLogModel resourceLogModel2 = resourceLogDao.queryById(resourceLogModel.getPk_resources_log());
		    if(resourceLogModel2!=null){
		    	// 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
		    	if(resourceLogModel2.getOperate_type()==1||resourceLogModel2.getOperate_type()==4){
		    		resourceLogModel.setOperate_type(4);
		    	}else{
		    		resourceLogModel.setOperate_type(3);
		    	}
				resourceLogService.update(new YJWYResourcesLogModel[]{resourceLogModel});
			}else{
				resourceLogModel.setOperate_type(3);
				resourceLogService.save(new YJWYResourcesLogModel[]{resourceLogModel});
			}
		}
		// 2017-1-16 kim end
		return list.toArray(new YJWYResourcesModel[0]);
	}


    /*
 * (non-Javadoc)
 *
 * @see com.shareworx.ezfm.baseinfo.resources.service.
 * YJWYResourcesBusinessService#update(com.shareworx.ezfm.baseinfo.resources
 * .model.YJWYResourcesModel[])
 */
    //#models.!['__RESOURCE_CACHA_NAMESPACE__'+pk_resources]
    @Override
    @DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
    public YJWYResourcesModel[] updateByDv(YJWYResourcesModel[] models) throws ShareworxServiceException {
        YJWYResourcesModel updateModel;
        YJWYResourcesModel[] updateModels = new YJWYResourcesModel[models.length];
        for (int i = 0; i < models.length; i++) {
            models[i].setUpdate_time(System.currentTimeMillis() + "");
        }
        List<YJWYResourcesModel> list = domainService.update(models);
        // 2017-1-16 kim start
        // 保存“更新”空间资源操作日志数据到yjwy_resources_log表里面
        for (YJWYResourcesModel yjwyResourcesModel : list) {
            YJWYResourcesLogModel resourceLogModel = new YJWYResourcesLogModel();
            resourceLogModel.setPk_resources_log(yjwyResourcesModel.getPk_resources());
            resourceLogModel.setOperate_date(this.obtainTime().substring(0, 10));
            YJWYResourcesLogModel resourceLogModel2 = resourceLogDao.queryById(resourceLogModel.getPk_resources_log());
            if(resourceLogModel2!=null){
                // 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
                if(resourceLogModel2.getOperate_type()==1||resourceLogModel2.getOperate_type()==4){
                    resourceLogModel.setOperate_type(4);
                }else{
                    resourceLogModel.setOperate_type(3);
                }
                resourceLogService.update(new YJWYResourcesLogModel[]{resourceLogModel});
            }else{
                resourceLogModel.setOperate_type(3);
                resourceLogService.save(new YJWYResourcesLogModel[]{resourceLogModel});
            }
        }
        // 2017-1-16 kim end
        return list.toArray(new YJWYResourcesModel[0]);
    }

	@Override
	public String findAllParent(String pk_resources) {
    	if(pk_resources == null ||pk_resources.isEmpty()){
    		return "";
		}
        YJWYResourcesDomainService bean = SpringUtils.getBean(YJWYResourcesDomainService.ID);
        YJWYResourcesModel model = bean.queryById(pk_resources);
        String name = model.getResources_name();
        while(!"0".equals(model.getParent_id())){
            model = bean.queryById(model.getParent_id());
            name =model.getResources_name()+ " | "+name;
        }
        return name;
	}

	@Override
	public List<Map<String, Object>> findAttrById(String pk_resources) {
        String sql = "select *   " +
                "from yjwy_attr_resource   " +
                "LEFT JOIN yjwy_attribute_name on yjwy_attribute_name.attribute_code = yjwy_attr_resource.attr_name  " +
                "where yjwy_attr_resource.pk_resources = '"+pk_resources+"'  ";
        return dao.getReadTemplate().queryForList(sql);

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resources.service.
	 * YJWYResourcesBusinessService#delete(com.shareworx.ezfm.baseinfo.resources
	 * .model.YJWYResourcesModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
	public YJWYResourcesModel[] delete(YJWYResourcesModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		// 2017-1-16 kim start
		// 保存“删除”空间资源操作日志数据到yjwy_resources_log表里面
		for (YJWYResourcesModel yjwyResourcesModel : models) {
			YJWYResourcesLogModel resourceLogModel = new YJWYResourcesLogModel();
			resourceLogModel.setPk_resources_log(yjwyResourcesModel.getPk_resources());
			resourceLogModel.setOperate_date(this.obtainTime().substring(0, 10));
			YJWYResourcesLogModel resourceLogModel2 = resourceLogDao.queryById(resourceLogModel.getPk_resources_log());
			if(resourceLogModel2!=null){
				// 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
				if(resourceLogModel2.getOperate_type()==1||resourceLogModel2.getOperate_type()==4){
		    		resourceLogModel.setOperate_type(5);
		    	}else{
		    		resourceLogModel.setOperate_type(2);
		    	}
				resourceLogService.update(new YJWYResourcesLogModel[]{resourceLogModel});
			}else{
				resourceLogModel.setOperate_type(2);
				resourceLogService.save(new YJWYResourcesLogModel[]{resourceLogModel});
			}
		}
		// 2017-1-16 kim end
		return models;
	}

	// 公共方法，获取当前时间
	public String obtainTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", key="#_methodSignature+','+#pk", timeout=1, timeUnit=TimeUnit.DAYS)
	public YJWYResourcesModel findByPk(String pk) throws ShareworxServiceException {
		return domainService.queryById(pk);
	}

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", key="'YJWYResourcesBusinessServiceImpl.findResoucesIncludeAllChilds,'+#resourceid", timeout=1, timeUnit=TimeUnit.DAYS)
	public List<YJWYResourcesModel> findResoucesIncludeAllChilds(String resourceid) throws ShareworxServiceException {
		YJWYResourcesModel resource = domainService.queryById(resourceid);
		if(resource==null) {
			return null;
		}
		YJWYResourcesBusinessService service = SpringUtils.getBean(YJWYResourcesBusinessService.ID);
		List<YJWYResourcesModel> list = service.findAllResourceByProject(resource.getProject_id());
		//转换为树形结构
        Map<String, YJWYResourcesModel> map = new HashMap<String, YJWYResourcesModel>();
        for(int i=0;i<list.size();i++) {
            YJWYResourcesModel model = list.get(i);
            map.put(model.getPk_resources(), model);
        }
        for(int i=0;i<list.size();i++) {
            YJWYResourcesModel model = list.get(i);
            if(model.getParent_id()!=null&&!"".equals(model.getParent_id())) {
                YJWYResourcesModel parent = map.get(model.getParent_id());
                if(parent==null) {
                	continue;
                }
                List<YJWYResourcesModel> childs = parent.get("childs")==null?null:(List<YJWYResourcesModel>)parent.get("childs");
                if(childs==null) {
                    childs = new ArrayList<YJWYResourcesModel>();
                }
                childs.add(model);
                parent.put("childs", childs);
            }
        }
        YJWYResourcesModel model = map.get(resourceid);
        //获取子孙节点
        List<YJWYResourcesModel> modelList = new ArrayList<YJWYResourcesModel>();
        findAllChilds(model, map, modelList);
		return modelList;
	}

	private void findAllChilds(YJWYResourcesModel model, Map<String, YJWYResourcesModel> map, List<YJWYResourcesModel> list) {
        list.add(model);
        List<YJWYResourcesModel> childs = model.get("childs")==null?null:(List<YJWYResourcesModel>)model.get("childs");
        if(childs!=null&&childs.size()>0) {
            for(int i=0;i<childs.size();i++) {
                findAllChilds(childs.get(i), map, list);
            }
        }
    }

    @DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", key="'YJWYResourcesBusinessServiceImpl.findAllResourceByProject,'+#project", timeout=1, timeUnit=TimeUnit.DAYS)
	public List<YJWYResourcesModel> findAllResourceByProject(String project) throws ShareworxServiceException {
        YJWYResourcesModel example = new YJWYResourcesModel();
        example.setProject_id(project);
        List<YJWYResourcesModel> yjwyResourcesModels = domainService.queryByExample(example);
	    return yjwyResourcesModels;
    }
    
    @DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#pk_crop", key="'YJWYResourcesBusinessServiceImpl.findAllResourceByProject,'+#project", timeout=1, timeUnit=TimeUnit.DAYS)
	public List<YJWYResourcesModel> findAllResourceByProject(String pk_crop, String project) throws ShareworxServiceException {
        YJWYResourcesModel example = new YJWYResourcesModel();
        example.setProject_id(project);
        List<YJWYResourcesModel> yjwyResourcesModels = domainService.queryByExample(example);
	    return yjwyResourcesModels;
    }

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#pk_crop", key="'YJWYResourcesBusinessServiceImpl.findResoucesIncludeAllChilds,'+#resourceid", timeout=1, timeUnit=TimeUnit.DAYS)
	public List<YJWYResourcesModel> findResoucesIncludeAllChilds(String pk_crop, String resourceid)
			throws ShareworxServiceException {
		YJWYResourcesModel resource = domainService.queryById(resourceid);
		if(resource==null) {
			return null;
		}
		YJWYResourcesBusinessService service = SpringUtils.getBean(YJWYResourcesBusinessService.ID);
		List<YJWYResourcesModel> list = service.findAllResourceByProject(pk_crop, resource.getProject_id());
		//转换为树形结构
        Map<String, YJWYResourcesModel> map = new HashMap<String, YJWYResourcesModel>();
        for(int i=0;i<list.size();i++) {
            YJWYResourcesModel model = list.get(i);
            map.put(model.getPk_resources(), model);
        }
        for(int i=0;i<list.size();i++) {
            YJWYResourcesModel model = list.get(i);
            if(model.getParent_id()!=null&&!"".equals(model.getParent_id())) {
                YJWYResourcesModel parent = map.get(model.getParent_id());
                if(parent==null) {
                	continue;
                }
                List<YJWYResourcesModel> childs = parent.get("childs")==null?null:(List<YJWYResourcesModel>)parent.get("childs");
                if(childs==null) {
                    childs = new ArrayList<YJWYResourcesModel>();
                }
                childs.add(model);
                parent.put("childs", childs);
            }
        }
        YJWYResourcesModel model = map.get(resourceid);
        //获取子孙节点
        List<YJWYResourcesModel> modelList = new ArrayList<YJWYResourcesModel>();
        findAllChilds(model, map, modelList);
		return modelList;
	}
	
	
}
