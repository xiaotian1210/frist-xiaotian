package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 设备设施基本信息业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEqBusinessService.ID)
public class YJWYEqBusinessServiceImpl implements YJWYEqBusinessService {

	@Autowired
	@Qualifier(YJWYEqDomainService.ID)
	private YJWYEqDomainService domainService;
	
	public void setDomainService(YJWYEqDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEqModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYEqModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEqModel[0];
		}
		return list.toArray(new YJWYEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel)
	 */
	@Override
	public YJWYEqModel[] load(YJWYEqModel model) throws ShareworxServiceException {
		List<YJWYEqModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEqModel[0];
		}
		return list.toArray(new YJWYEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public YJWYEqModel[] save(YJWYEqModel[] models) throws ShareworxServiceException {
		List<YJWYEqModel> list = domainService.save(models);
		return list.toArray(new YJWYEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public YJWYEqModel[] update(YJWYEqModel[] models) throws ShareworxServiceException {
		List<YJWYEqModel> list = domainService.update(models);
		return list.toArray(new YJWYEqModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public YJWYEqModel[] delete(YJWYEqModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
