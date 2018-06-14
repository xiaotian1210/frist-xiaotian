package com.shareworx.ezfm.worktask.classpro.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 种类项目关联持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskRepairClassProjectDao.ID)
public class YJWYWorkTaskRepairClassProjectDaoImpl implements YJWYWorkTaskRepairClassProjectDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#saveModels(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#updateModels(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#updateModels(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskRepairClassProjectModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskRepairClassProjectModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#deleteModels(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskRepairClassProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
