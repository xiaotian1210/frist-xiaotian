package com.shareworx.ezfm.device.timer.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 定时任务执行记录持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYTimerDao.ID)
public class YJWYTimerDaoImpl implements YJWYTimerDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#saveModels(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public int[] saveModels(YJWYTimerModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#updateModels(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public int[] updateModels(YJWYTimerModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#updateModels(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYTimerModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYTimerModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#deleteModels(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public int deleteModels(YJWYTimerModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYTimerModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYTimerModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYTimerModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.model.YJWYTimerDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTimerModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
