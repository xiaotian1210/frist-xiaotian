package com.shareworx.ezfm.system.file.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 附件表持久化操作实现
 *
 * @author ying.chen
 * @version since Shareworx platform 2.0
 *
 */
@Repository(SystemFileDao.ID)
public class SystemFileDaoImpl implements SystemFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#saveModels(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public int[] saveModels(SystemFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#updateModels(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public int[] updateModels(SystemFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#updateModels(com.shareworx.ezfm.system.file.model.SystemFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(SystemFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(SystemFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#deleteModels(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public int deleteModels(SystemFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public SystemFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(SystemFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<SystemFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.model.SystemFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public SystemFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
