var renderUsable = function(value, row, index) {
	var is_able = row.is_able;
	return is_able == true ? '<div style="color:green">启用</div>' : '<div style="color:red">停用</div>';
}
var renderPre = function(value, row, index) {
	var is_pre = row.is_pre;
	return is_pre == true ? '<div style="color:blue">是</div>' : '否';
}

function loadUser(stationId) {
	var queryParam = {
		pk_station : stationId,
		flag : 'in'
	}
	var queryUrl = "ezfm/baseinfo/userstation/station/user/query";
	// 初始化grid
	var options = {
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true
	}
	initLoadGridDataByCustom("yjwy_user_main_grid", queryUrl, queryParam, options);
}

function loadStationEdition(stationId) {
	var queryParam = {
		pk_station : stationId,
		flag : 'in'
	}
	var queryUrl = "ezfm/quality/proinspect/inspect/stanedition/station/edition/query";
	// 初始化grid
	var options = {
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true
	}
	initLoadGridDataByCustom("yjwy_edtion_main_grid", queryUrl, queryParam, options);
}
function loadStationStandard(stationId) {
	var queryParam = {
		pk_station : stationId,
		flag : 'in'
	}
	var queryUrl = "ezfm/quality/proinspect/inspect/standard/station/standard/query";
	// 初始化grid
	var options = {
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true
	}
	initLoadGridDataByCustom("yjwy_standard_main_grid", queryUrl, queryParam, options);
}
$(function() {
	// 加入用户
	$("#station_add_user_btn").click(function() {
		var nodes = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes();
		if (nodes.length <= 0) {
			$.messager.alert('提示', '请先选择岗位');
			return;
		}
		var addUrl = "ezfm/baseinfo/station/son/user/index";
		showCustomWindow("station_add_user_win_id", "用户列表", addUrl, {
			width : 880,
			left : ($(window).width() - 880) * 0.5
		})
	});

	// 移除用户
	$("#station_remove_user_btn").click(function() {
		var deleteRecods = $('#yjwy_user_main_grid').datagrid("getSelections");

		if (deleteRecods.length < 1) {
			$.messager.alert('提示', '请您先选择移除的用户!', 'warning');
			return;
		}
		var node = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes()[0];
		var actionUrl = "ezfm/baseinfo/station/user/remove/" + node.id;
		$.messager.confirm('提示', '您确认移除选中的用户吗?', function(r) {
			if (r) {
				$.request.restPost({
					url : actionUrl,
					data : deleteRecods,
					success : function(rs) {
						if (rs.success) {
							$('#yjwy_user_main_grid').datagrid('reload');
							$('#yjwy_edtion_main_grid').datagrid('reload');
							$('#yjwy_standard_main_grid').datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '用户已移除',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", rs.message, "warning");
						}
					}
				});
			}
		});
	});

	// 加入版本
	$("#station_add_stanedtion_btn").click(function() {
		var nodes = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes();
		if (nodes.length <= 0) {
			$.messager.alert('提示', '请先选择岗位');
			return;
		}
		var addUrl = "ezfm/baseinfo/station/son/edition/index";
		showCustomWindow("station_add_stanedtion_win_id", "标准版本", addUrl)
	});

	// 移除版本
	$("#station_remove_stanedtion_btn").click(function() {
		var deleteRecods = $('#yjwy_edtion_main_grid').datagrid("getSelections");

		if (deleteRecods.length < 1) {
			$.messager.alert('提示', '请您先选择移除的版本!', 'warning');
			return;
		}
		var node = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes()[0];
		var actionUrl = "ezfm/baseinfo/station/edition/remove/" + node.id;
		$.messager.confirm('提示', '您确认移除选中的版本吗?', function(r) {
			if (r) {
				$.request.restPost({
					url : actionUrl,
					data : deleteRecods,
					success : function(rs) {
						if (rs.success) {
							$('#yjwy_edtion_main_grid').datagrid('reload');
							$('#yjwy_standard_main_grid').datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '版本已移除',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", rs.message, "warning");
						}
					}
				});
			}
		});
	});
	// 加入标准
	$("#station_add_standard_btn").click(function() {
		var nodes = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes();
		if (nodes.length <= 0) {
			$.messager.alert('提示', '请先选择岗位');
			return;
		}

		var addUrl = "ezfm/baseinfo/station/son/standard/index";
		showCustomWindow("station_add_standard_win_id", "核查标准", addUrl, {
			width : 930,
			left : ($(window).width() - 930) * 0.5
		})
	});

	// 移除标准
	$("#station_remove_standard_btn").click(function() {
		var deleteRecods = $('#yjwy_standard_main_grid').datagrid("getSelections");

		if (deleteRecods.length < 1) {
			$.messager.alert('提示', '请您先选择移除的标准!', 'warning');
			return;
		}
		var node = $.fn.zTree.getZTreeObj("stationtree").getSelectedNodes()[0];
		var actionUrl = "ezfm/baseinfo/station/standard/remove/" + node.id;
		$.messager.confirm('提示', '您确认移除选中的标准吗?', function(r) {
			if (r) {
				$.request.restPost({
					url : actionUrl,
					data : deleteRecods,
					success : function(rs) {
						if (rs.success) {
							$('#yjwy_standard_main_grid').datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '标准已移除',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", rs.message, "warning");
						}
					}
				});
			}
		});
	});
});

function areaColumnFormatter(value, row, index) {
	if (value) {
		return (value + "").split(",").length + "";
	} else {
		return "0"
	}
}