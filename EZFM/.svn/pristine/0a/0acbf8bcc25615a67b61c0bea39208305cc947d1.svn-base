package com.shareworx.ezfm.worktask.assistuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskAssistUserRecordModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAssistUserRecordDomainService.ID)
public class YJWYWorkTaskAssistUserRecordDomainServiceImpl implements YJWYWorkTaskAssistUserRecordDomainService {

	public final static String ID = "yJWYWorkTaskAssistUserRecordDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#save(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> save(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#update(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> update(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#update(java.util.List, com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> update(List<String> editFields, YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAssistUserRecordModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#delete(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskAssistUserRecordModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskAssistUserRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskAssistUserRecordModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskAssistUserRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskAssistUserRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.assistuser.service.YJWYWorkTaskAssistUserRecordDomainService#queryByExample(com.shareworx.ezfm.worktask.assistuser.model.YJWYWorkTaskAssistUserRecordModel)
	 */
	@Override
	public List<YJWYWorkTaskAssistUserRecordModel> queryByExample(YJWYWorkTaskAssistUserRecordModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskAssistUserRecordModel.META_ID, model);
	}

}
