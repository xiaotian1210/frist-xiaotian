package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYEnergyElectricModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEnergyElectricDomainService.ID)
public class YJWYEnergyElectricDomainServiceImpl implements YJWYEnergyElectricDomainService {

	public final static String ID = "yJWYEnergyElectricDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#save(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public List<YJWYEnergyElectricModel> save(YJWYEnergyElectricModel... models) throws ShareworxServiceException {
		return service.save(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#update(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public List<YJWYEnergyElectricModel> update(YJWYEnergyElectricModel... models) throws ShareworxServiceException {
		return service.update(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#update(java.util.List, com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public List<YJWYEnergyElectricModel> update(List<String> editFields, YJWYEnergyElectricModel... models) throws ShareworxServiceException {
		return service.update(YJWYEnergyElectricModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#delete(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public int delete(YJWYEnergyElectricModel... models) throws ShareworxServiceException {
		return service.delete(YJWYEnergyElectricModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYEnergyElectricModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYEnergyElectricModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYEnergyElectricModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYEnergyElectricModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYEnergyElectricModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService#queryByExample(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel)
	 */
	@Override
	public List<YJWYEnergyElectricModel> queryByExample(YJWYEnergyElectricModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYEnergyElectricModel.META_ID, model);
	}

}
