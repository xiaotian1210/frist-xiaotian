package com.shareworx.ezfm.worktask.areapersonnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskAreaPersonnelModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaPersonnelDomainService.ID)
public class YJWYWorkTaskAreaPersonnelDomainServiceImpl implements YJWYWorkTaskAreaPersonnelDomainService {

	public final static String ID = "yJWYWorkTaskAreaPersonnelDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#save(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> save(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#update(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> update(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#update(java.util.List, com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> update(List<String> editFields, YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaPersonnelModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#delete(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskAreaPersonnelModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskAreaPersonnelModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskAreaPersonnelModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskAreaPersonnelModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService#queryByExample(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel)
	 */
	@Override
	public List<YJWYWorkTaskAreaPersonnelModel> queryByExample(YJWYWorkTaskAreaPersonnelModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskAreaPersonnelModel.META_ID, model);
	}

}
