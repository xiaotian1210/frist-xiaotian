package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 属性名称表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(AttributeNameBusinessService.ID)
public class AttributeNameBusinessServiceImpl implements AttributeNameBusinessService {

	@Autowired
	@Qualifier(AttributeNameDomainService.ID)
	private AttributeNameDomainService domainService;
	
	public void setDomainService(AttributeNameDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public AttributeNameModel[] query(Query query) throws ShareworxServiceException {
		List<AttributeNameModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new AttributeNameModel[0];
		}
		return list.toArray(new AttributeNameModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService#load(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel)
	 */
	@Override
	public AttributeNameModel[] load(AttributeNameModel model) throws ShareworxServiceException {
		List<AttributeNameModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new AttributeNameModel[0];
		}
		return list.toArray(new AttributeNameModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService#save(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public AttributeNameModel[] save(AttributeNameModel[] models) throws ShareworxServiceException {
		List<AttributeNameModel> list = domainService.save(models);
		return list.toArray(new AttributeNameModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService#update(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public AttributeNameModel[] update(AttributeNameModel[] models) throws ShareworxServiceException {
		List<AttributeNameModel> list = domainService.update(models);
		return list.toArray(new AttributeNameModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService#delete(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public AttributeNameModel[] delete(AttributeNameModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
