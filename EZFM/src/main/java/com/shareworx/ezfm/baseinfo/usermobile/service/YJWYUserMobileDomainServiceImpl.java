package com.shareworx.ezfm.baseinfo.usermobile.service;

import java.util.List;

import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYUserMobileModel领域操作实现
 * @author yuting.wang
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYUserMobileDomainService.ID)
public class YJWYUserMobileDomainServiceImpl implements YJWYUserMobileDomainService {

	public final static String ID = "yJWYUserMobileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#save(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public List<YJWYUserMobileModel> save(YJWYUserMobileModel... models) throws ShareworxServiceException {
		return service.save(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#update(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override

	public List<YJWYUserMobileModel> update(YJWYUserMobileModel... models) throws ShareworxServiceException {
		return service.update(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public List<YJWYUserMobileModel> update(List<String> editFields, YJWYUserMobileModel... models) throws ShareworxServiceException {
		return service.update(YJWYUserMobileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#delete(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel[])
	 */
	@Override
	public int delete(YJWYUserMobileModel... models) throws ShareworxServiceException {
		return service.delete(YJWYUserMobileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYUserMobileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYUserMobileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYUserMobileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYUserMobileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYUserMobileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService#queryByExample(com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel)
	 */
	@Override
	public List<YJWYUserMobileModel> queryByExample(YJWYUserMobileModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYUserMobileModel.META_ID, model);
	}

}
