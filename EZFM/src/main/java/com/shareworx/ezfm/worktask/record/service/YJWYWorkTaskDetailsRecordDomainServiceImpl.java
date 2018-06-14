package com.shareworx.ezfm.worktask.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskDetailsRecordModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskDetailsRecordDomainService.ID)
public class YJWYWorkTaskDetailsRecordDomainServiceImpl implements YJWYWorkTaskDetailsRecordDomainService {

	public final static String ID = "yJWYWorkTaskDetailsRecordDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#save(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> save(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#update(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> update(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#update(java.util.List, com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> update(List<String> editFields, YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskDetailsRecordModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#delete(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskDetailsRecordModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskDetailsRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskDetailsRecordModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskDetailsRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskDetailsRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordDomainService#queryByExample(com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel)
	 */
	@Override
	public List<YJWYWorkTaskDetailsRecordModel> queryByExample(YJWYWorkTaskDetailsRecordModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskDetailsRecordModel.META_ID, model);
	}

}
