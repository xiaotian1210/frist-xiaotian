/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.resources.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 资源管理实体类
 * 
 * @author Administrator
 * @version since Shareworx platform 2.0
 */
public class YJWYResourcesModel extends DomainModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_resources";
	
	/** 主键ID */
	public final static String PK_RESOURCES = "pk_resources";	
	/** 外键(公司) */
	public final static String PK_CROP = "pk_crop";	
	/** 资源名称 */
	public final static String RESOURCES_NAME = "resources_name";	
	/** 资源编码 */
	public final static String RESOURCES_CODE = "resources_code";	
	/** 资源类型 */
	public final static String RESOURCES_TYPE = "resources_type";	
	/** 资源状态 */
	public final static String RESOURCES_STATE = "resources_state";	
	/** 户型 */
	public final static String APARTMENT_LAYOUT = "apartment_layout";	
	/** 父ID */
	public final static String PARENT_ID = "parent_id";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人ID */
	public final static String CREATE_USER = "create_user";	
	/** 最后修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 最后修改人 */
	public final static String UPDATE_USER = "update_user";	
	/** 项目ID */
	public final static String PROJECT_ID = "project_id";	
	/** BIM模型关联号 */
	public final static String EQ_CODE = "eq_code";	
	/** 资源l类型名称 */
	public final static String RESOURCETYPE_NAME = "resourcetype_name";	
	/** 资源类型编码 */
	public final static String RESOURCETYPE_CODE = "resourcetype_code";	

	public YJWYResourcesModel() {
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
		return PK_RESOURCES;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_RESOURCES:
			return "pk_resources";
		case PK_CROP:
			return "pk_crop";
		case RESOURCES_NAME:
			return "resources_name";
		case RESOURCES_CODE:
			return "resources_code";
		case RESOURCES_TYPE:
			return "resources_type";
		case RESOURCES_STATE:
			return "resources_state";
		case APARTMENT_LAYOUT:
			return "apartment_layout";
		case PARENT_ID:
			return "parent_id";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER:
			return "create_user";
		case UPDATE_TIME:
			return "update_time";
		case UPDATE_USER:
			return "update_user";
		case PROJECT_ID:
			return "project_id";
		case EQ_CODE:
			return "eq_code_";
		case RESOURCETYPE_NAME:
			return "resourcetype_name";
		case RESOURCETYPE_CODE:
			return "resourcetype_code";
		}
		return null;
	}

	/**
	 * 获取主键ID
	 * @return
	 */
	public String getPk_resources() {
		return getAttribute(PK_RESOURCES);
	}

	/**
	 * 设置主键ID
	 * @param pk_resources
	 */
	public void setPk_resources(String pk_resources) {
		setAttribute(PK_RESOURCES, pk_resources);
	}

	/**
	 * 获取外键(公司)
	 * @return
	 */
	public String getPk_crop() {
		return getAttribute(PK_CROP);
	}

	/**
	 * 设置外键(公司)
	 * @param pk_crop
	 */
	public void setPk_crop(String pk_crop) {
		setAttribute(PK_CROP, pk_crop);
	}

	/**
	 * 获取资源名称
	 * @return
	 */
	public String getResources_name() {
		return getAttribute(RESOURCES_NAME);
	}

	/**
	 * 设置资源名称
	 * @param resources_name
	 */
	public void setResources_name(String resources_name) {
		setAttribute(RESOURCES_NAME, resources_name);
	}

	/**
	 * 获取资源编码
	 * @return
	 */
	public String getResources_code() {
		return getAttribute(RESOURCES_CODE);
	}

	/**
	 * 设置资源编码
	 * @param resources_code
	 */
	public void setResources_code(String resources_code) {
		setAttribute(RESOURCES_CODE, resources_code);
	}

	/**
	 * 获取资源类型
	 * @return
	 */
	public String getResources_type() {
		return getAttribute(RESOURCES_TYPE);
	}

	/**
	 * 设置资源类型
	 * @param resources_type
	 */
	public void setResources_type(String resources_type) {
		setAttribute(RESOURCES_TYPE, resources_type);
	}

	/**
	 * 获取资源状态
	 * @return
	 */
	public Integer getResources_state() {
		return getAttribute(RESOURCES_STATE);
	}

	/**
	 * 设置资源状态
	 * @param resources_state
	 */
	public void setResources_state(Integer resources_state) {
		setAttribute(RESOURCES_STATE, resources_state);
	}

	/**
	 * 获取户型
	 * @return
	 */
	public String getApartment_layout() {
		return getAttribute(APARTMENT_LAYOUT);
	}

	/**
	 * 设置户型
	 * @param apartment_layout
	 */
	public void setApartment_layout(String apartment_layout) {
		setAttribute(APARTMENT_LAYOUT, apartment_layout);
	}

	/**
	 * 获取父ID
	 * @return
	 */
	public String getParent_id() {
		return getAttribute(PARENT_ID);
	}

	/**
	 * 设置父ID
	 * @param parent_id
	 */
	public void setParent_id(String parent_id) {
		setAttribute(PARENT_ID, parent_id);
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
	public String getCreate_user() {
		return getAttribute(CREATE_USER);
	}

	/**
	 * 设置创建人ID
	 * @param create_user
	 */
	public void setCreate_user(String create_user) {
		setAttribute(CREATE_USER, create_user);
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
	 * 获取最后修改人
	 * @return
	 */
	public String getUpdate_user() {
		return getAttribute(UPDATE_USER);
	}

	/**
	 * 设置最后修改人
	 * @param update_user
	 */
	public void setUpdate_user(String update_user) {
		setAttribute(UPDATE_USER, update_user);
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
	 * 获取BIM模型关联号
	 * @return
	 */
	public String getEq_code() {
		return getAttribute(EQ_CODE);
	}

	/**
	 * 设置BIM模型关联号
	 * @param eq_code
	 */
	public void setEq_code(String eq_code) {
		setAttribute(EQ_CODE, eq_code);
	}

	/**
	 * 获取资源l类型名称
	 * @return
	 */
	public String getResourcetype_name() {
		return getAttribute(RESOURCETYPE_NAME);
	}

	/**
	 * 设置资源l类型名称
	 * @param resourcetype_name
	 */
	public void setResourcetype_name(String resourcetype_name) {
		setAttribute(RESOURCETYPE_NAME, resourcetype_name);
	}

	/**
	 * 获取资源类型编码
	 * @return
	 */
	public String getResourcetype_code() {
		return getAttribute(RESOURCETYPE_CODE);
	}

	/**
	 * 设置资源类型编码
	 * @param resourcetype_code
	 */
	public void setResourcetype_code(String resourcetype_code) {
		setAttribute(RESOURCETYPE_CODE, resourcetype_code);
	}

}