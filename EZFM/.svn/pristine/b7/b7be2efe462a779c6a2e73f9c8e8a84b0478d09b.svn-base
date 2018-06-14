package com.shareworx.ezfm.device.basic.executor.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 巡检/维保人员分组持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYGroupDao.ID)
public class YJWYGroupDaoImpl implements YJWYGroupDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#saveModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public int[] saveModels(YJWYGroupModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#updateModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public int[] updateModels(YJWYGroupModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#updateModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYGroupModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYGroupModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#deleteModels(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public int deleteModels(YJWYGroupModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYGroupModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYGroupModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYGroupModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.model.YJWYGroupDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYGroupModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
