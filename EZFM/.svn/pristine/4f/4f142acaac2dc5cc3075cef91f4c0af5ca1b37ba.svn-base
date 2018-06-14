package com.shareworx.ezfm.quality.proinspect.inspect.standard.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 标准与岗位中间表持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(StandardStationDao.ID)
public class StandardStationDaoImpl implements StandardStationDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public int[] saveModels(StandardStationModel[] models) throws ShareworxServiceException {
		return dao.saveModels(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public int[] updateModels(StandardStationModel[] models) throws ShareworxServiceException {
		return dao.updateModels(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(StandardStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(StandardStationModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public int deleteModels(StandardStationModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#queryById(java.io.Serializable)
	 */
	@Override
	public StandardStationModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(StandardStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<StandardStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public StandardStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
