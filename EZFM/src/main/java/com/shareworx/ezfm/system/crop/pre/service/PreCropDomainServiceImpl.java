package com.shareworx.ezfm.system.crop.pre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * PreCropModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(PreCropDomainService.ID)
public class PreCropDomainServiceImpl implements PreCropDomainService {

	public final static String ID = "preCropDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#save(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public List<PreCropModel> save(PreCropModel... models) throws ShareworxServiceException {
		return service.save(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#update(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public List<PreCropModel> update(PreCropModel... models) throws ShareworxServiceException {
		return service.update(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#update(java.util.List, com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public List<PreCropModel> update(List<String> editFields, PreCropModel... models) throws ShareworxServiceException {
		return service.update(PreCropModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#delete(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public int delete(PreCropModel... models) throws ShareworxServiceException {
		return service.delete(PreCropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(PreCropModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#queryById(java.lang.String)
	 */
	@Override
	public PreCropModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(PreCropModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public PreCropModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<PreCropModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropDomainService#queryByExample(com.shareworx.ezfm.system.crop.pre.model.PreCropModel)
	 */
	@Override
	public List<PreCropModel> queryByExample(PreCropModel model) throws ShareworxServiceException {
		return service.queryByExample(PreCropModel.META_ID, model);
	}

}
