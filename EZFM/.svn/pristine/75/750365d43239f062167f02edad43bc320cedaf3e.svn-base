package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 标准版本领域操作接口
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
public interface StandardEditionDomainService {

	
	String ID = "standardEditionDomainService";
	
	/**
	 * 新增保存标准版本领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> save(StandardEditionModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准版本领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> update(StandardEditionModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存标准版本领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> update(List<String> editFields, StandardEditionModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除标准版本领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(StandardEditionModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除标准版本领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除标准版本领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询标准版本领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条标准版本领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询标准版本领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载标准版本对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> queryByExample(StandardEditionModel model) throws ShareworxServiceException;
}
