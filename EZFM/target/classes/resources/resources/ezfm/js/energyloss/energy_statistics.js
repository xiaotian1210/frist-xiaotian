
/**
 * 初始化下拉框
 * @param {Object} areaId
 * @param {Object} projectId
 */
function initAreaProject(areaId, projectId) {
	//初始化区域下拉列表
	queryData(areaId, "ezfm/device/query/area", "pk_area", "area_name");
	$("#" + areaId).combobox({
		onSelect: function(rec) {
			var url = 'ezfm/device/query/project?pk_area=' + rec.pk_area;
			queryData(projectId, url, "pk_project", "project_name");
		}
	});
	//初始化项目下拉列表
	queryData(projectId, "ezfm/device/query/project", "pk_project", "project_name");

}

/**
 * 提交查询条件
 */
function querySubmit() {
	//获取文本框
	var task_type = $("#task_type").val();
	// 获取下拉框参数
	var end_time=$("#combobox_year").combobox("getValue");
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	$("#datagrid_regularly").treegrid("load", {
		end_time:end_time,
		pk_area: pk_area,
		pk_project: pk_project,
	});
}

/**
 * 重置后查询
 */
function resetSubmit() {
	
	$("#combobox_area").combobox("setValue","default");
    $("#combobox_project").combobox("setValue","default");
	
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	// $("#datagrid_regularly").datagrid("load", {
	// 	pk_area: pk_area,
	// 	pk_project: pk_project,
	// });
	$("#datagrid_regularly").treegrid("load", {
		pk_area: pk_area,
		pk_project: pk_project,
	});
}










