package com.shareworx.ezfm.baseinfo.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYProjectModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProjectDomainService.ID)
public class YJWYProjectDomainServiceImpl implements YJWYProjectDomainService {

	public final static String ID = "yJWYProjectDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#save(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public List<YJWYProjectModel> save(YJWYProjectModel... models) throws ShareworxServiceException {
		return service.save(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#update(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public List<YJWYProjectModel> update(YJWYProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public List<YJWYProjectModel> update(List<String> editFields, YJWYProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#delete(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel[])
	 */
	@Override
	public int delete(YJWYProjectModel... models) throws ShareworxServiceException {
		return service.delete(YJWYProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYProjectModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYProjectModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService#queryByExample(com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel)
	 */
	@Override
	public List<YJWYProjectModel> queryByExample(YJWYProjectModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYProjectModel.META_ID, model);
	}

}
