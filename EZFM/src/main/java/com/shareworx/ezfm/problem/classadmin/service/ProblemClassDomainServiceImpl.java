package com.shareworx.ezfm.problem.classadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * ProblemClassModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemClassDomainService.ID)
public class ProblemClassDomainServiceImpl implements ProblemClassDomainService {

	public final static String ID = "problemClassDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#save(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public List<ProblemClassModel> save(ProblemClassModel... models) throws ShareworxServiceException {
		return service.save(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#update(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public List<ProblemClassModel> update(ProblemClassModel... models) throws ShareworxServiceException {
		return service.update(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#update(java.util.List, com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public List<ProblemClassModel> update(List<String> editFields, ProblemClassModel... models) throws ShareworxServiceException {
		return service.update(ProblemClassModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#delete(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public int delete(ProblemClassModel... models) throws ShareworxServiceException {
		return service.delete(ProblemClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(ProblemClassModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#queryById(java.lang.String)
	 */
	@Override
	public ProblemClassModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(ProblemClassModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public ProblemClassModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<ProblemClassModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService#queryByExample(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel)
	 */
	@Override
	public List<ProblemClassModel> queryByExample(ProblemClassModel model) throws ShareworxServiceException {
		return service.queryByExample(ProblemClassModel.META_ID, model);
	}

}
