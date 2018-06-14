package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * FM项目表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYSiteDao.ID)
public class YJWYSiteDaoImpl implements YJWYSiteDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public int[] saveModels(YJWYSiteModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public int[] updateModels(YJWYSiteModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYSiteModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYSiteModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public int deleteModels(YJWYSiteModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYSiteModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYSiteModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYSiteModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSiteModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
