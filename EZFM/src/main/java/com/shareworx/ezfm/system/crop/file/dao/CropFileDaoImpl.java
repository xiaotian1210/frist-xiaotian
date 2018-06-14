package com.shareworx.ezfm.system.crop.file.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 企业文件存储持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(CropFileDao.ID)
public class CropFileDaoImpl implements CropFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#saveModels(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public int[] saveModels(CropFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#updateModels(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public int[] updateModels(CropFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#updateModels(com.shareworx.ezfm.system.crop.file.model.CropFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(CropFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(CropFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#deleteModels(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public int deleteModels(CropFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public CropFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(CropFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<CropFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.model.CropFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public CropFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
