package com.shareworx.ezfm.worktask.projectuser.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 项目接口人员持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskProjectUserDao.ID)
public class YJWYWorkTaskProjectUserDaoImpl implements YJWYWorkTaskProjectUserDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#saveModels(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#updateModels(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#updateModels(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskProjectUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskProjectUserModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#deleteModels(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskProjectUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
