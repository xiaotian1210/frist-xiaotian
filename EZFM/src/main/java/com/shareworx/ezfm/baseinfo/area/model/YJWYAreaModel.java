/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.area.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 区域管理实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYAreaModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_area";
	
	/** 主键 */
	public final static String PK_AREA = "pk_area";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 区域名称 */
	public final static String AREA_NAME = "area_name";	
	/** 区域编码 */
	public final static String AREA_CODE = "area_code";	
	/** 区域描述 */
	public final static String AREA_MEMO = "area_memo";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 最后修改人 */
	public final static String LAST_MODIFY_USER = "last_modify_user";	
	/** 最后修改时间 */
	public final static String LAST_MODIFY_TIME = "last_modify_time";	
	/** 更新时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 删除标志，1已删除，0未删除 */
	public final static String DELETE_FLAG = "delete_flag";	
	/** 选中标识(供页面下拉框使用) */
	public final static String SELECTED = "selected";	

	public YJWYAreaModel() {
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
		return PK_AREA;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_AREA:
			return "pk_area_";
		case PK_CROP:
			return "pk_crop_";
		case AREA_NAME:
			return "area_name_";
		case AREA_CODE:
			return "area_code_";
		case AREA_MEMO:
			return "area_memo_";
		case CREATE_USER:
			return "create_user_";
		case CREATE_TIME:
			return "create_time_";
		case LAST_MODIFY_USER:
			return "last_modify_user_";
		case LAST_MODIFY_TIME:
			return "last_modify_time_";
		case UPDATE_TIME:
			return "update_time_";
		case DELETE_FLAG:
			return "delete_flag_";
		case SELECTED:
			return "selected_";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getPk_area() {
		return getAttribute(PK_AREA);
	}

	/**
	 * 设置主键
	 * @param pk_area
	 */
	public void setPk_area(String pk_area) {
		setAttribute(PK_AREA, pk_area);
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
	 * 获取区域名称
	 * @return
	 */
	public String getArea_name() {
		return getAttribute(AREA_NAME);
	}

	/**
	 * 设置区域名称
	 * @param area_name
	 */
	public void setArea_name(String area_name) {
		setAttribute(AREA_NAME, area_name);
	}

	/**
	 * 获取区域编码
	 * @return
	 */
	public String getArea_code() {
		return getAttribute(AREA_CODE);
	}

	/**
	 * 设置区域编码
	 * @param area_code
	 */
	public void setArea_code(String area_code) {
		setAttribute(AREA_CODE, area_code);
	}

	/**
	 * 获取区域描述
	 * @return
	 */
	public String getArea_memo() {
		return getAttribute(AREA_MEMO);
	}

	/**
	 * 设置区域描述
	 * @param area_memo
	 */
	public void setArea_memo(String area_memo) {
		setAttribute(AREA_MEMO, area_memo);
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
	 * 获取最后修改人
	 * @return
	 */
	public String getLast_modify_user() {
		return getAttribute(LAST_MODIFY_USER);
	}

	/**
	 * 设置最后修改人
	 * @param last_modify_user
	 */
	public void setLast_modify_user(String last_modify_user) {
		setAttribute(LAST_MODIFY_USER, last_modify_user);
	}

	/**
	 * 获取最后修改时间
	 * @return
	 */
	public String getLast_modify_time() {
		return getAttribute(LAST_MODIFY_TIME);
	}

	/**
	 * 设置最后修改时间
	 * @param last_modify_time
	 */
	public void setLast_modify_time(String last_modify_time) {
		setAttribute(LAST_MODIFY_TIME, last_modify_time);
	}

	/**
	 * 获取更新时间
	 * @return
	 */
	public String getUpdate_time() {
		return getAttribute(UPDATE_TIME);
	}

	/**
	 * 设置更新时间
	 * @param update_time
	 */
	public void setUpdate_time(String update_time) {
		setAttribute(UPDATE_TIME, update_time);
	}

	/**
	 * 获取删除标志，1已删除，0未删除
	 * @return
	 */
	public String getDelete_flag() {
		return getAttribute(DELETE_FLAG);
	}

	/**
	 * 设置删除标志，1已删除，0未删除
	 * @param delete_flag
	 */
	public void setDelete_flag(String delete_flag) {
		setAttribute(DELETE_FLAG, delete_flag);
	}

	/**
	 * 获取选中标识(供页面下拉框使用)
	 * @return
	 */
	public Boolean getSelected() {
		return getAttribute(SELECTED);
	}

	/**
	 * 设置选中标识(供页面下拉框使用)
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		setAttribute(SELECTED, selected);
	}

}