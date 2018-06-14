package com.shareworx.ezfm.baseinfo.usermobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 用户与设备关系表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYUserMobileBusinessService.ID)
public class YJWYUserMobileBusinessServiceImpl implements YJWYUserMobileBusinessService {

	@Autowired
	@Qualifier(YJWYUserMobileDomainService.ID)
	private YJWYUserMobileDomainService domainService;
	
	public void setDomainService(YJWYUserMobileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserMobileModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYUserMobileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserMobileModel[0];
		}
		return list.toArray(new YJWYUserMobileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileBusinessService#load(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel)
	 */
	@Override
	public YJWYUserMobileModel[] load(YJWYUserMobileModel model) throws ShareworxServiceException {
		List<YJWYUserMobileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserMobileModel[0];
		}
		return list.toArray(new YJWYUserMobileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileBusinessService#save(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public YJWYUserMobileModel[] save(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		List<YJWYUserMobileModel> list = domainService.save(models);
		return list.toArray(new YJWYUserMobileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileBusinessService#update(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public YJWYUserMobileModel[] update(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		List<YJWYUserMobileModel> list = domainService.update(models);
		return list.toArray(new YJWYUserMobileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileBusinessService#delete(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public YJWYUserMobileModel[] delete(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
