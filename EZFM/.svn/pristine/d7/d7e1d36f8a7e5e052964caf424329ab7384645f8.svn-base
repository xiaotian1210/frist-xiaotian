package com.shareworx.ezfm.problem.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 报事报修文件存储业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemFileBusinessService.ID)
public class ProblemFileBusinessServiceImpl implements ProblemFileBusinessService {

	@Autowired
	@Qualifier(ProblemFileDomainService.ID)
	private ProblemFileDomainService domainService;
	
	public void setDomainService(ProblemFileDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemFileModel[] query(Query query) throws ShareworxServiceException {
		List<ProblemFileModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new ProblemFileModel[0];
		}
		return list.toArray(new ProblemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService#load(com.shareworx.ezfm.problem.file.model.ProblemFileModel)
	 */
	@Override
	public ProblemFileModel[] load(ProblemFileModel model) throws ShareworxServiceException {
		List<ProblemFileModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new ProblemFileModel[0];
		}
		return list.toArray(new ProblemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService#save(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public ProblemFileModel[] save(ProblemFileModel[] models) throws ShareworxServiceException {
		List<ProblemFileModel> list = domainService.save(models);
		return list.toArray(new ProblemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService#update(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public ProblemFileModel[] update(ProblemFileModel[] models) throws ShareworxServiceException {
		List<ProblemFileModel> list = domainService.update(models);
		return list.toArray(new ProblemFileModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService#delete(com.shareworx.ezfm.problem.file.model.ProblemFileModel[])
	 */
	@Override
	public ProblemFileModel[] delete(ProblemFileModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
