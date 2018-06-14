package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYSiteModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSiteDomainService.ID)
public class YJWYSiteDomainServiceImpl implements YJWYSiteDomainService {

	public final static String ID = "yJWYSiteDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public List<YJWYSiteModel> save(YJWYSiteModel... models) throws ShareworxServiceException {
		return service.save(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public List<YJWYSiteModel> update(YJWYSiteModel... models) throws ShareworxServiceException {
		return service.update(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public List<YJWYSiteModel> update(List<String> editFields, YJWYSiteModel... models) throws ShareworxServiceException {
		return service.update(YJWYSiteModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel[])
	 */
	@Override
	public int delete(YJWYSiteModel... models) throws ShareworxServiceException {
		return service.delete(YJWYSiteModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYSiteModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYSiteModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYSiteModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYSiteModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYSiteModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYSiteDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel)
	 */
	@Override
	public List<YJWYSiteModel> queryByExample(YJWYSiteModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYSiteModel.META_ID, model);
	}

}
