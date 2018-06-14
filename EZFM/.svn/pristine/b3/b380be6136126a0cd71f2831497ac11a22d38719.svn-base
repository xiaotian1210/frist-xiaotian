package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 资源属性与资源关联持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYResourceAttributePkResourceDao.ID)
public class YJWYResourceAttributePkResourceDaoImpl implements YJWYResourceAttributePkResourceDao {
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getWriteTemplate();
	
	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#saveModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public int[] saveModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public int[] updateModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYResourceAttributePkResourceModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYResourceAttributePkResourceModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public int deleteModels(YJWYResourceAttributePkResourceModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYResourceAttributePkResourceModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

	@Override
	public int excuteUpdateSql(String sql) {
		return readJdbcTemplate.update(sql);
	}

}
