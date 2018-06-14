/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.role.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 角色实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYRoleModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_role";
	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 主键 */
	public final static String PK_ROLE = "pk_role";	
	/** 角色编码 */
	public final static String ROLE_CODE = "role_code";	
	/** 角色名称 */
	public final static String ROLE_NAME = "role_name";	
	/** 系统预置 */
	public final static String IS_PRE = "is_pre";	
	/** 描述 */
	public final static String MEMO = "memo";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 最后修改人 */
	public final static String LAST_MODIFY_USER = "last_modify_user";	
	/** 最后修改时间 */
	public final static String LAST_MODIFY_TIME = "last_modify_time";	
	/** 更新时间 */
	public final static String UPDATE_TIME = "update_time";	

	public YJWYRoleModel() {
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
		return PK_ROLE;
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
		case ROLE_CODE:
			return "role_code_";
		case ROLE_NAME:
			return "role_name_";
		case IS_PRE:
			return "is_pre_";
		case MEMO:
			return "memo_";
		case CREATE_USER:
			return "create_user_";
		case CREATE_TIME:
			return "create_time_";
		case LAST_MODIFY_USER:
			return "last_modify_user_";
		case LAST_MODIFY_TIME:
			return "last_modify_time_";
		case UPDATE_TIME:
			return "update_time_";
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
	 * 获取主键
	 * @return
	 */
	public String getPk_role() {
		return getAttribute(PK_ROLE);
	}

	/**
	 * 设置主键
	 * @param pk_role
	 */
	public void setPk_role(String pk_role) {
		setAttribute(PK_ROLE, pk_role);
	}

	/**
	 * 获取角色编码
	 * @return
	 */
	public String getRole_code() {
		return getAttribute(ROLE_CODE);
	}

	/**
	 * 设置角色编码
	 * @param role_code
	 */
	public void setRole_code(String role_code) {
		setAttribute(ROLE_CODE, role_code);
	}

	/**
	 * 获取角色名称
	 * @return
	 */
	public String getRole_name() {
		return getAttribute(ROLE_NAME);
	}

	/**
	 * 设置角色名称
	 * @param role_name
	 */
	public void setRole_name(String role_name) {
		setAttribute(ROLE_NAME, role_name);
	}

	/**
	 * 获取系统预置
	 * @return
	 */
	public Boolean getIs_pre() {
		return getAttribute(IS_PRE);
	}

	/**
	 * 设置系统预置
	 * @param is_pre
	 */
	public void setIs_pre(Boolean is_pre) {
		setAttribute(IS_PRE, is_pre);
	}

	/**
	 * 获取描述
	 * @return
	 */
	public String getMemo() {
		return getAttribute(MEMO);
	}

	/**
	 * 设置描述
	 * @param memo
	 */
	public void setMemo(String memo) {
		setAttribute(MEMO, memo);
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
	 * 获取最后修改人
	 * @return
	 */
	public String getLast_modify_user() {
		return getAttribute(LAST_MODIFY_USER);
	}

	/**
	 * 设置最后修改人
	 * @param last_modify_user
	 */
	public void setLast_modify_user(String last_modify_user) {
		setAttribute(LAST_MODIFY_USER, last_modify_user);
	}

	/**
	 * 获取最后修改时间
	 * @return
	 */
	public String getLast_modify_time() {
		return getAttribute(LAST_MODIFY_TIME);
	}

	/**
	 * 设置最后修改时间
	 * @param last_modify_time
	 */
	public void setLast_modify_time(String last_modify_time) {
		setAttribute(LAST_MODIFY_TIME, last_modify_time);
	}

	/**
	 * 获取更新时间
	 * @return
	 */
	public String getUpdate_time() {
		return getAttribute(UPDATE_TIME);
	}

	/**
	 * 设置更新时间
	 * @param update_time
	 */
	public void setUpdate_time(String update_time) {
		setAttribute(UPDATE_TIME, update_time);
	}

}
