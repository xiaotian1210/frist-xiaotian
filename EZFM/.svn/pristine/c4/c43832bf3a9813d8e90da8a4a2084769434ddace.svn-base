package com.shareworx.ezfm.baseinfo.station.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYStationModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYStationDomainService.ID)
public class YJWYStationDomainServiceImpl implements YJWYStationDomainService {

	public final static String ID = "yJWYStationDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#save(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public List<YJWYStationModel> save(YJWYStationModel... models) throws ShareworxServiceException {
		return service.save(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#update(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public List<YJWYStationModel> update(YJWYStationModel... models) throws ShareworxServiceException {
		return service.update(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public List<YJWYStationModel> update(List<String> editFields, YJWYStationModel... models) throws ShareworxServiceException {
		return service.update(YJWYStationModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#delete(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel[])
	 */
	@Override
	public int delete(YJWYStationModel... models) throws ShareworxServiceException {
		return service.delete(YJWYStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYStationModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYStationModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService#queryByExample(com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel)
	 */
	@Override
	public List<YJWYStationModel> queryByExample(YJWYStationModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYStationModel.META_ID, model);
	}

}
