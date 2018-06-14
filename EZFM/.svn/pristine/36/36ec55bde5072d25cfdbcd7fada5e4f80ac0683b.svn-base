// 字典详情删除按钮
function deleteDictDetails() {
	var data = $('#dictdetailsgrid').datagrid('getSelected');
	if (!data) {
		$.messager.alert('提示', '请选择待删除行');
		return;
	}
	$.messager.confirm('提示', '是否确认删除选定行', function(r) {
		if (r) {
			var url = 'ezfm/system/dict/del';
			var successFunc = function(result) {
				$('#dictdetailsgrid').datagrid('getPager').pagination('select');
				$('#dictdetailsgrid').datagrid('reload');
			}
			$.request.restPost({
				url : url,
				data : data,
				success : successFunc
			});

		}
	});
};

// 字典详情编辑按钮
function updateDictSon() {
	// 打开编辑窗口时设置编码框为只读
	$('#dictcode').textbox('textbox').attr('readonly', true);
	var data = $("#dictdetailsgrid").datagrid("getSelected");
	if (!data) {
		$.messager.alert('提示', '请选择需要编辑的数据');
		return;
	}
	$('#dictdetailsform').form('load', data);
	$('#dictdetailsdlg').window({
		title : '编辑字典详情',
		closed : false
	});
}

// 字典编辑按钮
function updateDictFather() {
	// 打开编辑窗口时设置编码框为只读
	$('#dictcode1').textbox('textbox').attr('readonly', true);
	var data = $("#dictgrid").datagrid("getSelected");
	if (!data) {
		$.messager.alert('提示', '请选择需要编辑的数据');
		return;
	}
	$('#dictform').form('clear');
	$('#dictform').form('load', data);
	$('#dictdlg').window({
		title : '编辑字典',
		closed : false
	});
}

function go(value, row) {
	return "<a href='javascript:open();'>" + value + "</a>";
};
var dict_parent = "";
function open() {
	// 打开详情表单 根据父级id查找子级
	var data = $("#dictgrid").datagrid("getSelected");
	dict_parent = data.dict_code;
	
	$('#win').dialog({
		width: 700,
		height: 380,
		top:($(window).height() - 380) * 0.5,  
	    left:($(window).width() - 700) * 0.5,
		title:'字典详情编辑',
		closed:false,
		modal: true,
		shadow: true,
		closable: true
	});
	
	var dictdetailsgrid = $('#dictdetailsgrid').datagrid({
		singleSelect : true,
		loadMsg : '数据装载中......',
		striped : true,// True 奇偶行使用不同背景色
		pagination : false,// 如果为true，则在数据表格控件底部显示分页工具栏
		pageSize : 50,// 页容量，必须和pageList对应起来，否则会报错
		pageNumber : 1,// 默认显示第几页
		pageList : [ 50, 60, 70 ],// 分页中下拉选项的数值
		rownumbers : true,// 如果为true，则显示一个行号列
		pagePosition : "bottom",
		fit : true,
		url : 'ezfm/system/dict/query',
		loader : function(param, success, error) {
			param = param || {};
			var extraParam = {
				metas : [ 'yjwy_dict' ],
				'andList' : [ {
					key : 'dict_parent_',
					operator : 'eq',
					value : data.dict_code
				} ]
			};
			if (param.rows) {
				extraParam.limit = param.rows;
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url : 'ezfm/system/dict/query',
				data : {
					param : JSON.stringify(param)
				},
				success : function(rs) {
					success({
						total : rs.total,
						rows : rs.data
					});
				}
			});
			return true;
		}
	});
	

	// 字典详情新增按钮
	$("#btnNewdetails").click(function() {
		// 打开新增窗口时设置编码框为可写
		$('#dictcode').textbox('textbox').attr('readonly', false);
		$('#dictdetailsform').form('clear');
		$('#pk_dict').val(pc);
		$('#dictdetailsdlg').window({
			title : '新增字典详情',
			closed : false
		});
	});

	// 字典详情新增及编辑窗口
	var dictdetailsdlg = $('#dictdetailsdlg').dialog({
		width : 480,
		height : 280,
		left : ($(window).width() - 480) * 0.5,
		top : ($(window).height() - 300) * 0.5,
		resizable : true,
		modal : true,
		closed : true,
		cache : false,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				if ($('#dictdetailsform').form('validate')) {
					var json = $('#dictdetailsform').serializeJson();
					json.dict_parent = dict_parent;
					$.request.restPost({
						url : json.pk_dict ? 'ezfm/system/dict/update' : 'ezfm/system/dict/save',
						data : json,
						success : function(result) {
							dictdetailsdlg.window('close');
							dictdetailsgrid.datagrid('reload');
							if(!result.success){
                                $.messager.alert('提示',result.message);
							}
						},
						failure : function(rs) {
							$.messager.alert('提示', '保存失败');
						}
					})
				}
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '字典详情未保存，是否确认退出', function(r){
					if(r){
						$('#dictdetailsform').form('clear');
						dictdetailsdlg.dialog('close');
					}
				});
			}
		}]
	});

};

// 页面加载
$(function() {
	var qryParam = {};
	var dictgrid = $('#dictgrid').datagrid({
		singleSelect : true,
		selectOnCheck : true,
		checkOnSelect : true,
		loadMsg : '数据装载中......',
		striped : true,// True 奇偶行使用不同背景色
		pagination : true,// 如果为true，则在数据表格控件底部显示分页工具栏
		pageSize : 15,// 页容量，必须和pageList对应起来，否则会报错
		pageNumber : 1,// 默认显示第几页
		pageList : [ 15, 30, 45 ],// 分页中下拉选项的数值
		rownumbers : true,// 如果为true，则显示一个行号列
		pagePosition : "bottom",
		fit : true,
		url : '${basePath?if_exists}ezfm/system/dict/query',
		loader : function(param, success, error) {
			param = param || {};
			var extraParam = {
				metas : [ 'yjwy_dict' ],
				'andList' : [ {
					key : 'dict_node_',
					operator : 'eq',
					value : 1
				} ]
			};
			if (param.rows) {
				extraParam.limit = param.rows;
				if (param.page) {
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url : 'ezfm/system/dict/query',
				data : {
					param : JSON.stringify(param)
				},
				success : function(rs) {
					success({
						total : rs.total,
						rows : rs.data
					});
				}
			});
			return true;
		}
	});
	// 分页
	$('#dictgrid').datagrid('getPager').pagination({
		onSelectPage : function(pPageIndex, pPageSize) {
			dataQuery('ezfm/system/dict/query', qryParam, (pPageIndex - 1) * pPageSize, pPageSize);
		}
	});

	// 分页工具栏
	var dataQuery = function(url, jsonParam, start, limit) {
		$('#dictgrid').datagrid('loading');
		jsonParam = jsonParam || {};
		$.extend(jsonParam, {
			metas : [ 'yjwy_dict' ],
			'andList' : [ {
				key : 'dict_node_',
				operator : 'eq',
				value : 1
			} ],
			limit : limit,
			start : start
		});
		$.request.httpPost({
			url : 'ezfm/system/dict/query',
			data : {
				param : JSON.stringify(jsonParam)
			},
			success : function(rs) {
				$('#dictgrid').datagrid('loadData', {
					total : rs.total,
					rows : rs.data
				});
				$('#dictgrid').datagrid('loaded');
			}
		});
		return true;
	}

	// 字典新增按钮
	$("#btnNew").click(function() {
		// 打开新增窗口时设置编码框为可写
		$('#dictcode1').textbox('textbox').attr('readonly', false);
		$('#dictform').form('clear');
		$('#pk_dict').val(pc);
		$('#dictdlg').window({
			title : '新增字典',
			closed : false
		});
	});

	// 字典删除按钮 如果没有子级才可以删除
	$('#btnDelete').bind('click', function() {
		var recs = dictgrid.datagrid('getSelections');
		if (!recs.length) {
			$.messager.alert('提示', '请选择待删除行');
			return;
		}
		$.messager.confirm('提示', '是否确认删除选定行', function(r) {
			if (r) {
				var url = 'ezfm/system/dict/delete';
				var successFunc = function(result) {
					$('#dictgrid').datagrid('getPager').pagination('select');
					if (result.data == null) {
						$.messager.alert('提示', '请先删除子级节点');
					} else {
						dictgrid.datagrid('reload');
					}

				}
				$.request.restPost({
					url : url,
					data : recs,
					success : successFunc
				});

			}
		});
	});

	// 根据条件模糊查询
	$("#btnselect").click(function() {
		// 获取文本框内容
		var textcode = $('#textcode').textbox('getValue');
		var textname = $('#textname').textbox('getValue');
		var dictgrid = $('#dictgrid').datagrid({
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : true,
			loadMsg : '数据装载中......',
			striped : true,
			pagination : true,
			pageSize : 15,
			pageNumber : 1,
			pageList : [ 15, 30, 45 ],
			rownumbers : true,
			url : '${basePath?if_exists}ezfm/system/dict/query',
			loader : function(param, success, error) {
				param = param || {};
				var extraParam = {
					metas : [ 'yjwy_dict' ],
					'andList' : [ {
						key : 'dict_code_',
						operator : 'like',
						value : textcode
					}, {
						key : 'dict_name_',
						operator : 'like',
						value : textname
					}, {
						key : 'dict_node_',
						operator : 'eq',
						value : 1
					} ]
				};
				if (param.rows) {
					extraParam.limit = param.rows;
					if (param.page) {
						extraParam.start = (param.page - 1) * param.rows;
					}
				}
				$.extend(param, extraParam);
				$.request.httpPost({
					url : 'ezfm/system/dict/query',
					data : {
						param : JSON.stringify(param)
					},
					success : function(rs) {
						success({
							total : rs.total,
							rows : rs.data
						});
					}
				});
				return true;
			}
		});
	});	
	
	// 字典新增及编辑窗口
	var dictdlg = $('#dictdlg').dialog({
		width : 480,
		height : 280,
		left : ($(window).width() - 480) * 0.5,
		top : ($(window).height() - 300) * 0.5,
		resizable : true,
		modal : true,
		closed : true,
		cache : false,
		buttons : [ {
			text : '提交',
			iconCls : 'icon-ok',
			handler : function() {
				if ($('#dictform').form('validate')) {
					var json = $('#dictform').serializeJson();
					$.request.restPost({
						url : json.pk_dict ? 'ezfm/system/dict/update' : 'ezfm/system/dict/saveParent',
						data : json,
						success : function(result) {
							if (result.success) {
								$('#dictgrid').datagrid('getPager').pagination('select');
								$('#dictform').form('clear');
								dictdlg.window('close');
							} else {
								$.messager.alert("提示", result.message);
							}
						},failure: function(rs){
							$.messager.alert('提示', rs.message || '保存失败');
						}
					})
				}
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '新增数据字典未保存，是否确认退出', function(r){
					if(r){
						$('#dictform').form('clear');
						dictdlg.dialog('close');
					}
				});
			}	
		}]
	});
});