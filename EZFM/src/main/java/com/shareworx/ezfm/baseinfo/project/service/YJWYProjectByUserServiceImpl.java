package com.shareworx.ezfm.baseinfo.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService;

@Service(YJWYProjectByUserService.ID)
public class YJWYProjectByUserServiceImpl implements YJWYProjectByUserService {
	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService staService;
	@Autowired
	@Qualifier(UserStationBusinessService.ID)
	private UserStationBusinessService usService;
	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService uService;
	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService orgService;
	@Override
	
	public YJWYUserModel[] queryUserByPro(String project_id) {
		
		//根据项目id查询
		Query orgQuery = Query.from(DefaultOrgModel.META_ID).where(Condition.create("org_project_").eq(project_id));
		DefaultOrgModel[] orgs = orgService.query(orgQuery);
		List<String> org_pk_l = new ArrayList<>();
		if(ArrayUtils.isEmpty(org_pk_l)){
			return null;
		}
		for(DefaultOrgModel o : orgs){
			org_pk_l.add(o.getPk_org());
		}
		Query query = Query.from(YJWYStationModel.META_ID).where(Condition.create("pk_org_").in(org_pk_l.toArray(new String[0])));
		YJWYStationModel[] sma = staService.query(query);
		if(ArrayUtils.isEmpty(sma)){
			return null;
		}
		List<String> sta_pk_l = new ArrayList<>();
		for(YJWYStationModel m : sma){
			sta_pk_l.add(m.getPk_station());
		}
		Query query2 = Query.from(UserStationModel.META_ID).where(Condition.create("pk_station_").in(sta_pk_l.toArray(new String[0])));
		UserStationModel[] usl = usService.query(query2);
		if(ArrayUtils.isEmpty(usl)){
			return null;
		}
		List<String> upl = new ArrayList<>();
		for(UserStationModel m : usl){
			upl.add(m.getPk_user());
		}
		Query query3 = Query.from(YJWYUserModel.META_ID).where(Condition.create("pk_user_").in(upl.toArray(new String[0])));
		YJWYUserModel[] us = uService.query(query3);
		return us;
	}
	
	
	

}
