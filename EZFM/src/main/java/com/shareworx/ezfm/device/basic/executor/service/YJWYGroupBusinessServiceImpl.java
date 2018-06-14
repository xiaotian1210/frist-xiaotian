package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 巡检/维保人员分组业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYGroupBusinessService.ID)
public class YJWYGroupBusinessServiceImpl implements YJWYGroupBusinessService {

	@Autowired
	@Qualifier(YJWYGroupDomainService.ID)
	private YJWYGroupDomainService domainService;
	
	public void setDomainService(YJWYGroupDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYGroupModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYGroupModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYGroupModel[0];
		}
		return list.toArray(new YJWYGroupModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService#load(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel)
	 */
	@Override
	public YJWYGroupModel[] load(YJWYGroupModel model) throws ShareworxServiceException {
		List<YJWYGroupModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYGroupModel[0];
		}
		return list.toArray(new YJWYGroupModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService#save(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public YJWYGroupModel[] save(YJWYGroupModel[] models) throws ShareworxServiceException {
		List<YJWYGroupModel> list = domainService.save(models);
		return list.toArray(new YJWYGroupModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService#update(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public YJWYGroupModel[] update(YJWYGroupModel[] models) throws ShareworxServiceException {
		List<YJWYGroupModel> list = domainService.update(models);
		return list.toArray(new YJWYGroupModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService#delete(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public YJWYGroupModel[] delete(YJWYGroupModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
