package com.shareworx.ezfm.system.function.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 系统功能业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYFunctionBusinessService.ID)
public class YJWYFunctionBusinessServiceImpl implements YJWYFunctionBusinessService {

	@Autowired
	@Qualifier(YJWYFunctionDomainService.ID)
	private YJWYFunctionDomainService domainService;
	
	public void setDomainService(YJWYFunctionDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYFunctionModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYFunctionModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYFunctionModel[0];
		}
		return list.toArray(new YJWYFunctionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService#load(com.shareworx.ezfm.system.function.model.YJWYFunctionModel)
	 */
	@Override
	public YJWYFunctionModel[] load(YJWYFunctionModel model) throws ShareworxServiceException {
		List<YJWYFunctionModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYFunctionModel[0];
		}
		return list.toArray(new YJWYFunctionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService#save(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public YJWYFunctionModel[] save(YJWYFunctionModel[] models) throws ShareworxServiceException {
		List<YJWYFunctionModel> list = domainService.save(models);
		return list.toArray(new YJWYFunctionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService#update(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public YJWYFunctionModel[] update(YJWYFunctionModel[] models) throws ShareworxServiceException {
		List<YJWYFunctionModel> list = domainService.update(models);
		return list.toArray(new YJWYFunctionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService#delete(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public YJWYFunctionModel[] delete(YJWYFunctionModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
