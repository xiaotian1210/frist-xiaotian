package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 角色业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleBusinessService.ID)
public class YJWYRoleBusinessServiceImpl implements YJWYRoleBusinessService {

	@Autowired
	@Qualifier(YJWYRoleDomainService.ID)
	private YJWYRoleDomainService domainService;
	
	public void setDomainService(YJWYRoleDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYRoleModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleModel[0];
		}
		return list.toArray(new YJWYRoleModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleBusinessService#load(com.shareworx.ezfm.baseinfo.role.model.RoleModel)
	 */
	@Override
	public YJWYRoleModel[] load(YJWYRoleModel model) throws ShareworxServiceException {
		List<YJWYRoleModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleModel[0];
		}
		return list.toArray(new YJWYRoleModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleBusinessService#save(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public YJWYRoleModel[] save(YJWYRoleModel[] models) throws ShareworxServiceException {
		List<YJWYRoleModel> list = domainService.save(models);
		return list.toArray(new YJWYRoleModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleBusinessService#update(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public YJWYRoleModel[] update(YJWYRoleModel[] models) throws ShareworxServiceException {
		List<YJWYRoleModel> list = domainService.update(models);
		return list.toArray(new YJWYRoleModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleBusinessService#delete(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public YJWYRoleModel[] delete(YJWYRoleModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
