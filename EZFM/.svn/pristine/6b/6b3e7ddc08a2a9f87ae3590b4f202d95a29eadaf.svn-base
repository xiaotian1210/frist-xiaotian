package com.shareworx.ezfm.device.fmdata_eq.dao;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 设备所属系统持久化操作实现
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYEqSysDao.ID)
public class YJWYEqSysDaoImpl implements YJWYEqSysDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#saveModels(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public int[] saveModels(YJWYEqSysModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#updateModels(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public int[] updateModels(YJWYEqSysModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#updateModels(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYEqSysModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYEqSysModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#deleteModels(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public int deleteModels(YJWYEqSysModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYEqSysModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYEqSysModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYEqSysModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEqSysModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
