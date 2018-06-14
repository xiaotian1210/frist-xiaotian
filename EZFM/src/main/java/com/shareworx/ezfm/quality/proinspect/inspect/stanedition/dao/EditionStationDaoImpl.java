package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 版本与岗位中间表持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(EditionStationDao.ID)
public class EditionStationDaoImpl implements EditionStationDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public int[] saveModels(EditionStationModel[] models) throws ShareworxServiceException {
		return dao.saveModels(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public int[] updateModels(EditionStationModel[] models) throws ShareworxServiceException {
		return dao.updateModels(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(EditionStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(EditionStationModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public int deleteModels(EditionStationModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#queryById(java.io.Serializable)
	 */
	@Override
	public EditionStationModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(EditionStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<EditionStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public EditionStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
