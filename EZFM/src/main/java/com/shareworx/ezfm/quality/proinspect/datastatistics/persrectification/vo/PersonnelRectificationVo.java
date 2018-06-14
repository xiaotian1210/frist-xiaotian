package com.shareworx.ezfm.quality.proinspect.datastatistics.persrectification.vo;

/**
 * 人员整改数据 model
 * @author lingwei.li
 *
 */
public class PersonnelRectificationVo {

	private String project_name;//项目名称
	
	private String project_id;//项目id
	
	private String area_id;//区域id
	
	private String rectify_user_name; //整改人姓名
	
	private String rectify_user_id; //整改人id
	
	private String station_id;//岗位ID
	
	private String station_name;//岗位名称
	
	private String unfinished_num;//未完成的整改 数

	private String finished_num;//完成的整改 数
	
	private String expire_finished_num;//过期完成的整改 数
	
	private String total_task_num;//总任务 数
	
	private String check_task_num;//考核总任务 数
	
	private String finish_rate;//整改完成率
	
	private String timely_rate;//整改完成及时率
	
	private String should_rec_num;//应该整改数量

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

	public String getRectify_user_name() {
		return rectify_user_name;
	}

	public void setRectify_user_name(String rectify_user_name) {
		this.rectify_user_name = rectify_user_name;
	}

	public String getRectify_user_id() {
		return rectify_user_id;
	}

	public void setRectify_user_id(String rectify_user_id) {
		this.rectify_user_id = rectify_user_id;
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

	public String getUnfinished_num() {
		return unfinished_num;
	}

	public void setUnfinished_num(String unfinished_num) {
		this.unfinished_num = unfinished_num;
	}

	public String getFinished_num() {
		return finished_num;
	}

	public void setFinished_num(String finished_num) {
		this.finished_num = finished_num;
	}

	public String getExpire_finished_num() {
		return expire_finished_num;
	}

	public void setExpire_finished_num(String expire_finished_num) {
		this.expire_finished_num = expire_finished_num;
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

	public String getFinish_rate() {
		return finish_rate;
	}

	public void setFinish_rate(String finish_rate) {
		this.finish_rate = finish_rate;
	}

	public String getTimely_rate() {
		return timely_rate;
	}

	public void setTimely_rate(String timely_rate) {
		this.timely_rate = timely_rate;
	}

	public String getShould_rec_num() {
		return should_rec_num;
	}

	public void setShould_rec_num(String should_rec_num) {
		this.should_rec_num = should_rec_num;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	
	
	
}
