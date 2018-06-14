/**
 * datagrid数据删除
 * @param {Object} datagridName
 * @param {Object} urlName
 */
function deleteData(datagridName, urlName) {
	var recList = $("#" + datagridName).datagrid("getSelections");
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择删除项！", "error");
		return;
	}
	$.messager.confirm("确认", "确定要删除吗？", function(r) {
		if(r) {
			$.ajax({
				type: "POST",
				url: urlName,
				contentType: 'application/json',
				data: JSON.stringify(recList),
				dataType: "json",
				success: function(json) {
					$.messager.alert('成功', json.data.length + '条数据被删除',
						'info');
					$("#" + datagridName).datagrid('reload');
				},
			});
		}
	});
};

/**
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitFormProblem(datagridName, dialogName, formName, urlName) {
	var form_data = $("#form_problemAgency").serializeObject();
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
};
/**
 * 表单窗口(新增)
 * @param {Object} titleName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} datagridName
 * @param {Object} toolbarName
 * @param {Object} index
 */
function openFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName) {
	$("#" + formName).form('clear');
	$("input[name='submit_method']").val('save');
	if(index != null) {
		$("input[name='submit_method']").val('update');
		$("#" + datagridName).datagrid("selectRow", index);
		var rec = $("#" + datagridName).datagrid("getSelected");
		$("#" + formName).form("load", rec);
	}
	$("#" + dialogName).dialog({
		title: titleName,
		width: 680,
		height: 470,
		modal: true,
		shadow: true,
		closable: false,
		cache: false,
		buttons: "#" + toolbarName,
	});
	closewin_flg = false;
};

/**
 * pagination格式化分页工具栏
 * @param {Object} datagridName
 */
function formatPager(datagridName) {
	var pager = $("#" + datagridName).datagrid('getPager');
	$(pager).pagination({
		showPageList: false, //每页显示数量选择框
		showRefresh: false, //刷新按钮
		displayMsg: "",
	});
};

/**
 * 关闭窗口，并清空表单数据
 * @param {Object} dialogName
 */
function closeDialogProblem(dialogName) {
	$("#" + dialogName).window('close');
};


/*//datagrid加载
var initLoadGridData = function(datagrid_id,queryUrl,toolbarName,titleName,queryParam){
	$('#'+datagrid_id).datagrid({
		url:queryUrl,
		title: titleName,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:true,
		striped:true,
		rownumbers:true,
		loadMsg:"数据加载中...",
		pagination:true,
		pagePosition:"bottom",
		pageNumber:1,
		pageSize:5,
		pageList:[5,10,20],
		toolbar:'#'+toolbarName,
		fit:true,
		loader:function(param, success, error){
			param = param || {};
			var extraParam = queryParam;
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: queryUrl,
				data: { param: JSON.stringify(param)},
				success: function(respose){
					success({
						total: respose.total,
						rows: respose.rows
					});
				}
			});
			return true;
		}
	});
	
	var pager_info = $('#'+datagrid_id).datagrid('getPager'); 
    $(pager_info).pagination({
    	displayMsg: '当前显示 第{from} - {to} 条     共{total}条记录'
    });
}*/



/**
 * dialog打开窗口
 * @param {Object} dialogName
 * @param {Object} titleName
 * @param {Object} toolbarName
 */
function openDialog(dialogName,titleName,toolbarName,wsize,hsize){
	$("#" + dialogName).dialog({
		title: titleName,
		width: wsize,
		height: hsize,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#" + toolbarName,
	});
	closewin_flg = false;
};

/**
 * ajax请求下拉框数据
 * @param {Object} comboboxName
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 * @param {Object} value
 */
function queryDataClass(comboboxName,urlName,valueField,textField){
	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",
        data:{class_id:'root'},
        url: urlName,//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。
        	combotreeInit(comboboxName,urlName,valueField,textField,data.rows);
        }  
    }); 
}

/**
 * combobox下拉框初始化
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 */
function comboboxInit(comboboxName,urlName,valueField,textField,data){
	$("#"+comboboxName).combobox({
		valueField:valueField,
		textField:textField,
		editable:false,
		data:data,
	});
}



/**
 * combotree下拉框初始化
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 */
function combotreeInit(comboboxName,urlName,valueField,textField,data){
	$("#"+comboboxName).combotree({
		editable:false,
		data:data,
	});
}