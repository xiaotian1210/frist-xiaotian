package com.shareworx.ezfm.worktask.repairclass.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 维修种类持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYWorkTaskRepairClassDao.ID)
public class YJWYWorkTaskRepairClassDaoImpl implements YJWYWorkTaskRepairClassDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#saveModels(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public int[] saveModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#updateModels(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#updateModels(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYWorkTaskRepairClassModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYWorkTaskRepairClassModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#deleteModels(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public int deleteModels(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYWorkTaskRepairClassModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
