package com.shareworx.ezfm.baseinfo.role.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 角色持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYRoleDao.ID)
public class YJWYRoleDaoImpl implements YJWYRoleDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#saveModels(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public int[] saveModels(YJWYRoleModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#updateModels(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public int[] updateModels(YJWYRoleModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#updateModels(com.shareworx.ezfm.baseinfo.role.model.RoleModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYRoleModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#deleteModels(com.shareworx.ezfm.baseinfo.role.model.RoleModel[])
	 */
	@Override
	public int deleteModels(YJWYRoleModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYRoleModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYRoleModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYRoleModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYRoleModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
