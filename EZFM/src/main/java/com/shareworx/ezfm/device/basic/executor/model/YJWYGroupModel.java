/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.device.basic.executor.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 巡检/维保人员分组实体类
 * 
 * @author jin.li
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYGroupModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_executor_group";
	
	/** 主键分组id */
	public final static String PK_GROUP = "pk_group";	
	/** 分组名称 */
	public final static String GROUP_NAME = "group_name";	
	/** 分组描述 */
	public final static String GROUP_DESC = "group_desc";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人id */
	public final static String CREATE_USER = "create_user";	
	/** 修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 修改人id */
	public final static String UPDATE_USER = "update_user";	
	/** 组内人员 */
	public final static String USERS = "users";	
	/** 下拉框选中标识 */
	public final static String SELECTED = "selected";	

	public YJWYGroupModel() {
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
		return PK_GROUP;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_GROUP:
			return "pk_group";
		case GROUP_NAME:
			return "group_name";
		case GROUP_DESC:
			return "group_desc";
		case PK_CROP:
			return "pk_crop";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER:
			return "create_user";
		case UPDATE_TIME:
			return "update_time";
		case UPDATE_USER:
			return "update_user";
		case USERS:
			return "users";
		case SELECTED:
			return "selected";
		}
		return null;
	}

	/**
	 * 获取主键分组id
	 * @return
	 */
	public String getPk_group() {
		return getAttribute(PK_GROUP);
	}

	/**
	 * 设置主键分组id
	 * @param pk_group
	 */
	public void setPk_group(String pk_group) {
		setAttribute(PK_GROUP, pk_group);
	}

	/**
	 * 获取分组名称
	 * @return
	 */
	public String getGroup_name() {
		return getAttribute(GROUP_NAME);
	}

	/**
	 * 设置分组名称
	 * @param group_name
	 */
	public void setGroup_name(String group_name) {
		setAttribute(GROUP_NAME, group_name);
	}

	/**
	 * 获取分组描述
	 * @return
	 */
	public String getGroup_desc() {
		return getAttribute(GROUP_DESC);
	}

	/**
	 * 设置分组描述
	 * @param group_desc
	 */
	public void setGroup_desc(String group_desc) {
		setAttribute(GROUP_DESC, group_desc);
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
	 * 获取创建人id
	 * @return
	 */
	public String getCreate_user() {
		return getAttribute(CREATE_USER);
	}

	/**
	 * 设置创建人id
	 * @param create_user
	 */
	public void setCreate_user(String create_user) {
		setAttribute(CREATE_USER, create_user);
	}

	/**
	 * 获取修改时间
	 * @return
	 */
	public String getUpdate_time() {
		return getAttribute(UPDATE_TIME);
	}

	/**
	 * 设置修改时间
	 * @param update_time
	 */
	public void setUpdate_time(String update_time) {
		setAttribute(UPDATE_TIME, update_time);
	}

	/**
	 * 获取修改人id
	 * @return
	 */
	public String getUpdate_user() {
		return getAttribute(UPDATE_USER);
	}

	/**
	 * 设置修改人id
	 * @param update_user
	 */
	public void setUpdate_user(String update_user) {
		setAttribute(UPDATE_USER, update_user);
	}

	/**
	 * 获取组内人员
	 * @return
	 */
	public String getUsers() {
		return getAttribute(USERS);
	}

	/**
	 * 设置组内人员
	 * @param users
	 */
	public void setUsers(String users) {
		setAttribute(USERS, users);
	}

	/**
	 * 获取下拉框选中标识
	 * @return
	 */
	public Boolean getSelected() {
		return getAttribute(SELECTED);
	}

	/**
	 * 设置下拉框选中标识
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		setAttribute(SELECTED, selected);
	}

}
