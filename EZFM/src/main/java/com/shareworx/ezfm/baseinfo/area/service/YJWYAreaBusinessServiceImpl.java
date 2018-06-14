package com.shareworx.ezfm.baseinfo.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 区域管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYAreaBusinessService.ID)
public class YJWYAreaBusinessServiceImpl implements YJWYAreaBusinessService {

	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService domainService;
	
	public void setDomainService(YJWYAreaDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYAreaModel[] query(Query query) throws ShareworxServiceException {
 		List<YJWYAreaModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAreaModel[0];
		}
		return list.toArray(new YJWYAreaModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService#load(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel)
	 */
	@Override
	public YJWYAreaModel[] load(YJWYAreaModel model) throws ShareworxServiceException {
		List<YJWYAreaModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAreaModel[0];
		}
		return list.toArray(new YJWYAreaModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService#save(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYAreaModel[] save(YJWYAreaModel[] models) throws ShareworxServiceException {
		List<YJWYAreaModel> list = domainService.save(models);
		return list.toArray(new YJWYAreaModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService#update(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYAreaModel[] update(YJWYAreaModel[] models) throws ShareworxServiceException {
		List<YJWYAreaModel> list = domainService.update(models);
		return list.toArray(new YJWYAreaModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService#delete(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYAreaModel[] delete(YJWYAreaModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	@Override
	public YJWYAreaModel queryById(String id) throws ShareworxServiceException {
		return domainService.queryById(id);
	}

}
