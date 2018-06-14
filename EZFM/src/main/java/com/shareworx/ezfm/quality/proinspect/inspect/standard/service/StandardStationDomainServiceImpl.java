package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * StandardStationModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardStationDomainService.ID)
public class StandardStationDomainServiceImpl implements StandardStationDomainService {

	public final static String ID = "standardStationDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public List<StandardStationModel> save(StandardStationModel... models) throws ShareworxServiceException {
		return service.save(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public List<StandardStationModel> update(StandardStationModel... models) throws ShareworxServiceException {
		return service.update(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public List<StandardStationModel> update(List<String> editFields, StandardStationModel... models) throws ShareworxServiceException {
		return service.update(StandardStationModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel[])
	 */
	@Override
	public int delete(StandardStationModel... models) throws ShareworxServiceException {
		return service.delete(StandardStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(StandardStationModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#queryById(java.lang.String)
	 */
	@Override
	public StandardStationModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(StandardStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public StandardStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<StandardStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel)
	 */
	@Override
	public List<StandardStationModel> queryByExample(StandardStationModel model) throws ShareworxServiceException {
		return service.queryByExample(StandardStationModel.META_ID, model);
	}

}
