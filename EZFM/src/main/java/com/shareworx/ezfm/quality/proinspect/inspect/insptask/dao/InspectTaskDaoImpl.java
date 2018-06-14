package com.shareworx.ezfm.quality.proinspect.inspect.insptask.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 核查与整改任务持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(InspectTaskDao.ID)
public class InspectTaskDaoImpl implements InspectTaskDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public int[] saveModels(InspectTaskModel[] models) throws ShareworxServiceException {
		return dao.saveModels(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public int[] updateModels(InspectTaskModel[] models) throws ShareworxServiceException {
		return dao.updateModels(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(InspectTaskModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(InspectTaskModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public int deleteModels(InspectTaskModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#queryById(java.io.Serializable)
	 */
	@Override
	public InspectTaskModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(InspectTaskModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<InspectTaskModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectTaskModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
