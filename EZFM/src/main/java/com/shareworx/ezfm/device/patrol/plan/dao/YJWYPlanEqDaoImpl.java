package com.shareworx.ezfm.device.patrol.plan.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;

/**
 * 计划设备中间表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYPlanEqDao.ID)
public class YJWYPlanEqDaoImpl implements YJWYPlanEqDao {

	public final static String ID = "demoMainEntityDao";

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#saveModels(com.
	 * shareworx.yjwy.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public int[] saveModels(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#updateModels(
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public int[] updateModels(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#updateModels(
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[],
	 * java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYPlanEqModel[] models, String[] include, String[] notIclude)
			throws ShareworxServiceException {
		return dao.updateModels(YJWYPlanEqModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#
	 * updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#deleteModels(
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public int deleteModels(YJWYPlanEqModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#
	 * deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#queryById(java.
	 * io.Serializable)
	 */
	@Override
	public YJWYPlanEqModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYPlanEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#
	 * queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYPlanEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#
	 * countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqDao#
	 * queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPlanEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}
}
