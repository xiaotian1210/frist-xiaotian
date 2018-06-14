/**
 * FM数据同步
 */
function synchro() {
//	$.messager.progress();
//	$.ajax({
//		type: "post",
//		url: "ezfm/device/fmdata/synchro",
//		async: true,
//		dataType: "json",
//		success: function(json) {
//			var flag = json.success;
//			if(flag) {
//				$.messager.progress('close');
//				$.messager.alert('成功', '同步完成!', 'info');
//			}else{
//				$.messager.progress('close');
//				$.messager.alert('失败', '同步失败，请稍后重试!', 'error');
//			}
//		}
//	});
	$.messager.alert('提示', '此功能暂未实现!', '');
}

/**
 * 任务生成
 */
function createTask() {
	$.messager.progress();
	$.ajax({
		type: "post",
		url: "ezfm/patrol/task/createTask",
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				$.messager.progress('close');
				$.messager.alert('提示', '生成成功', 'info');
				$("#datagrid_task").datagrid('reload');
			}
		}
	});
}

/**
 * 任务过期
 */
function judgeExpired() {
	$.messager.progress();
	$.ajax({
		type: "post",
		url: "ezfm/patrol/task/judgeExpired",
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				$.messager.progress('close');
				$.messager.alert('提示', '过期成功', 'info');
				$("#datagrid_task").datagrid('reload');
			}
		}
	});
}