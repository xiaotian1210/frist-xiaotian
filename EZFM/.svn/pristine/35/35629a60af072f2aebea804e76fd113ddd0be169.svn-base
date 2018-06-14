//下拉框初始化
function comboboxInit(comboboxName, urlName, valueField, textField, data) {
	$("#" + comboboxName).combobox({
		valueField: valueField,
		textField: textField,
		editable: false,
		data: data,
	});
}
//ajax请求下拉框数据
function queryData(comboboxName, urlName, valueField, textField) {
	$.ajax({
		type: "get",
		url: urlName,
		contentType: 'application/json',
		async: true,
		dataType: "json",
		success: function(json) {
			var data = json.data;
			comboboxInit(comboboxName, urlName, valueField, textField, data);
		}
	});
}

//页面加载
$(function(){	
	//下拉框
	queryData("combobox_area", "ezfm/device/list/queryArea", "pk_area", "area_name");
	$("#combobox_area").combobox({
		onSelect: function(rec) {
			var url = 'ezfm/device/list/queryProject?pk_area=' + rec.pk_area;
			queryData("combobox_project", url, "pk_project", "project_name");
		}
	});
	queryData("combobox_project", "ezfm/device/list/queryProject", "pk_project", "project_name");
	
	var qryParam = {};
	var rsgrid = query();
	//分页
	$('#datagrid_resources').datagrid('getPager').pagination({  
	    onSelectPage : function(pPageIndex, pPageSize) {   
	    	dataQuery('ezfm/baseinfo/resources/query', qryParam, (pPageIndex - 1) * pPageSize, pPageSize);
	    }   
	});

	//分页工具栏
	var dataQuery = function(url, jsonParam, start, limit) {
		$('#datagrid_resources').datagrid('loading');
		jsonParam = jsonParam || {};
		$.extend(jsonParam, {
			metas: ['yjwy_resources'],
			limit: limit,
			start: start
		});
		$.request.httpPost({
			url: 'ezfm/baseinfo/resources/query',
			data: { param: JSON.stringify(jsonParam)},
			success: function(rs){
				$('#datagrid_resources').datagrid('loadData', {
					total: rs.total,
					rows: rs.data
				});
				$('#datagrid_resources').datagrid('loaded');
			}
		});
		return true;
	}
	//导入文件窗口
	$("#btnfile").click(function() {
		$('#win').window('open'); 
		queryData("combobox_areaFile", "ezfm/device/list/queryArea", "pk_area", "area_name");
		$("#combobox_areaFile").combobox({
			onSelect: function(rec) {
				var url = 'ezfm/device/list/queryProject?pk_area=' + rec.pk_area;
				queryData("combobox_projectFile", url, "pk_project", "project_name");
			}
		});
		queryData("combobox_projectFile", "ezfm/device/list/queryProject", "pk_project", "project_name");
	});

	//下载模板按钮事件
	$("#download_mainbtn").click(function(){
		window.location.href="ezfm/baseinfo/resources/imptemplete/download";
	});
	
	//新增路址
	$("#btnNew").click(function() {
		$('#rsform').form('clear');
		$('#pk_resources').val(pc);
		$('#rsdlg').window({
			title:'新增路址信息',
			closed:false
		});
		queryData("combobox_areaWin", "ezfm/device/list/queryArea", "pk_area", "area_name");
		$("#combobox_areaWin").combobox({
			onSelect: function(rec) {
				var url = 'ezfm/device/list/queryProject?pk_area=' + rec.pk_area;
				queryData("combobox_projectWin", url, "pk_project", "project_name");
			}
		});
		queryData("combobox_projectWin", "ezfm/device/list/queryProject", "pk_project", "project_name");	
		$("#combobox_layout").combobox({
		    value:"0"  
		});
		$('#combobox_areaWin').combobox({
			onLoadSuccess:function(rec){
				$('#combobox_areaWin').combobox('select', '区域选择');
			}		
		});
		$('#combobox_projectWin').combobox({
			onLoadSuccess:function(rec){
				$('#combobox_projectWin').combobox('select', '项目选择');
			}		
		});
	});
	
	//新增修改路址
	var rsdlg = $('#rsdlg').dialog({
		width: 500,
		height: 350,
		left: 200,
		top: 100,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){		
				if($('#form').form('validate')){
					var json = $('#rsform').serializeJson();
					if($("#combobox_areaWin").combobox("getValue") == '区域选择'){
						$.messager.alert("错误", "请选择区域！", "error");
						return;
					}else if($("#combobox_projectWin").combobox("getValue") == '项目选择'){
						$.messager.alert("错误", "请选择项目！", "error");
						return;
					}else if($("#combobox_layout").combobox("getText") == '请选择'){
						$.messager.alert("错误", "请选择户型！", "error");
						return;
					}
					json.fk_region = $("#combobox_areaWin").combobox("getValue");
					json.fk_project = $("#combobox_projectWin").combobox("getValue");
					json.rs_layout = $("#combobox_layout").combobox("getText");
					$.request.restPost({
						url: json.pk_resources ? 'ezfm/baseinfo/resources/update':'ezfm/baseinfo/resources/save',
						data: json,
						success: function(result){
							$('#datagrid_resources').datagrid('getPager').pagination('select');
							$('#rsform').form('clear');
							rsdlg.window('close');
						},
						failure: function(rs){
							$.messager.alert('提示', '保存失败');
						}
					})
				}
			}
		}]
	});
	
//转换
$("#btnEdit").click(function() {
	var recList = $('#datagrid_resources').datagrid('getSelections');
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择转换项！", "error");
		return;
	}
	for (var i=0;i<recList.length;i++)
	{
		//(recList[i].rs_convert == 1) ? (recList[i].rs_convert = 0) : (recList[i].rs_convert = 1);
		if(recList[i].rs_convert != 1){
			recList[i].rs_convert = 1;
		}
	}
	$.messager.confirm("确认", "确定要转换吗？", function(r) {
		if(r) {
			$.ajax({
				type: "POST",
				url: contextPath+'/ezfm/baseinfo/resources/updateModels',
				contentType: 'application/json',
				data: JSON.stringify(recList),
				dataType: "json",
				success: function(json) {
					$.messager.alert('成功', json.data.length + '条数据被转换',
						'info');
					$('#datagrid_resources').datagrid('reload');
				},
			});
		}
	});
});
	
//删除
$("#btnDelete").click(function() {
	var recList = $('#datagrid_resources').datagrid('getSelections');
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择删除项！", "error");
		return;
	}
	$.messager.confirm("确认", "确定要删除吗？", function(r) {
		if(r) {
			$.ajax({
				type: "POST",
				url: contextPath+'/ezfm/baseinfo/resources/deleteModels',
				contentType: 'application/json',
				data: JSON.stringify(recList),
				dataType: "json",
				success: function(json) {
					$.messager.alert('成功', json.data.length + '条数据被删除',
						'info');
					$('#datagrid_resources').datagrid('reload');
				},
			});
			}
		});
	});
	

	//查询
	$("#btnSelect").click(function() {
		var area =  $("#combobox_area").combobox("getValue");
		var project =  $("#combobox_project").combobox("getValue");	
		var andList;
		if(area=='区域选择'){
			query();
		}else if(area!='区域选择' && project=='项目选择'){
			andList=[{key: 'fk_region_',operator: 'eq',value: area}];
			query(andList);
		}else if(area!='区域选择' && project!='项目选择'){
			andList=[{key: 'fk_region_',operator: 'eq',value: area},{key: 'fk_project_',operator: 'eq',value: project}];
			query(andList);
		}
	});


});


//查询方法
function query(andList){
	$('#datagrid_resources').datagrid({
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:true,
		pageSize: 15,
		pageNumber: 1,
		pageList: [15, 30, 45],
		rownumbers: true,
		pagePosition:"bottom",
		url: '${basePath?if_exists}ezfm/baseinfo/resources/query',
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { metas: ['yjwy_resources'],'andList':andList};
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/baseinfo/resources/query',
				data: { param: JSON.stringify(param)},
				success: function(rs){
					success({
						total: rs.total,
						rows: rs.data
					});
				}
			});
			return true;
		}
	});
}



//Excel文件导入
function submitImportExcel(){
	var area =  $("#combobox_areaFile").combobox("getValue");
	var project =  $("#combobox_projectFile").combobox("getValue");		
	if(area=="区域选择" || project=="项目选择"){
		$.messager.alert("提示","请选择正确的区域和项目","info");
		return;
	}
	$.ajax({
		type: "get",
		url: "ezfm/baseinfo/resources/import/result?project="+project+"&area="+area,
		contentType: 'application/json',
		async: true,
		dataType: "json"
	});
	
    $.messager.progress();
	$('#importExcelFormId').form('submit', {
		url: "ezfm/baseinfo/resources/import/excel",
		success: function(result){
			result = eval('('+result+')');
			$.messager.progress('close');// 如果提交成功则隐藏进度条
			if(result.success){
				$.messager.alert("提示","数据导入成功,请刷新查看","info");
				$("#win").dialog("close");
			}else{
				$.messager.alert('提示','数据导入失败,请检查导入数据是否符合要求','warning');
			}
		}
	});
}

//编辑
function updateRS() {	
	var data = $("#datagrid_resources").datagrid("getSelected");
	//获取选中的多行数据
	var rows = $('#datagrid_resources').datagrid('getSelections');
	if(rows.length>1){
		$.messager.alert('提示','请选择单行数据进行编辑');
		return ;
	}
	if(!data){
		$.messager.alert('提示','请选择需要编辑的数据');
		return ;
	}
	$('#rsform').form('load',data);
	$('#rsdlg').window({
		title:'修改路址信息',
		closed:false
	});
	//获得该条信息的下拉数据
	queryData("combobox_areaWin", "ezfm/device/list/queryArea", "pk_area", "area_name");
	$("#combobox_areaWin").combobox({
		onSelect: function(rec) {
			var url = 'ezfm/device/list/queryProject?pk_area=' + rec.pk_area;
			queryData("combobox_projectWin", url, "pk_project", "project_name");
		}
	});
	queryData("combobox_projectWin", "ezfm/device/list/queryProject", "pk_project", "project_name");
	$('#combobox_areaWin').combobox({
		onLoadSuccess:function(rec){
			$('#combobox_areaWin').combobox('select', data.fk_region);
		}		
	});
	$('#combobox_projectWin').combobox({
		onLoadSuccess:function(rec){
			var val = $('#combobox_projectWin').combobox("getData");
            for (var i=0;i<val.length;i++) {
                if (val[i].project_name == data.fk_project) {
                	$('#combobox_projectWin').combobox("select", val[i].pk_project);
                }
            }	
		}		
	});
	$('#combobox_layout').combobox({
		onLoadSuccess:function(rec){
			var val = $('#combobox_layout').combobox("getData");
			for (var i=0;i<val.length;i++) {
                if (val[i].text == data.rs_layout) {
                	$('#combobox_layout').combobox('select', val[i].value);
                }
            }	
		}		
	});
	
};

//格式化操作
function formatOperate(value, row, index) {
	return "<a href='javascript:updateRS();'>修改</a>";
}

//格式化转换
function formatConvert(value, row, index) {
	var enable = null;
	if(row.rs_convert == 1) {
		enable = "是";
	} else {
		enable = "否";
	}
	return enable;
}


