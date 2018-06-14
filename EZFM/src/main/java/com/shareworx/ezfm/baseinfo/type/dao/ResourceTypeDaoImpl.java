package com.shareworx.ezfm.baseinfo.type.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 资源类型持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(ResourceTypeDao.ID)
public class ResourceTypeDaoImpl implements ResourceTypeDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#saveModels(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public int[] saveModels(ResourcetypeModel[] models) throws ShareworxServiceException {
		return dao.saveModels(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#updateModels(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public int[] updateModels(ResourcetypeModel[] models) throws ShareworxServiceException {
		return dao.updateModels(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#updateModels(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(ResourcetypeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(ResourcetypeModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#deleteModels(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public int deleteModels(ResourcetypeModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#queryById(java.io.Serializable)
	 */
	@Override
	public ResourcetypeModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(ResourcetypeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<ResourcetypeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.model.resourcetypeDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ResourcetypeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
