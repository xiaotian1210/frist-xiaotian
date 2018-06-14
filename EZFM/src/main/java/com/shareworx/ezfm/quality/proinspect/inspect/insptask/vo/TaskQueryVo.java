package com.shareworx.ezfm.quality.proinspect.inspect.insptask.vo;

public class TaskQueryVo {
	String area_id;//区域ID
	String project_id;//项目ID
	String dept_id;//部门ID
	String station_id;//岗位ID
	String user_id;//人员ID
	String taskstate;//任务状态
	String rectify_code;//是否整改（code）
	String check_code;//是否检阅
	String genmode_code;//生成方式
	String stancode;//标准编码
	String taskcode;//任务编码
	String taskcq;//任务是否超期
	String start_time1;//任务开始时间1
	String start_time2;//任务开始时间2
	String end_time1;//任务结束时间1
	String end_time2;//任务结束时间2
	String specialty_code;//专业CODE
	int limit;
	int start;
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
	public String getTaskstate() {
		return taskstate;
	}
	public void setTaskstate(String taskstate) {
		this.taskstate = taskstate;
	}
	public String getRectify_code() {
		return rectify_code;
	}
	public void setRectify_code(String rectify_code) {
		this.rectify_code = rectify_code;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public String getGenmode_code() {
		return genmode_code;
	}
	public void setGenmode_code(String genmode_code) {
		this.genmode_code = genmode_code;
	}
	public String getStancode() {
		return stancode;
	}
	public void setStancode(String stancode) {
		this.stancode = stancode;
	}
	public String getTaskcode() {
		return taskcode;
	}
	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}
	public String getTaskcq() {
		return taskcq;
	}
	public void setTaskcq(String taskcq) {
		this.taskcq = taskcq;
	}
	public String getStart_time1() {
		return start_time1;
	}
	public void setStart_time1(String start_time1) {
		this.start_time1 = start_time1;
	}
	public String getStart_time2() {
		return start_time2;
	}
	public void setStart_time2(String start_time2) {
		this.start_time2 = start_time2;
	}
	public String getEnd_time1() {
		return end_time1;
	}
	public void setEnd_time1(String end_time1) {
		this.end_time1 = end_time1;
	}
	public String getEnd_time2() {
		return end_time2;
	}
	public void setEnd_time2(String end_time2) {
		this.end_time2 = end_time2;
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
	public String getSpecialty_code() {
		return specialty_code;
	}
	public void setSpecialty_code(String specialty_code) {
		this.specialty_code = specialty_code;
	}
}
