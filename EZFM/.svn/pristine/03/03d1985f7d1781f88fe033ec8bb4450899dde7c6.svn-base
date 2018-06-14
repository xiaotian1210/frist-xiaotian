package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYAPPRoleFuncModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYAPPRoleFuncDomainService.ID)
public class YJWYAPPRoleFuncDomainServiceImpl implements YJWYAPPRoleFuncDomainService {

	public final static String ID = "yJWYAPPRoleFuncDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#save(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> save(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException {
		return service.save(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#update(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> update(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException {
		return service.update(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> update(List<String> editFields, YJWYAPPRoleFuncModel... models) throws ShareworxServiceException {
		return service.update(YJWYAPPRoleFuncModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#delete(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel[])
	 */
	@Override
	public int delete(YJWYAPPRoleFuncModel... models) throws ShareworxServiceException {
		return service.delete(YJWYAPPRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYAPPRoleFuncModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYAPPRoleFuncModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYAPPRoleFuncModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYAPPRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncDomainService#queryByExample(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel)
	 */
	@Override
	public List<YJWYAPPRoleFuncModel> queryByExample(YJWYAPPRoleFuncModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYAPPRoleFuncModel.META_ID, model);
	}

}
