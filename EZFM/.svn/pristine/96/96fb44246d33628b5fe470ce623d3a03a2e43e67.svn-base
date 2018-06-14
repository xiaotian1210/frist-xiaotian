package com.shareworx.ezfm.system.crop.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * CropFileModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(CropFileDomainService.ID)
public class CropFileDomainServiceImpl implements CropFileDomainService {

	public final static String ID = "cropFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#save(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public List<CropFileModel> save(CropFileModel... models) throws ShareworxServiceException {
		return service.save(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#update(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public List<CropFileModel> update(CropFileModel... models) throws ShareworxServiceException {
		return service.update(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#update(java.util.List, com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public List<CropFileModel> update(List<String> editFields, CropFileModel... models) throws ShareworxServiceException {
		return service.update(CropFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#delete(com.shareworx.ezfm.system.crop.file.model.CropFileModel[])
	 */
	@Override
	public int delete(CropFileModel... models) throws ShareworxServiceException {
		return service.delete(CropFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(CropFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public CropFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(CropFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public CropFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<CropFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.file.service.CropFileDomainService#queryByExample(com.shareworx.ezfm.system.crop.file.model.CropFileModel)
	 */
	@Override
	public List<CropFileModel> queryByExample(CropFileModel model) throws ShareworxServiceException {
		return service.queryByExample(CropFileModel.META_ID, model);
	}

}
