package com.shareworx.ezfm.device.warn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;

/**
 * eba设备报警信息业务操作实现
 * 
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEbaWarningBusinessService.ID)
public class YJWYEbaWarningBusinessServiceImpl implements YJWYEbaWarningBusinessService {

	@Autowired
	@Qualifier(YJWYEbaWarningDomainService.ID)
	private YJWYEbaWarningDomainService domainService;

	public void setDomainService(YJWYEbaWarningDomainService domainService) {
		this.domainService = domainService;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService#
	 * query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEbaWarningModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYEbaWarningModel> list = domainService.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYEbaWarningModel[0];
		}
		return list.toArray(new YJWYEbaWarningModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService#load
	 * (com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel)
	 */
	@Override
	public YJWYEbaWarningModel[] load(YJWYEbaWarningModel model) throws ShareworxServiceException {
		List<YJWYEbaWarningModel> list = domainService.queryByExample(model);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYEbaWarningModel[0];
		}
		return list.toArray(new YJWYEbaWarningModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService#save
	 * (com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public YJWYEbaWarningModel[] save(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		List<YJWYEbaWarningModel> list = domainService.save(models);
		return list.toArray(new YJWYEbaWarningModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService#
	 * update(com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public YJWYEbaWarningModel[] update(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		List<YJWYEbaWarningModel> list = domainService.update(models);
		return list.toArray(new YJWYEbaWarningModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService#
	 * delete(com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public YJWYEbaWarningModel[] delete(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
