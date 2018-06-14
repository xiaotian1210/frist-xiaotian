package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 工艺程序步骤子表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYPmpsDao.ID)
public class YJWYPmpsDaoImpl implements YJWYPmpsDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public int[] saveModels(YJWYPmpsModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public int[] updateModels(YJWYPmpsModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYPmpsModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYPmpsModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public int deleteModels(YJWYPmpsModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYPmpsModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYPmpsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYPmpsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYPmpsDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPmpsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
