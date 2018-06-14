package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.vo;
/**
 * 岗位查询标本与版的查询条件VO
 * @author zhenwei.shi
 *
 */
public class StationStanQueryVo {
	private String pk_station;
	private String flag;
	private int start;
	private int limit;
	private String edition_name;
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
	public String getEdition_name() {
		return edition_name;
	}
	public void setEdition_name(String edition_name) {
		this.edition_name = edition_name;
	}
	public String getStan_code() {
		return stan_code;
	}
	public void setStan_code(String stan_code) {
		this.stan_code = stan_code;
	}
}
