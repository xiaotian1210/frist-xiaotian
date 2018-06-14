/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.device.patrol.task.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 巡检维保任务表实体类
 * 
 * @author jin.li
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYTaskEqModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_patrol_taskeq";
	
	/** 主键 */
	public final static String PK_ID = "pk_id";	
	/** 任务id */
	public final static String TASK_ID = "task_id";	
	/** 设备id */
	public final static String EQ_ID = "eq_id";	
	/** 计划id */
	public final static String PLAN_ID = "plan_id";	
	/** 设备分类id */
	public final static String CSI_ID = "csi_id";	
	/** 工艺主表id */
	public final static String PMP_ID = "pmp_id";	
	/** 房间位置id */
	public final static String RM_ID = "rm_id";	
	/** 巡检维保结果状态0：异常；1：正常；2：热备 */
	public final static String EQ_STATE = "eq_state";	
	/** 巡检维保备注 */
	public final static String EQ_DESC = "eq_desc";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	/** 创建时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 创建人id */
	public final static String CREATE_USER = "create_user";	
	/** 修改时间 */
	public final static String UPDATE_TIME = "update_time";	
	/** 修改人id */
	public final static String UPDATE_USER = "update_user";	

	public YJWYTaskEqModel() {
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
		return PK_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_ID:
			return "pk_id";
		case TASK_ID:
			return "task_id";
		case EQ_ID:
			return "eq_id";
		case PLAN_ID:
			return "plan_id";
		case CSI_ID:
			return "csi_id";
		case PMP_ID:
			return "pmp_id";
		case RM_ID:
			return "rm_id";
		case EQ_STATE:
			return "eq_state";
		case EQ_DESC:
			return "eq_desc";
		case PK_CROP:
			return "pk_crop";
		case CREATE_TIME:
			return "create_time";
		case CREATE_USER:
			return "create_user";
		case UPDATE_TIME:
			return "update_time";
		case UPDATE_USER:
			return "update_user";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getPk_id() {
		return getAttribute(PK_ID);
	}

	/**
	 * 设置主键
	 * @param pk_id
	 */
	public void setPk_id(String pk_id) {
		setAttribute(PK_ID, pk_id);
	}

	/**
	 * 获取任务id
	 * @return
	 */
	public String getTask_id() {
		return getAttribute(TASK_ID);
	}

	/**
	 * 设置任务id
	 * @param task_id
	 */
	public void setTask_id(String task_id) {
		setAttribute(TASK_ID, task_id);
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
	 * 获取计划id
	 * @return
	 */
	public String getPlan_id() {
		return getAttribute(PLAN_ID);
	}

	/**
	 * 设置计划id
	 * @param plan_id
	 */
	public void setPlan_id(String plan_id) {
		setAttribute(PLAN_ID, plan_id);
	}

	/**
	 * 获取设备分类id
	 * @return
	 */
	public String getCsi_id() {
		return getAttribute(CSI_ID);
	}

	/**
	 * 设置设备分类id
	 * @param csi_id
	 */
	public void setCsi_id(String csi_id) {
		setAttribute(CSI_ID, csi_id);
	}

	/**
	 * 获取工艺主表id
	 * @return
	 */
	public String getPmp_id() {
		return getAttribute(PMP_ID);
	}

	/**
	 * 设置工艺主表id
	 * @param pmp_id
	 */
	public void setPmp_id(String pmp_id) {
		setAttribute(PMP_ID, pmp_id);
	}

	/**
	 * 获取房间位置id
	 * @return
	 */
	public String getRm_id() {
		return getAttribute(RM_ID);
	}

	/**
	 * 设置房间位置id
	 * @param rm_id
	 */
	public void setRm_id(String rm_id) {
		setAttribute(RM_ID, rm_id);
	}

	/**
	 * 获取巡检维保结果状态0：异常；1：正常；2：热备
	 * @return
	 */
	public Integer getEq_state() {
		return getAttribute(EQ_STATE);
	}

	/**
	 * 设置巡检维保结果状态0：异常；1：正常；2：热备
	 * @param eq_state
	 */
	public void setEq_state(Integer eq_state) {
		setAttribute(EQ_STATE, eq_state);
	}

	/**
	 * 获取巡检维保备注
	 * @return
	 */
	public String getEq_desc() {
		return getAttribute(EQ_DESC);
	}

	/**
	 * 设置巡检维保备注
	 * @param eq_desc
	 */
	public void setEq_desc(String eq_desc) {
		setAttribute(EQ_DESC, eq_desc);
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
	 * 获取创建人id
	 * @return
	 */
	public String getCreate_user() {
		return getAttribute(CREATE_USER);
	}

	/**
	 * 设置创建人id
	 * @param create_user
	 */
	public void setCreate_user(String create_user) {
		setAttribute(CREATE_USER, create_user);
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
	 * 获取修改人id
	 * @return
	 */
	public String getUpdate_user() {
		return getAttribute(UPDATE_USER);
	}

	/**
	 * 设置修改人id
	 * @param update_user
	 */
	public void setUpdate_user(String update_user) {
		setAttribute(UPDATE_USER, update_user);
	}

}