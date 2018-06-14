package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 资源_属性业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(AttrResouorceBusinessService.ID)
public class AttrResouorceBusinessServiceImpl implements AttrResouorceBusinessService {

	@Autowired
	@Qualifier(AttrResouorceDomainService.ID)
	private AttrResouorceDomainService domainService;
	
	public void setDomainService(AttrResouorceDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public AttrResouorceModel[] query(Query query) throws ShareworxServiceException {
		List<AttrResouorceModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new AttrResouorceModel[0];
		}
		return list.toArray(new AttrResouorceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceBusinessService#load(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel)
	 */
	@Override
	public AttrResouorceModel[] load(AttrResouorceModel model) throws ShareworxServiceException {
		List<AttrResouorceModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new AttrResouorceModel[0];
		}
		return list.toArray(new AttrResouorceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceBusinessService#save(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public AttrResouorceModel[] save(AttrResouorceModel[] models) throws ShareworxServiceException {
		List<AttrResouorceModel> list = domainService.save(models);
		return list.toArray(new AttrResouorceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceBusinessService#update(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public AttrResouorceModel[] update(AttrResouorceModel[] models) throws ShareworxServiceException {
		List<AttrResouorceModel> list = domainService.update(models);
		return list.toArray(new AttrResouorceModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceBusinessService#delete(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public AttrResouorceModel[] delete(AttrResouorceModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
