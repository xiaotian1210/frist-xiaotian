package com.shareworx.ezfm.baseinfo.role.vo;

/**
 * 人员与岗位的查询条件VO
 * 
 * @author jin.li
 *
 */
public class UserRoleQueryVo {
	private String pk_role;
	private String flag;
	private int start;
	private int limit;
	private String user_name;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPk_role() {
		return pk_role;
	}

	public void setPk_role(String pk_role) {
		this.pk_role = pk_role;
	}
}
