package com.shareworx.ezfm.problem.nexus.proandclass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYProjectInfoClassNexusModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProjectInfoClassNexusDomainService.ID)
public class YJWYProjectInfoClassNexusDomainServiceImpl implements YJWYProjectInfoClassNexusDomainService {

	public final static String ID = "yJWYProjectInfoClassNexusDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#save(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> save(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException {
		return service.save(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#update(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> update(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#update(java.util.List, com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> update(List<String> editFields, YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYProjectInfoClassNexusModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#delete(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public int delete(YJWYProjectInfoClassNexusModel... models) throws ShareworxServiceException {
		return service.delete(YJWYProjectInfoClassNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYProjectInfoClassNexusModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYProjectInfoClassNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService#queryByExample(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel)
	 */
	@Override
	public List<YJWYProjectInfoClassNexusModel> queryByExample(YJWYProjectInfoClassNexusModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYProjectInfoClassNexusModel.META_ID, model);
	}

}
