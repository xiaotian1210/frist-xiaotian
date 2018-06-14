package com.shareworx.ezfm.baseinfo.rolefunc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYRoleFuncModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoleFuncDomainService.ID)
public class YJWYRoleFuncDomainServiceImpl implements YJWYRoleFuncDomainService {

	public final static String ID = "yJWYRoleFuncDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#save(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public List<YJWYRoleFuncModel> save(YJWYRoleFuncModel... models) throws ShareworxServiceException {
		return service.save(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#update(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public List<YJWYRoleFuncModel> update(YJWYRoleFuncModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public List<YJWYRoleFuncModel> update(List<String> editFields, YJWYRoleFuncModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoleFuncModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#delete(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel[])
	 */
	@Override
	public int delete(YJWYRoleFuncModel... models) throws ShareworxServiceException {
		return service.delete(YJWYRoleFuncModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYRoleFuncModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYRoleFuncModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYRoleFuncModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYRoleFuncModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYRoleFuncModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService#queryByExample(com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel)
	 */
	@Override
	public List<YJWYRoleFuncModel> queryByExample(YJWYRoleFuncModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYRoleFuncModel.META_ID, model);
	}

}
