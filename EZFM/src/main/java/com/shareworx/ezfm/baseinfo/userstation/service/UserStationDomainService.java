package com.shareworx.ezfm.baseinfo.userstation.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 人员岗位关系领域操作接口
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
public interface UserStationDomainService {

	
	String ID = "userStationDomainService";
	
	/**
	 * 新增保存人员岗位关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> save(UserStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存人员岗位关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> update(UserStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存人员岗位关系领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> update(List<String> editFields, UserStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除人员岗位关系领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(UserStationModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除人员岗位关系领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除人员岗位关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询人员岗位关系领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条人员岗位关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	UserStationModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询人员岗位关系领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载人员岗位关系对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<UserStationModel> queryByExample(UserStationModel model) throws ShareworxServiceException;
}
