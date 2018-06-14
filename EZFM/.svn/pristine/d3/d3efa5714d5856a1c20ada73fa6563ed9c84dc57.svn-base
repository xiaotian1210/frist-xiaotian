package com.shareworx.ezfm.baseinfo.user.service;

import org.springframework.stereotype.Service;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
/**
 * 用户删除操作 
 * @author dms
 */
@Service(YJWYUserDeleteService.ID)
public class YJWYUserDeleteService {
	public static final String ID = "YJWYUserDeleteServiceID";
	/**
	 * 用户删除前操作
	 * @return
	 */
	public ModelAndResult beforeDete(YJWYUserModel[] models){
		String inSql = "";
		for(YJWYUserModel model : models){
			inSql = inSql+","+model.getPk_user();
		}
		String[] inFiled = inSql.substring(1).split(",");
		//所有查询走Condition.in("field",inFied);查询到关联数据，不让删除返回false
		return new ModelAndResult(true,"用户可以删除");
		//return new ModelAndResult(false,"用户已被XX使用，请先移除XX的使用");
	}
	
}
