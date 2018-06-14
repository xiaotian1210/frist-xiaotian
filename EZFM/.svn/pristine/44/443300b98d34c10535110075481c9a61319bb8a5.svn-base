package com.shareworx.ezfm.quality.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 品质核查业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(QualityFileBusinessService.ID)
public class QualityFileBusinessServiceImpl implements QualityFileBusinessService {

	@Autowired
	@Qualifier(QualityFileDomainService.ID)
	private QualityFileDomainService domainService;
	
	public void setDomainService(QualityFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public QualityFileModel[] query(Query query) throws ShareworxServiceException {
		List<QualityFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new QualityFileModel[0];
		}
		return list.toArray(new QualityFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileBusinessService#load(com.shareworx.ezfm.quality.file.model.QualityFileModel)
	 */
	@Override
	public QualityFileModel[] load(QualityFileModel model) throws ShareworxServiceException {
		List<QualityFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new QualityFileModel[0];
		}
		return list.toArray(new QualityFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileBusinessService#save(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public QualityFileModel[] save(QualityFileModel[] models) throws ShareworxServiceException {
		List<QualityFileModel> list = domainService.save(models);
		return list.toArray(new QualityFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileBusinessService#update(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public QualityFileModel[] update(QualityFileModel[] models) throws ShareworxServiceException {
		List<QualityFileModel> list = domainService.update(models);
		return list.toArray(new QualityFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileBusinessService#delete(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public QualityFileModel[] delete(QualityFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
