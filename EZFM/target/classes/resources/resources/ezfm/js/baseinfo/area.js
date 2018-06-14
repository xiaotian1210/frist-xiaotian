$(function(){
	var qryParam = {};
	var areagrid = $('#areagrid').datagrid({
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:true,
		pageSize: 20,
		pageNumber: 1,
		rownumbers: true,
		url: 'ezfm/baseinfo/area/query',
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { 
				metas: ['yjwy_area'],
				'andList': [{
					key: 'pk_crop_',
					operator: 'eq',
					value: pc
				}] 
			};
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/baseinfo/area/query',
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
	
	$('#areagrid').datagrid('getPager').pagination({   
	    onSelectPage : function(pPageIndex, pPageSize) {   
	    	dataQuery('ezfm/baseinfo/area/query', qryParam, (pPageIndex - 1) * pPageSize, pPageSize);
	    }   
	});
	
	var dataQuery = function(url, jsonParam, start, limit) {
		$('#areagrid').datagrid('loading');
		var andList = [];
		andList.push({
			key: 'pk_crop_',
			operator: 'eq',
			value: pc
		});
		if(!$.isEmptyObject(jsonParam)){
			andList.push(jsonParam);
		}
		var param= {
			metas: ['yjwy_area'],
			'andList':andList,
			limit: limit,
			start: start
		};
		$.request.httpPost({
			url: 'ezfm/baseinfo/area/query',
			data: {param: JSON.stringify(param)},
			success: function(rs){
				$('#areagrid').datagrid('loadData', {
					total: rs.total,
					rows: rs.data
				});
				$('#areagrid').datagrid('loaded');
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '查询错误');
			}
		});
		return true;
	}
	
	$('#querysearch').searchbox({ 
		prompt:'请输入区域名称', 
		searcher:function(value,name){
			var options = $('#areagrid').datagrid('options');
			var pageSize = options.pageSize;
			var qryParam = {};
			if(value){
				qryParam = {
					key: name,
					operator: 'like',
					value: value
				};
			}
			dataQuery('ezfm/baseinfo/area/query', qryParam, 0, pageSize);
		}
	});
	
	$("#btnNew").click(function() {
		closewin_flg = false;
		$('#areaform').form('clear');
		$('#area_mainform_pk_crop_id').textbox("setValue",pc);
		$('#areadlg').dialog({
			title:'新增区域',
			closed:false
		});
	});
	
	$("#btnEdit").click(function() {
		closewin_flg = false;
		var data = $("#areagrid").datagrid("getSelected");
		if(!data){
			$.messager.alert('提示','请选择需要编辑的数据');
			return ;
		}
		$('#areaform').form('clear')
		$('#areaform').form('load',data);
		$('#areadlg').dialog({
			title:'编辑区域信息',
			closed:false
		});
	});
	
	
	$('#btnDelete').bind('click', function(){
		var recs = areagrid.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待删除行');
			return;
		}
		$.messager.confirm('提示', '是否确认删除选中区域，请谨慎操作', function(r){
			if(r){
				var url = 'ezfm/baseinfo/area/delete';
				var successFunc = function(result){
					if(result.success){
						$('#areagrid').datagrid('getPager').pagination('select');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$.messager.alert("提示",result.message,"warning");
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
	
	var areadlg = $('#areadlg').dialog({
		width:540,
		height:270,
		closed:true,
		cache:false,
		left:($(window).width()-500)/2,
		top:($(window).height()-280)/2,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				if($('#areaform').form('validate')){
					var json = $('#areaform').serializeJson();
					$.request.restPost({
						url: json.pk_area ? 'ezfm/baseinfo/area/update':'ezfm/baseinfo/area/save',
						data: json,
						success: function(result){
							if(result.success){
								$('#areagrid').datagrid('getPager').pagination('select');
								$('#areaform').form('clear');
								areadlg.dialog('close');
								$.messager.show({
									title:'提示',
									msg:'数据已保存',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert("提示",result.message);
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
						$('#areaform').form('clear');
						areadlg.dialog('close');
					}
				});
			}
		}]
	});
});