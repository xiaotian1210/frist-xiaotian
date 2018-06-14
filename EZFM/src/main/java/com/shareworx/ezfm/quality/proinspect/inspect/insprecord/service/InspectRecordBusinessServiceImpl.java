package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 核查与整改记录业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectRecordBusinessService.ID)
public class InspectRecordBusinessServiceImpl implements InspectRecordBusinessService {

	@Autowired
	@Qualifier(InspectRecordDomainService.ID)
	private InspectRecordDomainService domainService;
	
	public void setDomainService(InspectRecordDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectRecordModel[] query(Query query) throws ShareworxServiceException {
		List<InspectRecordModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new InspectRecordModel[0];
		}
		return list.toArray(new InspectRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel)
	 */
	@Override
	public InspectRecordModel[] load(InspectRecordModel model) throws ShareworxServiceException {
		List<InspectRecordModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new InspectRecordModel[0];
		}
		return list.toArray(new InspectRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public InspectRecordModel[] save(InspectRecordModel[] models) throws ShareworxServiceException {
		List<InspectRecordModel> list = domainService.save(models);
		return list.toArray(new InspectRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public InspectRecordModel[] update(InspectRecordModel[] models) throws ShareworxServiceException {
		List<InspectRecordModel> list = domainService.update(models);
		return list.toArray(new InspectRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel[])
	 */
	@Override
	public InspectRecordModel[] delete(InspectRecordModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
