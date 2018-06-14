package com.shareworx.ezfm.device.siteproject.dao;

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
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * FM与YJWY项目关联表持久化操作实现
 *
 * @author jin.li
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYSiteProjectDao.ID)
public class YJWYSiteProjectDaoImpl implements YJWYSiteProjectDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	
	/**
	 * 查询FM项目id集合
	 */
	@Override
	public List<String> queryIds(String sql) {
		return readTemplate.queryForList(sql, String.class);
	}

	/**
	 * 项目关联数据查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		return readTemplate.queryForList(sql);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public int[] saveModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public int[] updateModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYSiteProjectModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYSiteProjectModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public int deleteModels(YJWYSiteProjectModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYSiteProjectModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYSiteProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYSiteProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYSiteProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}


}
