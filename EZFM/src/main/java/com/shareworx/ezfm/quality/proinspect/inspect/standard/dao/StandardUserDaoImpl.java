package com.shareworx.ezfm.quality.proinspect.inspect.standard.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 核查标准人员中间表持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(StandardUserDao.ID)
public class StandardUserDaoImpl implements StandardUserDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public int[] saveModels(StandardUserModel[] models) throws ShareworxServiceException {
		return dao.saveModels(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public int[] updateModels(StandardUserModel[] models) throws ShareworxServiceException {
		return dao.updateModels(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(StandardUserModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(StandardUserModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public int deleteModels(StandardUserModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#queryById(java.io.Serializable)
	 */
	@Override
	public StandardUserModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(StandardUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<StandardUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public StandardUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
