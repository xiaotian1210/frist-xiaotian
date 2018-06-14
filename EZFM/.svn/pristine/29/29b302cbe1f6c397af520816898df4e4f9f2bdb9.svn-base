package com.shareworx.ezfm.worktask.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 工单详情表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskDetailsRecordBusinessService.ID)
public class YJWYWorkTaskDetailsRecordBusinessServiceImpl implements YJWYWorkTaskDetailsRecordBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordDomainService.ID)
	private YJWYWorkTaskDetailsRecordDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskDetailsRecordDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsRecordModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskDetailsRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskDetailsRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService#load(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel[] load(YJWYWorkTaskDetailsRecordModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsRecordModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskDetailsRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskDetailsRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService#save(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel[] save(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsRecordModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskDetailsRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService#update(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel[] update(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsRecordModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskDetailsRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService#delete(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel[] delete(YJWYWorkTaskDetailsRecordModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
