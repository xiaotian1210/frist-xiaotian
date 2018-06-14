package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * InspectRecordModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectRecordDomainService.ID)
public class InspectRecordDomainServiceImpl implements InspectRecordDomainService {

	public final static String ID = "inspectRecordDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public List<InspectRecordModel> save(InspectRecordModel... models) throws ShareworxServiceException {
		return service.save(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public List<InspectRecordModel> update(InspectRecordModel... models) throws ShareworxServiceException {
		return service.update(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public List<InspectRecordModel> update(List<String> editFields, InspectRecordModel... models) throws ShareworxServiceException {
		return service.update(InspectRecordModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public int delete(InspectRecordModel... models) throws ShareworxServiceException {
		return service.delete(InspectRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(InspectRecordModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#queryById(java.lang.String)
	 */
	@Override
	public InspectRecordModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(InspectRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public InspectRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<InspectRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel)
	 */
	@Override
	public List<InspectRecordModel> queryByExample(InspectRecordModel model) throws ShareworxServiceException {
		return service.queryByExample(InspectRecordModel.META_ID, model);
	}

}
