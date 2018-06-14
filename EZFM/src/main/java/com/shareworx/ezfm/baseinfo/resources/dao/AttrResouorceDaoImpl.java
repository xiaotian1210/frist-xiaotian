package com.shareworx.ezfm.baseinfo.resources.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 资源_属性持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(AttrResouorceDao.ID)
public class AttrResouorceDaoImpl implements AttrResouorceDao {

	public final static String ID = "demoMainEntityDao";
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getWriteTemplate();
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#saveModels(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public int[] saveModels(AttrResouorceModel[] models) throws ShareworxServiceException {
		return dao.saveModels(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public int[] updateModels(AttrResouorceModel[] models) throws ShareworxServiceException {
		return dao.updateModels(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#updateModels(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(AttrResouorceModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(AttrResouorceModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#deleteModels(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public int deleteModels(AttrResouorceModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#queryById(java.io.Serializable)
	 */
	@Override
	public AttrResouorceModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(AttrResouorceModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<AttrResouorceModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.model.attrResouorceDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public AttrResouorceModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

	@Override
	public int excuteUpdateSql(String sql) throws ShareworxServiceException {
		return readJdbcTemplate.update(sql);
	}

}
