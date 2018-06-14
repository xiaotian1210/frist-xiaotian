package com.shareworx.ezfm.baseinfo.org.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 组织架构持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(DefaultOrgDao.ID)
public class DefaultOrgDaoImpl implements DefaultOrgDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#saveModels(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public int[] saveModels(DefaultOrgModel[] models) throws ShareworxServiceException {
		return dao.saveModels(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#updateModels(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public int[] updateModels(DefaultOrgModel[] models) throws ShareworxServiceException {
		return dao.updateModels(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#updateModels(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(DefaultOrgModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(DefaultOrgModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#deleteModels(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public int deleteModels(DefaultOrgModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#queryById(java.io.Serializable)
	 */
	@Override
	public DefaultOrgModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(DefaultOrgModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<DefaultOrgModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.model.DefaultOrgDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public DefaultOrgModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
