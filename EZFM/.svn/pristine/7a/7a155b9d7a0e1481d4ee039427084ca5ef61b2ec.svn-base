package com.shareworx.ezfm.quality.proinspect.inspect.standard.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 核查标准持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(InspectStandardDao.ID)
public class InspectStandardDaoImpl implements InspectStandardDao {

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
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public int[] saveModels(InspectStandardModel[] models) throws ShareworxServiceException {
		return dao.saveModels(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public int[] updateModels(InspectStandardModel[] models) throws ShareworxServiceException {
		return dao.updateModels(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(InspectStandardModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(InspectStandardModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public int deleteModels(InspectStandardModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#queryById(java.io.Serializable)
	 */
	@Override
	public InspectStandardModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(InspectStandardModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<InspectStandardModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectStandardModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

	@Override
	public List<Map<String, Object>> queryMap(String sql) {
		
		return readTemplate.queryForList(sql);
	}

	@Override
	public Long queryCount(String sql) {
		
		return readTemplate.queryForObject(sql, Long.class);
	}

}
