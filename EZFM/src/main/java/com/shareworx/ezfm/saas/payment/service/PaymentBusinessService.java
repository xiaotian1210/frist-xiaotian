package com.shareworx.ezfm.saas.payment.service;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 缴费业务操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface PaymentBusinessService extends BusinessService<PaymentModel> {

	String ID = "paymentBusinessService";
	
	/**
	 * 查询缴费
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载缴费
	 */
	PaymentModel[] load(PaymentModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel[] save(PaymentModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel[] update(PaymentModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel[] delete(PaymentModel[] models) throws ShareworxServiceException;
}
