package com.shareworx.ezfm.problem.classadmin.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 测试用的持久化操作接口
 *
 * @author jiangwei.peng
 * @version since Shareworx platform 2.0
 *
 */
public interface yjwyjwtest_csDao {

	String ID = "yjwyjwtest_csDao";
	
	/**
	 * 保存测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改测试用的
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(yjwyjwtest_csModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改测试用的
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除测试用的
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(yjwyjwtest_csModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除测试用的
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询测试用的
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询测试用的
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<yjwyjwtest_csModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询测试用的条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询测试用的
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	yjwyjwtest_csModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
