/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.device.fmdata.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * FM项目表实体类
 * 
 * @author jin.li
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYSiteModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_fmdata_site";
	
	/** 项目id */
	public final static String SITE_ID = "site_id";	
	/** 项目类型 */
	public final static String FORMATS = "formats";	
	/** 项目地址 */
	public final static String ADDRESS = "address";	
	/** 项目名称 */
	public final static String NAME = "name";	
	/** 最后更改时间 */
	public final static String DMS_UPDATE_TIME = "dms_update_time";	
	/** 有效标识 */
	public final static String FLAG = "flag";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

	public YJWYSiteModel() {
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
		return SITE_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case SITE_ID:
			return "site_id";
		case FORMATS:
			return "formats";
		case ADDRESS:
			return "address";
		case NAME:
			return "name";
		case DMS_UPDATE_TIME:
			return "dms_update_time";
		case FLAG:
			return "flag";
		case PK_CROP:
			return "pk_crop";
		}
		return null;
	}

	/**
	 * 获取项目id
	 * @return
	 */
	public String getSite_id() {
		return getAttribute(SITE_ID);
	}

	/**
	 * 设置项目id
	 * @param site_id
	 */
	public void setSite_id(String site_id) {
		setAttribute(SITE_ID, site_id);
	}

	/**
	 * 获取项目类型
	 * @return
	 */
	public String getFormats() {
		return getAttribute(FORMATS);
	}

	/**
	 * 设置项目类型
	 * @param formats
	 */
	public void setFormats(String formats) {
		setAttribute(FORMATS, formats);
	}

	/**
	 * 获取项目地址
	 * @return
	 */
	public String getAddress() {
		return getAttribute(ADDRESS);
	}

	/**
	 * 设置项目地址
	 * @param address
	 */
	public void setAddress(String address) {
		setAttribute(ADDRESS, address);
	}

	/**
	 * 获取项目名称
	 * @return
	 */
	public String getName() {
		return getAttribute(NAME);
	}

	/**
	 * 设置项目名称
	 * @param name
	 */
	public void setName(String name) {
		setAttribute(NAME, name);
	}

	/**
	 * 获取最后更改时间
	 * @return
	 */
	public String getDms_update_time() {
		return getAttribute(DMS_UPDATE_TIME);
	}

	/**
	 * 设置最后更改时间
	 * @param dms_update_time
	 */
	public void setDms_update_time(String dms_update_time) {
		setAttribute(DMS_UPDATE_TIME, dms_update_time);
	}

	/**
	 * 获取有效标识
	 * @return
	 */
	public Integer getFlag() {
		return getAttribute(FLAG);
	}

	/**
	 * 设置有效标识
	 * @param flag
	 */
	public void setFlag(Integer flag) {
		setAttribute(FLAG, flag);
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

}
