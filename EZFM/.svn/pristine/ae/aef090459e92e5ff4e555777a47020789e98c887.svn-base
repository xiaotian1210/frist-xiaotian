package com.shareworx.ezfm.energyloss.data.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 水表电表抄表持久化操作实现
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YjwyEnergyDataDao.ID)
public class YjwyEnergyDataDaoImpl implements YjwyEnergyDataDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#saveModels(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public int[] saveModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#updateModels(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public int[] updateModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#updateModels(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YjwyEnergyDataModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YjwyEnergyDataModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#deleteModels(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public int deleteModels(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#queryById(java.io.Serializable)
	 */
	@Override
	public YjwyEnergyDataModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YjwyEnergyDataModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YjwyEnergyDataModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YjwyEnergyDataModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
