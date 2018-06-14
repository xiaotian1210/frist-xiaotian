package com.shareworx.ezfm.device.patrol.task.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 巡检维保任务表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYTaskDao.ID)
public class YJWYTaskDaoImpl implements YJWYTaskDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/**
	 * sql语句联合查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		return readTemplate.queryForList(sql);
	}

	/**
	 * sql语句联合数据数量查询
	 */
	@Override
	public Long queryCount(String sql) {
		return readTemplate.queryForObject(sql, Long.class);
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#saveModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public int[] saveModels(YJWYTaskModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#updateModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public int[] updateModels(YJWYTaskModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#updateModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYTaskModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYTaskModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#deleteModels(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public int deleteModels(YJWYTaskModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYTaskModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYTaskModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYTaskModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.model.YJWYTaskDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
