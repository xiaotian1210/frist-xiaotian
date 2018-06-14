package com.shareworx.ezfm.performance.leave.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 休假备案持久化操作实现
 *
 * @author ying.chen
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYLeaveDao.ID)
public class YJWYLeaveDaoImpl implements YJWYLeaveDao {

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
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#saveModels(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public int[] saveModels(YJWYLeaveModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#updateModels(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public int[] updateModels(YJWYLeaveModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#updateModels(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYLeaveModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYLeaveModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#deleteModels(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public int deleteModels(YJWYLeaveModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYLeaveModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYLeaveModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYLeaveModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.model.YJWYLeaveDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYLeaveModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

	/**
	 * sql语句联合查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		return readTemplate.queryForList(sql);
	}

	/**
	 * 获取数据库条数
	 */
	@Override
	public Long queryTaskCount(String sql) {
		return readTemplate.queryForObject(sql, Long.class);
	}

}
