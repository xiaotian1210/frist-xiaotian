/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 标准版本实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class StandardEditionModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_quality_standardedition";
	
	/** 标准版本ID */
	public final static String PK_EDITION = "pk_edition";	
	/** 标准版本CODE */
	public final static String EDITION_CODE = "edition_code";	
	/** 标准版本NAME */
	public final static String EDITION_NAME = "edition_name";	
	/** 版本所属区域IDS(格式：'id3','id2','id3') */
	public final static String AREA_IDS = "area_ids";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 修改人 */
	public final static String UPDATE_USER = "update_user";	
	/** 修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 是否有效 */
	public final static String IS_VALID = "is_valid";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

	public StandardEditionModel() {
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
		return PK_EDITION;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_EDITION:
			return "pk_edition";
		case EDITION_CODE:
			return "edition_code";
		case EDITION_NAME:
			return "edition_name";
		case AREA_IDS:
			return "area_ids";
		case CREATE_USER:
			return "create_user";
		case CREATE_TIME:
			return "create_time";
		case UPDATE_USER:
			return "update_user";
		case UPDATE_TIME:
			return "update_time";
		case IS_VALID:
			return "is_valid";
		case PK_CROP:
			return "pk_crop";
		}
		return null;
	}

	/**
	 * 获取标准版本ID
	 * @return
	 */
	public String getPk_edition() {
		return getAttribute(PK_EDITION);
	}

	/**
	 * 设置标准版本ID
	 * @param pk_edition
	 */
	public void setPk_edition(String pk_edition) {
		setAttribute(PK_EDITION, pk_edition);
	}

	/**
	 * 获取标准版本CODE
	 * @return
	 */
	public String getEdition_code() {
		return getAttribute(EDITION_CODE);
	}

	/**
	 * 设置标准版本CODE
	 * @param edition_code
	 */
	public void setEdition_code(String edition_code) {
		setAttribute(EDITION_CODE, edition_code);
	}

	/**
	 * 获取标准版本NAME
	 * @return
	 */
	public String getEdition_name() {
		return getAttribute(EDITION_NAME);
	}

	/**
	 * 设置标准版本NAME
	 * @param edition_name
	 */
	public void setEdition_name(String edition_name) {
		setAttribute(EDITION_NAME, edition_name);
	}

	/**
	 * 获取版本所属区域IDS(格式：'id3','id2','id3')
	 * @return
	 */
	public String getArea_ids() {
		return getAttribute(AREA_IDS);
	}

	/**
	 * 设置版本所属区域IDS(格式：'id3','id2','id3')
	 * @param area_ids
	 */
	public void setArea_ids(String area_ids) {
		setAttribute(AREA_IDS, area_ids);
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
	 * 获取修改人
	 * @return
	 */
	public String getUpdate_user() {
		return getAttribute(UPDATE_USER);
	}

	/**
	 * 设置修改人
	 * @param update_user
	 */
	public void setUpdate_user(String update_user) {
		setAttribute(UPDATE_USER, update_user);
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
	 * 获取是否有效
	 * @return
	 */
	public String getIs_valid() {
		return getAttribute(IS_VALID);
	}

	/**
	 * 设置是否有效
	 * @param is_valid
	 */
	public void setIs_valid(String is_valid) {
		setAttribute(IS_VALID, is_valid);
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
