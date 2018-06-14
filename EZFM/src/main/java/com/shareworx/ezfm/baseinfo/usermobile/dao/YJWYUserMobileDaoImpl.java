package com.shareworx.ezfm.baseinfo.usermobile.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 用户与设备关系表持久化操作实现
 *
 * @author yuting.wang
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYUserMobileDao.ID)
public class YJWYUserMobileDaoImpl implements YJWYUserMobileDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#saveModels(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public int[] saveModels(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#updateModels(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public int[] updateModels(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#updateModels(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYUserMobileModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYUserMobileModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#deleteModels(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public int deleteModels(YJWYUserMobileModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYUserMobileModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYUserMobileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYUserMobileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserMobileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
