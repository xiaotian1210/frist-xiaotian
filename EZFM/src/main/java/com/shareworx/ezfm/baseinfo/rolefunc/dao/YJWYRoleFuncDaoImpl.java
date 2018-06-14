package com.shareworx.ezfm.baseinfo.rolefunc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 基于角色的功能权限持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYRoleFuncDao.ID)
public class YJWYRoleFuncDaoImpl implements YJWYRoleFuncDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#saveModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public int[] saveModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#updateModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public int[] updateModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#updateModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYRoleFuncModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoleFuncModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#deleteModels(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public int deleteModels(YJWYRoleFuncModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYRoleFuncModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYRoleFuncModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
