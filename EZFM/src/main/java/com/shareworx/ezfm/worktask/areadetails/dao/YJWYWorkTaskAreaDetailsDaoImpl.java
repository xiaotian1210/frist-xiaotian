package com.shareworx.ezfm.worktask.areadetails.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 片区详情持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskAreaDetailsDao.ID)
public class YJWYWorkTaskAreaDetailsDaoImpl implements YJWYWorkTaskAreaDetailsDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#saveModels(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#updateModels(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#updateModels(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaDetailsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaDetailsModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#deleteModels(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskAreaDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
