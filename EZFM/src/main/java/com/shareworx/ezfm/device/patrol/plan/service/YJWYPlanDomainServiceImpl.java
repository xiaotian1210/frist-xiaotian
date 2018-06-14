package com.shareworx.ezfm.device.patrol.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYPlanModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPlanDomainService.ID)
public class YJWYPlanDomainServiceImpl implements YJWYPlanDomainService {

	public final static String ID = "yJWYPlanDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#save(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public List<YJWYPlanModel> save(YJWYPlanModel... models) throws ShareworxServiceException {
		return service.save(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#update(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public List<YJWYPlanModel> update(YJWYPlanModel... models) throws ShareworxServiceException {
		return service.update(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#update(java.util.List, com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public List<YJWYPlanModel> update(List<String> editFields, YJWYPlanModel... models) throws ShareworxServiceException {
		return service.update(YJWYPlanModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#delete(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel[])
	 */
	@Override
	public int delete(YJWYPlanModel... models) throws ShareworxServiceException {
		return service.delete(YJWYPlanModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYPlanModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYPlanModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYPlanModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYPlanModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYPlanModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanDomainService#queryByExample(com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel)
	 */
	@Override
	public List<YJWYPlanModel> queryByExample(YJWYPlanModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYPlanModel.META_ID, model);
	}

}
