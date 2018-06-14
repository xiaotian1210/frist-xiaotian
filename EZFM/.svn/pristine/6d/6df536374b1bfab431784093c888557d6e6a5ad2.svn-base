package com.shareworx.ezfm.baseinfo.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * RoleModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleDomainService.ID)
public class YJWYRoleDomainServiceImpl implements YJWYRoleDomainService {

	public final static String ID = "yjwyRoleDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#save(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public List<YJWYRoleModel> save(YJWYRoleModel... models) throws ShareworxServiceException {
		return service.save(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#update(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public List<YJWYRoleModel> update(YJWYRoleModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public List<YJWYRoleModel> update(List<String> editFields, YJWYRoleModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#delete(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public int delete(YJWYRoleModel... models) throws ShareworxServiceException {
		return service.delete(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYRoleModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYRoleModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYRoleModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYRoleModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYRoleModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.service.RoleDomainService#queryByExample(com.shareworx.ezfm.baseinfo.role.model.RoleModel)
	 */
	@Override
	public List<YJWYRoleModel> queryByExample(YJWYRoleModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYRoleModel.META_ID, model);
	}

}
