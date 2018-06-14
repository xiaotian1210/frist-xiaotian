/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.project.model;

import com.shareworx.platform.persist.DomainModel;
import java.math.BigDecimal;

/**
 * 项目管理实体类
 * 
 * @author zhi.zhang
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYProjectModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_project";
	
	/** 主键 */
	public final static String PK_PROJECT = "pk_project";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 所属区域 */
	public final static String PK_AREA = "pk_area";	
	/** 项目名称 */
	public final static String PROJECT_NAME = "project_name";	
	/** 项目编码 */
	public final static String PROJECT_CODE = "project_code";	
	/** 项目描述 */
	public final static String PROJECT_MEMO = "project_memo";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 最后修改人 */
	public final static String LAST_MODIFY_USER = "last_modify_user";	
	/** 最后修改时间 */
	public final static String LAST_MODIFY_TIME = "last_modify_time";	
	/** 占地面积 */
	public final static String PROJECT_COVER_AREA = "project_cover_area";	
	/** 建筑面积 */
	public final static String PROJECT_BUILD_AREA = "project_build_area";	
	/** 项目地址 */
	public final static String PROJECT_ADD = "project_add";	
	/** 更新时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 选中标识 */
	public final static String SELECTED = "selected";	
	/** 删除标志，1.已删除，0.未删除 */
	public final static String DELETE_FLAG = "delete_flag";	
	/** 省份ID */
	public final static String PK_PROVINCE = "pk_province";	
	/** 城市ID */
	public final static String PK_CITY = "pk_city";	
	/** 纬度 */
	public final static String SITE_LAT = "site_lat";	
	/** 经度 */
	public final static String SITE_LON = "site_lon";	
	/** 围栏 */
	public final static String SITE_RAILS = "site_rails";	
	/** 项目标记 */
	public final static String PROJECT_REMARK = "project_remark";	
	/** bim模型 */
	public final static String BIM_URL = "bim_url";	

	public YJWYProjectModel() {
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
		return PK_PROJECT;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_PROJECT:
			return "pk_project_";
		case PK_CROP:
			return "pk_crop_";
		case PK_AREA:
			return "pk_area_";
		case PROJECT_NAME:
			return "project_name_";
		case PROJECT_CODE:
			return "project_code_";
		case PROJECT_MEMO:
			return "project_memo_";
		case CREATE_USER:
			return "create_user_";
		case CREATE_TIME:
			return "create_time_";
		case LAST_MODIFY_USER:
			return "last_modify_user_";
		case LAST_MODIFY_TIME:
			return "last_modify_time_";
		case PROJECT_COVER_AREA:
			return "project_cover_area_";
		case PROJECT_BUILD_AREA:
			return "project_build_area_";
		case PROJECT_ADD:
			return "project_add_";
		case UPDATE_TIME:
			return "update_time_";
		case SELECTED:
			return "selected_";
		case DELETE_FLAG:
			return "delete_flag_";
		case PK_PROVINCE:
			return "pk_province_";
		case PK_CITY:
			return "pk_city_";
		case SITE_LAT:
			return "site_lat_";
		case SITE_LON:
			return "site_lon_";
		case SITE_RAILS:
			return "site_rails_";
		case PROJECT_REMARK:
			return "project_remark_";
		case BIM_URL:
			return "bim_url";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getPk_project() {
		return getAttribute(PK_PROJECT);
	}

	/**
	 * 设置主键
	 * @param pk_project
	 */
	public void setPk_project(String pk_project) {
		setAttribute(PK_PROJECT, pk_project);
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
	 * 获取所属区域
	 * @return
	 */
	public String getPk_area() {
		return getAttribute(PK_AREA);
	}

	/**
	 * 设置所属区域
	 * @param pk_area
	 */
	public void setPk_area(String pk_area) {
		setAttribute(PK_AREA, pk_area);
	}

	/**
	 * 获取项目名称
	 * @return
	 */
	public String getProject_name() {
		return getAttribute(PROJECT_NAME);
	}

	/**
	 * 设置项目名称
	 * @param project_name
	 */
	public void setProject_name(String project_name) {
		setAttribute(PROJECT_NAME, project_name);
	}

	/**
	 * 获取项目编码
	 * @return
	 */
	public String getProject_code() {
		return getAttribute(PROJECT_CODE);
	}

	/**
	 * 设置项目编码
	 * @param project_code
	 */
	public void setProject_code(String project_code) {
		setAttribute(PROJECT_CODE, project_code);
	}

	/**
	 * 获取项目描述
	 * @return
	 */
	public String getProject_memo() {
		return getAttribute(PROJECT_MEMO);
	}

	/**
	 * 设置项目描述
	 * @param project_memo
	 */
	public void setProject_memo(String project_memo) {
		setAttribute(PROJECT_MEMO, project_memo);
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
	 * 获取占地面积
	 * @return
	 */
	public BigDecimal getProject_cover_area() {
		return getAttribute(PROJECT_COVER_AREA);
	}

	/**
	 * 设置占地面积
	 * @param project_cover_area
	 */
	public void setProject_cover_area(BigDecimal project_cover_area) {
		setAttribute(PROJECT_COVER_AREA, project_cover_area);
	}

	/**
	 * 获取建筑面积
	 * @return
	 */
	public BigDecimal getProject_build_area() {
		return getAttribute(PROJECT_BUILD_AREA);
	}

	/**
	 * 设置建筑面积
	 * @param project_build_area
	 */
	public void setProject_build_area(BigDecimal project_build_area) {
		setAttribute(PROJECT_BUILD_AREA, project_build_area);
	}

	/**
	 * 获取项目地址
	 * @return
	 */
	public String getProject_add() {
		return getAttribute(PROJECT_ADD);
	}

	/**
	 * 设置项目地址
	 * @param project_add
	 */
	public void setProject_add(String project_add) {
		setAttribute(PROJECT_ADD, project_add);
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
	 * 获取选中标识
	 * @return
	 */
	public Boolean getSelected() {
		return getAttribute(SELECTED);
	}

	/**
	 * 设置选中标识
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		setAttribute(SELECTED, selected);
	}

	/**
	 * 获取删除标志，1.已删除，0.未删除
	 * @return
	 */
	public String getDelete_flag() {
		return getAttribute(DELETE_FLAG);
	}

	/**
	 * 设置删除标志，1.已删除，0.未删除
	 * @param delete_flag
	 */
	public void setDelete_flag(String delete_flag) {
		setAttribute(DELETE_FLAG, delete_flag);
	}

	/**
	 * 获取省份ID
	 * @return
	 */
	public String getPk_province() {
		return getAttribute(PK_PROVINCE);
	}

	/**
	 * 设置省份ID
	 * @param pk_province
	 */
	public void setPk_province(String pk_province) {
		setAttribute(PK_PROVINCE, pk_province);
	}

	/**
	 * 获取城市ID
	 * @return
	 */
	public String getPk_city() {
		return getAttribute(PK_CITY);
	}

	/**
	 * 设置城市ID
	 * @param pk_city
	 */
	public void setPk_city(String pk_city) {
		setAttribute(PK_CITY, pk_city);
	}

	/**
	 * 获取纬度
	 * @return
	 */
	public String getSite_lat() {
		return getAttribute(SITE_LAT);
	}

	/**
	 * 设置纬度
	 * @param site_lat
	 */
	public void setSite_lat(String site_lat) {
		setAttribute(SITE_LAT, site_lat);
	}

	/**
	 * 获取经度
	 * @return
	 */
	public String getSite_lon() {
		return getAttribute(SITE_LON);
	}

	/**
	 * 设置经度
	 * @param site_lon
	 */
	public void setSite_lon(String site_lon) {
		setAttribute(SITE_LON, site_lon);
	}

	/**
	 * 获取围栏
	 * @return
	 */
	public String getSite_rails() {
		return getAttribute(SITE_RAILS);
	}

	/**
	 * 设置围栏
	 * @param site_rails
	 */
	public void setSite_rails(String site_rails) {
		setAttribute(SITE_RAILS, site_rails);
	}

	/**
	 * 获取项目标记
	 * @return
	 */
	public String getProject_remark() {
		return getAttribute(PROJECT_REMARK);
	}

	/**
	 * 设置项目标记
	 * @param project_remark
	 */
	public void setProject_remark(String project_remark) {
		setAttribute(PROJECT_REMARK, project_remark);
	}

	/**
	 * 获取bim模型
	 * @return
	 */
	public String getBim_url() {
		return getAttribute(BIM_URL);
	}

	/**
	 * 设置bim模型
	 * @param bim_url
	 */
	public void setBim_url(String bim_url) {
		setAttribute(BIM_URL, bim_url);
	}

}
