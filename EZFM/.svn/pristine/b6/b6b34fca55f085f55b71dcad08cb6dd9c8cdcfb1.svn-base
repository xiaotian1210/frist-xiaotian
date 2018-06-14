package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * yjwyResourceAttributePkResourceModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourceAttributePkResourceDomainService.ID)
public class YJWYResourceAttributePkResourceDomainServiceImpl implements YJWYResourceAttributePkResourceDomainService {

	public final static String ID = "yjwyResourceAttributePkResourceDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#save(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> save(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException {
		return service.save(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#update(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> update(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> update(List<String> editFields, YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourceAttributePkResourceModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#delete(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel[])
	 */
	@Override
	public int delete(YJWYResourceAttributePkResourceModel... models) throws ShareworxServiceException {
		return service.delete(YJWYResourceAttributePkResourceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYResourceAttributePkResourceModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYResourceAttributePkResourceModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYResourceAttributePkResourceModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyResourceAttributePkResourceDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.yjwyResourceAttributePkResourceModel)
	 */
	@Override
	public List<YJWYResourceAttributePkResourceModel> queryByExample(YJWYResourceAttributePkResourceModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYResourceAttributePkResourceModel.META_ID, model);
	}

}
