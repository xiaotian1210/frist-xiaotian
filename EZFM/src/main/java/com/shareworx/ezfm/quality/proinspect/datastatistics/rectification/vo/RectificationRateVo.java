package com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.vo;

/**
 * 整改率统计 vo
 * @author lingwei.li
 *
 */
public class RectificationRateVo{

	private String project_name;//项目名称
	
	private String project_id;//项目id
	
	private String taskuser_name; //创建人
	
	private String taskuser_id; //创建人id
	
	private String check_task_num;//考核内整改项 数
	
	private String active_task_num;//主动发起整改项
	
	private String finished_task_num;//整改完成数
	
	private String overdue_task_num;//超期整改完成数
	
	private String rec_task_num;//整改任务数
	
	private String timely_rate;//完成及时数
	
	private String finish_rate;//整改完成率
	
	private String total_rate;//整改任务占比

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

	public String getTaskuser_name() {
		return taskuser_name;
	}

	public void setTaskuser_name(String taskuser_name) {
		this.taskuser_name = taskuser_name;
	}

	public String getTaskuser_id() {
		return taskuser_id;
	}

	public void setTaskuser_id(String taskuser_id) {
		this.taskuser_id = taskuser_id;
	}

	public String getCheck_task_num() {
		return check_task_num;
	}

	public void setCheck_task_num(String check_task_num) {
		this.check_task_num = check_task_num;
	}

	public String getActive_task_num() {
		return active_task_num;
	}

	public void setActive_task_num(String active_task_num) {
		this.active_task_num = active_task_num;
	}

	public String getFinished_task_num() {
		return finished_task_num;
	}

	public void setFinished_task_num(String finished_task_num) {
		this.finished_task_num = finished_task_num;
	}

	public String getOverdue_task_num() {
		return overdue_task_num;
	}

	public void setOverdue_task_num(String overdue_task_num) {
		this.overdue_task_num = overdue_task_num;
	}

	public String getTimely_rate() {
		return timely_rate;
	}

	public void setTimely_rate(String timely_rate) {
		this.timely_rate = timely_rate;
	}

	public String getFinish_rate() {
		return finish_rate;
	}

	public void setFinish_rate(String finish_rate) {
		this.finish_rate = finish_rate;
	}

	public String getTotal_rate() {
		return total_rate;
	}

	public void setTotal_rate(String total_rate) {
		this.total_rate = total_rate;
	}

	public String getRec_task_num() {
		return rec_task_num;
	}

	public void setRec_task_num(String rec_task_num) {
		this.rec_task_num = rec_task_num;
	}

}
