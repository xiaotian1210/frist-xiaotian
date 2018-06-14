package com.shareworx.ezfm.baseinfo.type.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 资源类型业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(ResourceTypeBusinessService.ID)
public class ResourceTypeBusinessServiceImpl implements ResourceTypeBusinessService {

	@Autowired
	@Qualifier(ResourceTypeDomainService.ID)
	private ResourceTypeDomainService domainService;
	
	public void setDomainService(ResourceTypeDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ResourcetypeModel[] query(Query query) throws ShareworxServiceException {
		List<ResourcetypeModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new ResourcetypeModel[0];
		}
		return list.toArray(new ResourcetypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeBusinessService#load(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel)
	 */
	@Override
	public ResourcetypeModel[] load(ResourcetypeModel model) throws ShareworxServiceException {
		List<ResourcetypeModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new ResourcetypeModel[0];
		}
		return list.toArray(new ResourcetypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeBusinessService#save(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public ResourcetypeModel[] save(ResourcetypeModel[] models) throws ShareworxServiceException {
		List<ResourcetypeModel> list = domainService.save(models);
		return list.toArray(new ResourcetypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeBusinessService#update(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	@DMSCacheEvict(namespace="#models.!['"+CacheConstants.RESOURCE_CACHA_NAMESPACE+"'+pk_crop]", allEntries="true")
	public ResourcetypeModel[] update(ResourcetypeModel[] models) throws ShareworxServiceException {
		List<ResourcetypeModel> list = domainService.update(models);
		return list.toArray(new ResourcetypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeBusinessService#delete(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public ResourcetypeModel[] delete(ResourcetypeModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
