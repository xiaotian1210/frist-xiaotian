package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * FM项目表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSiteBusinessService.ID)
public class YJWYSiteBusinessServiceImpl implements YJWYSiteBusinessService {

	@Autowired
	@Qualifier(YJWYSiteDomainService.ID)
	private YJWYSiteDomainService domainService;
	
	public void setDomainService(YJWYSiteDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSiteModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYSiteModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSiteModel[0];
		}
		return list.toArray(new YJWYSiteModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel)
	 */
	@Override
	public YJWYSiteModel[] load(YJWYSiteModel model) throws ShareworxServiceException {
		List<YJWYSiteModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSiteModel[0];
		}
		return list.toArray(new YJWYSiteModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public YJWYSiteModel[] save(YJWYSiteModel[] models) throws ShareworxServiceException {
		List<YJWYSiteModel> list = domainService.save(models);
		return list.toArray(new YJWYSiteModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public YJWYSiteModel[] update(YJWYSiteModel[] models) throws ShareworxServiceException {
		List<YJWYSiteModel> list = domainService.update(models);
		return list.toArray(new YJWYSiteModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public YJWYSiteModel[] delete(YJWYSiteModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
