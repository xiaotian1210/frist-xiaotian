package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWJEnergyUpdateModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWJEnergyUpdateDomainService.ID)
public class YJWJEnergyUpdateDomainServiceImpl implements YJWJEnergyUpdateDomainService {

	public final static String ID = "yJWJEnergyUpdateDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#save(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public List<YJWJEnergyUpdateModel> save(YJWJEnergyUpdateModel... models) throws ShareworxServiceException {
		return service.save(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#update(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public List<YJWJEnergyUpdateModel> update(YJWJEnergyUpdateModel... models) throws ShareworxServiceException {
		return service.update(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#update(java.util.List, com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public List<YJWJEnergyUpdateModel> update(List<String> editFields, YJWJEnergyUpdateModel... models) throws ShareworxServiceException {
		return service.update(YJWJEnergyUpdateModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#delete(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public int delete(YJWJEnergyUpdateModel... models) throws ShareworxServiceException {
		return service.delete(YJWJEnergyUpdateModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWJEnergyUpdateModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWJEnergyUpdateModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWJEnergyUpdateModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWJEnergyUpdateModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWJEnergyUpdateModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateDomainService#queryByExample(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel)
	 */
	@Override
	public List<YJWJEnergyUpdateModel> queryByExample(YJWJEnergyUpdateModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWJEnergyUpdateModel.META_ID, model);
	}

}
