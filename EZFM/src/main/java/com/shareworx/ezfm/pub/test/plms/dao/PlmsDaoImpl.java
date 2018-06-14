package com.shareworx.ezfm.pub.test.plms.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 测试plms持久化操作实现
 *
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 *
 */
@Repository(PlmsDao.ID)
public class PlmsDaoImpl implements PlmsDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#saveModels(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public int[] saveModels(PlmsModel[] models) throws ShareworxServiceException {
		return dao.saveModels(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#updateModels(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public int[] updateModels(PlmsModel[] models) throws ShareworxServiceException {
		return dao.updateModels(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#updateModels(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(PlmsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(PlmsModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#deleteModels(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public int deleteModels(PlmsModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#queryById(java.io.Serializable)
	 */
	@Override
	public PlmsModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(PlmsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<PlmsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.model.PlmsDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PlmsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
