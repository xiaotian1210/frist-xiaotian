package com.shareworx.ezfm.energyloss.tabledefinition.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 电表持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYEnergyElectricDao.ID)
public class YJWYEnergyElectricDaoImpl implements YJWYEnergyElectricDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#saveModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public int[] saveModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#updateModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public int[] updateModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#updateModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYEnergyElectricModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYEnergyElectricModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#deleteModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public int deleteModels(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYEnergyElectricModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYEnergyElectricModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYEnergyElectricModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEnergyElectricModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
