//加载区域
function loadArea() {
	var url = "ezfm/baseinfo/pub/query/dictionary/area/query";
	var simpleFormat = {
		valueField : 'pk_area',
		textField : 'area_name'
	};
	initDictionaryCombobox("queray_main_area_id", url, null, simpleFormat);
}
function areaChange(newValue, oldValue) {
	loadProject(newValue);
	loadUser(newValue);
}

// 加载项目
function loadProject(areaId) {
	var url = "ezfm/baseinfo/pub/query/dictionary/project/query";
	var param = {
		areaId : areaId
	};
	var simpleFormat = {
		valueField : 'pk_project',
		textField : 'project_name'
	};
	initDictionaryCombobox("queray_main_project_id", url, param, simpleFormat);
}
function projectChange(newValue, oldValue) {
	loadUser(null, newValue);
}

// 加载人员
function loadUser(areaId, projectId, stationId) {
	var url = "ezfm/baseinfo/pub/query/dictionary/user/query";
	var param = {
		areaId : areaId,
		projectId : projectId,
		stationId : stationId
	};
	var simpleFormat = {
		valueField : 'pk_user',
		textField : 'user_name'
	};
	initDictionaryCombobox("queray_main_user_id", url, param, simpleFormat);
}
var jsonData = {};
// 页面加载
$(function() {
	$("#yjwy_main_grid").datagrid({
		singleSelect:false,
		onLoadSuccess: function (data) {
		  for (var i = 0; i < data.rows.length; i++) {
		    if (data.rows[i].lv_operation == 2 || data.rows[i].lv_operation == 1) {
		    	//禁用checkbox
		    	$(".datagrid-row[datagrid-row-index=" + i + "] input[type='checkbox']")[0].disabled = true;
		    }
		  }
		},
		onClickRow: function(rowIndex, rowData) {  
            $("#yjwy_main_grid").datagrid("unselectRow", rowIndex);  
        }
        /*onCheckAll:function(rows){
           //加载完毕后获取所有的checkbox遍历
            $("input[type='checkbox']").each(function(index, el){
                //如果当前的复选框不可选，则不让其选中
                if (el.disabled == true) {
                	$("#yjwy_main_grid").datagrid('unselectRow', index - 1);
                }
            })
        }*/
		
	});
	
	
	loadArea();
	var queryUrl = "ezfm/performance/leave/query";
	initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
	
	// 查询
	$("#btn_mainquery").click(function() {
		jsonData = $("#yjwy_query_mianform").serializeObject();
		initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
		//$('#yjwy_main_grid').datagrid('reload');
		//$("#yjwy_main_grid").datagrid({
		//	singleSelect:false,
		//});
	});
	
	// 重置
	$("#btn_resetQuery").click(function() {
		
//	    $("#queray_main_area_id").combobox("setText","请选择");
	    $("#queray_main_area_id").combobox("setValue","");
	    $("#queray_main_project_id").combobox("setValue","");
	    $("#queray_main_user_id").combobox("setValue","");
	    $("#queray_main_state_id").combobox("setValue","");	
		
		jsonData = $("#yjwy_query_mianform").serializeObject();
		initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);

	});
	
	
	//根据条件导出excel
	$("#export_mainbtn").click(function(){
		var url = "ezfm/performance/leave/export?param="+JSON.stringify(jsonData);
		window.location.href=url;
	});

});

function bodyload(){
	jsonData = $("#yjwy_query_mianform").serializeObject();
	var queryUrl = "ezfm/performance/leave/query";
	initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
	//$('#yjwy_main_grid').datagrid('reload');
	//$("#yjwy_main_grid").datagrid({
	//	singleSelect:false,
	//});
}

// 格式化操作
function formatOperation(value, row, index) {
	var enable = "";
	if(row.lv_operation == "0"){
		enable = "<a href='javascript:approval(\""+index+"\",1);'>批准</a>" 
			+ "&nbsp;&nbsp;"
			+ "<a href='javascript:approval(\""+index+"\",2);'>拒绝</a>";
	}else if(row.lv_operation == "1"){
		enable = "审批通过";
	}else if(row.lv_operation == "2"){
		enable = "审批拒绝";
	}else if(row.lv_operation == "3"){
		enable = "已撤销";
	}
	return enable;
}

// 获取当前日期时间 格式为 yyyy-mm-dd hh:MM:ss
Date.prototype.format = function(format) {
	var args = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var i in args) {
		var n = args[i];
		if (new RegExp("(" + i + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n
					: ("00" + n).substr(("" + n).length));
	}
	return format;
};

// 批准
function approval(index, type) {
	$("#yjwy_main_grid").datagrid("selectRow", index);
	var row = $("#yjwy_main_grid").datagrid("getSelected");
	var msg = "确定要批准吗？";
	if (type == 1) {
		row.lv_operation = "1";
	}else if(type == 2){
		row.lv_operation = "2";
		msg = "确定要拒绝吗？"
	}
	$.messager.confirm("确认", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : 'ezfm/performance/leave/update',
				contentType : 'application/json',
				dataType : "json",
				data: JSON.stringify(row),
				success : function(json) {
					$.messager.alert('成功', '该记录已修改成功', 'info');
					$('#yjwy_main_grid').datagrid('reload');
				},
			});
		}
	});
}
//批量批准 或者 批量拒绝
function updateData(datagridName,type){
	//获取所有选中的 列
	var recList = $("#" + datagridName).datagrid("getSelections");
	var list = new Array();
	//判断是否有选中的列
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择修改项！", "error");
		return;
	}else{
		var index;
		for (var int = 0; int < recList.length; int++) {
		
			var array_element = recList[int].lv_operation;
			if (array_element == "0") {
				list.push(recList[int]);
			}
		}
		$("#" + datagridName).datagrid("reload");
	}
	//设置需要 提示以及修改的值
	var msg = "确定要批准吗？";
	var operation = 1;
	if(type == 2){
		operation = 2;
		msg = "确定要拒绝吗？"
	}
	//循环设置 需要修改列的 状态
	for(var i=0; i<list.length;i++){
		list[i].lv_operation = operation;
	}
	
	//ajax 异步请求
	$.messager.confirm("确认", msg, function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : 'ezfm/performance/leave/updateModels',
				contentType : 'application/json',
				dataType : "json",
				data: JSON.stringify(list),
				success : function(json) {
					$.messager.alert('成功', '记录已修改成功', 'info');
					$('#yjwy_main_grid').datagrid('reload');
				},
			});
		}
	});
	
}
// 格式化附件
function formatfile(value, row, index) {
	var enable = null;
	if (!row.file_id) {
		enable = "无附件";
	} else {
		enable = "<a href='javascript:void(0)' onclick='read_download_files_leave(/" + row['pk_leave'] + "/)'>查看附件</a>";
	}
	return enable;
}


function read_download_files_leave(record_id) {
	record_id = (record_id+"").replace(/\//g,'');
	var url = "ezfm/file/index?query_table=" + "yjwy_system_file" + "&table_name=" + "yjwy_performance_leave" + "&flag=" + "leave" + "&record_id=" + record_id;
	window.open(url, '_blank');
}


