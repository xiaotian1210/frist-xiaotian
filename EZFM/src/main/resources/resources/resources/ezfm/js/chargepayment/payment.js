
// 页面加载
$(function() {
	$.ajax({
        url: "ezfm/system/crop/allPayment",
        type: "get",
        dataType: "json",
        contentType:"application/json",
        async:false,
        success: function(result){
        	$("#all_amount").text(result.data);
		},
        error: function (request, textStatus, errorThrown) {
    		alert(request);
    		alert(textStatus);
            alert(errorThrown);
        }
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
			var extraParam = { metas: ['yjwy_saas_payment'] };
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/system/crop/payment/query',
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


