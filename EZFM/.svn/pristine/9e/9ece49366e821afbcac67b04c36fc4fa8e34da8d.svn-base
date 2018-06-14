package com.shareworx.ezfm.quality.proinspect.inspect.probtype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 问题类型业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemTypeBusinessService.ID)
public class ProblemTypeBusinessServiceImpl implements ProblemTypeBusinessService {

	@Autowired
	@Qualifier(ProblemTypeDomainService.ID)
	private ProblemTypeDomainService domainService;
	
	public void setDomainService(ProblemTypeDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemTypeModel[] query(Query query) throws ShareworxServiceException {
		List<ProblemTypeModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new ProblemTypeModel[0];
		}
		return list.toArray(new ProblemTypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel)
	 */
	@Override
	public ProblemTypeModel[] load(ProblemTypeModel model) throws ShareworxServiceException {
		List<ProblemTypeModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new ProblemTypeModel[0];
		}
		return list.toArray(new ProblemTypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public ProblemTypeModel[] save(ProblemTypeModel[] models) throws ShareworxServiceException {
		List<ProblemTypeModel> list = domainService.save(models);
		return list.toArray(new ProblemTypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public ProblemTypeModel[] update(ProblemTypeModel[] models) throws ShareworxServiceException {
		List<ProblemTypeModel> list = domainService.update(models);
		return list.toArray(new ProblemTypeModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public ProblemTypeModel[] delete(ProblemTypeModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
