package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;

/**
 * 巡检维保计划业务操作实现
 * 
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPlanBusinessService.ID)
public class YJWYPlanBusinessServiceImpl implements YJWYPlanBusinessService {

	@Autowired
	@Qualifier(YJWYPlanDomainService.ID)
	private YJWYPlanDomainService domainService;

	public void setDomainService(YJWYPlanDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService#
	 * query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPlanModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYPlanModel> list = domainService.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYPlanModel[0];
		}
		return list.toArray(new YJWYPlanModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService#
	 * load(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel)
	 */
	@Override
	public YJWYPlanModel[] load(YJWYPlanModel model) throws ShareworxServiceException {
		List<YJWYPlanModel> list = domainService.queryByExample(model);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYPlanModel[0];
		}
		return list.toArray(new YJWYPlanModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService#
	 * save(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public YJWYPlanModel[] save(YJWYPlanModel[] models) throws ShareworxServiceException {
		List<YJWYPlanModel> list = domainService.save(models);
		return list.toArray(new YJWYPlanModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService#
	 * update(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public YJWYPlanModel[] update(YJWYPlanModel[] models) throws ShareworxServiceException {
		List<YJWYPlanModel> list = domainService.update(models);
		return list.toArray(new YJWYPlanModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService#
	 * delete(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public YJWYPlanModel[] delete(YJWYPlanModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
