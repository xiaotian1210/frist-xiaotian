package com.shareworx.ezfm.problem.file.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 报事报修文件存储持久化操作实现
 *
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 *
 */
@Repository(ProblemFileDao.ID)
public class ProblemFileDaoImpl implements ProblemFileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#saveModels(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public int[] saveModels(ProblemFileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#updateModels(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public int[] updateModels(ProblemFileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#updateModels(com.shareworx.ezfm.problem.file.model.ProblemFileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(ProblemFileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(ProblemFileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#deleteModels(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public int deleteModels(ProblemFileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#queryById(java.io.Serializable)
	 */
	@Override
	public ProblemFileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(ProblemFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<ProblemFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.model.ProblemFileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
