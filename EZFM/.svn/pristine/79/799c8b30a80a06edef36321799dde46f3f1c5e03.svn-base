package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;

/**
 * 标准与岗位中间表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardStationBusinessService.ID)
public class StandardStationBusinessServiceImpl implements StandardStationBusinessService {

	@Autowired
	@Qualifier(StandardStationDomainService.ID)
	private StandardStationDomainService domainService;
	
	public void setDomainService(StandardStationDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public StandardStationModel[] query(Query query) throws ShareworxServiceException {
		List<StandardStationModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new StandardStationModel[0];
		}
		return list.toArray(new StandardStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel)
	 */
	@Override
	public StandardStationModel[] load(StandardStationModel model) throws ShareworxServiceException {
		List<StandardStationModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new StandardStationModel[0];
		}
		return list.toArray(new StandardStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public StandardStationModel[] save(StandardStationModel[] models) throws ShareworxServiceException {
		List<StandardStationModel> list = domainService.save(models);
		return list.toArray(new StandardStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public StandardStationModel[] update(StandardStationModel[] models) throws ShareworxServiceException {
		List<StandardStationModel> list = domainService.update(models);
		return list.toArray(new StandardStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public StandardStationModel[] delete(StandardStationModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
