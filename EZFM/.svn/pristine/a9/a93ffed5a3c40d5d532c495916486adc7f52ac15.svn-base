package com.shareworx.ezfm.baseinfo.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 系统用户业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYUserBusinessService.ID)
public class YJWYUserBusinessServiceImpl implements YJWYUserBusinessService {
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaDomainService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectDomainService;
	
	
	public void setDomainService(YJWYUserDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYUserModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserModel[0];
		}
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserBusinessService#load(com.shareworx.ezfm.baseinfo.user.model.UserModel)
	 */
	@Override
	public YJWYUserModel[] load(YJWYUserModel model) throws ShareworxServiceException {
		List<YJWYUserModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserModel[0];
		}
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserBusinessService#save(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.USER_CACHA_NAMESPACE+"'",key = "#models.![pk_user]")
	public YJWYUserModel[] save(YJWYUserModel[] models) throws ShareworxServiceException {
		List<YJWYUserModel> list = domainService.save(models);
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserBusinessService#update(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.USER_CACHA_NAMESPACE+"'",key = "#models.![pk_user]")
	public YJWYUserModel[] update(YJWYUserModel[] models) throws ShareworxServiceException {
		List<YJWYUserModel> list = domainService.update(models);
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserBusinessService#delete(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.USER_CACHA_NAMESPACE+"'",key = "#models.![pk_user]")
	public YJWYUserModel[] delete(YJWYUserModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.admin.user.service.DefaultUserBusinessService#execEnable(com.shareworx.admin.user.model.UserModel[])
	 */
	@Override
	public YJWYUserModel[] execEnable(YJWYUserModel[] models) throws ShareworxServiceException {
		for(YJWYUserModel model: models){
			model.setIs_able(Boolean.TRUE);
		}
		domainService.update(Arrays.asList("is_able_"), models);
		return models;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.admin.user.service.DefaultUserBusinessService#execDisable(com.shareworx.admin.user.model.UserModel[])
	 */
	@Override
	public YJWYUserModel[] execDisable(YJWYUserModel[] models) throws ShareworxServiceException {
		for(YJWYUserModel model: models){
			model.setIs_able(Boolean.FALSE);
		}
		domainService.update(Arrays.asList("is_able_"), models);
		return models;
	}

	@Override
	public YJWYAreaModel[] getUserArea(String pk_user) {
		String sql = "select DISTINCT(t3.org_area_) from YJWY_PUB_USER_STATION t1,YJWY_PUB_STATION t2,YJWY_PUB_ORG t3 where t1.pk_user_='"+pk_user+"' and t1.pk_station_=t2.pk_station_ and t2.pk_org_=t3.pk_org_ and t3.org_area_ is not null";
		JdbcTemplate jdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
		String ids = "";
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<mapList.size();i++) {
			Map<String, Object> map = mapList.get(i);
			ids += ("'"+map.get("org_area_")+"'");
			if(i!=mapList.size()-1) {
				ids+=",";
			}
			idList.add(map.get("org_area_")+"");
		}
		Query query = Query.from(YJWYAreaModel.META_ID).and(Condition.in(YJWYAreaModel.PK_AREA+"_", idList.toArray(new String[]{})));
		List<YJWYAreaModel> list = areaDomainService.queryListByCondition(query);
		
		if(list!=null) {
			return list.toArray(new YJWYAreaModel[]{});
		}
		return null;
	}

	@Override
	public YJWYProjectModel[] getUserProject(String pk_user) {
		String sql = "select DISTINCT(t3.org_project_) from YJWY_PUB_USER_STATION t1,YJWY_PUB_STATION t2,YJWY_PUB_ORG t3 where t1.pk_user_='"+pk_user+"' and t1.pk_station_=t2.pk_station_ and t2.pk_org_=t3.pk_org_ and t3.org_project_ is not null";
		JdbcTemplate jdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
		String ids = "";
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<mapList.size();i++) {
			Map<String, Object> map = mapList.get(i);
			ids += ("'"+map.get("org_project_")+"'");
			if(i!=mapList.size()-1) {
				ids+=",";
			}
			idList.add(map.get("org_project_")+"");
		}
		Query query = Query.from(YJWYProjectModel.META_ID).and(Condition.in(YJWYProjectModel.PK_PROJECT+"_", idList.toArray(new String[]{})));
		List<YJWYProjectModel> list = projectDomainService.queryListByCondition(query);
		
		if(list!=null) {
			return list.toArray(new YJWYProjectModel[]{});
		}
		return null;
	}

	@Override
	public YJWYProjectModel[] getUserProject(String pk_user, String areaid) {
		String sql = "select DISTINCT(t3.org_project_) from YJWY_PUB_USER_STATION t1,YJWY_PUB_STATION t2,YJWY_PUB_ORG t3 where t1.pk_user_='"+pk_user+"' and t1.pk_station_=t2.pk_station_ and t2.pk_org_=t3.pk_org_ and t3.org_project_ is not null and t3.org_area_='"+areaid+"'";
		JdbcTemplate jdbcTemplate = dao.getReadTemplate();
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
		String ids = "";
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<mapList.size();i++) {
			Map<String, Object> map = mapList.get(i);
			ids += ("'"+map.get("org_project_")+"'");
			if(i!=mapList.size()-1) {
				ids+=",";
			}
			idList.add(map.get("org_project_")+"");
		}
		Query query = Query.from(YJWYProjectModel.META_ID).and(Condition.in(YJWYProjectModel.PK_PROJECT+"_", idList.toArray(new String[]{})));
		List<YJWYProjectModel> list = projectDomainService.queryListByCondition(query);
		
		if(list!=null) {
			return list.toArray(new YJWYProjectModel[]{});
		}
		return null;
	}


}
