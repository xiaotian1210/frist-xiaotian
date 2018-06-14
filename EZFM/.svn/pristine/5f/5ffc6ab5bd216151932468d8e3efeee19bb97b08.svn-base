package com.shareworx.ezfm.worktask.sonclass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 工单维修种类记录表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskSonClassRecordBusinessService.ID)
public class YJWYWorkTaskSonClassRecordBusinessServiceImpl implements YJWYWorkTaskSonClassRecordBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskSonClassRecordDomainService.ID)
	private YJWYWorkTaskSonClassRecordDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskSonClassRecordDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskSonClassRecordModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskSonClassRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskSonClassRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService#load(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel[] load(YJWYWorkTaskSonClassRecordModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskSonClassRecordModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskSonClassRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskSonClassRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService#save(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel[] save(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskSonClassRecordModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskSonClassRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService#update(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel[] update(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskSonClassRecordModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskSonClassRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService#delete(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel[] delete(YJWYWorkTaskSonClassRecordModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
