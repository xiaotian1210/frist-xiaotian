package com.shareworx.ezfm.baseinfo.area.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shareworx.platform.business.BusinessAdapter;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessServiceImpl;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
@Component
public class YJWYAreaDeleteListener extends BusinessAdapter<YJWYAreaModel> {

	@Override
	public String[] getServiceExpress() {
		return new String[]{YJWYAreaBusinessServiceImpl.class.getName()};
	}
	
	@Override
	public String getMethodName() {
		return "delete";
	}
	
	@Override
	public boolean doBefore(Object arg) throws ShareworxServiceException {
		YJWYAreaModel[] models = (YJWYAreaModel[]) arg;
		List<String> pks = new ArrayList<>();
		for (YJWYAreaModel model : models) {
			pks.add(model.getPk_area());
		}
		Query query1 = Query.from(YJWYProjectModel.META_ID).where(Condition.create("pk_area_").in(pks.toArray()));
		YJWYProjectDomainService service = SpringUtils.getBean(YJWYProjectDomainService.class);	
		List<YJWYProjectModel> pl = service.queryListByCondition(query1);
		if(!ArrayUtils.isEmpty(pl)){
			throw new ShareworxServiceException("要删除的对象存在项目引用关系");
		}
		
		Query query2 = Query.from(DefaultOrgModel.META_ID).where(Condition.create("org_area_").in(pks.toArray()));
		DefaultOrgDomainService service2 = SpringUtils.getBean(DefaultOrgDomainService.class);	
		List<DefaultOrgModel> ol = service2.queryListByCondition(query2);
		if(!ArrayUtils.isEmpty(ol)){
			throw new ShareworxServiceException("要删除的对象存在组织引用关系");
		}
		return true;
	}

}
