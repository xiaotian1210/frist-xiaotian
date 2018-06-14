package com.shareworx.ezfm.pub.test.plms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 测试plms业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(PlmsBusinessService.ID)
public class PlmsBusinessServiceImpl implements PlmsBusinessService {

	@Autowired
	@Qualifier(PlmsDomainService.ID)
	private PlmsDomainService domainService;
	
	public void setDomainService(PlmsDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PlmsModel[] query(Query query) throws ShareworxServiceException {
		List<PlmsModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new PlmsModel[0];
		}
		return list.toArray(new PlmsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService#load(com.shareworx.ezfm.pub.test.plms.model.PlmsModel)
	 */
	@Override
	public PlmsModel[] load(PlmsModel model) throws ShareworxServiceException {
		List<PlmsModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new PlmsModel[0];
		}
		return list.toArray(new PlmsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService#save(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public PlmsModel[] save(PlmsModel[] models) throws ShareworxServiceException {
		List<PlmsModel> list = domainService.save(models);
		return list.toArray(new PlmsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService#update(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public PlmsModel[] update(PlmsModel[] models) throws ShareworxServiceException {
		List<PlmsModel> list = domainService.update(models);
		return list.toArray(new PlmsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService#delete(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public PlmsModel[] delete(PlmsModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
