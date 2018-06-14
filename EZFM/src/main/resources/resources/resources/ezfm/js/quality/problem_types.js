$(function(){
	$('#problem_types_id').datagrid({
		data: [
			{problem_code:'value11', problem_name:'value12'},
			{problem_code:'value11', problem_name:'value12'}
		],
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
		pageList:[25],
		toolbar:'#tb'
	});
	var problem_pager = $('#problem_types_id').datagrid('getPager'); 
    $(problem_pager).pagination({
    	displayMsg: '当前显示 {from} - {to} 条记录    共 {total} 条记录'
    });
});