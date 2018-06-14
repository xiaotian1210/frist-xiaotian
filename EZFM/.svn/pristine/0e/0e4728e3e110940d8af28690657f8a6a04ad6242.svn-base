package com.shareworx.ezfm.quality.proinspect.inspect.insptask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * InspectTaskModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectTaskDomainService.ID)
public class InspectTaskDomainServiceImpl implements InspectTaskDomainService {

	public final static String ID = "inspectTaskDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public List<InspectTaskModel> save(InspectTaskModel... models) throws ShareworxServiceException {
		return service.save(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public List<InspectTaskModel> update(InspectTaskModel... models) throws ShareworxServiceException {
		return service.update(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public List<InspectTaskModel> update(List<String> editFields, InspectTaskModel... models) throws ShareworxServiceException {
		return service.update(InspectTaskModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public int delete(InspectTaskModel... models) throws ShareworxServiceException {
		return service.delete(InspectTaskModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(InspectTaskModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#queryById(java.lang.String)
	 */
	@Override
	public InspectTaskModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(InspectTaskModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public InspectTaskModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<InspectTaskModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel)
	 */
	@Override
	public List<InspectTaskModel> queryByExample(InspectTaskModel model) throws ShareworxServiceException {
		return service.queryByExample(InspectTaskModel.META_ID, model);
	}

}
