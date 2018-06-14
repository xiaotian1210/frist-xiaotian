package com.shareworx.ezfm.saas.payment.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 缴费持久化操作实现
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
@Repository(PaymentDao.ID)
public class PaymentDaoImpl implements PaymentDao {

	public final static String ID = "demoMainEntityDao";
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#saveModels(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public int[] saveModels(PaymentModel[] models) throws ShareworxServiceException {
		return dao.saveModels(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#updateModels(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public int[] updateModels(PaymentModel[] models) throws ShareworxServiceException {
		return dao.updateModels(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#updateModels(com.shareworx.ezfm.saas.payment.model.PaymentModel[], java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(PaymentModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException {
		return dao.updateModels(PaymentModel.META_ID, models, include, notIclude);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#updateModelsByCondition(com.shareworx.platform.persist.Update)
	 */
	@Override
	public int updateModelsByCondition(Update update) throws ShareworxServiceException {
		return dao.updateModelsByCondition(update);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#deleteModels(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public int deleteModels(PaymentModel[] models) throws ShareworxServiceException {
		return dao.deleteModels(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#deleteByCondition(com.shareworx.platform.persist.Delete)
	 */
	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#queryById(java.io.Serializable)
	 */
	@Override
	public PaymentModel queryById(Serializable id) throws ShareworxServiceException {
		return dao.queryById(PaymentModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#queryListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<PaymentModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return dao.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#countListByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public Long countListByCondition(Query query) throws ShareworxServiceException {
		return dao.countListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.model.PaymentDao#queryOneByCondition(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PaymentModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return dao.queryOneByCondition(query);
	}

}
