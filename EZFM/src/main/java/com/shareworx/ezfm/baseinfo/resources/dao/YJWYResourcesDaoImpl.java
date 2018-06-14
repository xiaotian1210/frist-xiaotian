package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 资源管理持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYResourcesDao.ID)
public class YJWYResourcesDaoImpl implements YJWYResourcesDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#saveModels(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public int[] saveModels(YJWYResourcesModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public int[] updateModels(YJWYResourcesModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYResourcesModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourcesModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public int deleteModels(YJWYResourcesModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYResourcesModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYResourcesModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYResourcesModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourcesModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
