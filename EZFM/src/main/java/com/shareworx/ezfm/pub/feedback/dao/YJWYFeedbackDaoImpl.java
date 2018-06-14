package com.shareworx.ezfm.pub.feedback.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.pub.feedback.model.YJWYFeedbackModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * app的意见反馈持久化操作实现
 *
 * @author yuting.wang
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYFeedbackDao.ID)
public class YJWYFeedbackDaoImpl implements YJWYFeedbackDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#saveModels(com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackModel[])
	 */
	@Override
	public int[] saveModels(YJWYFeedbackModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYFeedbackModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#updateModels(com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackModel[])
	 */
	@Override
	public int[] updateModels(YJWYFeedbackModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYFeedbackModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#updateModels(com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYFeedbackModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYFeedbackModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#deleteModels(com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackModel[])
	 */
	@Override
	public int deleteModels(YJWYFeedbackModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYFeedbackModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYFeedbackModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYFeedbackModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYFeedbackModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.feedback.model.YJWYFeedbackDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYFeedbackModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
