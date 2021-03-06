/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.system.function.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 系统功能实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYFunctionModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_function";
	
	/** 主键 */
	public final static String ID = "id";	
	/** 上级功能 */
	public final static String PID = "pid";	
	/** 功能名称 */
	public final static String NAME = "name";	
	/** 功能编码 */
	public final static String CODE = "code";	
	/** 功能链接 */
	public final static String URL = "url";	
	/** 功能类型(menu菜单，function功能，button按钮) */
	public final static String TYPE = "type";	
	/** 描述 */
	public final static String MEMO = "memo";	
	/** icon */
	public final static String ICON = "icon";	
	/** 排序(整数) */
	public final static String ORDER = "order";	

	public YJWYFunctionModel() {
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
		case PID:
			return "pid_";
		case NAME:
			return "name_";
		case CODE:
			return "code_";
		case URL:
			return "url_";
		case TYPE:
			return "type_";
		case MEMO:
			return "memo_";
		case ICON:
			return "icon_";
		case ORDER:
			return "order_";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getId() {
		return getAttribute(ID);
	}

	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(String id) {
		setAttribute(ID, id);
	}

	/**
	 * 获取上级功能
	 * @return
	 */
	public String getPid() {
		return getAttribute(PID);
	}

	/**
	 * 设置上级功能
	 * @param pid
	 */
	public void setPid(String pid) {
		setAttribute(PID, pid);
	}

	/**
	 * 获取功能名称
	 * @return
	 */
	public String getName() {
		return getAttribute(NAME);
	}

	/**
	 * 设置功能名称
	 * @param name
	 */
	public void setName(String name) {
		setAttribute(NAME, name);
	}

	/**
	 * 获取功能编码
	 * @return
	 */
	public String getCode() {
		return getAttribute(CODE);
	}

	/**
	 * 设置功能编码
	 * @param code
	 */
	public void setCode(String code) {
		setAttribute(CODE, code);
	}

	/**
	 * 获取功能链接
	 * @return
	 */
	public String getUrl() {
		return getAttribute(URL);
	}

	/**
	 * 设置功能链接
	 * @param url
	 */
	public void setUrl(String url) {
		setAttribute(URL, url);
	}

	/**
	 * 获取功能类型(menu菜单，function功能，button按钮)
	 * @return
	 */
	public String getType() {
		return getAttribute(TYPE);
	}

	/**
	 * 设置功能类型(menu菜单，function功能，button按钮)
	 * @param type
	 */
	public void setType(String type) {
		setAttribute(TYPE, type);
	}

	/**
	 * 获取描述
	 * @return
	 */
	public String getMemo() {
		return getAttribute(MEMO);
	}

	/**
	 * 设置描述
	 * @param memo
	 */
	public void setMemo(String memo) {
		setAttribute(MEMO, memo);
	}

	/**
	 * 获取icon
	 * @return
	 */
	public String getIcon() {
		return getAttribute(ICON);
	}

	/**
	 * 设置icon
	 * @param icon
	 */
	public void setIcon(String icon) {
		setAttribute(ICON, icon);
	}

	/**
	 * 获取排序(整数)
	 * @return
	 */
	public Integer getOrder() {
		return getAttribute(ORDER);
	}

	/**
	 * 设置排序(整数)
	 * @param order
	 */
	public void setOrder(Integer order) {
		setAttribute(ORDER, order);
	}

}
