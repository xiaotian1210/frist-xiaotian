package com.shareworx.ezfm.baseinfo.org.service;

import java.util.List;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;

/**
 * 组织架构业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(DefaultOrgBusinessService.ID)
public class DefaultOrgBusinessServiceImpl implements DefaultOrgBusinessService {

	@Autowired
	@Qualifier(DefaultOrgDomainService.ID)
	private DefaultOrgDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	YJWYProjectDomainService projectDomainService;
	
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	YJWYAreaDomainService areaDomainService;
	
	public void setDomainService(DefaultOrgDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public DefaultOrgModel[] query(Query query) throws ShareworxServiceException {
		List<DefaultOrgModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new DefaultOrgModel[0];
		}
		return list.toArray(new DefaultOrgModel[0]);
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService#load(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel)
	 */
	@Override
	public DefaultOrgModel[] load(DefaultOrgModel model) throws ShareworxServiceException {
		List<DefaultOrgModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new DefaultOrgModel[0];
		}
		return list.toArray(new DefaultOrgModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService#save(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public DefaultOrgModel[] save(DefaultOrgModel[] models) throws ShareworxServiceException {
		List<DefaultOrgModel> list = domainService.save(models);
		return list.toArray(new DefaultOrgModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService#update(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public DefaultOrgModel[] update(DefaultOrgModel[] models) throws ShareworxServiceException {
		List<DefaultOrgModel> list = domainService.update(models);
		for(DefaultOrgModel item:list){
			YJWYProjectModel yjwyProjectModel = projectDomainService.queryById(item.getOrg_project());
			if(yjwyProjectModel != null){
				String name = yjwyProjectModel.getProject_name();
				item.put("project_name", name);
			}
			YJWYAreaModel yjwyAreaModel = areaDomainService.queryById(item.getOrg_area());
			if (yjwyAreaModel !=null){
				String area = yjwyAreaModel.getArea_name();
				item.put("area_name", area);
			}

		}
		return list.toArray(new DefaultOrgModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService#delete(com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel[])
	 */
	@Override
	public DefaultOrgModel[] delete(DefaultOrgModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
