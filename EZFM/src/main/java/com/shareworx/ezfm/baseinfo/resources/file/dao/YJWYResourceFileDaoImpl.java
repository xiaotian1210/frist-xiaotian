package com.shareworx.ezfm.baseinfo.resources.file.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 资源附件持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYResourceFileDao.ID)
public class YJWYResourceFileDaoImpl implements YJWYResourceFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#saveModels(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public int[] saveModels(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#updateModels(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public int[] updateModels(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#updateModels(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYResourceFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourceFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public int deleteModels(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYResourceFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYResourceFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYResourceFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourceFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
