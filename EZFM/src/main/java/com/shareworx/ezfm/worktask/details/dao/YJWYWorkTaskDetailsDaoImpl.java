package com.shareworx.ezfm.worktask.details.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工单详情表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskDetailsDao.ID)
public class YJWYWorkTaskDetailsDaoImpl implements YJWYWorkTaskDetailsDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#saveModels(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#updateModels(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#updateModels(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskDetailsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskDetailsModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#deleteModels(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskDetailsModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
