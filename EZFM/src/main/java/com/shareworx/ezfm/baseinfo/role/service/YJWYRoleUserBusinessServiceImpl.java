package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 角色人员关系业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleUserBusinessService.ID)
public class YJWYRoleUserBusinessServiceImpl implements YJWYRoleUserBusinessService {

	@Autowired
	@Qualifier(YJWYRoleUserDomainService.ID)
	private YJWYRoleUserDomainService domainService;
	
	public void setDomainService(YJWYRoleUserDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleUserModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYRoleUserModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleUserModel[0];
		}
		return list.toArray(new YJWYRoleUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserBusinessService#load(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel)
	 */
	@Override
	public YJWYRoleUserModel[] load(YJWYRoleUserModel model) throws ShareworxServiceException {
		List<YJWYRoleUserModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYRoleUserModel[0];
		}
		return list.toArray(new YJWYRoleUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserBusinessService#save(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public YJWYRoleUserModel[] save(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		List<YJWYRoleUserModel> list = domainService.save(models);
		return list.toArray(new YJWYRoleUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserBusinessService#update(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public YJWYRoleUserModel[] update(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		List<YJWYRoleUserModel> list = domainService.update(models);
		return list.toArray(new YJWYRoleUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserBusinessService#delete(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public YJWYRoleUserModel[] delete(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
