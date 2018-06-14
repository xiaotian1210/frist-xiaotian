package com.shareworx.ezfm.worktask.areaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskAreaProjectNexusModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaProjectNexusDomainService.ID)
public class YJWYWorkTaskAreaProjectNexusDomainServiceImpl implements YJWYWorkTaskAreaProjectNexusDomainService {

	public final static String ID = "yJWYWorkTaskAreaProjectNexusDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#save(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> save(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#update(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> update(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#update(java.util.List, com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> update(List<String> editFields, YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaProjectNexusModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#delete(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskAreaProjectNexusModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskAreaProjectNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskAreaProjectNexusModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskAreaProjectNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService#queryByExample(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel)
	 */
	@Override
	public List<YJWYWorkTaskAreaProjectNexusModel> queryByExample(YJWYWorkTaskAreaProjectNexusModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskAreaProjectNexusModel.META_ID, model);
	}

}
