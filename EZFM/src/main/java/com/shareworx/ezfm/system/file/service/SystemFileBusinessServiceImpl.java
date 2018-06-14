package com.shareworx.ezfm.system.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 附件表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(SystemFileBusinessService.ID)
public class SystemFileBusinessServiceImpl implements SystemFileBusinessService {

	@Autowired
	@Qualifier(SystemFileDomainService.ID)
	private SystemFileDomainService domainService;
	
	public void setDomainService(SystemFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public SystemFileModel[] query(Query query) throws ShareworxServiceException {
		List<SystemFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new SystemFileModel[0];
		}
		return list.toArray(new SystemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileBusinessService#load(com.shareworx.ezfm.system.file.model.SystemFileModel)
	 */
	@Override
	public SystemFileModel[] load(SystemFileModel model) throws ShareworxServiceException {
		List<SystemFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new SystemFileModel[0];
		}
		return list.toArray(new SystemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileBusinessService#save(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public SystemFileModel[] save(SystemFileModel[] models) throws ShareworxServiceException {
		List<SystemFileModel> list = domainService.save(models);
		return list.toArray(new SystemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileBusinessService#update(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public SystemFileModel[] update(SystemFileModel[] models) throws ShareworxServiceException {
		List<SystemFileModel> list = domainService.update(models);
		return list.toArray(new SystemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileBusinessService#delete(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public SystemFileModel[] delete(SystemFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
