package com.shareworx.ezfm.worktask.record.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工单记录表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskDetailsRecordDao.ID)
public class YJWYWorkTaskDetailsRecordDaoImpl implements YJWYWorkTaskDetailsRecordDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#saveModels(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#updateModels(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#updateModels(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskDetailsRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskDetailsRecordModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#deleteModels(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskDetailsRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
