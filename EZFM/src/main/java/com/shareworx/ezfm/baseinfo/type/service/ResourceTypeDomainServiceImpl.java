package com.shareworx.ezfm.baseinfo.type.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * resourcetypeModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(ResourceTypeDomainService.ID)
public class ResourceTypeDomainServiceImpl implements ResourceTypeDomainService {

	public final static String ID = "resourcetypeDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#save(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public List<ResourcetypeModel> save(ResourcetypeModel... models) throws ShareworxServiceException {
		return service.save(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#update(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public List<ResourcetypeModel> update(ResourcetypeModel... models) throws ShareworxServiceException {
		return service.update(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public List<ResourcetypeModel> update(List<String> editFields, ResourcetypeModel... models) throws ShareworxServiceException {
		return service.update(ResourcetypeModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#delete(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel[])
	 */
	@Override
	public int delete(ResourcetypeModel... models) throws ShareworxServiceException {
		return service.delete(ResourcetypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(ResourcetypeModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#queryById(java.lang.String)
	 */
	@Override
	public ResourcetypeModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(ResourcetypeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public ResourcetypeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<ResourcetypeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.type.service.resourcetypeDomainService#queryByExample(com.shareworx.ezfm.baseinfo.type.model.resourcetypeModel)
	 */
	@Override
	public List<ResourcetypeModel> queryByExample(ResourcetypeModel model) throws ShareworxServiceException {
		return service.queryByExample(ResourcetypeModel.META_ID, model);
	}

}
