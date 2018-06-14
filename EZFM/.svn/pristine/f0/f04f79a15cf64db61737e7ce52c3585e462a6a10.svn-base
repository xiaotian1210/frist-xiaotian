package com.shareworx.ezfm.performance.sign.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;

/**
 * 签到管理持久化操作实现
 *
 * @author lingwei.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYSignDao.ID)
public class YJWYSignDaoImpl implements YJWYSignDao {

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
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#saveModels(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public int[] saveModels(YJWYSignModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#updateModels(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public int[] updateModels(YJWYSignModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#updateModels(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYSignModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYSignModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#deleteModels(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public int deleteModels(YJWYSignModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYSignModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYSignModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYSignModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.model.YJWYSignDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSignModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		return readTemplate.queryForList(sql);
	}

	@Override
	public Long queryTaskCount(String sql) {
		return readTemplate.queryForObject(sql, Long.class);
	}

}
