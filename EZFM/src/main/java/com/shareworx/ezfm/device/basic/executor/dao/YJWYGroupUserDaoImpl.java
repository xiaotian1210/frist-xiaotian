package com.shareworx.ezfm.device.basic.executor.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 分组和人员关系表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYGroupUserDao.ID)
public class YJWYGroupUserDaoImpl implements YJWYGroupUserDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#saveModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public int[] saveModels(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#updateModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public int[] updateModels(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#updateModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYGroupUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYGroupUserModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#deleteModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public int deleteModels(YJWYGroupUserModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYGroupUserModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYGroupUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYGroupUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYGroupUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
