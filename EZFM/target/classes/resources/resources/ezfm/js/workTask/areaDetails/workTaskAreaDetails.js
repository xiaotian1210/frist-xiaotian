//操作符，save 和 update;
var operation;

//打开新增窗口
function openSaveDialog() {
	$("#form_areaDetails").form("reset");
	close_flag=false;
	operation="save";
	$("#dialog_areaDetails").dialog({
		title: '新增片区',
		width: 470,
		height: 222,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#toolbar_areaDetails",
	});
	closewin_flg = false;
	var url = "ezfm/baseinfo/pub/query/dictionary/area/query";
	queryData("fk_region_id",url,"pk_area","area_name");
}; 

function submitFormAreaDetails(datagridName, dialogName, formName, urlName){
	submitForm(datagridName, dialogName, formName, urlName+operation);
}

/**
 * ajax请求下拉框数据
 * @param {Object} comboboxName
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 * @param {Object} value
 */
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
/**
 * combobox下拉框初始化
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 */
function comboboxInit(comboboxName, urlName, valueField, textField, data) {
	$("#" + comboboxName).combobox({
		valueField: valueField,
		textField: textField,
		editable: false,
		data: data,
	});
}

/**
 * datagrid格式化操作按钮
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatDetails(value, row, index) {
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='onclickEdit(\""+index+"\")' >编辑</a>"+
	"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='deleteData(\""+index+"\")' >删除</a>"
	return operation ;
};
/**
 * 创建详情按钮
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function clickDetails(value, row, index){
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='onclickSunPage(\""+index+"\")' >"+value+"</a>";
	return operation ;
}


/**
 * 打开子页面
 *  @param {Object} id
 */
function onclickSunPage(index){
	$("#datagrid_areaDetailsList").datagrid("selectRow", index);
	var row = $("#datagrid_areaDetailsList").datagrid("getSelections");
	if(row.length>0){
		var obj = row[0];
		if(obj!=null){
			sonpageUrl = "ezfm/worktask/areadetails/sonpage/"+obj.pk_area_id;
		} 
	}else{
		$.messager.alert("提示", "请选择片区！", "info");
		return;
	}
	showSonPageEvent("详情",sonpageUrl);
	return false;
}
 
//SONPAGE EVENT 显示子页面
var showSonPageEvent = function(title,actionUrl,options){
 	closewin_flg = false;
 	showSonPageInfo(title,actionUrl,options);
}

//修改
function onclickEdit(index){
	$("#form_areaDetails").form("reset");
	var row;
	if(index != null) {
		close_flag=false;
		operation="update";
		$("#datagrid_areaDetailsList").datagrid("selectRow", index);
		row = $("#datagrid_areaDetailsList").datagrid("getSelections");
		$("#dialog_areaDetails").dialog({
			title: '编辑片区',
			width: 500,
			height: 300,
			modal: true,
			shadow: true,
			closable: true,
			cache: false,
			buttons: "#toolbar_areaDetails",
		});
		closewin_flg = false;
		var url = "ezfm/baseinfo/pub/query/dictionary/area/id/query?areaId="+row[0].fk_region_id;
		queryData("fk_region_id",url,"pk_area","area_name");
		$("#form_areaDetails").form('load',row[0]);
	}
}


/**
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitForm(datagridName, dialogName, formName, urlName) {
	var area_name = $("#area_name").textbox('getText');
	var form_data = $("#form_areaDetails").serializeObject();
	var fk_region_id = form_data.fk_region_id;
	if (area_name=="") {
		$.messager.alert('提示', '请输入名称！', 'info');
		return;
	}
	if(fk_region_id==""){
		$.messager.alert('提示', '请选择区域！', 'info');
		return;
	}
	$.ajax({
		type: "post",
		url: urlName,
		contentType: 'application/json',
		data: JSON.stringify(form_data),
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				closeDialogProblem(dialogName);
				$("#" + datagridName).datagrid('reload');
				$.messager.show({
					title:'提示',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
			} else {
				$.messager.alert('错误', '操作失败!请稍后再试！', 'error');
			}
		}
	});
}


//删除
function deleteData(index) {
	closewin_flg = false;
	var row;
	if(index != null) {
		$("#datagrid_areaDetailsList").datagrid("selectRow", index);
		row = $("#datagrid_areaDetailsList").datagrid("getSelections");
		$.messager.confirm("确认", "确定要删除吗？", function(r) {
			if(r) {
				$.request.restPost({
					url: "ezfm/worktask/areadetails/delete",
					data: row,
					success: function(rs){
						$('#datagrid_areaDetailsList').datagrid('reload');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}
				});
			}
		});
	}	
}