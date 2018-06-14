package com.shareworx.ezfm.worktask.areadetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYWorkTaskAreaDetailsModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaDetailsDomainService.ID)
public class YJWYWorkTaskAreaDetailsDomainServiceImpl implements YJWYWorkTaskAreaDetailsDomainService {

	public final static String ID = "yJWYWorkTaskAreaDetailsDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#save(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> save(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#update(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> update(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#update(java.util.List, com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> update(List<String> editFields, YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskAreaDetailsModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#delete(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskAreaDetailsModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskAreaDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskAreaDetailsModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskAreaDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService#queryByExample(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel)
	 */
	@Override
	public List<YJWYWorkTaskAreaDetailsModel> queryByExample(YJWYWorkTaskAreaDetailsModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskAreaDetailsModel.META_ID, model);
	}

}
