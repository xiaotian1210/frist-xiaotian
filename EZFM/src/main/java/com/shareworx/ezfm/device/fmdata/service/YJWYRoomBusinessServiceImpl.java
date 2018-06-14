package com.shareworx.ezfm.device.fmdata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.shareworx.platform.persist.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 房间位置信息业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoomBusinessService.ID)
public class YJWYRoomBusinessServiceImpl implements YJWYRoomBusinessService {

	@Autowired
	@Qualifier(YJWYRoomDomainService.ID)
	private YJWYRoomDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService resourcesBusinessService;
	
	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectBusinessService;
	
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaBusinessService;
	
	public void setDomainService(YJWYRoomDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoomModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYRoomModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoomModel[0];
		}
		return list.toArray(new YJWYRoomModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel)
	 */
	@Override
	public YJWYRoomModel[] load(YJWYRoomModel model) throws ShareworxServiceException {
		List<YJWYRoomModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoomModel[0];
		}
		return list.toArray(new YJWYRoomModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public YJWYRoomModel[] save(YJWYRoomModel[] models) throws ShareworxServiceException {
		List<YJWYRoomModel> list = domainService.save(models);
		return list.toArray(new YJWYRoomModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public YJWYRoomModel[] update(YJWYRoomModel[] models) throws ShareworxServiceException {
		List<YJWYRoomModel> list = domainService.update(models);
		return list.toArray(new YJWYRoomModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public YJWYRoomModel[] delete(YJWYRoomModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", key="#_methodSignature+','+#resourceid", timeout=1, timeUnit=TimeUnit.DAYS)
	public String getRoomPlaceByResourceId(String resourceid) {
		String name = "";
		YJWYResourcesModel resource = resourcesBusinessService.findByPk(resourceid);
		if(resource==null) {
			return "";
		}
		YJWYResourcesModel temp = resource;
		while(resource!=null) {
			temp = resource;
			name = resource.getResources_name()+("".equals(name)?"":("|"+name));
			String parentId = resource.getParent_id();
			if(parentId!=null&&!"".equals(parentId.trim())&&"0".equals(parentId.trim())) {
				resource = resourcesBusinessService.findByPk(parentId);
			} else {
				resource = null;
			}
		}
		YJWYProjectModel project = projectBusinessService.queryById(temp.getProject_id());
		YJWYAreaModel area = areaBusinessService.queryById(project.getPk_area());
		name = area.getArea_name()+"|"+project.getProject_name()+"|"+name;
		return name;
	}

	@Override
	@DMSCacheable(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", key="#_methodSignature+','+#roomid", timeout=1, timeUnit=TimeUnit.DAYS)
	public String getRoomPlaceByRoomId(String roomid) {
		YJWYRoomModel room = domainService.queryById(roomid);
		if(room==null) {
			return "";
		}
		String resourceId = room.getFk_resource_id();
		if(resourceId==null||"".equals(resourceId)) {
			return "";
		}
		return getRoomPlaceByResourceId(resourceId);
	}

	@Override
	public List<String> getRoomIdsByResources(List<YJWYResourcesModel> list) {
		List<String> ids = new ArrayList<>();
		Query query = new Query();
		for(YJWYResourcesModel item:list){
			query.or(Condition.create("fk_resource_id",item.getPk_resources()));
//			YJWYRoomModel model =  new YJWYRoomModel();
//			model.setFk_resource_id(item.getPk_resources());
//			List<YJWYRoomModel> yjwyRoomModels = domainService.queryByExample(model);
//			for(YJWYRoomModel roomModel :yjwyRoomModels){
//				ids.add(roomModel.getRm_id());
//			}
		}

		query.addFrom(YJWYRoomModel.META_ID);

        List<YJWYRoomModel> yjwyRoomModels = domainService.queryListByCondition(query);
        for(YJWYRoomModel roomModel :yjwyRoomModels){
				ids.add(roomModel.getRm_id());
			}
        return ids;
	}

}
