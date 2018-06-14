package com.shareworx.ezfm.login.vo;

import java.util.ArrayList;
import java.util.List;

public class UiFunctionVo {
	private String id;
	private String pid;
	private String text;
	private String state;
	private String iconCls;
	private String url;
	private String iconUrl;
	private String memo;
	private String fun_type;
	private String code;
	private List<UiFunctionVo> children = new ArrayList<UiFunctionVo>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public List<UiFunctionVo> getChildren() {
		return children;
	}
	public void setChildren(List<UiFunctionVo> children) {
		this.children = children;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFun_type() {
		return fun_type;
	}
	public void setFun_type(String fun_type) {
		this.fun_type = fun_type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
