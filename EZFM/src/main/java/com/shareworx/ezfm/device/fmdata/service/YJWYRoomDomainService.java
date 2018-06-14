package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 房间位置信息领域操作接口
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYRoomDomainService {

	
	String ID = "yJWYRoomDomainService";
	
	/**
	 * 新增保存房间位置信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> save(YJWYRoomModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存房间位置信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> update(YJWYRoomModel... models) throws ShareworxServiceException;
	
	/**
	 * 修改保存房间位置信息领域对象
	 * @param editFields {@link MetaField#getId()} 编辑字段
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> update(List<String> editFields, YJWYRoomModel... models) throws ShareworxServiceException;
	
	/**
	 * 删除房间位置信息领域对象
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int delete(YJWYRoomModel... models) throws ShareworxServiceException;
	
	/**
	 * 根据主键删除房间位置信息领域对象
	 * @param metaName
	 * @param ids
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByIds(String[] ids) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除房间位置信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByConditions(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询房间位置信息领域对象
	 * @param metaName
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel queryById(String id) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询一条房间位置信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYRoomModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询房间位置信息领域对象
	 * @param metaName
	 * @param conds
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载房间位置信息对象
	 * @param model
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<YJWYRoomModel> queryByExample(YJWYRoomModel model) throws ShareworxServiceException;
}
