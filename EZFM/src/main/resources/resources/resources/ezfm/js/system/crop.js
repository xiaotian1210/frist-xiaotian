var renderUsable = function(value, row, index){
	return value == '1' ? '<div style="color:green">启用</div>' : '<div style="color:red">停用</div>';
}
function formatFile(value, row, index) {

	return "<a href='javascript:void(0)' onclick='read_download_files(/" + row['pk_crop'] + "/)'>查看</a>";
}

function read_download_files(record_id) {
	record_id = (record_id+"").replace(/\//g,'');
	var url = "ezfm/file/index?query_table=" + "yjwy_pub_crop_file" + "&table_name=" + "yjwy_pub_crop" + "&flag=" + "crop" + "&record_id=" + record_id;
	window.open(url, '_blank');
}
$(function(){
	var dialog = $('#dialog').dialog({
		title: '新增企业',
		width: 480,
		height: 410,
		left:($(window).width()-480)/2,
		top:($(window).height()-400)/2,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				var worknumber = $("#workphone").textbox("getValue");
//				var myreg = /^1\d{10}$/;
				var myreg = /^1[3|4|5|7|8|9]\d{9}$/;
				if(!myreg.test(worknumber)){
					$.messager.alert('提示','请输入以13,14,15,17,18,19开头的格式');
					return;
				}
				if($('#form').form('validate')){
					var json = $('#form').serializeJson();
					$.request.restPost({
						url: json.pk_crop ? 'ezfm/system/crop/update':'ezfm/system/crop/save',
						data: json,
						success: function(result){
							if(result.success){
								$('#datagrid').datagrid('getPager').pagination('select');
								$('#form').form('clear');
								dialog.window('close');
								$.messager.show({
									title:'提示',
									msg:'数据已保存',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert('提示', result.message || '保存失败');
							}
						},
						failure: function(rs){
							$.messager.alert('提示', rs.message || '保存失败');
						}
					})
				}
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r){
					if(r){
						$('#form').form('clear');
						dialog.window('close');
					}
				});
			}
		}]
	});
	var dataQuery = function(url, jsonParam, start, limit) {
		$('#datagrid').datagrid('loading');
		var andList = [];
		if(jsonParam){
			andList.push(jsonParam);
		}
		var param= {
			metas: ['yjwy_crop'],
			'andList':andList,
			limit: limit,
			start: start
		};
		$.request.httpPost({
			url: 'ezfm/system/crop/query',
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
		url: 'ezfm/system/crop/query',
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { metas: ['yjwy_crop'] };
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/system/crop/query',
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
	    	dataQuery('ezfm/system/crop/query', null, (pPageIndex - 1) * pPageSize, pPageSize);
	    }   
	});
	var form = $('#form').form();
	$('#btnNew').click(function(){
		closewin_flg = false;
		$('#form').form('clear');
		dialog.dialog({
			title: '新增企业'
		});
		dialog.window('open');
	});
	$('#btnEdit').click(function(){
		closewin_flg = false;
		var row = $('#datagrid').datagrid('getSelected');
		if(!row){
			$.messager.alert('提示', '请选择待编辑行');
			return;
		}
		$('#form').form('reset')
		$('#form').form('load', row);
		dialog.dialog({
			title: '编辑企业信息'
		});
		dialog.window('open');
	});
	$('#btnDelete').bind('click', function(){
		var recs = datagrid.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待删除行');
			return;
		}
		$.messager.confirm('提示', '是否确认删除选定行', function(r){
			if(r){
				var url = 'ezfm/system/crop/delete';
				var successFunc = function(result){
					if(result.success){
						$('#datagrid').datagrid('getPager').pagination('select');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$.messager.alert('提示', result.message || '删除失败');
					}
				}
				$.request.restPost({
					url: url,
					data: recs,
					success: successFunc,
					
					failure: function(rs){
						$.messager.alert('提示', rs.message || '删除失败');
					}
				});
			}
		});
	});
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
	
	var userDlg = $('#userDlg').dialog({
		title: '管理员初始化',
		width: 480,
		height: 280,
		left:($(window).width()-480)/2,
		top:($(window).height()-350)/2,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				if($('#userForm').form('validate')){
					var json = $('#userForm').serializeJson();
					if(json.password!=json.password2){
						$.messager.alert('提示', "两次密码输入不一致");
						return;
					}
					$.request.restPost({
						url: json.pk_user ?'ezfm/system/crop/user/update':'ezfm/system/crop/user/save',
						data: json,
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
						success: function(result){
							if(result.success){
								userDlg.window('close');
								$.messager.show({
									title:'提示',
									msg:'数据已保存',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert('提示', result.message || '保存失败');
							}
						},
						failure: function(rs){
							$.messager.alert('提示', rs.message || '保存失败');
						}
						
					})
				}	
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r){
					if(r){
						$('#form').form('clear');
						userDlg.window('close');
					}
				});
			}
		}]
	});
	$('#btnInit').click(function(){
		closewin_flg = false;
		$('#userForm').form('clear');
		var row = $('#datagrid').datagrid('getSelected');
		
		if(!row){
			$.messager.alert('提示', '请选择企业');
			return;
		}
		
		var extraParam = { 
			metas: ['yjwy_user'],
			'andList': [{
				key: 'pk_crop_',
				operator: 'eq',
				value: row.pk_crop
			},{
				key: 'is_pre_',
				operator: 'eq',
				value: true
			}]
		}
		
		$.request.httpPost({
			url: 'ezfm/baseinfo/user/query',
			data: { param: JSON.stringify(extraParam)},
			success: function(rs){
				if(rs.data.length>0){
					d = rs.data[0];
					$('#userForm').form('load',d);
					$('#password2').textbox("setValue",d.password);
				}else{
					$('#corp_user').val(row.pk_crop);
					$('#is_pre').val(true);
				}
				userDlg.window('open');
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '查询失败');
			}
		});
	});
});