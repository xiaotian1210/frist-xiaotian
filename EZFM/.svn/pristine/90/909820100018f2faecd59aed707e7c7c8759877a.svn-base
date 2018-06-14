package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.dao;

import java.io.Serializable;
import java.util.List;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;

/**
 * 标准版本持久化操作接口
 *
 * @author dms
 * @version since Shareworx platform 2.0
 *
 */
public interface StandardEditionDao {

	String ID = "standardEditionDao";
	
	/**
	 * 保存标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] saveModels(StandardEditionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardEditionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改标准版本
	 * @param models
	 * @param include {@link MetaField#getId()} 如果为空时默认全部有效，非空时根据 notIclude 判断是否有效
	 * @param notIclude {@link MetaField#getId()} 优先级高，当存在时不被修改
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(StandardEditionModel[] models, String[] include, String[] notIclude) throws ShareworxServiceException;
	
	/**
	 * 根据条件修改标准版本
	 * @param update
	 * @return
	 * @throws ShareworxServiceException
	 */
	int updateModelsByCondition(Update update) throws ShareworxServiceException;
	
	/**
	 * 删除标准版本
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteModels(StandardEditionModel[] models) throws ShareworxServiceException;
	
	/**
	 * 根据条件删除标准版本
	 * @param delete
	 * @return
	 * @throws ShareworxServiceException
	 */
	int deleteByCondition(Delete delete) throws ShareworxServiceException;
	
	/**
	 * 根据主键查询标准版本
	 * @param id
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel queryById(Serializable id) throws ShareworxServiceException;

	/**
	 * 根据条件查询标准版本
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	List<StandardEditionModel> queryListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询标准版本条数
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	Long countListByCondition(Query query) throws ShareworxServiceException;
	
	/**
	 * 根据条件查询标准版本
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	StandardEditionModel queryOneByCondition(Query query) throws ShareworxServiceException;
	
}
