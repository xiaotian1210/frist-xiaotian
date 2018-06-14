package com.shareworx.ezfm.problem.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * ProblemFileModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemFileDomainService.ID)
public class ProblemFileDomainServiceImpl implements ProblemFileDomainService {

	public final static String ID = "problemFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#save(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public List<ProblemFileModel> save(ProblemFileModel... models) throws ShareworxServiceException {
		return service.save(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#update(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public List<ProblemFileModel> update(ProblemFileModel... models) throws ShareworxServiceException {
		return service.update(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#update(java.util.List, com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public List<ProblemFileModel> update(List<String> editFields, ProblemFileModel... models) throws ShareworxServiceException {
		return service.update(ProblemFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#delete(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public int delete(ProblemFileModel... models) throws ShareworxServiceException {
		return service.delete(ProblemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(ProblemFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public ProblemFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(ProblemFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public ProblemFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<ProblemFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileDomainService#queryByExample(com.shareworx.ezfm.problem.file.model.ProblemFileModel)
	 */
	@Override
	public List<ProblemFileModel> queryByExample(ProblemFileModel model) throws ShareworxServiceException {
		return service.queryByExample(ProblemFileModel.META_ID, model);
	}

}
