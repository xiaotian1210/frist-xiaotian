package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * RoleUserModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleUserDomainService.ID)
public class YJWYRoleUserDomainServiceImpl implements YJWYRoleUserDomainService {

	public final static String ID = "yjwyRroleUserDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#save(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public List<YJWYRoleUserModel> save(YJWYRoleUserModel... models) throws ShareworxServiceException {
		return service.save(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#update(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public List<YJWYRoleUserModel> update(YJWYRoleUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public List<YJWYRoleUserModel> update(List<String> editFields, YJWYRoleUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleUserModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#delete(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public int delete(YJWYRoleUserModel... models) throws ShareworxServiceException {
		return service.delete(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYRoleUserModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYRoleUserModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYRoleUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYRoleUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYRoleUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleUserDomainService#queryByExample(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel)
	 */
	@Override
	public List<YJWYRoleUserModel> queryByExample(YJWYRoleUserModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYRoleUserModel.META_ID, model);
	}

}
