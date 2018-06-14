package com.shareworx.ezfm.baseinfo.userstation.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 人员岗位关系持久化操作接口
 *
 * @author qiang.gu
 * @version since Shareworx platform 2.0
 *
 */
public interface UserStationDao {

	String ID = "userStationDao";
	
	/**
	 * 保存人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(UserStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(UserStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改人员岗位关系
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(UserStationModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改人员岗位关系
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除人员岗位关系
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(UserStationModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除人员岗位关系
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询人员岗位关系
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询人员岗位关系
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询人员岗位关系条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询人员岗位关系
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
