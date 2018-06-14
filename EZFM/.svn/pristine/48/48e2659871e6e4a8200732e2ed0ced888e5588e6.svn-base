package com.shareworx.ezfm.performance.sign.service;

import java.util.List;
import java.util.Map;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.ezfm.performance.sign.vo.YJWYSignModelVo;

/**
 * 签到管理领域操作接口
 * @author lingwei.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYSignDomainService {

	
	String ID = "yJWYSignDomainService";
	
	/**
	 * 新增保存签到管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> save(YJWYSignModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存签到管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> update(YJWYSignModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存签到管理领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> update(List<String> editFields, YJWYSignModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除签到管理领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYSignModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除签到管理领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除签到管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询签到管理领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条签到管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYSignModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询签到管理领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载签到管理对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYSignModel> queryByExample(YJWYSignModel model) throws ShareworxServiceException;
	
	/**
	 * 根据 页面传过来的参数获取 list
	 * @param leaveModelVo
	 * @return
	 */
	List<Map<String, Object>> queryMap(YJWYSignModelVo signModelVo);
	
	/**
	 * 任务数量查询
	 * @param params
	 * @return
	 */
	Long queryTaskCount(YJWYSignModelVo signModelVo);
}
