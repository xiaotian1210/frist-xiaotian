package com.shareworx.ezfm.device.patrol.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 巡检维保任务表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskBusinessService.ID)
public class YJWYTaskBusinessServiceImpl implements YJWYTaskBusinessService {

	@Autowired
	@Qualifier(YJWYTaskDomainService.ID)
	private YJWYTaskDomainService domainService;
	
	public void setDomainService(YJWYTaskDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYTaskModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskModel[0];
		}
		return list.toArray(new YJWYTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService#load(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel)
	 */
	@Override
	public YJWYTaskModel[] load(YJWYTaskModel model) throws ShareworxServiceException {
		List<YJWYTaskModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskModel[0];
		}
		return list.toArray(new YJWYTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService#save(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public YJWYTaskModel[] save(YJWYTaskModel[] models) throws ShareworxServiceException {
		List<YJWYTaskModel> list = domainService.save(models);
		return list.toArray(new YJWYTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService#update(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public YJWYTaskModel[] update(YJWYTaskModel[] models) throws ShareworxServiceException {
		List<YJWYTaskModel> list = domainService.update(models);
		return list.toArray(new YJWYTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskBusinessService#delete(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public YJWYTaskModel[] delete(YJWYTaskModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
