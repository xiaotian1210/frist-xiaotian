package com.shareworx.ezfm.device.timer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYTimerModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTimerDomainService.ID)
public class YJWYTimerDomainServiceImpl implements YJWYTimerDomainService {

	public final static String ID = "yJWYTimerDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#save(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public List<YJWYTimerModel> save(YJWYTimerModel... models) throws ShareworxServiceException {
		return service.save(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#update(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public List<YJWYTimerModel> update(YJWYTimerModel... models) throws ShareworxServiceException {
		return service.update(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#update(java.util.List, com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public List<YJWYTimerModel> update(List<String> editFields, YJWYTimerModel... models) throws ShareworxServiceException {
		return service.update(YJWYTimerModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#delete(com.shareworx.ezfm.device.timer.model.YJWYTimerModel[])
	 */
	@Override
	public int delete(YJWYTimerModel... models) throws ShareworxServiceException {
		return service.delete(YJWYTimerModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYTimerModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYTimerModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYTimerModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYTimerModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYTimerModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.timer.service.YJWYTimerDomainService#queryByExample(com.shareworx.ezfm.device.timer.model.YJWYTimerModel)
	 */
	@Override
	public List<YJWYTimerModel> queryByExample(YJWYTimerModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYTimerModel.META_ID, model);
	}

}
