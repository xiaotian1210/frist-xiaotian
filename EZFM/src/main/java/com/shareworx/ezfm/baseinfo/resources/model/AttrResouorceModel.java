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
 * 资源_属性实体类
 * 
 * @author Administrator
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class AttrResouorceModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_attr_resource";
	
	/** 主键 */
	public final static String ID = "id";	
	/** 属性名称 */
	public final static String ATTR_NAME = "attr_name";	
	/** 属性值 */
	public final static String ATTR_VALUE = "attr_value";	
	/** 资源id */
	public final static String PK_RESOURCES = "pk_resources";	

	public AttrResouorceModel() {
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
		return ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case ID:
			return "id";
		case ATTR_NAME:
			return "attr_name";
		case ATTR_VALUE:
			return "attr_value";
		case PK_RESOURCES:
			return "pk_resources";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getId() {
		return getAttribute(ID);
	}

	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(String id) {
		setAttribute(ID, id);
	}

	/**
	 * 获取属性名称
	 * @return
	 */
	public String getAttr_name() {
		return getAttribute(ATTR_NAME);
	}

	/**
	 * 设置属性名称
	 * @param attr_name
	 */
	public void setAttr_name(String attr_name) {
		setAttribute(ATTR_NAME, attr_name);
	}

	/**
	 * 获取属性值
	 * @return
	 */
	public String getAttr_value() {
		return getAttribute(ATTR_VALUE);
	}

	/**
	 * 设置属性值
	 * @param attr_value
	 */
	public void setAttr_value(String attr_value) {
		setAttribute(ATTR_VALUE, attr_value);
	}

	/**
	 * 获取资源id
	 * @return
	 */
	public String getPk_resources() {
		return getAttribute(PK_RESOURCES);
	}

	/**
	 * 设置资源id
	 * @param pk_resources
	 */
	public void setPk_resources(String pk_resources) {
		setAttribute(PK_RESOURCES, pk_resources);
	}

}
