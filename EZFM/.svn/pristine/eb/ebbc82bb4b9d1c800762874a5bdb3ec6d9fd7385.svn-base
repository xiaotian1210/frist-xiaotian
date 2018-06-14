package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 基于角色的功能权限业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleFuncBusinessService.ID)
public class YJWYRoleFuncBusinessServiceImpl implements YJWYRoleFuncBusinessService {

	@Autowired
	@Qualifier(YJWYRoleFuncDomainService.ID)
	private YJWYRoleFuncDomainService domainService;
	
	public void setDomainService(YJWYRoleFuncDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleFuncModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYRoleFuncModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleFuncModel[0];
		}
		return list.toArray(new YJWYRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService#load(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel)
	 */
	@Override
	public YJWYRoleFuncModel[] load(YJWYRoleFuncModel model) throws ShareworxServiceException {
		List<YJWYRoleFuncModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleFuncModel[0];
		}
		return list.toArray(new YJWYRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService#save(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public YJWYRoleFuncModel[] save(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		List<YJWYRoleFuncModel> list = domainService.save(models);
		return list.toArray(new YJWYRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService#update(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public YJWYRoleFuncModel[] update(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		List<YJWYRoleFuncModel> list = domainService.update(models);
		return list.toArray(new YJWYRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService#delete(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public YJWYRoleFuncModel[] delete(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
