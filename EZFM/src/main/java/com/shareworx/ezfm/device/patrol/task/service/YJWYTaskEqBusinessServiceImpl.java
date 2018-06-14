package com.shareworx.ezfm.device.patrol.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 巡检维保任务表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskEqBusinessService.ID)
public class YJWYTaskEqBusinessServiceImpl implements YJWYTaskEqBusinessService {

	@Autowired
	@Qualifier(YJWYTaskEqDomainService.ID)
	private YJWYTaskEqDomainService domainService;
	
	public void setDomainService(YJWYTaskEqDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskEqModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYTaskEqModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskEqModel[0];
		}
		return list.toArray(new YJWYTaskEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService#load(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel)
	 */
	@Override
	public YJWYTaskEqModel[] load(YJWYTaskEqModel model) throws ShareworxServiceException {
		List<YJWYTaskEqModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskEqModel[0];
		}
		return list.toArray(new YJWYTaskEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService#save(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public YJWYTaskEqModel[] save(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		List<YJWYTaskEqModel> list = domainService.save(models);
		return list.toArray(new YJWYTaskEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService#update(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public YJWYTaskEqModel[] update(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		List<YJWYTaskEqModel> list = domainService.update(models);
		return list.toArray(new YJWYTaskEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService#delete(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public YJWYTaskEqModel[] delete(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
