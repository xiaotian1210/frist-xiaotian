package com.shareworx.ezfm.problem.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 报事记录业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProblemRecordBusinessService.ID)
public class YJWYProblemRecordBusinessServiceImpl implements YJWYProblemRecordBusinessService {

	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService domainService;
	
	public void setDomainService(YJWYProblemRecordDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProblemRecordModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYProblemRecordModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProblemRecordModel[0];
		}
		return list.toArray(new YJWYProblemRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService#load(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel)
	 */
	@Override
	public YJWYProblemRecordModel[] load(YJWYProblemRecordModel model) throws ShareworxServiceException {
		List<YJWYProblemRecordModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProblemRecordModel[0];
		}
		return list.toArray(new YJWYProblemRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService#save(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public YJWYProblemRecordModel[] save(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		List<YJWYProblemRecordModel> list = domainService.save(models);
		return list.toArray(new YJWYProblemRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService#update(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public YJWYProblemRecordModel[] update(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		List<YJWYProblemRecordModel> list = domainService.update(models);
		return list.toArray(new YJWYProblemRecordModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService#delete(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public YJWYProblemRecordModel[] delete(YJWYProblemRecordModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
