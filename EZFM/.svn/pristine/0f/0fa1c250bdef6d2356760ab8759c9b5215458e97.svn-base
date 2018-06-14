package com.shareworx.ezfm.baseinfo.resourceslog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYResourcesLogModel领域操作实现
 * @author kimguo
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourcesLogDomainService.ID)
public class YJWYResourcesLogDomainServiceImpl implements YJWYResourcesLogDomainService {

	public final static String ID = "yJWYResourcesLogDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#save(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public List<YJWYResourcesLogModel> save(YJWYResourcesLogModel... models) throws ShareworxServiceException {
		return service.save(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#update(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public List<YJWYResourcesLogModel> update(YJWYResourcesLogModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public List<YJWYResourcesLogModel> update(List<String> editFields, YJWYResourcesLogModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourcesLogModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#delete(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public int delete(YJWYResourcesLogModel... models) throws ShareworxServiceException {
		return service.delete(YJWYResourcesLogModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYResourcesLogModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYResourcesLogModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYResourcesLogModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYResourcesLogModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYResourcesLogModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel)
	 */
	@Override
	public List<YJWYResourcesLogModel> queryByExample(YJWYResourcesLogModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYResourcesLogModel.META_ID, model);
	}

}
