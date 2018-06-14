package com.shareworx.ezfm.baseinfo.resources.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 资源附件业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourceFileBusinessService.ID)
public class YJWYResourceFileBusinessServiceImpl implements YJWYResourceFileBusinessService {

	@Autowired
	@Qualifier(YJWYResourceFileDomainService.ID)
	private YJWYResourceFileDomainService domainService;
	
	public void setDomainService(YJWYResourceFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYResourceFileModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYResourceFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYResourceFileModel[0];
		}
		return list.toArray(new YJWYResourceFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService#load(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel)
	 */
	@Override
	public YJWYResourceFileModel[] load(YJWYResourceFileModel model) throws ShareworxServiceException {
		List<YJWYResourceFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYResourceFileModel[0];
		}
		return list.toArray(new YJWYResourceFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService#save(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public YJWYResourceFileModel[] save(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		List<YJWYResourceFileModel> list = domainService.save(models);
		return list.toArray(new YJWYResourceFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService#update(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public YJWYResourceFileModel[] update(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		List<YJWYResourceFileModel> list = domainService.update(models);
		return list.toArray(new YJWYResourceFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService#delete(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public YJWYResourceFileModel[] delete(YJWYResourceFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
