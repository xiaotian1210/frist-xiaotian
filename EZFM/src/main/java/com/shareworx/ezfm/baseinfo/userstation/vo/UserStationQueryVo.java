package com.shareworx.ezfm.baseinfo.userstation.vo;

/**
 * 人员与岗位的查询条件VO
 * 
 * @author jin.li
 *
 */
public class UserStationQueryVo {
	private String pk_station;
	private String flag;
	private int start;
	private int limit;
	private String user_name;
	private String stan_code;

	public String getPk_station() {
		return pk_station;
	}

	public void setPk_station(String pk_station) {
		this.pk_station = pk_station;
	}

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

	public String getStan_code() {
		return stan_code;
	}

	public void setStan_code(String stan_code) {
		this.stan_code = stan_code;
	}
}
