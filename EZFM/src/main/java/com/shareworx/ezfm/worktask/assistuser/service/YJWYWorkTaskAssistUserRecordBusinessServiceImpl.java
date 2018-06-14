package com.shareworx.ezfm.worktask.assistuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 工单协助人表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAssistUserRecordBusinessService.ID)
public class YJWYWorkTaskAssistUserRecordBusinessServiceImpl implements YJWYWorkTaskAssistUserRecordBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskAssistUserRecordDomainService.ID)
	private YJWYWorkTaskAssistUserRecordDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskAssistUserRecordDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskAssistUserRecordModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAssistUserRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskAssistUserRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordBusinessService#load(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel[] load(YJWYWorkTaskAssistUserRecordModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskAssistUserRecordModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAssistUserRecordModel[0];
		}
		return list.toArray(new YJWYWorkTaskAssistUserRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordBusinessService#save(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel[] save(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskAssistUserRecordModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskAssistUserRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordBusinessService#update(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel[] update(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskAssistUserRecordModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskAssistUserRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordBusinessService#delete(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel[] delete(YJWYWorkTaskAssistUserRecordModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
