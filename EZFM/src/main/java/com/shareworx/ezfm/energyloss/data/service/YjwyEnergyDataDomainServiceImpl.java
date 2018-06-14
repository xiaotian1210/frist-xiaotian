package com.shareworx.ezfm.energyloss.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YjwyEnergyDataModel领域操作实现
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
@Service(YjwyEnergyDataDomainService.ID)
public class YjwyEnergyDataDomainServiceImpl implements YjwyEnergyDataDomainService {

	public final static String ID = "yjwyEnergyDataDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#save(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public List<YjwyEnergyDataModel> save(YjwyEnergyDataModel... models) throws ShareworxServiceException {
		return service.save(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#update(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public List<YjwyEnergyDataModel> update(YjwyEnergyDataModel... models) throws ShareworxServiceException {
		return service.update(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#update(java.util.List, com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public List<YjwyEnergyDataModel> update(List<String> editFields, YjwyEnergyDataModel... models) throws ShareworxServiceException {
		return service.update(YjwyEnergyDataModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#delete(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public int delete(YjwyEnergyDataModel... models) throws ShareworxServiceException {
		return service.delete(YjwyEnergyDataModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YjwyEnergyDataModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#queryById(java.lang.String)
	 */
	@Override
	public YjwyEnergyDataModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YjwyEnergyDataModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YjwyEnergyDataModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YjwyEnergyDataModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService#queryByExample(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel)
	 */
	@Override
	public List<YjwyEnergyDataModel> queryByExample(YjwyEnergyDataModel model) throws ShareworxServiceException {
		return service.queryByExample(YjwyEnergyDataModel.META_ID, model);
	}

}
