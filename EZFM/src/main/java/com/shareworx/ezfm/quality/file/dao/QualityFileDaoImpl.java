package com.shareworx.ezfm.quality.file.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 品质核查持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(QualityFileDao.ID)
public class QualityFileDaoImpl implements QualityFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#saveModels(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public int[] saveModels(QualityFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#updateModels(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public int[] updateModels(QualityFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#updateModels(com.shareworx.ezfm.quality.file.model.QualityFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(QualityFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(QualityFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#deleteModels(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public int deleteModels(QualityFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public QualityFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(QualityFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<QualityFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.model.QualityFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public QualityFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
