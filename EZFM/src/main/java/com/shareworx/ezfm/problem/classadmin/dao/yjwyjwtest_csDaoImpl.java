package com.shareworx.ezfm.problem.classadmin.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 测试用的持久化操作实现
 *
 * @author jiangwei.peng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(yjwyjwtest_csDao.ID)
public class yjwyjwtest_csDaoImpl implements yjwyjwtest_csDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#saveModels(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public int[] saveModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		return dao.saveModels(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#updateModels(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public int[] updateModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		return dao.updateModels(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#updateModels(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(yjwyjwtest_csModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(yjwyjwtest_csModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#deleteModels(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public int deleteModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#queryById(java.io.Serializable)
	 */
	@Override
	public yjwyjwtest_csModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(yjwyjwtest_csModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<yjwyjwtest_csModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public yjwyjwtest_csModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
