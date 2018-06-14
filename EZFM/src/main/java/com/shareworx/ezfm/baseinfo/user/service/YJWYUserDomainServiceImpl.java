package com.shareworx.ezfm.baseinfo.user.service;

import java.util.List;

import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import com.shareworx.ezfm.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * UserModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYUserDomainService.ID)
public class YJWYUserDomainServiceImpl implements YJWYUserDomainService {

	public final static String ID = "userDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#save(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public List<YJWYUserModel> save(YJWYUserModel... models) throws ShareworxServiceException {
		for(YJWYUserModel model : models){
			if(LoginCommons.QYUSERCODE.equals(model.getUser_code())){
				throw new ShareworxAuthencatinException("编码重复，请换个编码");
			}
		}
		return service.save(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#update(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public List<YJWYUserModel> update(YJWYUserModel... models) throws ShareworxServiceException {
		for(YJWYUserModel model : models){
			if(LoginCommons.QYUSERCODE.equals(model.getUser_code())){
				throw new ShareworxAuthencatinException("编码重复，请换个编码");
			}
		}
		return service.update(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public List<YJWYUserModel> update(List<String> editFields, YJWYUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYUserModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#delete(com.shareworx.ezfm.baseinfo.user.model.UserModel[])
	 */
	@Override
	public int delete(YJWYUserModel... models) throws ShareworxServiceException {
		return service.delete(YJWYUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYUserModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#queryById(java.lang.String)
	 */
	@Override
	@DMSCacheable(namespace = "'"+CacheConstants.USER_CACHA_NAMESPACE+"'",key = "#id")
	public YJWYUserModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.user.service.UserDomainService#queryByExample(com.shareworx.ezfm.baseinfo.user.model.UserModel)
	 */
	@Override
	public List<YJWYUserModel> queryByExample(YJWYUserModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYUserModel.META_ID, model);
	}

}
