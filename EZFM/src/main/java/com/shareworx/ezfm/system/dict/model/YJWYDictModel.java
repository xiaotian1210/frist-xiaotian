/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.system.dict.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 数据字典实体类
 * 
 * @author ying.chen
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class YJWYDictModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_dict";
	
	/** 主键 */
	public final static String PK_DICT = "pk_dict";	
	/** 字典编码 */
	public final static String DICT_CODE = "dict_code";	
	/** 字典名称 */
	public final static String DICT_NAME = "dict_name";	
	/** 字典描述 */
	public final static String DICT_DESCRIBE = "dict_describe";	
	/** 排序 */
	public final static String DICT_SORT = "dict_sort";	
	/**  节点  1父 2子 */
	public final static String DICT_NODE = "dict_node";	
	/** 父级编码 */
	public final static String DICT_PARENT = "dict_parent";	
	/** 状态 0关闭 1启用 */
	public final static String DICT_STATE = "dict_state";	
	/** 创建者 */
	public final static String DICT_CREATOR = "dict_creator";	
	/** 创建时间 */
	public final static String DICT_CREATIONTIME = "dict_creationTime";	
	/** 修改者 */
	public final static String DICT_MODIFIER = "dict_modifier";	
	/** 修改时间 */
	public final static String DICT_MODIFICATIONTIME = "dict_modificationTime";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	
	//选中标志
	public final static String SELECTED = "selected";	

	public YJWYDictModel() {
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
		return PK_DICT;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case PK_DICT:
			return "pk_dict_";
		case DICT_CODE:
			return "dict_code_";
		case DICT_NAME:
			return "dict_name_";
		case DICT_DESCRIBE:
			return "dict_describe_";
		case DICT_SORT:
			return "dict_sort_";
		case DICT_NODE:
			return "dict_node_";
		case DICT_PARENT:
			return "dict_parent_";
		case DICT_STATE:
			return "dict_state_";
		case DICT_CREATOR:
			return "dict_creator_";
		case DICT_CREATIONTIME:
			return "dict_creationTime_";
		case DICT_MODIFIER:
			return "dict_modifier_";
		case DICT_MODIFICATIONTIME:
			return "dict_modificationTime_";
		case PK_CROP:
			return "pk_crop_";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getPk_dict() {
		return getAttribute(PK_DICT);
	}

	/**
	 * 设置主键
	 * @param pk_dict
	 */
	public void setPk_dict(String pk_dict) {
		setAttribute(PK_DICT, pk_dict);
	}

	/**
	 * 获取字典编码
	 * @return
	 */
	public String getDict_code() {
		return getAttribute(DICT_CODE);
	}

	/**
	 * 设置字典编码
	 * @param dict_code
	 */
	public void setDict_code(String dict_code) {
		setAttribute(DICT_CODE, dict_code);
	}

	/**
	 * 获取字典名称
	 * @return
	 */
	public String getDict_name() {
		return getAttribute(DICT_NAME);
	}

	/**
	 * 设置字典名称
	 * @param dict_name
	 */
	public void setDict_name(String dict_name) {
		setAttribute(DICT_NAME, dict_name);
	}

	/**
	 * 获取字典描述
	 * @return
	 */
	public String getDict_describe() {
		return getAttribute(DICT_DESCRIBE);
	}

	/**
	 * 设置字典描述
	 * @param dict_describe
	 */
	public void setDict_describe(String dict_describe) {
		setAttribute(DICT_DESCRIBE, dict_describe);
	}

	/**
	 * 获取排序
	 * @return
	 */
	public Integer getDict_sort() {
		return getAttribute(DICT_SORT);
	}

	/**
	 * 设置排序
	 * @param dict_sort
	 */
	public void setDict_sort(Integer dict_sort) {
		setAttribute(DICT_SORT, dict_sort);
	}

	/**
	 * 获取 节点  1父 2子
	 * @return
	 */
	public Integer getDict_node() {
		return getAttribute(DICT_NODE);
	}

	/**
	 * 设置 节点  1父 2子
	 * @param dict_node
	 */
	public void setDict_node(Integer dict_node) {
		setAttribute(DICT_NODE, dict_node);
	}

	/**
	 * 获取父级编码
	 * @return
	 */
	public String getDict_parent() {
		return getAttribute(DICT_PARENT);
	}

	/**
	 * 设置父级编码
	 * @param dict_parent
	 */
	public void setDict_parent(String dict_parent) {
		setAttribute(DICT_PARENT, dict_parent);
	}

	/**
	 * 获取状态 0关闭 1启用
	 * @return
	 */
	public Integer getDict_state() {
		return getAttribute(DICT_STATE);
	}

	/**
	 * 设置状态 0关闭 1启用
	 * @param dict_state
	 */
	public void setDict_state(Integer dict_state) {
		setAttribute(DICT_STATE, dict_state);
	}

	/**
	 * 获取创建者
	 * @return
	 */
	public String getDict_creator() {
		return getAttribute(DICT_CREATOR);
	}

	/**
	 * 设置创建者
	 * @param dict_creator
	 */
	public void setDict_creator(String dict_creator) {
		setAttribute(DICT_CREATOR, dict_creator);
	}

	/**
	 * 获取创建时间
	 * @return
	 */
	public String getDict_creationTime() {
		return getAttribute(DICT_CREATIONTIME);
	}

	/**
	 * 设置创建时间
	 * @param dict_creationTime
	 */
	public void setDict_creationTime(String dict_creationTime) {
		setAttribute(DICT_CREATIONTIME, dict_creationTime);
	}

	/**
	 * 获取修改者
	 * @return
	 */
	public String getDict_modifier() {
		return getAttribute(DICT_MODIFIER);
	}

	/**
	 * 设置修改者
	 * @param dict_modifier
	 */
	public void setDict_modifier(String dict_modifier) {
		setAttribute(DICT_MODIFIER, dict_modifier);
	}

	/**
	 * 获取修改时间
	 * @return
	 */
	public String getDict_modificationTime() {
		return getAttribute(DICT_MODIFICATIONTIME);
	}

	/**
	 * 设置修改时间
	 * @param dict_modificationTime
	 */
	public void setDict_modificationTime(String dict_modificationTime) {
		setAttribute(DICT_MODIFICATIONTIME, dict_modificationTime);
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
	 * 获取下拉框选中标识
	 * @return
	 */
	public Boolean getSelected() {
		return getAttribute(SELECTED);
	}

	/**
	 * 设置下拉框选中标识
	 * @param selected
	 */
	public void setSelected(Boolean selected) {
		setAttribute(SELECTED, selected);
	}
}
