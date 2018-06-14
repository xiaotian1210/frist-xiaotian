package com.shareworx.ezfm.baseinfo.station.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 岗位管理持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYStationDao.ID)
public class YJWYStationDaoImpl implements YJWYStationDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#saveModels(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public int[] saveModels(YJWYStationModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#updateModels(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public int[] updateModels(YJWYStationModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#updateModels(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYStationModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#deleteModels(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public int deleteModels(YJWYStationModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYStationModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.model.YJWYStationDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
