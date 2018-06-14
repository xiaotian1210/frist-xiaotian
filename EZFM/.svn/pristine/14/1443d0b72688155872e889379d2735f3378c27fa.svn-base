package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 基础属性表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYBaseAttributeBusinessService.ID)
public class YJWYBaseAttributeBusinessServiceImpl implements YJWYBaseAttributeBusinessService {

	@Autowired
	@Qualifier(YJWYBaseAttributeDomainService.ID)
	private YJWYBaseAttributeDomainService domainService;
	
	public void setDomainService(YJWYBaseAttributeDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYBaseAttributeModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYBaseAttributeModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYBaseAttributeModel[0];
		}
		return list.toArray(new YJWYBaseAttributeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeBusinessService#load(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel)
	 */
	@Override
	public YJWYBaseAttributeModel[] load(YJWYBaseAttributeModel model) throws ShareworxServiceException {
		List<YJWYBaseAttributeModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYBaseAttributeModel[0];
		}
		return list.toArray(new YJWYBaseAttributeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeBusinessService#save(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public YJWYBaseAttributeModel[] save(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		List<YJWYBaseAttributeModel> list = domainService.save(models);
		return list.toArray(new YJWYBaseAttributeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeBusinessService#update(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public YJWYBaseAttributeModel[] update(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		List<YJWYBaseAttributeModel> list = domainService.update(models);
		return list.toArray(new YJWYBaseAttributeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeBusinessService#delete(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public YJWYBaseAttributeModel[] delete(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
