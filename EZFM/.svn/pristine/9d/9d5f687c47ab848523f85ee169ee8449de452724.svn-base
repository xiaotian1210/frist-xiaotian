package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * EditionStationModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(EditionStationDomainService.ID)
public class EditionStationDomainServiceImpl implements EditionStationDomainService {

	public final static String ID = "editionStationDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public List<EditionStationModel> save(EditionStationModel... models) throws ShareworxServiceException {
		return service.save(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public List<EditionStationModel> update(EditionStationModel... models) throws ShareworxServiceException {
		return service.update(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public List<EditionStationModel> update(List<String> editFields, EditionStationModel... models) throws ShareworxServiceException {
		return service.update(EditionStationModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel[])
	 */
	@Override
	public int delete(EditionStationModel... models) throws ShareworxServiceException {
		return service.delete(EditionStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(EditionStationModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#queryById(java.lang.String)
	 */
	@Override
	public EditionStationModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(EditionStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public EditionStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<EditionStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel)
	 */
	@Override
	public List<EditionStationModel> queryByExample(EditionStationModel model) throws ShareworxServiceException {
		return service.queryByExample(EditionStationModel.META_ID, model);
	}

}
