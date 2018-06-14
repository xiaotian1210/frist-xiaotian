package com.shareworx.ezfm.baseinfo.userstation.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 人员岗位关系持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(UserStationDao.ID)
public class UserStationDaoImpl implements UserStationDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#saveModels(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public int[] saveModels(UserStationModel[] models) throws ShareworxServiceException {
		return dao.saveModels(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#updateModels(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public int[] updateModels(UserStationModel[] models) throws ShareworxServiceException {
		return dao.updateModels(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#updateModels(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(UserStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(UserStationModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#deleteModels(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public int deleteModels(UserStationModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#queryById(java.io.Serializable)
	 */
	@Override
	public UserStationModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(UserStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<UserStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.model.UserStationDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public UserStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
