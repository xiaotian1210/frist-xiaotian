package com.shareworx.ezfm.problem.classadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 测试用的业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(yjwyjwtest_csBusinessService.ID)
public class yjwyjwtest_csBusinessServiceImpl implements yjwyjwtest_csBusinessService {

	@Autowired
	@Qualifier(yjwyjwtest_csDomainService.ID)
	private yjwyjwtest_csDomainService domainService;
	
	public void setDomainService(yjwyjwtest_csDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public yjwyjwtest_csModel[] query(Query query) throws ShareworxServiceException {
		List<yjwyjwtest_csModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new yjwyjwtest_csModel[0];
		}
		return list.toArray(new yjwyjwtest_csModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService#load(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel)
	 */
	@Override
	public yjwyjwtest_csModel[] load(yjwyjwtest_csModel model) throws ShareworxServiceException {
		List<yjwyjwtest_csModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new yjwyjwtest_csModel[0];
		}
		return list.toArray(new yjwyjwtest_csModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService#save(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public yjwyjwtest_csModel[] save(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		List<yjwyjwtest_csModel> list = domainService.save(models);
		return list.toArray(new yjwyjwtest_csModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService#update(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public yjwyjwtest_csModel[] update(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		List<yjwyjwtest_csModel> list = domainService.update(models);
		return list.toArray(new yjwyjwtest_csModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService#delete(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public yjwyjwtest_csModel[] delete(yjwyjwtest_csModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
