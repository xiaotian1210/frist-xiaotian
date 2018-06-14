package com.shareworx.ezfm.baseinfo.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 项目管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProjectBusinessService.ID)
public class YJWYProjectBusinessServiceImpl implements YJWYProjectBusinessService {

	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService domainService;
	
	public void setDomainService(YJWYProjectDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYProjectModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectModel[0];
		}
		return list.toArray(new YJWYProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService#load(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel)
	 */
	@Override
	public YJWYProjectModel[] load(YJWYProjectModel model) throws ShareworxServiceException {
		List<YJWYProjectModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectModel[0];
		}
		return list.toArray(new YJWYProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService#save(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYProjectModel[] save(YJWYProjectModel[] models) throws ShareworxServiceException {
		List<YJWYProjectModel> list = domainService.save(models);
		return list.toArray(new YJWYProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService#update(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYProjectModel[] update(YJWYProjectModel[] models) throws ShareworxServiceException {
		List<YJWYProjectModel> list = domainService.update(models);
		return list.toArray(new YJWYProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService#delete(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="'"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+#_currentUser.pk_crop", allEntries="true")
	public YJWYProjectModel[] delete(YJWYProjectModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	@Override
	public YJWYProjectModel queryById(String id) throws ShareworxServiceException {
		return domainService.queryById(id);
	}

}
