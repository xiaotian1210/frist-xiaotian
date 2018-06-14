/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.performance.sign.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 签到管理实体类
 * 
 * @author lingwei.li
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYSignModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_performance_sign";
	
	/** 主键ID */
	public final static String PK_SIGN = "pk_sign";	
	/** 签到人 */
	public final static String SIGN_PERSON = "sign_person";	
	/** 外键(项目) */
	public final static String FK_PROJECT = "fk_project";	
	/** 外键(岗位) */
	public final static String FK_JOB = "fk_job";	
	/** 外键(部门) */
	public final static String FK_DEPARTMENT = "fk_department";	
	/** 外键(区域) */
	public final static String FK_REGION = "fk_region";	
	/** 提交时间 */
	public final static String SIGN_COMMITTIME = "sign_commitTime";	
	/** 动作  1签到  0签退 */
	public final static String SIGN_ACTION = "sign_action";	
	/** 定位 */
	public final static String SIGN_LOCATION = "sign_location";	
	/** 签到区域 */
	public final static String SIGN_REGION = "sign_region";	
	/** 签到项目 */
	public final static String SIGN_PROJECT = "sign_project";	
	/** 创建人 */
	public final static String CREATE_USER = "create_user";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 更新人 */
	public final static String UPDATE_USER = "update_user";	
	/** 更新时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

	public YJWYSignModel() {
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
		return PK_SIGN;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_SIGN:
			return "pk_sign_";
		case SIGN_PERSON:
			return "sign_person_";
		case FK_PROJECT:
			return "fk_project_";
		case FK_JOB:
			return "fk_job_";
		case FK_DEPARTMENT:
			return "fk_department_";
		case FK_REGION:
			return "fk_region_";
		case SIGN_COMMITTIME:
			return "sign_commitTime_";
		case SIGN_ACTION:
			return "sign_action_";
		case SIGN_LOCATION:
			return "sign_location_";
		case SIGN_REGION:
			return "sign_region_";
		case SIGN_PROJECT:
			return "sign_project_";
		case CREATE_USER:
			return "create_user_";
		case CREATE_TIME:
			return "create_time_";
		case UPDATE_USER:
			return "update_user_";
		case UPDATE_TIME:
			return "update_time_";
		case PK_CROP:
			return "pk_crop_";
		}
		return null;
	}

	/**
	 * 获取主键ID
	 * @return
	 */
	public String getPk_sign() {
		return getAttribute(PK_SIGN);
	}

	/**
	 * 设置主键ID
	 * @param pk_sign
	 */
	public void setPk_sign(String pk_sign) {
		setAttribute(PK_SIGN, pk_sign);
	}

	/**
	 * 获取签到人
	 * @return
	 */
	public String getSign_person() {
		return getAttribute(SIGN_PERSON);
	}

	/**
	 * 设置签到人
	 * @param sign_person
	 */
	public void setSign_person(String sign_person) {
		setAttribute(SIGN_PERSON, sign_person);
	}

	/**
	 * 获取外键(项目)
	 * @return
	 */
	public String getFk_project() {
		return getAttribute(FK_PROJECT);
	}

	/**
	 * 设置外键(项目)
	 * @param fk_project
	 */
	public void setFk_project(String fk_project) {
		setAttribute(FK_PROJECT, fk_project);
	}

	/**
	 * 获取外键(岗位)
	 * @return
	 */
	public String getFk_job() {
		return getAttribute(FK_JOB);
	}

	/**
	 * 设置外键(岗位)
	 * @param fk_job
	 */
	public void setFk_job(String fk_job) {
		setAttribute(FK_JOB, fk_job);
	}

	/**
	 * 获取外键(部门)
	 * @return
	 */
	public String getFk_department() {
		return getAttribute(FK_DEPARTMENT);
	}

	/**
	 * 设置外键(部门)
	 * @param fk_department
	 */
	public void setFk_department(String fk_department) {
		setAttribute(FK_DEPARTMENT, fk_department);
	}

	/**
	 * 获取外键(区域)
	 * @return
	 */
	public String getFk_region() {
		return getAttribute(FK_REGION);
	}

	/**
	 * 设置外键(区域)
	 * @param fk_region
	 */
	public void setFk_region(String fk_region) {
		setAttribute(FK_REGION, fk_region);
	}

	/**
	 * 获取提交时间
	 * @return
	 */
	public String getSign_commitTime() {
		return getAttribute(SIGN_COMMITTIME);
	}

	/**
	 * 设置提交时间
	 * @param sign_commitTime
	 */
	public void setSign_commitTime(String sign_commitTime) {
		setAttribute(SIGN_COMMITTIME, sign_commitTime);
	}

	/**
	 * 获取动作  1签到  0签退
	 * @return
	 */
	public String getSign_action() {
		return getAttribute(SIGN_ACTION);
	}

	/**
	 * 设置动作  1签到  0签退
	 * @param sign_action
	 */
	public void setSign_action(String sign_action) {
		setAttribute(SIGN_ACTION, sign_action);
	}

	/**
	 * 获取定位
	 * @return
	 */
	public String getSign_location() {
		return getAttribute(SIGN_LOCATION);
	}

	/**
	 * 设置定位
	 * @param sign_location
	 */
	public void setSign_location(String sign_location) {
		setAttribute(SIGN_LOCATION, sign_location);
	}

	/**
	 * 获取签到区域
	 * @return
	 */
	public String getSign_region() {
		return getAttribute(SIGN_REGION);
	}

	/**
	 * 设置签到区域
	 * @param sign_region
	 */
	public void setSign_region(String sign_region) {
		setAttribute(SIGN_REGION, sign_region);
	}

	/**
	 * 获取签到项目
	 * @return
	 */
	public String getSign_project() {
		return getAttribute(SIGN_PROJECT);
	}

	/**
	 * 设置签到项目
	 * @param sign_project
	 */
	public void setSign_project(String sign_project) {
		setAttribute(SIGN_PROJECT, sign_project);
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
	 * 获取更新人
	 * @return
	 */
	public String getUpdate_user() {
		return getAttribute(UPDATE_USER);
	}

	/**
	 * 设置更新人
	 * @param update_user
	 */
	public void setUpdate_user(String update_user) {
		setAttribute(UPDATE_USER, update_user);
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
