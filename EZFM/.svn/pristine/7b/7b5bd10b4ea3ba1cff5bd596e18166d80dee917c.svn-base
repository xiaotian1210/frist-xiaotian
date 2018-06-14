package com.shareworx.ezfm.quality.proinspect.inspect.probtype.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 问题类型持久化操作实现
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
@Repository(ProblemTypeDao.ID)
public class ProblemTypeDaoImpl implements ProblemTypeDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#saveModels(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public int[] saveModels(ProblemTypeModel[] models) throws ShareworxServiceException {
		return dao.saveModels(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public int[] updateModels(ProblemTypeModel[] models) throws ShareworxServiceException {
		return dao.updateModels(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#updateModels(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(ProblemTypeModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(ProblemTypeModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#deleteModels(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public int deleteModels(ProblemTypeModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#queryById(java.io.Serializable)
	 */
	@Override
	public ProblemTypeModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(ProblemTypeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<ProblemTypeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemTypeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
