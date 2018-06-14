package com.shareworx.ezfm.saas.payment.service;

import java.util.List;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 缴费领域操作接口
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
public interface PaymentDomainService {

	
	String ID = "paymentDomainService";
	
	/**
	 * 新增保存缴费领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> save(PaymentModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存缴费领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> update(PaymentModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存缴费领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> update(List<String> editFields, PaymentModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除缴费领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(PaymentModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除缴费领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除缴费领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询缴费领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条缴费领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询缴费领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载缴费对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> queryByExample(PaymentModel model) throws ShareworxServiceException;
}
