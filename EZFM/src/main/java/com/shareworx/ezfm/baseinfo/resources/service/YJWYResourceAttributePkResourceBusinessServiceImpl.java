package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 资源属性与资源关联业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourceAttributePkResourceBusinessService.ID)
public class YJWYResourceAttributePkResourceBusinessServiceImpl implements YJWYResourceAttributePkResourceBusinessService {

	@Autowired
	@Qualifier(YJWYResourceAttributePkResourceDomainService.ID)
	private YJWYResourceAttributePkResourceDomainService domainService;
	
	public void setDomainService(YJWYResourceAttributePkResourceDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYResourceAttributePkResourceModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYResourceAttributePkResourceModel[0];
		}
		return list.toArray(new YJWYResourceAttributePkResourceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceBusinessService#load(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel[] load(YJWYResourceAttributePkResourceModel model) throws ShareworxServiceException {
		List<YJWYResourceAttributePkResourceModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYResourceAttributePkResourceModel[0];
		}
		return list.toArray(new YJWYResourceAttributePkResourceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceBusinessService#save(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public YJWYResourceAttributePkResourceModel[] save(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		List<YJWYResourceAttributePkResourceModel> list = domainService.save(models);
		return list.toArray(new YJWYResourceAttributePkResourceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceBusinessService#update(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public YJWYResourceAttributePkResourceModel[] update(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		List<YJWYResourceAttributePkResourceModel> list = domainService.update(models);
		return list.toArray(new YJWYResourceAttributePkResourceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceBusinessService#delete(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public YJWYResourceAttributePkResourceModel[] delete(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
