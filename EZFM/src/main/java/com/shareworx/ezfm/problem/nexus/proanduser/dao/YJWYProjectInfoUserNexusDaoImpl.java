package com.shareworx.ezfm.problem.nexus.proanduser.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 接口人员关系表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYProjectInfoUserNexusDao.ID)
public class YJWYProjectInfoUserNexusDaoImpl implements YJWYProjectInfoUserNexusDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#saveModels(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public int[] saveModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#updateModels(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public int[] updateModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#updateModels(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYProjectInfoUserNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectInfoUserNexusModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#deleteModels(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public int deleteModels(YJWYProjectInfoUserNexusModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYProjectInfoUserNexusModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYProjectInfoUserNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectInfoUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
