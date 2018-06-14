package com.shareworx.ezfm.device.siteproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * FM与YJWY项目关联表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSiteProjectBusinessService.ID)
public class YJWYSiteProjectBusinessServiceImpl implements YJWYSiteProjectBusinessService {

	@Autowired
	@Qualifier(YJWYSiteProjectDomainService.ID)
	private YJWYSiteProjectDomainService domainService;
	
	public void setDomainService(YJWYSiteProjectDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSiteProjectModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYSiteProjectModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSiteProjectModel[0];
		}
		return list.toArray(new YJWYSiteProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel)
	 */
	@Override
	public YJWYSiteProjectModel[] load(YJWYSiteProjectModel model) throws ShareworxServiceException {
		List<YJWYSiteProjectModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSiteProjectModel[0];
		}
		return list.toArray(new YJWYSiteProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public YJWYSiteProjectModel[] save(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		List<YJWYSiteProjectModel> list = domainService.save(models);
		return list.toArray(new YJWYSiteProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public YJWYSiteProjectModel[] update(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		List<YJWYSiteProjectModel> list = domainService.update(models);
		return list.toArray(new YJWYSiteProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public YJWYSiteProjectModel[] delete(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
