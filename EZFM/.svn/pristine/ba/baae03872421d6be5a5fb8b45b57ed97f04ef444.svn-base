package com.shareworx.ezfm.baseinfo.user.listener;

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
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessServiceImpl;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
//@Component
public class YJWYUserSaveBusinessListener extends BusinessAdapter<YJWYAreaModel>{


	@Override
	public String[] getServiceExpress() {
		return new String[]{YJWYUserBusinessServiceImpl.class.getName()};
	}
	
	@Override
	public String getMethodName() {
		return "save";
	}
	
	@Override
	public boolean doBefore(Object arg) throws ShareworxServiceException {
		YJWYUserModel[] models = (YJWYUserModel[]) arg;
		List<Condition> andList = new ArrayList<>();
		Query query = new Query();
		query.getMetas().add(YJWYUserModel.META_ID);
		andList.add(Condition.create("user_code_", models[0].getUser_code()));
		query.setAndList(andList);;
		YJWYUserDomainService service = SpringUtils.getBean(YJWYUserDomainService.class);	
		List<YJWYUserModel> l = service.queryListByCondition(query);
		if(!ArrayUtils.isEmpty(l)){
			throw new ShareworxServiceException("该账号已存在,请换个账号试试");
		}
		return true;
	}
	
}
