package com.shareworx.ezfm.saas.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 缴费业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(PaymentBusinessService.ID)
public class PaymentBusinessServiceImpl implements PaymentBusinessService {

	@Autowired
	@Qualifier(PaymentDomainService.ID)
	private PaymentDomainService domainService;
	
	public void setDomainService(PaymentDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PaymentModel[] query(Query query) throws ShareworxServiceException {
		List<PaymentModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new PaymentModel[0];
		}
		return list.toArray(new PaymentModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentBusinessService#load(com.shareworx.ezfm.saas.payment.model.PaymentModel)
	 */
	@Override
	public PaymentModel[] load(PaymentModel model) throws ShareworxServiceException {
		List<PaymentModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new PaymentModel[0];
		}
		return list.toArray(new PaymentModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentBusinessService#save(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public PaymentModel[] save(PaymentModel[] models) throws ShareworxServiceException {
		List<PaymentModel> list = domainService.save(models);
		return list.toArray(new PaymentModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentBusinessService#update(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public PaymentModel[] update(PaymentModel[] models) throws ShareworxServiceException {
		List<PaymentModel> list = domainService.update(models);
		return list.toArray(new PaymentModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.saas.payment.service.PaymentBusinessService#delete(com.shareworx.ezfm.saas.payment.model.PaymentModel[])
	 */
	@Override
	public PaymentModel[] delete(PaymentModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
