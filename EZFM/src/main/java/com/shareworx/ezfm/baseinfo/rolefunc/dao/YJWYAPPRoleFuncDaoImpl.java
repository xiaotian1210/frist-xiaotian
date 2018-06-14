package com.shareworx.ezfm.baseinfo.rolefunc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * APP手机权限持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYAPPRoleFuncDao.ID)
public class YJWYAPPRoleFuncDaoImpl implements YJWYAPPRoleFuncDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#saveModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public int[] saveModels(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#updateModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public int[] updateModels(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#updateModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYAPPRoleFuncModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYAPPRoleFuncModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#deleteModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public int deleteModels(YJWYAPPRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYAPPRoleFuncModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYAPPRoleFuncModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYAPPRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
