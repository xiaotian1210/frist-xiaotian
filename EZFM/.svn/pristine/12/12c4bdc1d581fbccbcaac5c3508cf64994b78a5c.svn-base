/*片区维修种类统计表*/

/**
 * 初始化下拉框
 * @param {Object} areaId
 * @param {Object} projectId
 */
function initAreaProject(areaId, projectId) {
	//初始化区域下拉列表
	queryData(areaId, "ezfm/device/query/area", "pk_area", "area_name");
}

/**
 * 提交查询条件
 */
function querySubmit() {
	$("#datagrid_maintclass").datagrid("loading", "数据加载中……");
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	$.ajax({
		type: "post",
		url: "ezfm/worktask/maintclass/query",
		async: true,
		data: {
			pk_area: pk_area,
			start_time: start_time,
			end_time: end_time,
		},
		dataType: "json",
		success: function(data) {
			stitchCells('datagrid_maintclass', data);
		}
	});
}

/**
 * 重置并查询
 */
function resetSubmit() {

	$("#combobox_area").combobox("setValue","default");
    $('#datebox_start_time').datebox('setValue', '');
    $('#datebox_end_time').datebox('setValue', '');	
	
	$("#datagrid_maintclass").datagrid("loading", "数据加载中……");
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	$.ajax({
		type: "post",
		url: "ezfm/worktask/maintclass/query",
		async: true,
		data: {
			pk_area: pk_area,
			start_time: start_time,
			end_time: end_time,
		},
		dataType: "json",
		success: function(data) {
			stitchCells('datagrid_maintclass', data);
		}
	});
}

/**
 * 拼凑datagrid表格
 * @param {Object} tableName
 */
function stitchCells(tableName, rowData) {
	var dg = $("#" + tableName);
	var rows = rowData['rows'];
	var columns = [];
	columns.push(rowData['firstColumns']);
	columns.push(rowData['secondColumns']);
	dg.datagrid("loaded");
	$("#datagrid_maintclass").datagrid({
		columns: columns,
		data: rows,
	});
}