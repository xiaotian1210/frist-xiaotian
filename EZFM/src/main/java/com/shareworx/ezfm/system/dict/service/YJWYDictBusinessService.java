package com.shareworx.ezfm.system.dict.service;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;

import java.util.Map;

import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 数据字典业务操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYDictBusinessService extends BusinessService<YJWYDictModel> {

	String ID = "yJWYDictBusinessService";
	
	/**
	 * 查询数据字典
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载数据字典
	 */
	YJWYDictModel[] load(YJWYDictModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] save(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 新增保存父级数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] saveParent(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] update(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除父级数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] delete(YJWYDictModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除子级数据字典
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] del(YJWYDictModel models) throws ShareworxServiceException;
	
	
	/**
	 * 通用方法
	 * @param code
	 * @return 
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] getDictByCode(String code) throws ShareworxServiceException;
	
	
	/**
	 * 通用方法
	 * @param code
	 * @return 
	 * @throws ShareworxServiceException
	 */
	YJWYDictModel[] getDictAndNameByCode(String code,String dictName) throws ShareworxServiceException;

	YJWYDictModel[] getDictByNameAndParentCode(String code,String dictName) throws ShareworxServiceException;
	
}
