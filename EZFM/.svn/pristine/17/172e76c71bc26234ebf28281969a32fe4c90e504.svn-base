package com.shareworx.ezfm.worktask.sonclass.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工单维修种类记录表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskSonClassRecordDao.ID)
public class YJWYWorkTaskSonClassRecordDaoImpl implements YJWYWorkTaskSonClassRecordDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#saveModels(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#updateModels(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#updateModels(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskSonClassRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskSonClassRecordModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#deleteModels(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskSonClassRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
