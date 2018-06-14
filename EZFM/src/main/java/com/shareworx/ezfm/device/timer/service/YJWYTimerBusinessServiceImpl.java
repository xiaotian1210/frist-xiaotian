package com.shareworx.ezfm.device.timer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 定时任务执行记录业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTimerBusinessService.ID)
public class YJWYTimerBusinessServiceImpl implements YJWYTimerBusinessService {

	@Autowired
	@Qualifier(YJWYTimerDomainService.ID)
	private YJWYTimerDomainService domainService;
	
	public void setDomainService(YJWYTimerDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTimerModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYTimerModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTimerModel[0];
		}
		return list.toArray(new YJWYTimerModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService#load(com.shareworx.ezfm.device.timer.model.YJWYTimerModel)
	 */
	@Override
	public YJWYTimerModel[] load(YJWYTimerModel model) throws ShareworxServiceException {
		List<YJWYTimerModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTimerModel[0];
		}
		return list.toArray(new YJWYTimerModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService#save(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public YJWYTimerModel[] save(YJWYTimerModel[] models) throws ShareworxServiceException {
		List<YJWYTimerModel> list = domainService.save(models);
		return list.toArray(new YJWYTimerModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService#update(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public YJWYTimerModel[] update(YJWYTimerModel[] models) throws ShareworxServiceException {
		List<YJWYTimerModel> list = domainService.update(models);
		return list.toArray(new YJWYTimerModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService#delete(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public YJWYTimerModel[] delete(YJWYTimerModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
