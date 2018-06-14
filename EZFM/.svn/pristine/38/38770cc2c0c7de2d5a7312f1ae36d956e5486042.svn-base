package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 属性名称表持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(AttributeNameDao.ID)
public class AttributeNameDaoImpl implements AttributeNameDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#saveModels(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public int[] saveModels(AttributeNameModel[] models) throws ShareworxServiceException {
		return dao.saveModels(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public int[] updateModels(AttributeNameModel[] models) throws ShareworxServiceException {
		return dao.updateModels(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(AttributeNameModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(AttributeNameModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public int deleteModels(AttributeNameModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#queryById(java.io.Serializable)
	 */
	@Override
	public AttributeNameModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(AttributeNameModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<AttributeNameModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.AttributeNameDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public AttributeNameModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
