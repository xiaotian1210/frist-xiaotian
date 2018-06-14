package com.shareworx.ezfm.worktask.areauser.service;

import java.util.List;

import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 片区与人员关系表领域操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskAreaUserNexusDomainService {

	
	String ID = "yJWYWorkTaskAreaUserNexusDomainService";
	
	/**
	 * 新增保存片区与人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaUserNexusModel> save(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区与人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaUserNexusModel> update(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存片区与人员关系表领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaUserNexusModel> update(List<String> editFields, YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除片区与人员关系表领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除片区与人员关系表领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除片区与人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询片区与人员关系表领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条片区与人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskAreaUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询片区与人员关系表领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载片区与人员关系表对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYWorkTaskAreaUserNexusModel> queryByExample(YJWYWorkTaskAreaUserNexusModel model) throws ShareworxServiceException;
}
