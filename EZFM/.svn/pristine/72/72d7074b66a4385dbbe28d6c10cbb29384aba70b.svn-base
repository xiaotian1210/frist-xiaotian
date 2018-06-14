package com.shareworx.ezfm.problem.nexus.proanduser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYProjectInfoUserNexusModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProjectInfoUserNexusDomainService.ID)
public class YJWYProjectInfoUserNexusDomainServiceImpl implements YJWYProjectInfoUserNexusDomainService {

	public final static String ID = "yJWYProjectInfoUserNexusDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#save(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> save(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException {
		return service.save(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#update(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> update(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#update(java.util.List, com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> update(List<String> editFields, YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectInfoUserNexusModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#delete(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel[])
	 */
	@Override
	public int delete(YJWYProjectInfoUserNexusModel... models) throws ShareworxServiceException {
		return service.delete(YJWYProjectInfoUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYProjectInfoUserNexusModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYProjectInfoUserNexusModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYProjectInfoUserNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYProjectInfoUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService#queryByExample(com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel)
	 */
	@Override
	public List<YJWYProjectInfoUserNexusModel> queryByExample(YJWYProjectInfoUserNexusModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYProjectInfoUserNexusModel.META_ID, model);
	}

}
