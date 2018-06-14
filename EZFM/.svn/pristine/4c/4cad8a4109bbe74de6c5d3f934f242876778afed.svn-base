package com.shareworx.ezfm.system.crop.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 企业管理持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(CropDao.ID)
public class CropDaoImpl implements CropDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#saveModels(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public int[] saveModels(CropModel[] models) throws ShareworxServiceException {
		return dao.saveModels(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#updateModels(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public int[] updateModels(CropModel[] models) throws ShareworxServiceException {
		return dao.updateModels(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#updateModels(com.shareworx.ezfm.system.crop.model.CropModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(CropModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(CropModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#deleteModels(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public int deleteModels(CropModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#queryById(java.io.Serializable)
	 */
	@Override
	public CropModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(CropModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<CropModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.model.CropDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public CropModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
