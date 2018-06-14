package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 设备分类信息业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYCsiBusinessService.ID)
public class YJWYCsiBusinessServiceImpl implements YJWYCsiBusinessService {

	@Autowired
	@Qualifier(YJWYCsiDomainService.ID)
	private YJWYCsiDomainService domainService;
	
	public void setDomainService(YJWYCsiDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYCsiModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYCsiModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYCsiModel[0];
		}
		return list.toArray(new YJWYCsiModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService#load(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel)
	 */
	@Override
	public YJWYCsiModel[] load(YJWYCsiModel model) throws ShareworxServiceException {
		List<YJWYCsiModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYCsiModel[0];
		}
		return list.toArray(new YJWYCsiModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService#save(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public YJWYCsiModel[] save(YJWYCsiModel[] models) throws ShareworxServiceException {
		List<YJWYCsiModel> list = domainService.save(models);
		return list.toArray(new YJWYCsiModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService#update(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public YJWYCsiModel[] update(YJWYCsiModel[] models) throws ShareworxServiceException {
		List<YJWYCsiModel> list = domainService.update(models);
		return list.toArray(new YJWYCsiModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiBusinessService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public YJWYCsiModel[] delete(YJWYCsiModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
