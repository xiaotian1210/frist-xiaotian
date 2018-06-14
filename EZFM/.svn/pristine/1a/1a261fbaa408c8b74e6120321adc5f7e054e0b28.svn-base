package com.shareworx.ezfm.device.patrol.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYTaskEqModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskEqDomainService.ID)
public class YJWYTaskEqDomainServiceImpl implements YJWYTaskEqDomainService {

	public final static String ID = "yJWYTaskEqDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#save(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public List<YJWYTaskEqModel> save(YJWYTaskEqModel... models) throws ShareworxServiceException {
		return service.save(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#update(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public List<YJWYTaskEqModel> update(YJWYTaskEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#update(java.util.List, com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public List<YJWYTaskEqModel> update(List<String> editFields, YJWYTaskEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskEqModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#delete(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel[])
	 */
	@Override
	public int delete(YJWYTaskEqModel... models) throws ShareworxServiceException {
		return service.delete(YJWYTaskEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYTaskEqModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYTaskEqModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYTaskEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYTaskEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYTaskEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqDomainService#queryByExample(com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel)
	 */
	@Override
	public List<YJWYTaskEqModel> queryByExample(YJWYTaskEqModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYTaskEqModel.META_ID, model);
	}

}
