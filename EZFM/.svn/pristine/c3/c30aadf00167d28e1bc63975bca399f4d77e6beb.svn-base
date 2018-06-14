package com.shareworx.ezfm.problem.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYProblemRecordModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProblemRecordDomainService.ID)
public class YJWYProblemRecordDomainServiceImpl implements YJWYProblemRecordDomainService {

	public final static String ID = "yJWYProblemRecordDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#save(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public List<YJWYProblemRecordModel> save(YJWYProblemRecordModel... models) throws ShareworxServiceException {
		return service.save(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#update(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public List<YJWYProblemRecordModel> update(YJWYProblemRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#update(java.util.List, com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public List<YJWYProblemRecordModel> update(List<String> editFields, YJWYProblemRecordModel... models) throws ShareworxServiceException {
		return service.update(YJWYProblemRecordModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#delete(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel[])
	 */
	@Override
	public int delete(YJWYProblemRecordModel... models) throws ShareworxServiceException {
		return service.delete(YJWYProblemRecordModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYProblemRecordModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYProblemRecordModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYProblemRecordModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYProblemRecordModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYProblemRecordModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService#queryByExample(com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel)
	 */
	@Override
	public List<YJWYProblemRecordModel> queryByExample(YJWYProblemRecordModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYProblemRecordModel.META_ID, model);
	}

}
