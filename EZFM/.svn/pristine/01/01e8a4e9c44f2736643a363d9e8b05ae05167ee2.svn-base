package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * StandardUserModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardUserDomainService.ID)
public class StandardUserDomainServiceImpl implements StandardUserDomainService {

	public final static String ID = "standardUserDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public List<StandardUserModel> save(StandardUserModel... models) throws ShareworxServiceException {
		return service.save(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public List<StandardUserModel> update(StandardUserModel... models) throws ShareworxServiceException {
		return service.update(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public List<StandardUserModel> update(List<String> editFields, StandardUserModel... models) throws ShareworxServiceException {
		return service.update(StandardUserModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel[])
	 */
	@Override
	public int delete(StandardUserModel... models) throws ShareworxServiceException {
		return service.delete(StandardUserModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(StandardUserModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#queryById(java.lang.String)
	 */
	@Override
	public StandardUserModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(StandardUserModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public StandardUserModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<StandardUserModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel)
	 */
	@Override
	public List<StandardUserModel> queryByExample(StandardUserModel model) throws ShareworxServiceException {
		return service.queryByExample(StandardUserModel.META_ID, model);
	}

}
