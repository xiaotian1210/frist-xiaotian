package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * StandardEditionModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(StandardEditionDomainService.ID)
public class StandardEditionDomainServiceImpl implements StandardEditionDomainService {

	public final static String ID = "standardEditionDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public List<StandardEditionModel> save(StandardEditionModel... models) throws ShareworxServiceException {
		return service.save(StandardEditionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public List<StandardEditionModel> update(StandardEditionModel... models) throws ShareworxServiceException {
		return service.update(StandardEditionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public List<StandardEditionModel> update(List<String> editFields, StandardEditionModel... models) throws ShareworxServiceException {
		return service.update(StandardEditionModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel[])
	 */
	@Override
	public int delete(StandardEditionModel... models) throws ShareworxServiceException {
		return service.delete(StandardEditionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(StandardEditionModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#queryById(java.lang.String)
	 */
	@Override
	public StandardEditionModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(StandardEditionModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public StandardEditionModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<StandardEditionModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel)
	 */
	@Override
	public List<StandardEditionModel> queryByExample(StandardEditionModel model) throws ShareworxServiceException {
		return service.queryByExample(StandardEditionModel.META_ID, model);
	}

}
