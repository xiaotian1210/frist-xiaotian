package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 基础属性表持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYBaseAttributeDao.ID)
public class YJWYBaseAttributeDaoImpl implements YJWYBaseAttributeDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#saveModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public int[] saveModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public int[] updateModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYBaseAttributeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYBaseAttributeModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public int deleteModels(YJWYBaseAttributeModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYBaseAttributeModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYBaseAttributeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYBaseAttributeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYBaseAttributeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
