package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYPlanEqModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPlanEqDomainService.ID)
public class YJWYPlanEqDomainServiceImpl implements YJWYPlanEqDomainService {

	public final static String ID = "yJWYPlanEqDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#save(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public List<YJWYPlanEqModel> save(YJWYPlanEqModel... models) throws ShareworxServiceException {
		return service.save(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#update(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public List<YJWYPlanEqModel> update(YJWYPlanEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#update(java.util.List, com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public List<YJWYPlanEqModel> update(List<String> editFields, YJWYPlanEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYPlanEqModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#delete(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel[])
	 */
	@Override
	public int delete(YJWYPlanEqModel... models) throws ShareworxServiceException {
		return service.delete(YJWYPlanEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYPlanEqModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYPlanEqModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYPlanEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYPlanEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYPlanEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanEqDomainService#queryByExample(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel)
	 */
	@Override
	public List<YJWYPlanEqModel> queryByExample(YJWYPlanEqModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYPlanEqModel.META_ID, model);
	}

}
