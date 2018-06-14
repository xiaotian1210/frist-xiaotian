//成章京- 调度人员登录后定时刷新任务信息 
var worktask_timer;
function workTask_overtime_remind(){
	$.request.httpPost({
		url: 'ezfm/worktask/details/timing',
		async:false,
		success: function(rs){
			if(rs.success){
				$.messager.show({
					title:"<span style='color:blue;'>超时工单提醒</span>",
					width: 350,
					height:200,
					msg:'您有以下单号超时工单未处理<br>'+JSON.stringify(rs.datailsCodeList),
					timeout:300000,
					showType:'slide'
				});
			}
		}
	});
}

$(function(){
	$.request.httpPost({
		url: 'ezfm/worktask/areauser/whether/dispatch',
		async:false,
		data: {},
		success: function(rs){
			if(rs.success){
				worktask_timer = setInterval(workTask_overtime_remind,500000); 
			}
		},
		failure: function(rs){
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
});