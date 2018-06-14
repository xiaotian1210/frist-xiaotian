package com.shareworx.ezfm.system.crop.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 企业文件存储业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(CropFileBusinessService.ID)
public class CropFileBusinessServiceImpl implements CropFileBusinessService {

	@Autowired
	@Qualifier(CropFileDomainService.ID)
	private CropFileDomainService domainService;
	
	public void setDomainService(CropFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public CropFileModel[] query(Query query) throws ShareworxServiceException {
		List<CropFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new CropFileModel[0];
		}
		return list.toArray(new CropFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService#load(com.shareworx.ezfm.system.crop.file.model.CropFileModel)
	 */
	@Override
	public CropFileModel[] load(CropFileModel model) throws ShareworxServiceException {
		List<CropFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new CropFileModel[0];
		}
		return list.toArray(new CropFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService#save(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public CropFileModel[] save(CropFileModel[] models) throws ShareworxServiceException {
		List<CropFileModel> list = domainService.save(models);
		return list.toArray(new CropFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService#update(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public CropFileModel[] update(CropFileModel[] models) throws ShareworxServiceException {
		List<CropFileModel> list = domainService.update(models);
		return list.toArray(new CropFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService#delete(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public CropFileModel[] delete(CropFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
