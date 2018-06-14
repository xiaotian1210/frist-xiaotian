/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.worktask.projectuser.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 项目接口人员实体类
 * 
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYWorkTaskProjectUserModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_worktask_project_user";
	
	/** 主键ID */
	public final static String PK_NEXUS_ID = "pk_nexus_id";	
	/** 项目ID */
	public final static String PROJECT_ID = "project_id";	
	/** 用户ID */
	public final static String USER_ID = "user_id";	
	/** 关系类型 */
	public final static String NEXUS_TYPE = "nexus_type";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人ID */
	public final static String CREATE_USER_ID = "create_user_id";	

	public YJWYWorkTaskProjectUserModel() {
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
		case NEXUS_TYPE:
			return "nexus_type";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER_ID:
			return "create_user_id";
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
	 * 获取关系类型
	 * @return
	 */
	public String getNexus_type() {
		return getAttribute(NEXUS_TYPE);
	}

	/**
	 * 设置关系类型
	 * @param nexus_type
	 */
	public void setNexus_type(String nexus_type) {
		setAttribute(NEXUS_TYPE, nexus_type);
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
	 * 获取创建人ID
	 * @return
	 */
	public String getCreate_user_id() {
		return getAttribute(CREATE_USER_ID);
	}

	/**
	 * 设置创建人ID
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		setAttribute(CREATE_USER_ID, create_user_id);
	}

}
