package com.shareworx.ezfm.device.patrol.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYTaskModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskDomainService.ID)
public class YJWYTaskDomainServiceImpl implements YJWYTaskDomainService {

	public final static String ID = "yJWYTaskDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#save(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public List<YJWYTaskModel> save(YJWYTaskModel... models) throws ShareworxServiceException {
		return service.save(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#update(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public List<YJWYTaskModel> update(YJWYTaskModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#update(java.util.List, com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public List<YJWYTaskModel> update(List<String> editFields, YJWYTaskModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#delete(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel[])
	 */
	@Override
	public int delete(YJWYTaskModel... models) throws ShareworxServiceException {
		return service.delete(YJWYTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYTaskModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYTaskModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYTaskModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYTaskModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYTaskModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskDomainService#queryByExample(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskModel)
	 */
	@Override
	public List<YJWYTaskModel> queryByExample(YJWYTaskModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYTaskModel.META_ID, model);
	}

}
