package com.shareworx.ezfm.worktask.areauser.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 片区与人员关系表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskAreaUserNexusDao.ID)
public class YJWYWorkTaskAreaUserNexusDaoImpl implements YJWYWorkTaskAreaUserNexusDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#saveModels(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#updateModels(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#updateModels(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaUserNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaUserNexusModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#deleteModels(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskAreaUserNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
