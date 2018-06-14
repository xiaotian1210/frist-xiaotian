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
	var task_dealt = $("#combobox_dealt").combobox("getValue");
	$("#datagrid_record").datagrid("load", {
		task_type: '0',
		pk_area: pk_area,
		pk_project: pk_project,
		start_time: start_time,
		end_time: end_time,
		pk_user: pk_user,
		task_dealt: task_dealt,
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
    $("#combobox_dealt").combobox("setText","处理情况");
    $("#combobox_dealt").combobox("setValue","-1");
  
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var start_time = $('#datebox_start_time').datebox('getValue');
	var end_time = $('#datebox_end_time').datebox('getValue');
	var pk_user = $("#combobox_user").combobox("getValue");
	var task_dealt= $("#combobox_dealt").combobox("getValue");
	
	$("#datagrid_record").datagrid("load",{
		task_type: whole_task_type,  //写这句  不改变
//		task_type: '0',  重置后只出现一条数据  如果不写 出现比原先多一条
		pk_area: pk_area,
		pk_project: pk_project,
		start_time: start_time,
		end_time: end_time,
		pk_user: pk_user,
		task_dealt: task_dealt,
	});
}

/**
 * 格式化字段：操作
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatOperate(value, row, index) {
	var task_id = row['task_id'];
	var file_count = row['file_count'];
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='taskDetails(" + index + ")' >任务详情</a>";
}

/**
 * 无附件提示
 */
function noFilePoint() {
	$.messager.alert('提示', '该任务没有附件!', 'info');
}

/**
 * 格式化字段：查看附件
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatGetFile(value, row, index) {
	if(row['children'] != undefined) {
		return "";
	} else {
		return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='readFiles(/" + row['pk_id'] + "/)' >附件查看</a>";
	}
}

/**
 * 格式化字段：巡检维保结果
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatTask_result(value, row, index) {
	switch(value) {
		case 0:
			return "异常";
		case 1:
			return "正常";
		case 2:
			return "";
	}
}

/**
 * 格式化字段：处理情况
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatTask_dealt(value, row, index) {
	switch(value) {
		case 0:
			return "派单";
		case 1:
			return "正常";
		case -1:
			return "";
	}
}

/**
 * 格式化字段：任务状态
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
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
 * 查看附件
 * 
 * @param {Object}
 *            row
 */
function readFiles(pk_id) {
	pk_id = "" + pk_id;
	pk_id = pk_id.substring(1, pk_id.length - 1);
	read_download_files('yjwy_patrol_task_file','yjwy_patrol_taskeq',pk_id);
}

/**
 * 任务详情
 * 
 * @param {Object}
 *            index
 */
function taskDetails(index) {
	$(".tableSpan").text("");
	$("#datagrid_record").datagrid('selectRow', index);
	var rows = $("#datagrid_record").datagrid('getSelections');
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

	// 初始化设备信息列表
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