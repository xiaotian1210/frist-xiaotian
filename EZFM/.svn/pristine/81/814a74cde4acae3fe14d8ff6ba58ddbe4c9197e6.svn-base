package com.shareworx.ezfm.quality.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * QualityFileModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(QualityFileDomainService.ID)
public class QualityFileDomainServiceImpl implements QualityFileDomainService {

	public final static String ID = "qualityFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#save(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public List<QualityFileModel> save(QualityFileModel... models) throws ShareworxServiceException {
		return service.save(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#update(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public List<QualityFileModel> update(QualityFileModel... models) throws ShareworxServiceException {
		return service.update(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#update(java.util.List, com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public List<QualityFileModel> update(List<String> editFields, QualityFileModel... models) throws ShareworxServiceException {
		return service.update(QualityFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#delete(com.shareworx.ezfm.quality.file.model.QualityFileModel[])
	 */
	@Override
	public int delete(QualityFileModel... models) throws ShareworxServiceException {
		return service.delete(QualityFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(QualityFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public QualityFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(QualityFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public QualityFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<QualityFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.file.service.QualityFileDomainService#queryByExample(com.shareworx.ezfm.quality.file.model.QualityFileModel)
	 */
	@Override
	public List<QualityFileModel> queryByExample(QualityFileModel model) throws ShareworxServiceException {
		return service.queryByExample(QualityFileModel.META_ID, model);
	}

}
