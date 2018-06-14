package com.shareworx.ezfm.device.patrol.record.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 巡检维保附件表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYTaskFileDao.ID)
public class YJWYTaskFileDaoImpl implements YJWYTaskFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#saveModels(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public int[] saveModels(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#updateModels(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public int[] updateModels(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#updateModels(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYTaskFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#deleteModels(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public int deleteModels(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYTaskFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYTaskFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYTaskFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
