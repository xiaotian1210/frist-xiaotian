package com.shareworx.ezfm.worktask.areaproject.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 片区项目关联持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskAreaProjectNexusDao.ID)
public class YJWYWorkTaskAreaProjectNexusDaoImpl implements YJWYWorkTaskAreaProjectNexusDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#saveModels(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#updateModels(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#updateModels(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskAreaProjectNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskAreaProjectNexusModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#deleteModels(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskAreaProjectNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
