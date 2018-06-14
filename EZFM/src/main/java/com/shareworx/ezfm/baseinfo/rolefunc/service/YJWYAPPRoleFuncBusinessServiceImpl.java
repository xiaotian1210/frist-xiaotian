package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * APP手机权限业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYAPPRoleFuncBusinessService.ID)
public class YJWYAPPRoleFuncBusinessServiceImpl implements YJWYAPPRoleFuncBusinessService {

	@Autowired
	@Qualifier(YJWYAPPRoleFuncDomainService.ID)
	private YJWYAPPRoleFuncDomainService domainService;
	
	public void setDomainService(YJWYAPPRoleFuncDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYAPPRoleFuncModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYAPPRoleFuncModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAPPRoleFuncModel[0];
		}
		return list.toArray(new YJWYAPPRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService#load(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel)
	 */
	@Override
	public YJWYAPPRoleFuncModel[] load(YJWYAPPRoleFuncModel model) throws ShareworxServiceException {
		List<YJWYAPPRoleFuncModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAPPRoleFuncModel[0];
		}
		return list.toArray(new YJWYAPPRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService#save(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public YJWYAPPRoleFuncModel[] save(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		List<YJWYAPPRoleFuncModel> list = domainService.save(models);
		return list.toArray(new YJWYAPPRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService#update(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public YJWYAPPRoleFuncModel[] update(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		List<YJWYAPPRoleFuncModel> list = domainService.update(models);
		return list.toArray(new YJWYAPPRoleFuncModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService#delete(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public YJWYAPPRoleFuncModel[] delete(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
