package com.shareworx.ezfm.device.util.vo;

import java.util.List;
import java.util.Set;

/**
 * 用于接收参数的实体类
 * 
 * @author jin.li
 *
 */
public class ParamEntity {
	private Integer page;// 当前页
	private Integer rows;// 每页数量
	private boolean selected;// 是否选中

	private String pk_group;// 人员分组id
	private String group_name;// 人员分组名称

	private String eqKey;// 设备列表搜索条件
	private String pk_area;// 区域主键id
	private String pk_project;// YJWY项目主键id

	private String site_id;// FM项目主键id
	/**
	 * 0  不传-- 表示项目ID
	 * 1  资源ID
	 */
	private String type;
	/**
	 * 项目ID 或者资源ＩＤ
	 */
	private String id;
	private String rm_id;// 房间位置主键id
	private String area_name;// 区域名
	private String project_name;// 项目名
	private String name;// 房间名
	private String csi_id;// 分类id
	private String pk_user;// 人员id
	private String user_name;// 人员姓名

	private String plan_type;// 计划类型
	private String plan_id;// 计划id
	private String plan_name;// 计划名称
	private String start_time;// 开始时间
	private String end_time;// 结束时间
	private String group_id;// 分组id
	private String frequency_num;
	private String frequency_unit;
	private String is_enable;// 是否启用
	private String task_type;// 任务类型
	private String csis;
	private String eqs;
	private String rooms;
	private String task_state;
	private String task_id;
	private String eq_id;
	private String task_dealt;
	private String queryMouth;

	private String bus_type;// 作业类型

	private String pk_crop;// 所属公司
	private String flag;// 标识

	private String fk_eq_sys_id;

	private Integer active;
	
	private String sort;
	
	private String order = "asc";
	
	private Set<String> site_ids;
	
	private Set<String> project_ids;

	private String parent_id;


	public Set<String> getProject_ids() {
		return project_ids;
	}

	public void setProject_ids(Set<String> project_ids) {
		this.project_ids = project_ids;
	}




	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}




	public Set<String> getSite_ids() {
		return site_ids;
	}

	public void setSite_ids(Set<String> site_ids) {
		this.site_ids = site_ids;
	}

	private String isTemplate;

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getFk_eq_sys_id() {
		return fk_eq_sys_id;
	}

	public void setFk_eq_sys_id(String fk_eq_sys_id) {
		this.fk_eq_sys_id = fk_eq_sys_id;
	}

	public String getQueryMouth() {
		return queryMouth;
	}

	public void setQueryMouth(String queryMouth) {
		this.queryMouth = queryMouth;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getTask_dealt() {
		return task_dealt;
	}

	public void setTask_dealt(String task_dealt) {
		this.task_dealt = task_dealt;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTask_state() {
		return task_state;
	}

	public void setTask_state(String task_state) {
		this.task_state = task_state;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPk_user() {
		return pk_user;
	}

	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}

	public String getPk_crop() {
		return pk_crop;
	}

	public void setPk_crop(String pk_crop) {
		this.pk_crop = pk_crop;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public String getCsi_id() {
		return csi_id;
	}

	public void setCsi_id(String csi_id) {
		this.csi_id = csi_id;
	}

	public String getCsis() {
		return csis;
	}

	public void setCsis(String csis) {
		this.csis = csis;
	}

	public String getEqs() {
		return eqs;
	}

	public void setEqs(String eqs) {
		this.eqs = eqs;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
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

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getFrequency_num() {
		return frequency_num;
	}

	public void setFrequency_num(String frequency_num) {
		this.frequency_num = frequency_num;
	}

	public String getFrequency_unit() {
		return frequency_unit;
	}

	public void setFrequency_unit(String frequency_unit) {
		this.frequency_unit = frequency_unit;
	}

	public String getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}

	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_type() {
		return plan_type;
	}

	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getPk_area() {
		return pk_area;
	}

	public void setPk_area(String pk_area) {
		this.pk_area = pk_area;
	}

	public String getPk_project() {
		return pk_project;
	}

	public void setPk_project(String pk_project) {
		this.pk_project = pk_project;
	}

	public String getRm_id() {
		return rm_id;
	}

	public void setRm_id(String rm_id) {
		this.rm_id = rm_id;
	}

	public String getEqKey() {
		return eqKey;
	}

	public void setEqKey(String eqKey) {
		this.eqKey = eqKey;
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
