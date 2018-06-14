/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.rolefunc.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * APP手机权限实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYAPPRoleFuncModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_app_role_func";
	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 角色 */
	public final static String PK_ROLE = "pk_role";	
	/** 功能 */
	public final static String PK_FUNC = "pk_func";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	

	public YJWYAPPRoleFuncModel() {
		super(META_ID);
	}
	
	/**
	 * 获取元数据名称
	 */
	@Override
	public String getMetaName() {
		return META_ID;
	}
	
	/**
	 * 获取实体主键
	 */
	public String getPrimaryKey() {
		return PK_CROP;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_CROP:
			return "pk_crop_";
		case PK_ROLE:
			return "pk_role_";
		case PK_FUNC:
			return "pk_func_";
		case CREATE_TIME:
			return "create_time_";
		case CREATE_USER:
			return "create_user_";
		}
		return null;
	}

	/**
	 * 获取所属公司
	 * @return
	 */
	public String getPk_crop() {
		return getAttribute(PK_CROP);
	}

	/**
	 * 设置所属公司
	 * @param pk_crop
	 */
	public void setPk_crop(String pk_crop) {
		setAttribute(PK_CROP, pk_crop);
	}

	/**
	 * 获取角色
	 * @return
	 */
	public String getPk_role() {
		return getAttribute(PK_ROLE);
	}

	/**
	 * 设置角色
	 * @param pk_role
	 */
	public void setPk_role(String pk_role) {
		setAttribute(PK_ROLE, pk_role);
	}

	/**
	 * 获取功能
	 * @return
	 */
	public String getPk_func() {
		return getAttribute(PK_FUNC);
	}

	/**
	 * 设置功能
	 * @param pk_func
	 */
	public void setPk_func(String pk_func) {
		setAttribute(PK_FUNC, pk_func);
	}

	/**
	 * 获取创建时间
	 * @return
	 */
	public String getCreate_time() {
		return getAttribute(CREATE_TIME);
	}

	/**
	 * 设置创建时间
	 * @param create_time
	 */
	public void setCreate_time(String create_time) {
		setAttribute(CREATE_TIME, create_time);
	}

	/**
	 * 获取创建人
	 * @return
	 */
	public String getCreate_user() {
		return getAttribute(CREATE_USER);
	}

	/**
	 * 设置创建人
	 * @param create_user
	 */
	public void setCreate_user(String create_user) {
		setAttribute(CREATE_USER, create_user);
	}

}
