package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * AttributeNameModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(AttributeNameDomainService.ID)
public class AttributeNameDomainServiceImpl implements AttributeNameDomainService {

	public final static String ID = "attributeNameDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#save(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public List<AttributeNameModel> save(AttributeNameModel... models) throws ShareworxServiceException {
		return service.save(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#update(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public List<AttributeNameModel> update(AttributeNameModel... models) throws ShareworxServiceException {
		return service.update(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public List<AttributeNameModel> update(List<String> editFields, AttributeNameModel... models) throws ShareworxServiceException {
		return service.update(AttributeNameModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#delete(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel[])
	 */
	@Override
	public int delete(AttributeNameModel... models) throws ShareworxServiceException {
		return service.delete(AttributeNameModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(AttributeNameModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#queryById(java.lang.String)
	 */
	@Override
	public AttributeNameModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(AttributeNameModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public AttributeNameModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<AttributeNameModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel)
	 */
	@Override
	public List<AttributeNameModel> queryByExample(AttributeNameModel model) throws ShareworxServiceException {
		return service.queryByExample(AttributeNameModel.META_ID, model);
	}

}
