package com.shareworx.ezfm.system.dict.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 数据字典持久化操作实现
 *
 * @author ying.chen
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYDictDao.ID)
public class YJWYDictDaoImpl implements YJWYDictDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#saveModels(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	public int[] saveModels(YJWYDictModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#updateModels(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	public int[] updateModels(YJWYDictModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#updateModels(com.shareworx.ezfm.system.dict.model.YJWYDictModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYDictModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYDictModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#deleteModels(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	public int deleteModels(YJWYDictModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYDictModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYDictModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYDictModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.model.YJWYDictDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYDictModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
