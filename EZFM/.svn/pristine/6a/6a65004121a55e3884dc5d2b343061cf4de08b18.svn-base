package com.shareworx.ezfm.performance.sign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 签到管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSignBusinessService.ID)
public class YJWYSignBusinessServiceImpl implements YJWYSignBusinessService {

	@Autowired
	@Qualifier(YJWYSignDomainService.ID)
	private YJWYSignDomainService domainService;
	
	public void setDomainService(YJWYSignDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSignModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYSignModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSignModel[0];
		}
		return list.toArray(new YJWYSignModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService#load(com.shareworx.ezfm.performance.sign.model.YJWYSignModel)
	 */
	@Override
	public YJWYSignModel[] load(YJWYSignModel model) throws ShareworxServiceException {
		List<YJWYSignModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYSignModel[0];
		}
		return list.toArray(new YJWYSignModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService#save(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public YJWYSignModel[] save(YJWYSignModel[] models) throws ShareworxServiceException {
		List<YJWYSignModel> list = domainService.save(models);
		return list.toArray(new YJWYSignModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService#update(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public YJWYSignModel[] update(YJWYSignModel[] models) throws ShareworxServiceException {
		List<YJWYSignModel> list = domainService.update(models);
		return list.toArray(new YJWYSignModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService#delete(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public YJWYSignModel[] delete(YJWYSignModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
