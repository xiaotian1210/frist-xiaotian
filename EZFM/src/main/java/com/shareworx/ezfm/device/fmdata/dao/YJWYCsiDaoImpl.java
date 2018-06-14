package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 设备分类信息持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYCsiDao.ID)
public class YJWYCsiDaoImpl implements YJWYCsiDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public int[] saveModels(YJWYCsiModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public int[] updateModels(YJWYCsiModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYCsiModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYCsiModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public int deleteModels(YJWYCsiModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYCsiModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYCsiModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYCsiModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYCsiDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYCsiModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
