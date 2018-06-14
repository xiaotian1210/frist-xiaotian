/**
 * 查询提交
 */
function querySubmit() {
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	var pk_user = $("#combobox_user").combobox("getValue");
	var task_state = $("#combobox_state").combobox("getValue");
	$("#datagrid_task").datagrid("load", {
		task_type: whole_task_type,
		pk_area: pk_area,
		pk_project: pk_project,
		start_time: start_time,
		end_time: end_time,
		pk_user: pk_user,
		task_state: task_state,
	});
}


/**
 * 重置后提交
 */
function resetSubmit(){	
	$("#combobox_area").combobox("setText","区域选择");
	$("#combobox_area").combobox("setValue","default");
    $("#combobox_project").combobox("setText","项目选择");
    $("#combobox_project").combobox("setValue","default");
    $('#datebox_start_time').datebox('setValue', '');
    $('#datebox_end_time').datebox('setValue', '');
    $("#combobox_user").combobox("setText","人员选择");
    $("#combobox_user").combobox("setValue","default");
    $("#combobox_state").combobox("setText","任务状态");
    $("#combobox_state").combobox("setValue","-1");
  
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var start_time = $('#datebox_start_time').datebox('getValue');
	var end_time = $('#datebox_end_time').datebox('getValue');
	var pk_user = $("#combobox_user").combobox("getValue");
	var task_state= $("#combobox_state").combobox("getValue");

	$("#datagrid_task").datagrid("load",{
		task_type: whole_task_type,
		pk_area:pk_area,
		pk_project:pk_project,
		start_time: start_time,
		end_time: end_time,
		pk_user: pk_user,
		task_state: task_state,
	});
}

/**
 * 格式化字段：操作
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatOperate(value, row, index) {
	var state = row.task_state;
	//结束任务按钮权限标识
	var finishFlag = row.finishFlag;
	//1:有结束权限
	if(finishFlag == 1) {
		if(state == 0 || state == 4 || state == 6) {
			return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='taskDetails(" +
				index +
				")' >任务详情</a>" +
				"&nbsp;&nbsp;" +
				"<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='finishTask(" +
				index + ")' >结束任务</a>";
		}
	}
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='taskDetails(" +
		index +
		")' >任务详情</a>";

}

/**
 * 格式化字段：任务状态
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatTask_state(value, row, index) {
	switch(value) {
		case 0:
			return "未完成";
		case 1:
			return "已完成";
		case 2:
			return "已过期";
		case 3:
			return "转发";
		case 4:
			return "保养中";
		case 5:
			return "销单";
		case 6:
			return "未派单";
	}
}

/**
 * 结束任务
 * @param {Object} index
 */
function finishTask(index) {
	$("#datagrid_task").datagrid('selectRow', index);
	var rows = $("#datagrid_task").datagrid('getSelections');
	var row = rows[0];
	$.ajax({
		type: "post",
		url: "ezfm/patrol/task/finish",
		contentType: 'application/json',
		data: JSON.stringify(row),
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				$.messager.alert('提示', '结束成功', 'info');
				$("#datagrid_task").datagrid('reload');
			}
		}
	});
}

/**
 * 任务详情
 * @param {Object} index
 */
function taskDetails(index) {
	$(".tableSpan").text("");
	$("#datagrid_task").datagrid('selectRow', index);
	var rows = $("#datagrid_task").datagrid('getSelections');
	var row = rows[0];
	var task_id = row['task_id'];
	var value = null;
	for(var p in row) {
		var id = "span_" + p;
		if(null == row[p]) {
			value = "";
		} else if("task_state" == p) {
			value = formatTask_state(row[p], null, null);
		} else if("task_type" == p) {
			switch(row[p]) {
				case 0:
					value = "巡检";
					break;
				case 1:
					value = "维保";
					break;
			}
		} else {
			value = row[p];
		}
		$("#" + id).text(value);
	}

	//初始化设备信息列表
	$("#datagrid_eqList").treegrid({
		url: 'ezfm/patrol/task/queryRmEqList',
		idField: 'id',
		treeField: 'name',
		queryParams: {
			"task_id": task_id
		}
	});
	openDialog('dialog_details', '任务详情', null, 630, 470);
}