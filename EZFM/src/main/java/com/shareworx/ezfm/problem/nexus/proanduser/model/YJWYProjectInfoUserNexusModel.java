/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.problem.nexus.proanduser.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 接口人员关系表实体类
 * 
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYProjectInfoUserNexusModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_proinfo_user_nexus";
	
	/** 主键ID */
	public final static String PK_NEXUS_ID = "pk_nexus_id";	
	/** 项目ID */
	public final static String PROJECT_ID = "project_id";	
	/** 用户ID */
	public final static String USER_ID = "user_id";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人员ID */
	public final static String CREATE_USER_ID = "create_user_id";	
	/** 创建人姓名 */
	public final static String CREATE_USER_NAME = "create_user_name";	
	/** 修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 修改人ID */
	public final static String UPDATE_USER_ID = "update_user_id";	
	/** 修改人姓名 */
	public final static String UPDATE_USER_NAME = "update_user_name";	

	public YJWYProjectInfoUserNexusModel() {
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
		return PK_NEXUS_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_NEXUS_ID:
			return "pk_nexus_id";
		case PROJECT_ID:
			return "project_id";
		case USER_ID:
			return "user_id";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER_ID:
			return "create_user_id";
		case CREATE_USER_NAME:
			return "create_user_name";
		case UPDATE_TIME:
			return "update_time";
		case UPDATE_USER_ID:
			return "update_user_id";
		case UPDATE_USER_NAME:
			return "update_user_name";
		}
		return null;
	}

	/**
	 * 获取主键ID
	 * @return
	 */
	public String getPk_nexus_id() {
		return getAttribute(PK_NEXUS_ID);
	}

	/**
	 * 设置主键ID
	 * @param pk_nexus_id
	 */
	public void setPk_nexus_id(String pk_nexus_id) {
		setAttribute(PK_NEXUS_ID, pk_nexus_id);
	}

	/**
	 * 获取项目ID
	 * @return
	 */
	public String getProject_id() {
		return getAttribute(PROJECT_ID);
	}

	/**
	 * 设置项目ID
	 * @param project_id
	 */
	public void setProject_id(String project_id) {
		setAttribute(PROJECT_ID, project_id);
	}

	/**
	 * 获取用户ID
	 * @return
	 */
	public String getUser_id() {
		return getAttribute(USER_ID);
	}

	/**
	 * 设置用户ID
	 * @param user_id
	 */
	public void setUser_id(String user_id) {
		setAttribute(USER_ID, user_id);
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
	 * 获取创建人员ID
	 * @return
	 */
	public String getCreate_user_id() {
		return getAttribute(CREATE_USER_ID);
	}

	/**
	 * 设置创建人员ID
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		setAttribute(CREATE_USER_ID, create_user_id);
	}

	/**
	 * 获取创建人姓名
	 * @return
	 */
	public String getCreate_user_name() {
		return getAttribute(CREATE_USER_NAME);
	}

	/**
	 * 设置创建人姓名
	 * @param create_user_name
	 */
	public void setCreate_user_name(String create_user_name) {
		setAttribute(CREATE_USER_NAME, create_user_name);
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
	 * 获取修改人ID
	 * @return
	 */
	public String getUpdate_user_id() {
		return getAttribute(UPDATE_USER_ID);
	}

	/**
	 * 设置修改人ID
	 * @param update_user_id
	 */
	public void setUpdate_user_id(String update_user_id) {
		setAttribute(UPDATE_USER_ID, update_user_id);
	}

	/**
	 * 获取修改人姓名
	 * @return
	 */
	public String getUpdate_user_name() {
		return getAttribute(UPDATE_USER_NAME);
	}

	/**
	 * 设置修改人姓名
	 * @param update_user_name
	 */
	public void setUpdate_user_name(String update_user_name) {
		setAttribute(UPDATE_USER_NAME, update_user_name);
	}

}