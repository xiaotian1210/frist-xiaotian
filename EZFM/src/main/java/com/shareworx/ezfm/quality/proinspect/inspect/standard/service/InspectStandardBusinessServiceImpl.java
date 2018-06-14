package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 核查标准业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectStandardBusinessService.ID)
public class InspectStandardBusinessServiceImpl implements InspectStandardBusinessService {

	@Autowired
	@Qualifier(InspectStandardDomainService.ID)
	private InspectStandardDomainService domainService;
	
	public void setDomainService(InspectStandardDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public InspectStandardModel[] query(Query query) throws ShareworxServiceException {
		List<InspectStandardModel> list = domainService.queryListByCondition(query);
		
		if(ArrayUtils.isEmpty(list)){
			return new InspectStandardModel[0];
		}
		return list.toArray(new InspectStandardModel[0]);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#load(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel)
	 */
	@Override
	public InspectStandardModel[] load(InspectStandardModel model) throws ShareworxServiceException {
		List<InspectStandardModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new InspectStandardModel[0];
		}
		return list.toArray(new InspectStandardModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public InspectStandardModel[] save(InspectStandardModel[] models) throws ShareworxServiceException {
		for(InspectStandardModel model : models){
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
			model.setUpdate_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis()+"");
		}
		List<InspectStandardModel> list = domainService.save(models);
		return list.toArray(new InspectStandardModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public InspectStandardModel[] update(InspectStandardModel[] models) throws ShareworxServiceException {
		for(InspectStandardModel model : models){	
			model.setUpdate_user(UserUtil.getCurrentUserPk());
			model.setUpdate_time(System.currentTimeMillis()+"");
		}
		List<InspectStandardModel> list = domainService.update(models);
		return list.toArray(new InspectStandardModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public InspectStandardModel[] delete(InspectStandardModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	

}
