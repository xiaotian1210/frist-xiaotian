package com.shareworx.ezfm.quality.proinspect.inspect.probtype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * ProblemTypeModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemTypeDomainService.ID)
public class ProblemTypeDomainServiceImpl implements ProblemTypeDomainService {

	public final static String ID = "problemTypeDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public List<ProblemTypeModel> save(ProblemTypeModel... models) throws ShareworxServiceException {
		return service.save(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public List<ProblemTypeModel> update(ProblemTypeModel... models) throws ShareworxServiceException {
		return service.update(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public List<ProblemTypeModel> update(List<String> editFields, ProblemTypeModel... models) throws ShareworxServiceException {
		return service.update(ProblemTypeModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel[])
	 */
	@Override
	public int delete(ProblemTypeModel... models) throws ShareworxServiceException {
		return service.delete(ProblemTypeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(ProblemTypeModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#queryById(java.lang.String)
	 */
	@Override
	public ProblemTypeModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(ProblemTypeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public ProblemTypeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<ProblemTypeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel)
	 */
	@Override
	public List<ProblemTypeModel> queryByExample(ProblemTypeModel model) throws ShareworxServiceException {
		return service.queryByExample(ProblemTypeModel.META_ID, model);
	}

}
