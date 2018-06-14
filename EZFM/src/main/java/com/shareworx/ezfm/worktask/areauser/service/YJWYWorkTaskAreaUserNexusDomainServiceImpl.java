package com.shareworx.ezfm.worktask.areauser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskAreaUserNexusModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaUserNexusDomainService.ID)
public class YJWYWorkTaskAreaUserNexusDomainServiceImpl implements YJWYWorkTaskAreaUserNexusDomainService {

	public final static String ID = "yJWYWorkTaskAreaUserNexusDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#save(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> save(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#update(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> update(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#update(java.util.List, com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> update(List<String> editFields, YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaUserNexusModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#delete(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskAreaUserNexusModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskAreaUserNexusModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskAreaUserNexusModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskAreaUserNexusModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusDomainService#queryByExample(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel)
	 */
	@Override
	public List<YJWYWorkTaskAreaUserNexusModel> queryByExample(YJWYWorkTaskAreaUserNexusModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskAreaUserNexusModel.META_ID, model);
	}

}
