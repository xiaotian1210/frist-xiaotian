package com.shareworx.ezfm.problem.record.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 报事记录持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYProblemRecordDao.ID)
public class YJWYProblemRecordDaoImpl implements YJWYProblemRecordDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#saveModels(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public int[] saveModels(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#updateModels(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public int[] updateModels(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#updateModels(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYProblemRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYProblemRecordModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#deleteModels(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public int deleteModels(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYProblemRecordModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYProblemRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYProblemRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.model.YJWYProblemRecordDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProblemRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
