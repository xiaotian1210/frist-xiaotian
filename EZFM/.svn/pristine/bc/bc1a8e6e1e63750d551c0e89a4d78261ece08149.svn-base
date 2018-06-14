package com.shareworx.ezfm.device.siteproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * YJWYSiteProjectModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSiteProjectDomainService.ID)
public class YJWYSiteProjectDomainServiceImpl implements YJWYSiteProjectDomainService {

	public final static String ID = "yJWYSiteProjectDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public List<YJWYSiteProjectModel> save(YJWYSiteProjectModel... models) throws ShareworxServiceException {
		return service.save(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public List<YJWYSiteProjectModel> update(YJWYSiteProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public List<YJWYSiteProjectModel> update(List<String> editFields, YJWYSiteProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYSiteProjectModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel[])
	 */
	@Override
	public int delete(YJWYSiteProjectModel... models) throws ShareworxServiceException {
		return service.delete(YJWYSiteProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYSiteProjectModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYSiteProjectModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYSiteProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYSiteProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYSiteProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteProjectDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYSiteProjectModel)
	 */
	@Override
	public List<YJWYSiteProjectModel> queryByExample(YJWYSiteProjectModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYSiteProjectModel.META_ID, model);
	}

}
