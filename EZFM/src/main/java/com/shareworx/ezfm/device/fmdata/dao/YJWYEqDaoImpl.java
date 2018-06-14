package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 设备设施基本信息持久化操作实现
 *
 * @author liping.dai
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYEqDao.ID)
public class YJWYEqDaoImpl implements YJWYEqDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public int[] saveModels(YJWYEqModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public int[] updateModels(YJWYEqModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYEqModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYEqModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public int deleteModels(YJWYEqModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYEqModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYEqDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
