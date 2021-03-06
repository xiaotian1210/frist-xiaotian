/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.resourceslog.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 记录资源空间日志表实体类
 * 
 * @author kimguo
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYResourcesLogModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_resources_log";
	
	/** 记录操作的资源空间ID */
	public final static String PK_RESOURCES_LOG = "pk_resources_log";	
	/** 资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除) */
	public final static String OPERATE_TYPE = "operate_type";	
	/** 操作的时间 */
	public final static String OPERATE_DATE = "operate_date";	

	public YJWYResourcesLogModel() {
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
		return PK_RESOURCES_LOG;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_RESOURCES_LOG:
			return "pk_resources_log";
		case OPERATE_TYPE:
			return "operate_type";
		case OPERATE_DATE:
			return "operate_date";
		}
		return null;
	}

	/**
	 * 获取记录操作的资源空间ID
	 * @return
	 */
	public String getPk_resources_log() {
		return getAttribute(PK_RESOURCES_LOG);
	}

	/**
	 * 设置记录操作的资源空间ID
	 * @param pk_resources_log
	 */
	public void setPk_resources_log(String pk_resources_log) {
		setAttribute(PK_RESOURCES_LOG, pk_resources_log);
	}

	/**
	 * 获取资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除) 
	 * @return
	 */
	public Integer getOperate_type() {
		return getAttribute(OPERATE_TYPE);
	}

	/**
	 * 设置资源空间操作类型(1代表 新增 2代表 删除 3代表更新 4代表保存之后的更新 5代表保存之后的删除)
	 * @param operate_type
	 */
	public void setOperate_type(Integer operate_type) {
		setAttribute(OPERATE_TYPE, operate_type);
	}

	/**
	 * 获取操作的时间
	 * @return
	 */
	public String getOperate_date() {
		return getAttribute(OPERATE_DATE);
	}

	/**
	 * 设置操作的时间
	 * @param operate_date
	 */
	public void setOperate_date(String operate_date) {
		setAttribute(OPERATE_DATE, operate_date);
	}

}
