package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 计划设备中间表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPlanEqBusinessService.ID)
public class YJWYPlanEqBusinessServiceImpl implements YJWYPlanEqBusinessService {

	@Autowired
	@Qualifier(YJWYPlanEqDomainService.ID)
	private YJWYPlanEqDomainService domainService;
	
	public void setDomainService(YJWYPlanEqDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPlanEqModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYPlanEqModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPlanEqModel[0];
		}
		return list.toArray(new YJWYPlanEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService#load(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel)
	 */
	@Override
	public YJWYPlanEqModel[] load(YJWYPlanEqModel model) throws ShareworxServiceException {
		List<YJWYPlanEqModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPlanEqModel[0];
		}
		return list.toArray(new YJWYPlanEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService#save(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public YJWYPlanEqModel[] save(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		List<YJWYPlanEqModel> list = domainService.save(models);
		return list.toArray(new YJWYPlanEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService#update(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public YJWYPlanEqModel[] update(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		List<YJWYPlanEqModel> list = domainService.update(models);
		return list.toArray(new YJWYPlanEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqBusinessService#delete(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public YJWYPlanEqModel[] delete(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
