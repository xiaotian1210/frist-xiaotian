/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.energyloss.tabledefinition.model;

import com.shareworx.platform.persist.DomainModel;
import java.math.BigDecimal;

/**
 * 电表实体类
 * 
 * @author Administrator
 * @version since Shareworx platform 2.0
 */
public class YJWYEnergyElectricModel extends DomainModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_energy_electric";
	
	/** 主键设备id */
	public final static String EQ_ID = "eq_id";	
	/** 区域编码 */
	public final static String PK_AREA = "pk_area";	
	/** 项目编码 */
	public final static String PK_PROJECT = "pk_project";	
	/** 型号 */
	public final static String VERSION = "version";	
	/** 安装日期 */
	public final static String INSTALL_TIME = "install_time";	
	/** 表号 */
	public final static String SURFACE_NUM = "surface_num";	
	/** 启用时间 */
	public final static String ENABLE_TIME = "enable_time";	
	/** 倍率 */
	public final static String RATE = "rate";	
	/** 预期使用时长 */
	public final static String EXPECT_USE_TIME = "expect_use_time";	
	/** 用途名称 */
	public final static String PURPOSE_NAME = "purpose_name";	
	/** 能耗预警值 */
	public final static String NOTICE_NUM = "notice_num";	
	/** 计量范围 */
	public final static String METERING_RANGE = "metering_range";	
	/** 设备设施名称 */
	public final static String EQ_NAME = "eq_name";	
	/** 父id */
	public final static String PARENT_ID = "parent_id";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 用于区分水表、电表、其他表 */
	public final static String HYDROPOWER = "hydropower";	
	/** 设备编码 */
	public final static String EQ_CODE = "eq_code";	
	/** 用途 */
	public final static String PURPOSE = "purpose";	
	/** 资源id */
	public final static String PK_RESOURCE = "pk_resource";	
	/** 初始值 */
	public final static String STAR_NUM = "star_num";	
	/** 刪除表示 */
	public final static String DELETE_FLAG = "delete_flag";	

	public YJWYEnergyElectricModel() {
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
		return EQ_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case EQ_ID:
			return "eq_id";
		case PK_AREA:
			return "pk_area";
		case PK_PROJECT:
			return "pk_project";
		case VERSION:
			return "version";
		case INSTALL_TIME:
			return "install_time";
		case SURFACE_NUM:
			return "surface_num";
		case ENABLE_TIME:
			return "enable_time";
		case RATE:
			return "rate";
		case EXPECT_USE_TIME:
			return "expect_use_time";
		case PURPOSE_NAME:
			return "purpose_name";
		case NOTICE_NUM:
			return "notice_num";
		case METERING_RANGE:
			return "metering_range";
		case EQ_NAME:
			return "eq_name";
		case PARENT_ID:
			return "parent_id";
		case PK_CROP:
			return "pk_crop";
		case HYDROPOWER:
			return "hydropower";
		case EQ_CODE:
			return "eq_code";
		case PURPOSE:
			return "purpose";
		case PK_RESOURCE:
			return "pk_resource";
		case STAR_NUM:
			return "star_num";
		case DELETE_FLAG:
			return "delete_flag";
		}
		return null;
	}

	/**
	 * 获取主键设备id
	 * @return
	 */
	public String getEq_id() {
		return getAttribute(EQ_ID);
	}

	/**
	 * 设置主键设备id
	 * @param eq_id
	 */
	public void setEq_id(String eq_id) {
		setAttribute(EQ_ID, eq_id);
	}

	/**
	 * 获取区域编码
	 * @return
	 */
	public String getPk_area() {
		return getAttribute(PK_AREA);
	}

	/**
	 * 设置区域编码
	 * @param pk_area
	 */
	public void setPk_area(String pk_area) {
		setAttribute(PK_AREA, pk_area);
	}

	/**
	 * 获取项目编码
	 * @return
	 */
	public String getPk_project() {
		return getAttribute(PK_PROJECT);
	}

	/**
	 * 设置项目编码
	 * @param pk_project
	 */
	public void setPk_project(String pk_project) {
		setAttribute(PK_PROJECT, pk_project);
	}

	/**
	 * 获取型号
	 * @return
	 */
	public String getVersion() {
		return getAttribute(VERSION);
	}

	/**
	 * 设置型号
	 * @param version
	 */
	public void setVersion(String version) {
		setAttribute(VERSION, version);
	}

	/**
	 * 获取安装日期
	 * @return
	 */
	public String getInstall_time() {
		return getAttribute(INSTALL_TIME);
	}

	/**
	 * 设置安装日期
	 * @param install_time
	 */
	public void setInstall_time(String install_time) {
		setAttribute(INSTALL_TIME, install_time);
	}

	/**
	 * 获取表号
	 * @return
	 */
	public String getSurface_num() {
		return getAttribute(SURFACE_NUM);
	}

	/**
	 * 设置表号
	 * @param surface_num
	 */
	public void setSurface_num(String surface_num) {
		setAttribute(SURFACE_NUM, surface_num);
	}

	/**
	 * 获取启用时间
	 * @return
	 */
	public String getEnable_time() {
		return getAttribute(ENABLE_TIME);
	}

	/**
	 * 设置启用时间
	 * @param enable_time
	 */
	public void setEnable_time(String enable_time) {
		setAttribute(ENABLE_TIME, enable_time);
	}

	/**
	 * 获取倍率
	 * @return
	 */
	public Integer getRate() {
		return getAttribute(RATE);
	}

	/**
	 * 设置倍率
	 * @param rate
	 */
	public void setRate(Integer rate) {
		setAttribute(RATE, rate);
	}

	/**
	 * 获取预期使用时长
	 * @return
	 */
	public Integer getExpect_use_time() {
		return getAttribute(EXPECT_USE_TIME);
	}

	/**
	 * 设置预期使用时长
	 * @param expect_use_time
	 */
	public void setExpect_use_time(Integer expect_use_time) {
		setAttribute(EXPECT_USE_TIME, expect_use_time);
	}

	/**
	 * 获取用途名称
	 * @return
	 */
	public String getPurpose_name() {
		return getAttribute(PURPOSE_NAME);
	}

	/**
	 * 设置用途名称
	 * @param purpose_name
	 */
	public void setPurpose_name(String purpose_name) {
		setAttribute(PURPOSE_NAME, purpose_name);
	}

	/**
	 * 获取能耗预警值
	 * @return
	 */
	public BigDecimal getNotice_num() {
		return getAttribute(NOTICE_NUM);
	}

	/**
	 * 设置能耗预警值
	 * @param notice_num
	 */
	public void setNotice_num(BigDecimal notice_num) {
		setAttribute(NOTICE_NUM, notice_num);
	}

	/**
	 * 获取计量范围
	 * @return
	 */
	public BigDecimal getMetering_range() {
		return getAttribute(METERING_RANGE);
	}

	/**
	 * 设置计量范围
	 * @param metering_range
	 */
	public void setMetering_range(BigDecimal metering_range) {
		setAttribute(METERING_RANGE, metering_range);
	}

	/**
	 * 获取设备设施名称
	 * @return
	 */
	public String getEq_name() {
		return getAttribute(EQ_NAME);
	}

	/**
	 * 设置设备设施名称
	 * @param eq_name
	 */
	public void setEq_name(String eq_name) {
		setAttribute(EQ_NAME, eq_name);
	}

	/**
	 * 获取父id
	 * @return
	 */
	public String getParent_id() {
		return getAttribute(PARENT_ID);
	}

	/**
	 * 设置父id
	 * @param parent_id
	 */
	public void setParent_id(String parent_id) {
		setAttribute(PARENT_ID, parent_id);
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
	 * 获取用于区分水表、电表、其他表
	 * @return
	 */
	public String getHydropower() {
		return getAttribute(HYDROPOWER);
	}

	/**
	 * 设置用于区分水表、电表、其他表
	 * @param hydropower
	 */
	public void setHydropower(String hydropower) {
		setAttribute(HYDROPOWER, hydropower);
	}

	/**
	 * 获取设备编码
	 * @return
	 */
	public String getEq_code() {
		return getAttribute(EQ_CODE);
	}

	/**
	 * 设置设备编码
	 * @param eq_code
	 */
	public void setEq_code(String eq_code) {
		setAttribute(EQ_CODE, eq_code);
	}

	/**
	 * 获取用途
	 * @return
	 */
	public String getPurpose() {
		return getAttribute(PURPOSE);
	}

	/**
	 * 设置用途
	 * @param purpose
	 */
	public void setPurpose(String purpose) {
		setAttribute(PURPOSE, purpose);
	}

	/**
	 * 获取资源id
	 * @return
	 */
	public String getPk_resource() {
		return getAttribute(PK_RESOURCE);
	}

	/**
	 * 设置资源id
	 * @param pk_resource
	 */
	public void setPk_resource(String pk_resource) {
		setAttribute(PK_RESOURCE, pk_resource);
	}

	/**
	 * 获取初始值
	 * @return
	 */
	public String getStar_num() {
		return getAttribute(STAR_NUM);
	}

	/**
	 * 设置初始值
	 * @param star_num
	 */
	public void setStar_num(String star_num) {
		setAttribute(STAR_NUM, star_num);
	}

	/**
	 * 获取刪除表示
	 * @return
	 */
	public Integer getDelete_flag() {
		return getAttribute(DELETE_FLAG);
	}

	/**
	 * 设置刪除表示
	 * @param delete_flag
	 */
	public void setDelete_flag(Integer delete_flag) {
		setAttribute(DELETE_FLAG, delete_flag);
	}

}
