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
 * 资源属性与资源关联实体类
 * 
 * @author Administrator
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYResourceAttributePkResourceModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_resource_attribute_pk_resource";
	
	/** 新属性 */
	public final static String ID = "id";	
	/** 资源ID */
	public final static String PK_RESOURCE = "pk_resource";	
	/** 资源属性ID */
	public final static String PK_ATTRIBUTE = "pk_attribute";	

	public YJWYResourceAttributePkResourceModel() {
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
			return "id_";
		case PK_RESOURCE:
			return "pk_resource";
		case PK_ATTRIBUTE:
			return "pk_attribute";
		}
		return null;
	}

	/**
	 * 获取新属性
	 * @return
	 */
	public String getId() {
		return getAttribute(ID);
	}

	/**
	 * 设置新属性
	 * @param id
	 */
	public void setId(String id) {
		setAttribute(ID, id);
	}

	/**
	 * 获取资源ID
	 * @return
	 */
	public String getPk_resource() {
		return getAttribute(PK_RESOURCE);
	}

	/**
	 * 设置资源ID
	 * @param pk_resource
	 */
	public void setPk_resource(String pk_resource) {
		setAttribute(PK_RESOURCE, pk_resource);
	}

	/**
	 * 获取资源属性ID
	 * @return
	 */
	public String getPk_attribute() {
		return getAttribute(PK_ATTRIBUTE);
	}

	/**
	 * 设置资源属性ID
	 * @param pk_attribute
	 */
	public void setPk_attribute(String pk_attribute) {
		setAttribute(PK_ATTRIBUTE, pk_attribute);
	}

}