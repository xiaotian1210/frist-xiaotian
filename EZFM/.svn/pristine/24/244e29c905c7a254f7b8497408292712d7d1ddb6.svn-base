package com.shareworx.ezfm.baseinfo.resourceslog.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 记录资源空间日志表持久化操作实现
 *
 * @author kimguo
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYResourcesLogDao.ID)
public class YJWYResourcesLogDaoImpl implements YJWYResourcesLogDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#saveModels(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public int[] saveModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#updateModels(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public int[] updateModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#updateModels(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYResourcesLogModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourcesLogModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#deleteModels(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public int deleteModels(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYResourcesLogModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYResourcesLogModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYResourcesLogModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourcesLogModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
