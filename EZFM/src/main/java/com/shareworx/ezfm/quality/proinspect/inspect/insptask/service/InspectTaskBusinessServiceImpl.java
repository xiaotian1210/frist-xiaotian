package com.shareworx.ezfm.quality.proinspect.inspect.insptask.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 核查与整改任务业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectTaskBusinessService.ID)
public class InspectTaskBusinessServiceImpl implements InspectTaskBusinessService {

	@Autowired
	@Qualifier(InspectTaskDomainService.ID)
	private InspectTaskDomainService domainService;
	
	public void setDomainService(InspectTaskDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectTaskModel[] query(Query query) throws ShareworxServiceException {
		List<InspectTaskModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new InspectTaskModel[0];
		}
		return list.toArray(new InspectTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel)
	 */
	@Override
	public InspectTaskModel[] load(InspectTaskModel model) throws ShareworxServiceException {
		List<InspectTaskModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new InspectTaskModel[0];
		}
		return list.toArray(new InspectTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public InspectTaskModel[] save(InspectTaskModel[] models) throws ShareworxServiceException {
		for(InspectTaskModel model : models){
			model.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis()+"");
			model.setUpdate_user(UserUtil.getCurrentUserPk());
		}
		List<InspectTaskModel> list = domainService.save(models);
		return list.toArray(new InspectTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public InspectTaskModel[] update(InspectTaskModel[] models) throws ShareworxServiceException {
		for(InspectTaskModel model : models){
			model.setUpdate_time(System.currentTimeMillis()+"");
			model.setUpdate_user(UserUtil.getCurrentUserPk());
		}
		List<InspectTaskModel> list = domainService.update(models);
		return list.toArray(new InspectTaskModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel[])
	 */
	@Override
	public InspectTaskModel[] delete(InspectTaskModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
}
