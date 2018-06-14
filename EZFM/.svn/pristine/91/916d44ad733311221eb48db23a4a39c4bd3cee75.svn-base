package com.shareworx.ezfm.worktask.repairclass.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskRepairClassModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskRepairClassDomainService.ID)
public class YJWYWorkTaskRepairClassDomainServiceImpl implements YJWYWorkTaskRepairClassDomainService {

	public final static String ID = "yJWYWorkTaskRepairClassDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#save(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> save(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#update(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> update(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#update(java.util.List, com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> update(List<String> editFields, YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskRepairClassModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#delete(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskRepairClassModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskRepairClassModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskRepairClassModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskRepairClassModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService#queryByExample(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel)
	 */
	@Override
	public List<YJWYWorkTaskRepairClassModel> queryByExample(YJWYWorkTaskRepairClassModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskRepairClassModel.META_ID, model);
	}

}
