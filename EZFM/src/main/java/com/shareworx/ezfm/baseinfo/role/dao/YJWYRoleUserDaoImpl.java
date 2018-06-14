package com.shareworx.ezfm.baseinfo.role.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 角色人员关系持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYRoleUserDao.ID)
public class YJWYRoleUserDaoImpl implements YJWYRoleUserDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#saveModels(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public int[] saveModels(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#updateModels(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public int[] updateModels(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#updateModels(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYRoleUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleUserModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#deleteModels(com.shareworx.ezfm.baseinfo.role.model.RoleUserModel[])
	 */
	@Override
	public int deleteModels(YJWYRoleUserModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYRoleUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYRoleUserModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYRoleUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYRoleUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.role.model.RoleUserDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
