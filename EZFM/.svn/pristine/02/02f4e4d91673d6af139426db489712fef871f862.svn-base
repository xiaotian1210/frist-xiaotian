package com.shareworx.ezfm.problem.classadmin.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 报事分类管理持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(ProblemClassDao.ID)
public class ProblemClassDaoImpl implements ProblemClassDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#saveModels(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public int[] saveModels(ProblemClassModel[] models) throws ShareworxServiceException {
		return dao.saveModels(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#updateModels(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public int[] updateModels(ProblemClassModel[] models) throws ShareworxServiceException {
		return dao.updateModels(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#updateModels(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(ProblemClassModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(ProblemClassModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#deleteModels(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public int deleteModels(ProblemClassModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#queryById(java.io.Serializable)
	 */
	@Override
	public ProblemClassModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(ProblemClassModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<ProblemClassModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.ProblemClassDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemClassModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
