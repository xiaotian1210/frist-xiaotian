package com.shareworx.ezfm.worktask.assistuser.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工单协助人表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskAssistUserRecordDao.ID)
public class YJWYWorkTaskAssistUserRecordDaoImpl implements YJWYWorkTaskAssistUserRecordDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#saveModels(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#updateModels(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#updateModels(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAssistUserRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAssistUserRecordModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#deleteModels(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskAssistUserRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
