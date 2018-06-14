package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 分组和人员关系表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYGroupUserBusinessService.ID)
public class YJWYGroupUserBusinessServiceImpl implements YJWYGroupUserBusinessService {

	@Autowired
	@Qualifier(YJWYGroupUserDomainService.ID)
	private YJWYGroupUserDomainService domainService;
	
	public void setDomainService(YJWYGroupUserDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYGroupUserModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYGroupUserModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYGroupUserModel[0];
		}
		return list.toArray(new YJWYGroupUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService#load(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel)
	 */
	@Override
	public YJWYGroupUserModel[] load(YJWYGroupUserModel model) throws ShareworxServiceException {
		List<YJWYGroupUserModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYGroupUserModel[0];
		}
		return list.toArray(new YJWYGroupUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService#save(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public YJWYGroupUserModel[] save(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		List<YJWYGroupUserModel> list = domainService.save(models);
		return list.toArray(new YJWYGroupUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService#update(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public YJWYGroupUserModel[] update(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		List<YJWYGroupUserModel> list = domainService.update(models);
		return list.toArray(new YJWYGroupUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserBusinessService#delete(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public YJWYGroupUserModel[] delete(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
