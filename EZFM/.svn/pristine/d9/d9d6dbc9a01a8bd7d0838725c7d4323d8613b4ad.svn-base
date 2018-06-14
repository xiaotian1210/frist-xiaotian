package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工艺程序主表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYPmpDao.ID)
public class YJWYPmpDaoImpl implements YJWYPmpDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public int[] saveModels(YJWYPmpModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public int[] updateModels(YJWYPmpModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYPmpModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYPmpModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public int deleteModels(YJWYPmpModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYPmpModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYPmpModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYPmpModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPmpModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
