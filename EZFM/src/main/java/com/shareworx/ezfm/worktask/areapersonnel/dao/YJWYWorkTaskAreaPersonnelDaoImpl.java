package com.shareworx.ezfm.worktask.areapersonnel.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 片区维修人员持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskAreaPersonnelDao.ID)
public class YJWYWorkTaskAreaPersonnelDaoImpl implements YJWYWorkTaskAreaPersonnelDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#saveModels(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#updateModels(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#updateModels(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaPersonnelModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaPersonnelModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#deleteModels(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskAreaPersonnelModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
