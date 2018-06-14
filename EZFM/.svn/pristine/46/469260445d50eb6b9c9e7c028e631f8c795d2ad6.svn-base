package com.shareworx.ezfm.device.patrol.plan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;

/**
 * 巡检维保计划持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYPlanDao.ID)
public class YJWYPlanDaoImpl implements YJWYPlanDao {

	public final static String ID = "demoMainEntityDao";

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	
	/**
	 * 根据sql查询models
	 */
	public List<YJWYPlanModel> queryModels(String sql) {
		return readTemplate.query(sql,new Object[]{}, new BeanPropertyRowMapper<YJWYPlanModel>(YJWYPlanModel.class));
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
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#saveModels(com.
	 * shareworx.yjwy.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public int[] saveModels(YJWYPlanModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#updateModels(com.
	 * shareworx.yjwy.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public int[] updateModels(YJWYPlanModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#updateModels(com.
	 * shareworx.yjwy.device.patrol.plan.model.YJWYPlanModel[],
	 * java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYPlanModel[] models, String[] include, String[] notIclude)
			throws ShareworxServiceException {
		return dao.updateModels(YJWYPlanModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#
	 * updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#deleteModels(com.
	 * shareworx.yjwy.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public int deleteModels(YJWYPlanModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#deleteByCondition
	 * (com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#queryById(java.io
	 * .Serializable)
	 */
	@Override
	public YJWYPlanModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYPlanModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#
	 * queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYPlanModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#
	 * countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanDao#
	 * queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYPlanModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}


}
