package com.shareworx.ezfm.problem.nexus.proandclass.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 项目与报事类型关系表持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYProjectInfoClassNexusDao.ID)
public class YJWYProjectInfoClassNexusDaoImpl implements YJWYProjectInfoClassNexusDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#saveModels(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public int[] saveModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#updateModels(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public int[] updateModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#updateModels(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYProjectInfoClassNexusModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYProjectInfoClassNexusModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#deleteModels(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public int deleteModels(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYProjectInfoClassNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
