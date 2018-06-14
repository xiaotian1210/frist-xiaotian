package com.shareworx.ezfm.baseinfo.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * DefaultOrgModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(DefaultOrgDomainService.ID)
public class DefaultOrgDomainServiceImpl implements DefaultOrgDomainService {

	public final static String ID = "defaultOrgDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#save(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public List<DefaultOrgModel> save(DefaultOrgModel... models) throws ShareworxServiceException {
		return service.save(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#update(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public List<DefaultOrgModel> update(DefaultOrgModel... models) throws ShareworxServiceException {
		return service.update(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public List<DefaultOrgModel> update(List<String> editFields, DefaultOrgModel... models) throws ShareworxServiceException {
		return service.update(DefaultOrgModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#delete(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public int delete(DefaultOrgModel... models) throws ShareworxServiceException {
		return service.delete(DefaultOrgModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(DefaultOrgModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#queryById(java.lang.String)
	 */
	@Override
	public DefaultOrgModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(DefaultOrgModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public DefaultOrgModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<DefaultOrgModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService#queryByExample(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel)
	 */
	@Override
	public List<DefaultOrgModel> queryByExample(DefaultOrgModel model) throws ShareworxServiceException {
		return service.queryByExample(DefaultOrgModel.META_ID, model);
	}

}
