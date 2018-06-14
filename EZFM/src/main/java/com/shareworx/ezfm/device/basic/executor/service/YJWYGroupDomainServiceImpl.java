package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYGroupModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYGroupDomainService.ID)
public class YJWYGroupDomainServiceImpl implements YJWYGroupDomainService {

	public final static String ID = "yJWYGroupDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#save(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public List<YJWYGroupModel> save(YJWYGroupModel... models) throws ShareworxServiceException {
		return service.save(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#update(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public List<YJWYGroupModel> update(YJWYGroupModel... models) throws ShareworxServiceException {
		return service.update(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#update(java.util.List, com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public List<YJWYGroupModel> update(List<String> editFields, YJWYGroupModel... models) throws ShareworxServiceException {
		return service.update(YJWYGroupModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#delete(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel[])
	 */
	@Override
	public int delete(YJWYGroupModel... models) throws ShareworxServiceException {
		return service.delete(YJWYGroupModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYGroupModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYGroupModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYGroupModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYGroupModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYGroupModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupDomainService#queryByExample(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel)
	 */
	@Override
	public List<YJWYGroupModel> queryByExample(YJWYGroupModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYGroupModel.META_ID, model);
	}

}
