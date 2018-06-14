package com.shareworx.ezfm.device.basic.executor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYGroupUserModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYGroupUserDomainService.ID)
public class YJWYGroupUserDomainServiceImpl implements YJWYGroupUserDomainService {

	public final static String ID = "yJWYGroupUserDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#save(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public List<YJWYGroupUserModel> save(YJWYGroupUserModel... models) throws ShareworxServiceException {
		return service.save(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#update(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public List<YJWYGroupUserModel> update(YJWYGroupUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#update(java.util.List, com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public List<YJWYGroupUserModel> update(List<String> editFields, YJWYGroupUserModel... models) throws ShareworxServiceException {
		return service.update(YJWYGroupUserModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#delete(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel[])
	 */
	@Override
	public int delete(YJWYGroupUserModel... models) throws ShareworxServiceException {
		return service.delete(YJWYGroupUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYGroupUserModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYGroupUserModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYGroupUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYGroupUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYGroupUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.basic.executor.service.YJWYGroupUserDomainService#queryByExample(com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel)
	 */
	@Override
	public List<YJWYGroupUserModel> queryByExample(YJWYGroupUserModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYGroupUserModel.META_ID, model);
	}

}
