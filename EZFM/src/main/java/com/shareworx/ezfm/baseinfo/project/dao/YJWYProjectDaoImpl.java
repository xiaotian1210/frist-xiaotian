package com.shareworx.ezfm.baseinfo.project.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 项目管理持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYProjectDao.ID)
public class YJWYProjectDaoImpl implements YJWYProjectDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#saveModels(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public int[] saveModels(YJWYProjectModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#updateModels(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public int[] updateModels(YJWYProjectModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#updateModels(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYProjectModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#deleteModels(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public int deleteModels(YJWYProjectModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYProjectModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.model.YJWYProjectDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
