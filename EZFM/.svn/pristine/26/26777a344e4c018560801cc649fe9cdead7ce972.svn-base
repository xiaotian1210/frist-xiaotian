package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 工艺程序步骤子表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPmpsBusinessService.ID)
public class YJWYPmpsBusinessServiceImpl implements YJWYPmpsBusinessService {

	@Autowired
	@Qualifier(YJWYPmpsDomainService.ID)
	private YJWYPmpsDomainService domainService;
	
	public void setDomainService(YJWYPmpsDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPmpsModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYPmpsModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPmpsModel[0];
		}
		return list.toArray(new YJWYPmpsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel)
	 */
	@Override
	public YJWYPmpsModel[] load(YJWYPmpsModel model) throws ShareworxServiceException {
		List<YJWYPmpsModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYPmpsModel[0];
		}
		return list.toArray(new YJWYPmpsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public YJWYPmpsModel[] save(YJWYPmpsModel[] models) throws ShareworxServiceException {
		List<YJWYPmpsModel> list = domainService.save(models);
		return list.toArray(new YJWYPmpsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public YJWYPmpsModel[] update(YJWYPmpsModel[] models) throws ShareworxServiceException {
		List<YJWYPmpsModel> list = domainService.update(models);
		return list.toArray(new YJWYPmpsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public YJWYPmpsModel[] delete(YJWYPmpsModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
