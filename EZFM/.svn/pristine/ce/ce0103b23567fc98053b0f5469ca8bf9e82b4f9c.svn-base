package com.shareworx.ezfm.worktask.projectuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskProjectUserModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskProjectUserDomainService.ID)
public class YJWYWorkTaskProjectUserDomainServiceImpl implements YJWYWorkTaskProjectUserDomainService {

	public final static String ID = "yJWYWorkTaskProjectUserDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#save(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> save(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#update(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> update(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#update(java.util.List, com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> update(List<String> editFields, YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskProjectUserModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#delete(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskProjectUserModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskProjectUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskProjectUserModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskProjectUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserDomainService#queryByExample(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel)
	 */
	@Override
	public List<YJWYWorkTaskProjectUserModel> queryByExample(YJWYWorkTaskProjectUserModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskProjectUserModel.META_ID, model);
	}

}
