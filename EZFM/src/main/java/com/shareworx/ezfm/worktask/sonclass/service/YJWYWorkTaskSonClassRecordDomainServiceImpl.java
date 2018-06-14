package com.shareworx.ezfm.worktask.sonclass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskSonClassRecordModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskSonClassRecordDomainService.ID)
public class YJWYWorkTaskSonClassRecordDomainServiceImpl implements YJWYWorkTaskSonClassRecordDomainService {

	public final static String ID = "yJWYWorkTaskSonClassRecordDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#save(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> save(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#update(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> update(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#update(java.util.List, com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> update(List<String> editFields, YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskSonClassRecordModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#delete(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskSonClassRecordModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskSonClassRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskSonClassRecordModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskSonClassRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskSonClassRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService#queryByExample(com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel)
	 */
	@Override
	public List<YJWYWorkTaskSonClassRecordModel> queryByExample(YJWYWorkTaskSonClassRecordModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskSonClassRecordModel.META_ID, model);
	}

}
