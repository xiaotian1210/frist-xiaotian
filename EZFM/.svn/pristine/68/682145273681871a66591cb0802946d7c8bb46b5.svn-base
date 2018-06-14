package com.shareworx.ezfm.energyloss.tabledefinition.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 能耗数据修改表持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWJEnergyUpdateDao.ID)
public class YJWJEnergyUpdateDaoImpl implements YJWJEnergyUpdateDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#saveModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public int[] saveModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#updateModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public int[] updateModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#updateModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWJEnergyUpdateModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWJEnergyUpdateModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#deleteModels(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public int deleteModels(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWJEnergyUpdateModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWJEnergyUpdateModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWJEnergyUpdateModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWJEnergyUpdateModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
