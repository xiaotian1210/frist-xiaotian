package com.shareworx.ezfm.problem.details.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 报事主类持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYProblemDetailsDao.ID)
public class YJWYProblemDetailsDaoImpl implements YJWYProblemDetailsDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#saveModels(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public int[] saveModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#updateModels(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public int[] updateModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#updateModels(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYProblemDetailsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYProblemDetailsModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#deleteModels(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public int deleteModels(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYProblemDetailsModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYProblemDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYProblemDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProblemDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
