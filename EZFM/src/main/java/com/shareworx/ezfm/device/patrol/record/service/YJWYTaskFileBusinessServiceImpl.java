package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 巡检维保附件表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskFileBusinessService.ID)
public class YJWYTaskFileBusinessServiceImpl implements YJWYTaskFileBusinessService {

	@Autowired
	@Qualifier(YJWYTaskFileDomainService.ID)
	private YJWYTaskFileDomainService domainService;
	
	public void setDomainService(YJWYTaskFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYTaskFileModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYTaskFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskFileModel[0];
		}
		return list.toArray(new YJWYTaskFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService#load(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel)
	 */
	@Override
	public YJWYTaskFileModel[] load(YJWYTaskFileModel model) throws ShareworxServiceException {
		List<YJWYTaskFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYTaskFileModel[0];
		}
		return list.toArray(new YJWYTaskFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService#save(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public YJWYTaskFileModel[] save(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		List<YJWYTaskFileModel> list = domainService.save(models);
		return list.toArray(new YJWYTaskFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService#update(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public YJWYTaskFileModel[] update(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		List<YJWYTaskFileModel> list = domainService.update(models);
		return list.toArray(new YJWYTaskFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService#delete(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public YJWYTaskFileModel[] delete(YJWYTaskFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
