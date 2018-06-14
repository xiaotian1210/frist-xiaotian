package com.shareworx.ezfm.device.fmdata.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 房间位置信息持久化操作实现
 *
 * @author lihui
 * @version since Shareworx platform 2.0
 *
 */
@Repository(YJWYRoomDao.ID)
public class YJWYRoomDaoImpl implements YJWYRoomDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#saveModels(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public int[] saveModels(YJWYRoomModel[] models) throws ShareworxServiceException {
		return dao.saveModels(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public int[] updateModels(YJWYRoomModel[] models) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#updateModels(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(YJWYRoomModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(YJWYRoomModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#deleteModels(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public int deleteModels(YJWYRoomModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#queryById(java.io.Serializable)
	 */
	@Override
	public YJWYRoomModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(YJWYRoomModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<YJWYRoomModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.model.YJWYRoomDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYRoomModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
