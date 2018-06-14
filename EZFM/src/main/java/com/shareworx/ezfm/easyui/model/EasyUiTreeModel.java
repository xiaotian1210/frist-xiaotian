/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.easyui.model;

import java.util.List;

import com.shareworx.platform.persist.DomainModel;

/**
 * easyui树形接口接口
 * @version since Shareworx platform 2.0
 *
 */
@SuppressWarnings("serial")
public class EasyUiTreeModel extends EasyObjectModel{
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "easyui_tree";
	
	/** id */
	public final static String ID = "id";	
	public final static String PARENT = "parent";
	/** text */
	public final static String TEXT = "text";	
	/** iconCls */
	public final static String ICONCLS = "iconCls";	
	/** iconCls */
	public final static String CHILDREN = "children";	
	/** iconCls */
	public final static String ATTRIBUTES = "attributes";	

	/**
	 * 获取元数据名称
	 */
	public String getMetaName() {
		return META_ID;
	}
	
	
	public List<EasyUiTreeModel> getChildren(){
		return getAttribute(CHILDREN);
	}
	
	public void setChildren(List<EasyUiTreeModel> children) {
		setAttribute(CHILDREN, children);
	}
	
	public DomainModel getAttributes(){
		return getAttribute(ATTRIBUTES);
	}
	
	public void setAttributes(DomainModel attributes) {
		setAttribute(ATTRIBUTES, attributes);
	}
	
	
	/**
	 * 获取id
	 * @return
	 */
	public String getId() {
		return getAttribute(ID);
	}

	
	public void setId(String id) {
		setAttribute(ID, id);
	}
	/**
	 * 获取id
	 * @return
	 */
	public String getParent() {
		return getAttribute(PARENT);
	}

	/**
	 * 设置id
	 * @param id
	 */
	public void setParent(String parent) {
		setAttribute(PARENT, parent);
	}
	


	/**
	 * 获取text
	 * @return
	 */
	public String getText() {
		return getAttribute(TEXT);
	}

	/**
	 * 设置text
	 * @param text
	 */
	public void setText(String text) {
		setAttribute(TEXT, text);
	}

	/**
	 * 获取iconCls
	 * @return
	 */
	public String getIconCls() {
		return getAttribute(ICONCLS);
	}

	/**
	 * 设置iconCls
	 * @param iconCls
	 */
	public void setIconCls(String iconCls) {
		setAttribute(ICONCLS, iconCls);
	}

}