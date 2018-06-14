package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 核查标准人员中间表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardUserBusinessService.ID)
public class StandardUserBusinessServiceImpl implements StandardUserBusinessService {

	@Autowired
	@Qualifier(StandardUserDomainService.ID)
	private StandardUserDomainService domainService;
	
	public void setDomainService(StandardUserDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public StandardUserModel[] query(Query query) throws ShareworxServiceException {
		List<StandardUserModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new StandardUserModel[0];
		}
		return list.toArray(new StandardUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel)
	 */
	@Override
	public StandardUserModel[] load(StandardUserModel model) throws ShareworxServiceException {
		List<StandardUserModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new StandardUserModel[0];
		}
		return list.toArray(new StandardUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public StandardUserModel[] save(StandardUserModel[] models) throws ShareworxServiceException {
		List<StandardUserModel> list = domainService.save(models);
		return list.toArray(new StandardUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public StandardUserModel[] update(StandardUserModel[] models) throws ShareworxServiceException {
		List<StandardUserModel> list = domainService.update(models);
		return list.toArray(new StandardUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public StandardUserModel[] delete(StandardUserModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
