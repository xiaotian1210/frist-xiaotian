package com.shareworx.ezfm.quality.proinspect.datastatistics.personnel.vo;
/**
 * 人员数据统计 Vo实体类
 * @author lingwei.li
 *
 */
public class PersonnelVo {

	private String project_name;//项目名称
	
	private String project_id;//项目id
	
	private String area_id;//区域id
	
	private String station_id;//岗位ID
	
	private String station_name;//岗位名称
	
	private String fk_taskuser;//任务所属人id（核查人）
	
	private String fk_taskuser_name;//任务所属人姓名（核查人）
	
	private String total_task_num;//总任务 数
	
	private String check_task_num;//考核总任务 数
	
	private String wait_task_num;//待办任务
	
	private String finished_task_num;//完成任务
	
	private String rec_finished_num; //整改完成数
	
	private String unfinished_task_num;//未完成任务
	
	private String finished_rate;//任务完成率

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getFk_taskuser() {
		return fk_taskuser;
	}

	public void setFk_taskuser(String fk_taskuser) {
		this.fk_taskuser = fk_taskuser;
	}

	public String getFk_taskuser_name() {
		return fk_taskuser_name;
	}

	public void setFk_taskuser_name(String fk_taskuser_name) {
		this.fk_taskuser_name = fk_taskuser_name;
	}

	public String getTotal_task_num() {
		return total_task_num;
	}

	public void setTotal_task_num(String total_task_num) {
		this.total_task_num = total_task_num;
	}

	public String getCheck_task_num() {
		return check_task_num;
	}

	public void setCheck_task_num(String check_task_num) {
		this.check_task_num = check_task_num;
	}

	public String getWait_task_num() {
		return wait_task_num;
	}

	public void setWait_task_num(String wait_task_num) {
		this.wait_task_num = wait_task_num;
	}

	public String getFinished_task_num() {
		return finished_task_num;
	}

	public void setFinished_task_num(String finished_task_num) {
		this.finished_task_num = finished_task_num;
	}

	public String getRec_finished_num() {
		return rec_finished_num;
	}

	public void setRec_finished_num(String rec_finished_num) {
		this.rec_finished_num = rec_finished_num;
	}

	public String getUnfinished_task_num() {
		return unfinished_task_num;
	}

	public void setUnfinished_task_num(String unfinished_task_num) {
		this.unfinished_task_num = unfinished_task_num;
	}

	public String getFinished_rate() {
		return finished_rate;
	}

	public void setFinished_rate(String finished_rate) {
		this.finished_rate = finished_rate;
	}
	
}
