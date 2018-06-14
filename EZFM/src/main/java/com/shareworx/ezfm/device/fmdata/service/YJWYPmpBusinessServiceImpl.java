package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 工艺程序主表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPmpBusinessService.ID)
public class YJWYPmpBusinessServiceImpl implements YJWYPmpBusinessService {

	@Autowired
	@Qualifier(YJWYPmpDomainService.ID)
	private YJWYPmpDomainService domainService;
	
	public void setDomainService(YJWYPmpDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPmpModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYPmpModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPmpModel[0];
		}
		return list.toArray(new YJWYPmpModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel)
	 */
	@Override
	public YJWYPmpModel[] load(YJWYPmpModel model) throws ShareworxServiceException {
		List<YJWYPmpModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPmpModel[0];
		}
		return list.toArray(new YJWYPmpModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public YJWYPmpModel[] save(YJWYPmpModel[] models) throws ShareworxServiceException {
		List<YJWYPmpModel> list = domainService.save(models);
		return list.toArray(new YJWYPmpModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public YJWYPmpModel[] update(YJWYPmpModel[] models) throws ShareworxServiceException {
		List<YJWYPmpModel> list = domainService.update(models);
		return list.toArray(new YJWYPmpModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public YJWYPmpModel[] delete(YJWYPmpModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
