function allAmount(result){
	$("#surplus").text(result.data);
	$("#all_amount").text(parseFloat($("#all_charge").text())+parseFloat($("#last").text())+parseFloat(result.data));
	
}
function getSurplus(result){
	$("#last").text(result.data);
	$.ajax({
        url: "ezfm/system/crop/surplusPrice",
        type: "get",
        dataType: "json",
        contentType:"application/json",
        async:false,
        success: allAmount,
        error: errorFunction
    });
}

function getLast(result){
	$("#all_charge").text(result.data);
	$.ajax({
        url: "ezfm/system/crop/lastCharge",
        type: "get",
        dataType: "json",
        contentType:"application/json",
        async:false,
        success: getSurplus,
        error: errorFunction
    });
}
function errorFunction(request, textStatus, errorThrown) {
	alert(request);
	alert(textStatus);
    alert(errorThrown);
}
// 页面加载
$(function() {
	$.ajax({
        url: "ezfm/system/crop/allCharge",
        type: "get",
        dataType: "json",
        contentType:"application/json",
        async:false,
        success: getLast,
        error: errorFunction
    });
	$("#yjwy_main_grid").datagrid({
		singleSelect:false,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:true,
		pageSize: 50,
		pageNumber: 1,
		rownumbers: true,
		singleSelect:true,
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { metas: ['yjwy_saas_charge'] };
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/system/crop/charge/query',
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

});


