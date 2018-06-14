package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 能耗数据修改表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWJEnergyUpdateBusinessService.ID)
public class YJWJEnergyUpdateBusinessServiceImpl implements YJWJEnergyUpdateBusinessService {

	@Autowired
	@Qualifier(YJWJEnergyUpdateDomainService.ID)
	private YJWJEnergyUpdateDomainService domainService;
	
	public void setDomainService(YJWJEnergyUpdateDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWJEnergyUpdateModel[] query(Query query) throws ShareworxServiceException {
		List<YJWJEnergyUpdateModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWJEnergyUpdateModel[0];
		}
		return list.toArray(new YJWJEnergyUpdateModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService#load(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel)
	 */
	@Override
	public YJWJEnergyUpdateModel[] load(YJWJEnergyUpdateModel model) throws ShareworxServiceException {
		List<YJWJEnergyUpdateModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWJEnergyUpdateModel[0];
		}
		return list.toArray(new YJWJEnergyUpdateModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService#save(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public YJWJEnergyUpdateModel[] save(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		List<YJWJEnergyUpdateModel> list = domainService.save(models);
		return list.toArray(new YJWJEnergyUpdateModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService#update(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public YJWJEnergyUpdateModel[] update(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		List<YJWJEnergyUpdateModel> list = domainService.update(models);
		return list.toArray(new YJWJEnergyUpdateModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService#delete(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel[])
	 */
	@Override
	public YJWJEnergyUpdateModel[] delete(YJWJEnergyUpdateModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
