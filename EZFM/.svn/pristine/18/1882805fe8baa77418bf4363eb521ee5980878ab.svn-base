package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 版本与岗位中间表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(EditionStationBusinessService.ID)
public class EditionStationBusinessServiceImpl implements EditionStationBusinessService {

	@Autowired
	@Qualifier(EditionStationDomainService.ID)
	private EditionStationDomainService domainService;
	
	public void setDomainService(EditionStationDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public EditionStationModel[] query(Query query) throws ShareworxServiceException {
		List<EditionStationModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new EditionStationModel[0];
		}
		return list.toArray(new EditionStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel)
	 */
	@Override
	public EditionStationModel[] load(EditionStationModel model) throws ShareworxServiceException {
		List<EditionStationModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new EditionStationModel[0];
		}
		return list.toArray(new EditionStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public EditionStationModel[] save(EditionStationModel[] models) throws ShareworxServiceException {
		List<EditionStationModel> list = domainService.save(models);
		return list.toArray(new EditionStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public EditionStationModel[] update(EditionStationModel[] models) throws ShareworxServiceException {
		List<EditionStationModel> list = domainService.update(models);
		return list.toArray(new EditionStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public EditionStationModel[] delete(EditionStationModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
