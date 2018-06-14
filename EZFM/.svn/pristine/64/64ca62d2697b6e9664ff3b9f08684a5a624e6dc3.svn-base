package com.shareworx.ezfm.system.function.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 系统功能持久化操作实现
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYFunctionDao.ID)
public class YJWYFunctionDaoImpl implements YJWYFunctionDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#saveModels(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public int[] saveModels(YJWYFunctionModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#updateModels(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public int[] updateModels(YJWYFunctionModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#updateModels(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYFunctionModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYFunctionModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#deleteModels(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public int deleteModels(YJWYFunctionModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYFunctionModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYFunctionModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYFunctionModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.model.YJWYFunctionDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYFunctionModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
