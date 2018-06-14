package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 标准版本业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardEditionBusinessService.ID)
public class StandardEditionBusinessServiceImpl implements StandardEditionBusinessService {

	@Autowired
	@Qualifier(StandardEditionDomainService.ID)
	private StandardEditionDomainService domainService;
	
	public void setDomainService(StandardEditionDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public StandardEditionModel[] query(Query query) throws ShareworxServiceException {
		List<StandardEditionModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new StandardEditionModel[0];
		}
		return list.toArray(new StandardEditionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel)
	 */
	@Override
	public StandardEditionModel[] load(StandardEditionModel model) throws ShareworxServiceException {
		List<StandardEditionModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new StandardEditionModel[0];
		}
		return list.toArray(new StandardEditionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public StandardEditionModel[] save(StandardEditionModel[] models) throws ShareworxServiceException {
		List<StandardEditionModel> list = domainService.save(models);
		return list.toArray(new StandardEditionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public StandardEditionModel[] update(StandardEditionModel[] models) throws ShareworxServiceException {
		List<StandardEditionModel> list = domainService.update(models);
		return list.toArray(new StandardEditionModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public StandardEditionModel[] delete(StandardEditionModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
