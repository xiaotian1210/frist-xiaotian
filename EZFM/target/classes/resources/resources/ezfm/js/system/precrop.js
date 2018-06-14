
$(function(){

	var dataQuery = function(url, jsonParam, start, limit) {
		$('#datagrid').datagrid('loading');
		var andList = [];
		if(jsonParam){
			andList.push(jsonParam);
		}
		var param= {
			metas: ['yjwy_pre_crop'],
			'andList':andList,
			limit: limit,
			start: start
		};
		$.request.httpPost({
			url: 'ezfm/system/crop/pre/query',
			data: { param: JSON.stringify(param)},
			success: function(rs){
				$('#datagrid').datagrid('loadData', {
					total: rs.total,
					rows: rs.data
				});
				$('#datagrid').datagrid('loaded');
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '查询失败');
			}
		});
	}
	var datagrid = $('#datagrid').datagrid({
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:true,
		pageSize: 50,
		pageNumber: 1,
		rownumbers: true,
		singleSelect:true,
		url: 'ezfm/system/crop/pre/query',
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { metas: ['yjwy_pre_crop'] };
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/system/crop/pre/query',
				data: { param: JSON.stringify(param)},
				success: function(rs){
					success({
						total: rs.total,
						rows: rs.data
					});
				},
				failure: function(rs){
					$.messager.alert('提示', rs.message || '查询失败');
				}
			});
			return true;
		}
	});
	$('#datagrid').datagrid('getPager').pagination({   
	    onSelectPage : function(pPageIndex, pPageSize) {   
	    	dataQuery('ezfm/system/crop/pre/query', null, (pPageIndex - 1) * pPageSize, pPageSize);
	    }   
	});
	var form = $('#form').form();
	
	$('#btnQuery').click(function(){
		$('#datagrid').datagrid('getPager').pagination('select');
	});
	$('#querysearch').searchbox({ 
		prompt:'请输入关键字', 
		searcher:function(value,name){
			var options = $('#datagrid').datagrid('options');
			var pageSize = options.pageSize;
			var qryParam = null;
			if(value){
				qryParam = {
					key: name,
					operator: 'like',
					value: value
				};
			}
			dataQuery(null, qryParam, 0, pageSize);
		}
	});

});

//格式化附件
function formatFile(value, row, index) {

	return "<a href='javascript:void(0)' onclick='read_download_files_pre_crop(/" + row['pk_pre_crop'] + "/)'>查看</a>";
}

function read_download_files_pre_crop(record_id) {
	record_id = (record_id+"").replace(/\//g,'');
	var url = "ezfm/file/index?query_table=" + "yjwy_pub_crop_file" + "&table_name=" + "yjwy_pre_crop" + "&flag=" + "precrop" + "&record_id=" + record_id;
	window.open(url, '_blank');
}


//格式化操作
function formatOperation(value, row, index) {
    var enable = "<a href='javascript:void(0)' onclick='check(/" + row['pk_pre_crop'] + "/,1)'>通过</a>&nbsp;<a href='javascript:void(0)' onclick='check(/" + row['pk_pre_crop'] + "/,2)'>拒绝</a>";
    if(value!="待审核")
    	enable = value;
	return enable;
}

//批准
function check(pk, type) {
	//$("#datagrid").datagrid("selectRow", index);
	//var row = $("#datagrid").datagrid("getSelected");
	pk = (pk+"").replace(/\//g,'');
	var msg = "确定要通过吗？";
	if (type == 1) {
	}else if(type == 2){
		msg = "确定要拒绝吗？"
	}
	$.messager.confirm("确认", msg, function(r) {
		if (r) {
			var datas = {"pk_pre_crop":pk,"type":type};
			var data = 
			$.ajax({
				type : "POST",
				url : 'ezfm/system/crop/check',
				data: datas,
			    beforeSend: function () {
			        $.messager.progress({ 
			           title: '提示', 
			           msg: '处理中，请稍候……', 
			           text: '' 
			        });
			    },
			    complete: function () {
			        $.messager.progress('close');
			    },
				success : function(json) {
					if(json.success){
						$.messager.alert('成功', '操作成功', 'info');
						$('#datagrid').datagrid('reload');
					}
					else{
						$.messager.alert('失败', json.message, 'info');
						$('#datagrid').datagrid('reload');
					}
					
				},
			});
		}
	});
}