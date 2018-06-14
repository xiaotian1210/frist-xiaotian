package com.shareworx.ezfm.system.crop.pre.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 企业信息预采集实体持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(PreCropDao.ID)
public class PreCropDaoImpl implements PreCropDao {

	public final static String ID = "demoMainEntityDao";
	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#saveModels(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public int[] saveModels(PreCropModel[] models) throws ShareworxServiceException {
		return dao.saveModels(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#updateModels(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public int[] updateModels(PreCropModel[] models) throws ShareworxServiceException {
		return dao.updateModels(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#updateModels(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(PreCropModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(PreCropModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#deleteModels(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public int deleteModels(PreCropModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#queryById(java.io.Serializable)
	 */
	@Override
	public PreCropModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(PreCropModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<PreCropModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.model.PreCropDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PreCropModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}
	
	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		return readTemplate.queryForList(sql);
	}

}
