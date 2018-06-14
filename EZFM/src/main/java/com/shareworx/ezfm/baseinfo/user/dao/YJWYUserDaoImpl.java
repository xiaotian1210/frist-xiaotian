package com.shareworx.ezfm.baseinfo.user.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 系统用户持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYUserDao.ID)
public class YJWYUserDaoImpl implements YJWYUserDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#saveModels(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public int[] saveModels(YJWYUserModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#updateModels(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public int[] updateModels(YJWYUserModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#updateModels(com.shareworx.ezfm.baseinfo.user.model.UserModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYUserModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#deleteModels(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public int deleteModels(YJWYUserModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYUserModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.model.UserDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
