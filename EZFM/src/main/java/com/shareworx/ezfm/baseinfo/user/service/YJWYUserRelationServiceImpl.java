package com.shareworx.ezfm.baseinfo.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgConst;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
@Service(YJWYUserRelationService.ID)
public class YJWYUserRelationServiceImpl implements YJWYUserRelationService {
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	@Override
	public List<YJWYUserModel> queryUserByOrg(String org_id) {
 		Query query = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_org_").eq(org_id));
		List<YJWYStationModel> sl = service.queryListByCondition(query);
		if(ArrayUtils.isEmpty(sl)){
			return new ArrayList<>();
		}
		Set<String> pk_stat_list = new HashSet<>();
		for(YJWYStationModel m : sl){
			pk_stat_list.add(m.getPk_station());
		}
		Query query2 = Query.from(UserStationModel.META_ID).where(Condition.create("pk_station_").in(pk_stat_list.toArray(new String[0])));
		List<UserStationModel> usml = service.queryListByCondition(query2);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_user_list = new HashSet<>();
		for(UserStationModel m: usml){
			pk_user_list.add(m.getPk_user());
		}
		Query query3 = Query.from(YJWYUserModel.META_ID).where(Condition.create("pk_user_").in(pk_user_list.toArray(new String[0])));
		List<YJWYUserModel> ul = service.queryListByCondition(query3);
		return ul;
	}

	@Override
	public List<DefaultOrgModel> queryOrgByUser(String user_id) {
		Query query = Query.from(UserStationModel.META_ID).where(Condition.create("pk_user_").eq(user_id));
		List<UserStationModel> usml = service.queryListByCondition(query);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_stat_list = new HashSet<>();
		for(UserStationModel m:usml){
			pk_stat_list.add(m.getPk_station());
		}
		
		Query query2 = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_station_").in(pk_stat_list.toArray(new String[0])));
		List<YJWYStationModel> sl = service.queryListByCondition(query2);
		if(ArrayUtils.isEmpty(sl)){
			return new ArrayList<>();
		}
		Set<String> pk_org_list = new HashSet<>();
		for(YJWYStationModel m : sl){
			pk_stat_list.add(m.getPk_station());
		}
		Query query3 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("pk_org_").in(pk_org_list.toArray(new String[0])));
		List<DefaultOrgModel> ol = service.queryListByCondition(query3);
		return ol;
	}

	@Override
	public List<YJWYStationModel> queryStationByUser(String user_id) {
		Query query = Query.from(UserStationModel.META_ID).where(Condition.create("pk_user_").eq(user_id));
		List<UserStationModel> usml = service.queryListByCondition(query);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_stat_list = new HashSet<>();
		for(UserStationModel m:usml){
			pk_stat_list.add(m.getPk_station());
		}
		
		Query query2 = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_station_").in(pk_stat_list.toArray(new String[0])));
		List<YJWYStationModel> sl = service.queryListByCondition(query2);
		return sl;
	}

	@Override
	public List<YJWYUserModel> queryUserByStation(String station_id) {
		Query query = Query.from(UserStationModel.META_ID).where(Condition.create("pk_station_").eq(station_id));
		List<UserStationModel> usml = service.queryListByCondition(query);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_user_list = new HashSet<>();
		for(UserStationModel m:usml){
			pk_user_list.add(m.getPk_user());
		}
		Query query2 = Query.from(YJWYUserModel.META_ID).where(Condition.create("pk_user_").in(pk_user_list.toArray(new String[0])));
		List<YJWYUserModel> ul = service.queryListByCondition(query2);
		return ul;
	}

	@Override
	public List<YJWYUserModel> queryUserByProject(String pk_project) {
		Query query1 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("org_project_").eq(pk_project));
		DefaultOrgModel org = service.queryOneByCondition(query1);
		Query query2 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("pk_parent_").eq(org.getPk_org()));
		List<DefaultOrgModel> ol = service.queryListByCondition(query2);
		List<String> pk_ol = new ArrayList<>();
		for(DefaultOrgModel o : ol){
			pk_ol.add(o.getPk_org());
		}
		pk_ol.add(org.getPk_org());
		
		Query query3 = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_org_").in(pk_ol.toArray(new String[0])));
		List<YJWYStationModel> sl = service.queryListByCondition(query3);
		if(ArrayUtils.isEmpty(sl)){
			return new ArrayList<>();
		}
		
		Set<String> pk_stat_list = new HashSet<>();
		for(YJWYStationModel m : sl){
			pk_stat_list.add(m.getPk_station());
		}
		
		Query query4 = Query.from(UserStationModel.META_ID).where(Condition.create("pk_station_").in(pk_stat_list.toArray(new String[0])));
		List<UserStationModel> usml = service.queryListByCondition(query4);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_user_list = new HashSet<>();
		for(UserStationModel m: usml){
			pk_user_list.add(m.getPk_user());
		}
		Query query5 = Query.from(YJWYUserModel.META_ID).where(Condition.create("pk_user_").in(pk_user_list.toArray(new String[0])));
		List<YJWYUserModel> ul = service.queryListByCondition(query5);
		return ul;
	}

	@Override
	public List<YJWYProjectModel> queryProjectByUser(String pk_user) {
		Query query = Query.from(UserStationModel.META_ID).where(Condition.create("pk_user_").eq(pk_user));
		List<UserStationModel> usml = service.queryListByCondition(query);
		if(ArrayUtils.isEmpty(usml)){
			return new ArrayList<>();
		}
		Set<String> pk_stat_list = new HashSet<>();
		for(UserStationModel m:usml){
			pk_stat_list.add(m.getPk_station());
		}
		
		Query query2 = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_station_").in(pk_stat_list.toArray(new String[0])));
		List<YJWYStationModel> sl = service.queryListByCondition(query2);
		if(ArrayUtils.isEmpty(sl)){
			return new ArrayList<>();
		}
		Set<String> pk_org_list = new HashSet<>();
		for(YJWYStationModel m : sl){
			pk_stat_list.add(m.getPk_station());
		}
		Query query3 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("pk_org_").in(pk_org_list.toArray(new String[0])));
		List<DefaultOrgModel> ol = service.queryListByCondition(query3);
		List<String> pk_org_parent_l = new ArrayList<>();
		Set<String> pk_project_set = new HashSet<>();
		for(DefaultOrgModel o : ol){
			if(DefaultOrgConst.org_type_dept.equals(o.getOrg_type())){
				pk_org_parent_l.add(o.getPk_org());
				continue;
			}
			if(DefaultOrgConst.org_type_project.equals(o.getOrg_type())){
				pk_project_set.add(o.getOrg_project());
			}
		}
		Query query4 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("pk_org_").in(pk_org_parent_l.toArray(new String[0])));
		List<DefaultOrgModel> org_project_l = service.queryListByCondition(query4);
		for(DefaultOrgModel o : org_project_l){
			pk_project_set.add(o.getOrg_project());
		}
		
		Query query5 = Query.from(YJWYProjectModel.META_ID).where(Condition.create("pk_project_").in(pk_org_parent_l.toArray(new String[0])));
		List<YJWYProjectModel> pl = service.queryListByCondition(query5);
		return pl;
	}

}
