package com.shareworx.ezfm.baseinfo.station.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceXlxsModel;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 岗位管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYStationBusinessService.ID)
public class YJWYStationBusinessServiceImpl implements YJWYStationBusinessService {

	@Autowired
	@Qualifier(YJWYStationDomainService.ID)
	private YJWYStationDomainService domainService;
	
	public void setDomainService(YJWYStationDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(DefaultOrgDomainService.ID)
	private DefaultOrgDomainService defaultdomainService;
	
	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService yjwydictdomainService;
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYStationModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYStationModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYStationModel[0];
		}
		return list.toArray(new YJWYStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService#load(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel)
	 */
	@Override
	public YJWYStationModel[] load(YJWYStationModel model) throws ShareworxServiceException {
		List<YJWYStationModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYStationModel[0];
		}
		return list.toArray(new YJWYStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService#save(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public YJWYStationModel[] save(YJWYStationModel[] models) throws ShareworxServiceException {
		List<YJWYStationModel> list = domainService.save(models);
		for(YJWYStationModel item : list){
			//获取组织名
			DefaultOrgModel orgModel = defaultdomainService.queryById(item.getPk_org());
			if(orgModel != null){
				String org_name = orgModel.getOrg_name();
				item.put("pk_org_name", org_name);
			}
			//获取任务级别
			Query queryp = Query.from(YJWYDictModel.META_ID);
			queryp.and(Condition.create("dict_code_",item.getTask_level()));
			YJWYDictModel dicModel = yjwydictdomainService.queryOneByCondition(queryp);
			if(dicModel != null){
				String dic_name = dicModel.getDict_name();
				item.put("task_level_name", dic_name);
			}	
			//获取岗位级别
			Query querys = Query.from(YJWYDictModel.META_ID);
			querys.and(Condition.create("dict_code_", item.getStation_level()));
			YJWYDictModel stationModel = yjwydictdomainService.queryOneByCondition(querys);
			if(stationModel != null){
				String station_name = stationModel.getDict_name();
				item.put("station_level_name",station_name);
			}
			//获取部门
			Query queryd = Query.from(YJWYDictModel.META_ID);
			queryd.and(Condition.create("dict_code_", item.getPk_dept()));
			YJWYDictModel deptModel = yjwydictdomainService.queryOneByCondition(queryd);
			if(deptModel != null){
				String dept_name = deptModel.getDict_name();
				item.put("pk_dept_name",dept_name);
			}
			
		}
		return list.toArray(new YJWYStationModel[0]);
	}

	/*yjwy_pub_station
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService#update(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public YJWYStationModel[] update(YJWYStationModel[] models) throws ShareworxServiceException {
		List<YJWYStationModel> list = domainService.update(models);
		for(YJWYStationModel item : list){
			//获取组织名
			DefaultOrgModel orgModel = defaultdomainService.queryById(item.getPk_org());
			if(orgModel != null){
				String org_name = orgModel.getOrg_name();
				item.put("pk_org_name", org_name);
			}
			//获取任务级别
			Query queryp = Query.from(YJWYDictModel.META_ID);
			queryp.and(Condition.create("dict_code_",item.getTask_level()));
			YJWYDictModel dicModel = yjwydictdomainService.queryOneByCondition(queryp);
			if(dicModel != null){
				String dic_name = dicModel.getDict_name();
				item.put("task_level_name", dic_name);
			}	
			//获取岗位级别
			Query querys = Query.from(YJWYDictModel.META_ID);
			querys.and(Condition.create("dict_code_", item.getStation_level()));
			YJWYDictModel stationModel = yjwydictdomainService.queryOneByCondition(querys);
			if(stationModel != null){
				String station_name = stationModel.getDict_name();
				item.put("station_level_name",station_name);
			}
			//获取部门
			Query queryd = Query.from(YJWYDictModel.META_ID);
			queryd.and(Condition.create("dict_code_", item.getPk_dept()));
			YJWYDictModel deptModel = yjwydictdomainService.queryOneByCondition(queryd);
			if(deptModel != null){
				String dept_name = deptModel.getDict_name();
				item.put("pk_dept_name",dept_name);
			}
			
		}		
		return list.toArray(new YJWYStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService#delete(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public YJWYStationModel[] delete(YJWYStationModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
