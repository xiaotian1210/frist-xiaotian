package com.shareworx.ezfm.system.crop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.system.crop.model.CropModel;

@Service(ICropService.ID)
public class CropServiceImp implements ICropService {
	@Autowired
	@Qualifier(CropDomainService.ID)
	private CropDomainService domainService;
	
	public void setDomainService(CropDomainService domainService) {
		this.domainService = domainService;
	}
	/**
	 * 根据编码查询公司对象
	 * @param code
	 * @return
	 */
	@Override
	public CropModel queryForObject(String code){
		return domainService.queryOneByCondition(Query.from(CropModel.META_ID).and(Condition.create("crop_code_", code)));
	}
	
	/**
	 * 根据编码查询公司主键ID
	 * @param code
	 * @return
	 */
	@Override
	public String queryForId(String code){
		CropModel model = domainService.queryOneByCondition(Query.from(CropModel.META_ID).and(Condition.create("crop_code_", code)));
		if(null!=model && !StringUtils.isEmpty(model.getPk_crop())){
			return model.getPk_crop();
		}else{
			return null;
		}
	}
	
	/**
	 * 根据ID查询公司
	 * @param code
	 * @return
	 */
	@Override
	public CropModel queryForObjectById(String id){
		CropModel model = domainService.queryById(id);
		return model;
	}
}
