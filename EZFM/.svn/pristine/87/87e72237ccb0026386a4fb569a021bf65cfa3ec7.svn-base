package com.shareworx.ezfm.worktask.classpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskRepairClassProjectModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskRepairClassProjectDomainService.ID)
public class YJWYWorkTaskRepairClassProjectDomainServiceImpl implements YJWYWorkTaskRepairClassProjectDomainService {

	public final static String ID = "yJWYWorkTaskRepairClassProjectDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#save(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> save(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#update(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> update(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#update(java.util.List, com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> update(List<String> editFields, YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskRepairClassProjectModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#delete(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskRepairClassProjectModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskRepairClassProjectModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskRepairClassProjectModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskRepairClassProjectModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectDomainService#queryByExample(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassProjectModel> queryByExample(YJWYWorkTaskRepairClassProjectModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskRepairClassProjectModel.META_ID, model);
	}

}
