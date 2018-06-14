package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 核查与整改记录持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(InspectRecordDao.ID)
public class InspectRecordDaoImpl implements InspectRecordDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public int[] saveModels(InspectRecordModel[] models) throws ShareworxServiceException {
		return dao.saveModels(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public int[] updateModels(InspectRecordModel[] models) throws ShareworxServiceException {
		return dao.updateModels(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(InspectRecordModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(InspectRecordModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public int deleteModels(InspectRecordModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#queryById(java.io.Serializable)
	 */
	@Override
	public InspectRecordModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(InspectRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<InspectRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
