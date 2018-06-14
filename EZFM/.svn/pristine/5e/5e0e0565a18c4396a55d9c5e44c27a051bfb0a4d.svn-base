package com.shareworx.ezfm.saas.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * PaymentModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(PaymentDomainService.ID)
public class PaymentDomainServiceImpl implements PaymentDomainService {

	public final static String ID = "paymentDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#save(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public List<PaymentModel> save(PaymentModel... models) throws ShareworxServiceException {
		return service.save(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#update(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public List<PaymentModel> update(PaymentModel... models) throws ShareworxServiceException {
		return service.update(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#update(java.util.List, com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public List<PaymentModel> update(List<String> editFields, PaymentModel... models) throws ShareworxServiceException {
		return service.update(PaymentModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#delete(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public int delete(PaymentModel... models) throws ShareworxServiceException {
		return service.delete(PaymentModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(PaymentModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#queryById(java.lang.String)
	 */
	@Override
	public PaymentModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(PaymentModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public PaymentModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<PaymentModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentDomainService#queryByExample(com.shareworx.ezfm.saas.payment.model.PaymentModel)
	 */
	@Override
	public List<PaymentModel> queryByExample(PaymentModel model) throws ShareworxServiceException {
		return service.queryByExample(PaymentModel.META_ID, model);
	}

}
