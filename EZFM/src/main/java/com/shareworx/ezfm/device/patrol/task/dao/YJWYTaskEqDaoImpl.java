package com.shareworx.ezfm.device.patrol.task.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 巡检维保任务表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYTaskEqDao.ID)
public class YJWYTaskEqDaoImpl implements YJWYTaskEqDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#saveModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public int[] saveModels(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#updateModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public int[] updateModels(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#updateModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYTaskEqModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskEqModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#deleteModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public int deleteModels(YJWYTaskEqModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYTaskEqModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYTaskEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYTaskEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
