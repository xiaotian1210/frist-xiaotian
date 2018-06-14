/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.worktask.classpro.model;

import java.util.List;

import com.shareworx.platform.persist.DomainModel;

/**
 * 种类项目关联实体类
 * 
 * @author zhangjing.cheng
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYWorkTaskRepairClassProjectModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_worktask_repair_class_project";
	
	/** 主键ID */
	public final static String PK_NEXUS_ID = "pk_nexus_id";	
	/** 项目ID */
	public final static String PROJECT_ID = "project_id";	
	/** 种类ID */
	public final static String CLASS_ID = "class_id";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人ID */
	public final static String CREATE_USER_ID = "create_user_id";	
	/** 最后修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 最后修改人ID */
	public final static String UPDATE_USER_ID = "update_user_id";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

/**
	 * 非持久化数据
	 */
	public final static String PROJECTIDS = "projectids";
	public YJWYWorkTaskRepairClassProjectModel() {
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
		case CLASS_ID:
			return "class_id";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER_ID:
			return "create_user_id";
		case UPDATE_TIME:
			return "update_time";
		case UPDATE_USER_ID:
			return "update_user_id";
		case PK_CROP:
			return "pk_crop";
	case PROJECTIDS:
			return "projectids";
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
	 * 获取种类ID
	 * @return
	 */
	public String getClass_id() {
		return getAttribute(CLASS_ID);
	}

	/**
	 * 设置种类ID
	 * @param class_id
	 */
	public void setClass_id(String class_id) {
		setAttribute(CLASS_ID, class_id);
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

	/**
	 * 获取最后修改时间
	 * @return
	 */
	public String getUpdate_time() {
		return getAttribute(UPDATE_TIME);
	}

	/**
	 * 设置最后修改时间
	 * @param update_time
	 */
	public void setUpdate_time(String update_time) {
		setAttribute(UPDATE_TIME, update_time);
	}

	/**
	 * 获取最后修改人ID
	 * @return
	 */
	public String getUpdate_user_id() {
		return getAttribute(UPDATE_USER_ID);
	}

	/**
	 * 设置最后修改人ID
	 * @param update_user_id
	 */
	public void setUpdate_user_id(String update_user_id) {
		setAttribute(UPDATE_USER_ID, update_user_id);
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
	 * 获取分类
	 * @return
	 */
	public List<Object> getProjectids() {
		return getAttribute(PROJECTIDS);
	}
	/**
	 * 设置分类
	 * @param pk_crop
	 */
	public void setProjectids(String projectids) {
		setAttribute(PROJECTIDS, projectids);
	}
}
