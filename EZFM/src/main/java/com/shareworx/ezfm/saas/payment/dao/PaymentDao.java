package com.shareworx.ezfm.saas.payment.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 缴费持久化操作接口
 *
 * @author Administrator
 * @version since Shareworx platform 2.0
 *
 */
public interface PaymentDao {

	String ID = "paymentDao";
	
	/**
	 * 保存缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(PaymentModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PaymentModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改缴费
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(PaymentModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改缴费
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除缴费
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(PaymentModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除缴费
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询缴费
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询缴费
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<PaymentModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询缴费条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询缴费
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	PaymentModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
