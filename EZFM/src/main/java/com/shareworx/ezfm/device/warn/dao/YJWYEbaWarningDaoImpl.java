package com.shareworx.ezfm.device.warn.dao;

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
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;

/**
 * eba设备报警信息持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYEbaWarningDao.ID)
public class YJWYEbaWarningDaoImpl implements YJWYEbaWarningDao {

	public final static String ID = "demoMainEntityDao";
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/**
	 * 设备列表查询
	 */
	public List<Map<String, Object>> queryList(String sql) {
		return readJdbcTemplate.queryForList(sql);
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return readJdbcTemplate.queryForObject(sql, Long.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#saveModels(com.
	 * shareworx.yjwy.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public int[] saveModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#updateModels(com.
	 * shareworx.yjwy.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public int[] updateModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#updateModels(com.
	 * shareworx.yjwy.device.warn.model.YJWYEbaWarningModel[],
	 * java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYEbaWarningModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYEbaWarningModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#
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
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#deleteModels(com.
	 * shareworx.yjwy.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public int deleteModels(YJWYEbaWarningModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#deleteByCondition(
	 * com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#queryById(java.io.
	 * Serializable)
	 */
	@Override
	public YJWYEbaWarningModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYEbaWarningModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#
	 * queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYEbaWarningModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#
	 * countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.model.YJWYEbaWarningDao#
	 * queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEbaWarningModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
