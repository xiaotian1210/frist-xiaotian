package com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.vo;

public class RectificationRateQueryVo {

	private String area_id;//区域ID
	
	private String project_id;//项目ID
	
	private String dept_id;//部门ID
	
	private String station_id;//岗位ID
	
	private String user_id;//人员ID
	
	private String project_attribute; //项目属性
	
	private String start_time;//任务创建开始时间1
	
	private String end_time;//任务创建结束时间1
	
	private String task_start_time;//任务开始时间1
	
	private String task_end_time;//任务结束时间1
	
	private int limit;
	
	private int start;

	public String getTask_start_time() {
		return task_start_time;
	}

	public void setTask_start_time(String task_start_time) {
		this.task_start_time = task_start_time;
	}

	public String getTask_end_time() {
		return task_end_time;
	}

	public void setTask_end_time(String task_end_time) {
		this.task_end_time = task_end_time;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getProject_attribute() {
		return project_attribute;
	}

	public void setProject_attribute(String project_attribute) {
		this.project_attribute = project_attribute;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	
	
}
