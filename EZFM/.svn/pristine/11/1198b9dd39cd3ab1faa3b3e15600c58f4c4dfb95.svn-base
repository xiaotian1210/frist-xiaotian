//datagrid加载
var initLoadGridDatas = function(datagrid_id,queryUrl,toolbarName,titleName,queryParam){
	$('#'+datagrid_id).datagrid({
		url:queryUrl,
		title: titleName,
		singleSelect:false,
		checkOnSelect:true,
		selectOnCheck:true,
		striped:true,
		rownumbers:true,
		loadMsg:"数据加载中...",
		pagination:true,
		pagePosition:"bottom",
		pageNumber:1,
		pageSize:25,
		pageList:[25,50,100],
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
}

/**
 * pagination格式化分页工具栏
 * @param {Object} datagridName
 *//*
function formatPager(datagridName) {
	var pager = $("#" + datagridName).datagrid('getPager');
	$(pager).pagination({
		showPageList: false, //每页显示数量选择框
		showRefresh: false, //刷新按钮
		displayMsg: "",
	});
};*/


/**
 * 关闭窗口，并清空表单数据
 * @param {Object} dialogName
 */
function closeDialogProblem(dialogName,formName) {
	if(formName != null) {
		$("#" + formName).form("clear");
		$("#" + formName).form("reset");
	}
	$("#" + dialogName).window('close');
};