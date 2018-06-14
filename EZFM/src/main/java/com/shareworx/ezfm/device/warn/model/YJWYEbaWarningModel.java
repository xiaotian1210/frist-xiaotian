/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.device.warn.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * eba设备报警信息实体类
 * 
 * @author jin.li
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYEbaWarningModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_eba_warning";
	
	/** 主键报警ID */
	public final static String REPORT_ID = "report_id";	
	/** 设备编号 */
	public final static String DEVICE_CODE = "device_code";	
	/** 报警描述文字 */
	public final static String WARNING_CONTENT = "warning_content";	
	/** 当前报警值 */
	public final static String WARNING_VALUE = "warning_value";	
	/** 报警生成时间 */
	public final static String WARNING_TIME = "warning_time";	
	/** 状态0：待处理；1：已报修；2：已忽略 */
	public final static String WARN_STATE = "warn_state";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 设备id */
	public final static String EQ_ID = "eq_id";	
	/** 告警消除接口地址 */
	public final static String ELIMINATE_URL = "eliminate_url";	

	public YJWYEbaWarningModel() {
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
		return REPORT_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case REPORT_ID:
			return "report_id";
		case DEVICE_CODE:
			return "device_code";
		case WARNING_CONTENT:
			return "warning_content";
		case WARNING_VALUE:
			return "warning_value";
		case WARNING_TIME:
			return "warning_time";
		case WARN_STATE:
			return "warn_state";
		case PK_CROP:
			return "pk_crop";
		case EQ_ID:
			return "eq_id";
		case ELIMINATE_URL:
			return "eliminate_url";
		}
		return null;
	}

	/**
	 * 获取主键报警ID
	 * @return
	 */
	public String getReport_id() {
		return getAttribute(REPORT_ID);
	}

	/**
	 * 设置主键报警ID
	 * @param report_id
	 */
	public void setReport_id(String report_id) {
		setAttribute(REPORT_ID, report_id);
	}

	/**
	 * 获取设备编号
	 * @return
	 */
	public String getDevice_code() {
		return getAttribute(DEVICE_CODE);
	}

	/**
	 * 设置设备编号
	 * @param device_code
	 */
	public void setDevice_code(String device_code) {
		setAttribute(DEVICE_CODE, device_code);
	}

	/**
	 * 获取报警描述文字
	 * @return
	 */
	public String getWarning_content() {
		return getAttribute(WARNING_CONTENT);
	}

	/**
	 * 设置报警描述文字
	 * @param warning_content
	 */
	public void setWarning_content(String warning_content) {
		setAttribute(WARNING_CONTENT, warning_content);
	}

	/**
	 * 获取当前报警值
	 * @return
	 */
	public String getWarning_value() {
		return getAttribute(WARNING_VALUE);
	}

	/**
	 * 设置当前报警值
	 * @param warning_value
	 */
	public void setWarning_value(String warning_value) {
		setAttribute(WARNING_VALUE, warning_value);
	}

	/**
	 * 获取报警生成时间
	 * @return
	 */
	public String getWarning_time() {
		return getAttribute(WARNING_TIME);
	}

	/**
	 * 设置报警生成时间
	 * @param warning_time
	 */
	public void setWarning_time(String warning_time) {
		setAttribute(WARNING_TIME, warning_time);
	}

	/**
	 * 获取状态0：待处理；1：已报修；2：已忽略
	 * @return
	 */
	public String getWarn_state() {
		return getAttribute(WARN_STATE);
	}

	/**
	 * 设置状态0：待处理；1：已报修；2：已忽略
	 * @param warn_state
	 */
	public void setWarn_state(String warn_state) {
		setAttribute(WARN_STATE, warn_state);
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
	 * 获取设备id
	 * @return
	 */
	public String getEq_id() {
		return getAttribute(EQ_ID);
	}

	/**
	 * 设置设备id
	 * @param eq_id
	 */
	public void setEq_id(String eq_id) {
		setAttribute(EQ_ID, eq_id);
	}

	/**
	 * 获取告警消除接口地址
	 * @return
	 */
	public String getEliminate_url() {
		return getAttribute(ELIMINATE_URL);
	}

	/**
	 * 设置告警消除接口地址
	 * @param eliminate_url
	 */
	public void setEliminate_url(String eliminate_url) {
		setAttribute(ELIMINATE_URL, eliminate_url);
	}

}