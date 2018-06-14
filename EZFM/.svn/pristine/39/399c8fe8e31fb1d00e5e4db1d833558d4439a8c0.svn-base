package com.shareworx.ezfm.performance.leave.service;

import java.util.List;
import java.util.Map;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.performance.leave.vo.YJWYLeaveModelVo;

/**
 * 休假备案领域操作接口
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYLeaveDomainService {

	
	String ID = "yJWYLeaveDomainService";
	
	/**
	 * 新增保存休假备案领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> save(YJWYLeaveModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存休假备案领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> update(YJWYLeaveModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存休假备案领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> update(List<String> editFields, YJWYLeaveModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除休假备案领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYLeaveModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除休假备案领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除休假备案领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询休假备案领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条休假备案领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYLeaveModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询休假备案领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载休假备案对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYLeaveModel> queryByExample(YJWYLeaveModel model) throws ShareworxServiceException;
	
	/**
	 * 判断用户是否在休假
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	boolean queryLeave(String id) throws ShareworxServiceException;
	
	/**
	 * 根据 页面传过来的参数获取 list
	 * @param leaveModelVo
	 * @return
	 */
	List<Map<String, Object>> queryMap(YJWYLeaveModelVo leaveModelVo);
	
	/**
	 * 任务数量查询
	 * @param params
	 * @return
	 */
	Long queryTaskCount(YJWYLeaveModelVo leaveModelVo);
}
