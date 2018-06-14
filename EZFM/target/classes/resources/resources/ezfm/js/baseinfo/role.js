$(function() {
	/** 以下关联代码 */
	// 加入用户
	$("#role_add_user_btn").click(function() {
		var recs = $("#rolegrid").datagrid('getSelections');
		if (recs.length <= 0) {
			$.messager.alert('提示', '请先选择角色');
			return;
		}
		//var addUrl = "ezfm/baseinfo/role/son/user/index";
		//showCustomWindow("role_add_user_win_id", "用户列表", addUrl)
		showAddUserDiaLog();
	});

	// 移除用户
	$("#role_remove_user_btn").click(function() {
		var deleteRecods = $('#yjwy_user_main_grid').datagrid("getSelections");

		if (deleteRecods.length < 1) {
			$.messager.alert('提示', '请您先选择移除的用户!', 'warning');
			return;
		}
		var recs = $("#rolegrid").datagrid('getSelections');
		var actionUrl = "ezfm/baseinfo/role/user/remove/" + recs[0]['pk_role'];
		$.messager.confirm('提示', '您确认移除选中的用户吗?', function(r) {
			if (r) {
				$.request.restPost({
					url : actionUrl,
					data : deleteRecods,
					success : function(rs) {
						if (rs.success) {
							$('#yjwy_user_main_grid').datagrid('reload');
							$.messager.show({
								title : '提示',
								msg : '用户已移除',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", rs.message, "warning");
						}
					}
				});
			}
		});
	});

	/** 以上关联代码 */
	var qryParam = {};
	var rolegrid = $('#rolegrid').datagrid({
		singleSelect : true,
		selectOnCheck : true,
		checkOnSelect : true,
		loadMsg : '数据装载中......',
		striped : true,
		pagination : true,
		pageSize : 50,
		pageNumber : 1,
		rownumbers : true,
		url : 'ezfm/baseinfo/role/query',
//		onClickRow : function(rowIndex, rowData) {
//			loadUser(rowData['pk_role']);
//		},
		onSelect:function(rowIndex, rowData) {
			loadUser(rowData['pk_role']);
		},
		loader : function(param, success, error) {
			param = param || {};
			var extraParam = {
				metas : [ 'yjwy_role' ],
				'andList' : [ {
					key : 'pk_crop_',
					operator : 'eq',
					value : pc
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
				url : 'ezfm/baseinfo/role/query',
				data : {
					param : JSON.stringify(param)
				},
				success : function(rs) {
					success({
						total : rs.total,
						rows : rs.data
					});
				},
				failure : function(rs) {
					$.messager.alert('提示', rs.message || '查询失败');
				}
			});
			return true;
		}
	});

	$('#rolegrid').datagrid('getPager').pagination({
		onSelectPage : function(pPageIndex, pPageSize) {
			dataQuery('ezfm/baseinfo/role/query', qryParam, (pPageIndex - 1) * pPageSize, pPageSize);
		}
	});

	var dataQuery = function(url, jsonParam, start, limit) {
		$('#rolegrid').datagrid('loading');
		jsonParam = jsonParam || {};
		$.extend(jsonParam, {
			metas : [ 'yjwy_role' ],
			'andList' : [ {
				key : 'pk_crop_',
				operator : 'eq',
				value : pc
			} ],
			limit : limit,
			start : start
		});
		$.request.httpPost({
			url : 'ezfm/baseinfo/role/query',
			data : {
				param : JSON.stringify(jsonParam)
			},
			success : function(rs) {
				$('#rolegrid').datagrid('loadData', {
					total : rs.total,
					rows : rs.data
				});
				$('#rolegrid').datagrid('loaded');
			},
			failure : function(rs) {
				$.messager.alert('提示', rs.message || '查询错误');
			}
		});
		return true;
	}

	$("#btnNew").click(function() {
		closewin_flg = false;
		$('#form').form('clear')
		$('#pk_crop').val(pc);
		$('#roledlg').window({
			title : '新增角色',
			closed : false
		});
	});

	$("#btnEdit").click(function() {
		closewin_flg = false;
		var data = $("#rolegrid").datagrid("getSelected");
		if (!data) {
			$.messager.alert('提示', '请选择需要编辑的数据');
			return;
		}
		$('#form').form('clear')
		$('#form').form('load', data);
		$('#roledlg').window({
			title : '编辑角色信息',
			closed : false
		});
	});

	$('#btnDelete').bind('click', function() {
		var recs = rolegrid.datagrid('getSelections');
		if (!recs.length) {
			$.messager.alert('提示', '请选择待删除行');
			return;
		}
		for ( var i in recs) {
			if ("1" == recs[i].is_pre || true === recs[i].is_pre) {
				$.messager.alert("提示", "系统预置角色不可以删除", "warning");
				return;
			}
		}
		$.messager.confirm('提示', '是否确认删除选中角色，请谨慎操作', function(r) {
			if (r) {
				var url = 'ezfm/baseinfo/role/delete';
				var successFunc = function(result) {
					if (result.success) {
						$('#rolegrid').datagrid('getPager').pagination('select');
						$("#yjwy_user_main_grid").datagrid("reload",[]);
						$.messager.show({
							title : '提示',
							msg : '数据已删除',
							timeout : 2000,
							showType : 'slide'
						});
					} else {
						$.messager.alert("提示", result.message);
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

	var roledlg = $('#roledlg').dialog({
		width : 540,
		height : 280,
		closed : true,
		cache : false,
		left : ($(window).width() - 600) / 2,
		top : ($(window).height() - 350) / 2,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				if ($('#form').form('validate')) {
					var json = $('#form').serializeJson();
					$.request.restPost({
						url : json.pk_role ? 'ezfm/baseinfo/role/update' : 'ezfm/baseinfo/role/save',
						data : json,
						success : function(result) {
							if (result.success) {
								$('#rolegrid').datagrid('getPager').pagination('select');
								$('#form').form('clear');
								roledlg.window('close');
								$.messager.show({
									title : '提示',
									msg : '数据已保存',
									timeout : 2000,
									showType : 'slide'
								});
							} else {
								$.messager.alert("提示", result.message);
							}
						},
						failure : function(rs) {
							$.messager.alert('提示', rs.message || '保存失败');
						}
					})
				}
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r) {
					if (r) {
						$('#form').form('clear');
						roledlg.window('close');
					}
				});
			}
		} ]
	});
	/** *************以下是系统授权代码******************** */
	var dlg_func = $('#dlg_func').dialog({
		width : 450,
		height : 450,
		left : ($(window).width() - 500) / 2,
		top : ($(window).height() - 450) / 2,
		closed : true,
		cache : false,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				var role = $("#rolegrid").datagrid("getSelected");
				var nodes = $("#function_tree_id").tree("getChecked");
				var param = [];
				for (var i = 0; i < nodes.length; i++) {
					p = {
						pk_crop : pc,
						pk_func : nodes[i].id,
						pk_role : role.pk_role
					};
					param.push(p);
				}
				if (param.length < 1) {
					p = {
						pk_crop : pc,
						pk_func : "",
						pk_role : role.pk_role
					};
					param.push(p);
				}
				$.request.restPost({
					url : 'ezfm/baseinfo/role/authorization',
					data : param,
					success : function(result) {
						if (result.success) {
							dlg_func.window('close');
							$.messager.show({
								title : '提示',
								msg : '数据已保存',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", result.message, "warning");
						}
					},
					failure : function(rs) {
						$.messager.alert('提示', rs.message || '保存失败');
					}
				})
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$.messager.confirm('提示', '当前操作尚未保存，是否确认退出', function(r) {
					if (r) {
						dlg_func.window('close');
					}
				});
			}
		} ]
	});

	// 加载功能菜单树
	function loadFunctionTree() {
		var role = $("#rolegrid").datagrid("getSelected");
		var treeUrl = "ezfm/system/function/role/func/query";
		$.request.httpGet({
			url : treeUrl,
			async : false,
			data : {
				param : JSON.stringify({
					metas : [ 'yjwy_rolefunc' ],
					andList : [ {
						key : 'pk_crop_',
						operator : 'eq',
						value : pc
					}, {
						key : 'pk_role_',
						operator : 'eq',
						value : role.pk_role
					} ]
				})
			},
			success : function(result) {
				if (result.success) {
					$("#function_tree_id").tree({
						animate : true,
						dataPlan : true,
						checkbox : true,
						cascadeCheck : false,
						data : result.data,
						onCheck : function(node, checked) {
							// 获取当前节点的所有子节点
							var childs = $("#function_tree_id").tree("getChildren", node.target);
							// 当前节点的所有父级。
							var parents = [];
							var target = node.target;
							// 一层一层向上获取所有父级节点
							while (true) {
								// 获取父级
								var parent = $("#function_tree_id").tree("getParent", target);
								// 判断有效
								if (parent) {
									// 放入父级数组
									target = parent.target;
									parents.push(parent);
								} else {
									break;
								}
							}

							// 判断选中
							if (checked) {
								for ( var i in parents) {
									$('#function_tree_id').tree("update", {
										target : parents[i].target,
										checked : true,
									});
								}
								// 循环选中当前节点下所有子节点。
								for ( var i in childs) {
									$('#function_tree_id').tree('check', childs[i].target);
								}
								$('#function_tree_id').tree('expandAll', node.target);
							} else {
								// 判断取消，取消选择所有子节点
								for ( var i in childs) {
									$('#function_tree_id').tree('uncheck', childs[i].target);
								}
							}
						}
					});
				}
			}
		});
	}
	$("#btnFunc").click(function() {
		closewin_flg = false;
		var role = rolegrid.datagrid("getSelected");
		if (!role) {
			$.messager.alert('提示', '请选择角色');
			return;
		}
		$('#dlg_func').dialog("open");
		loadFunctionTree();
	});
	/** *************以上是系统授权代码******************** */

	/** *************以下是手机APP授权代码***************** */
	var app_dlg_func = $('#app_dlg_func').dialog({
		width : 500,
		height : 450,
		left : ($(window).width() - 500) / 2,
		top : ($(window).height() - 450) / 2,
		closed : true,
		cache : false,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				var role = $("#rolegrid").datagrid("getSelected");
				var nodes = $("#app_function_tree_id").tree("getChecked");
				var param = [];
				for (var i = 0; i < nodes.length; i++) {
					p = {
						pk_crop : pc,
						pk_func : nodes[i].id,
						pk_role : role.pk_role
					};
					param.push(p);
				}
				if (param.length < 1) {
					p = {
						pk_crop : pc,
						pk_func : "",
						pk_role : role.pk_role
					};
					param.push(p);
				}
				$.request.restPost({
					url : 'ezfm/baseinfo/role/app/authorization',
					data : param,
					success : function(result) {
						if (result.success) {
							app_dlg_func.window('close');
							$.messager.show({
								title : '提示',
								msg : '数据已保存',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", result.message, "warning");
						}
					},
					failure : function(rs) {
						$.messager.alert('提示', rs.message || '保存失败');
					}
				})
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$.messager.confirm('提示', '当前操作尚未保存，是否确认退出', function(r) {
					if (r) {
						app_dlg_func.window('close');
					}
				});
			}
		} ]
	});

	// 加载功能菜单树
	function loadAPPFunctionTree() {
		var role = $("#rolegrid").datagrid("getSelected");
		var treeUrl = "ezfm/baseinfo/role/app/function/query";
		$.request.httpGet({
			url : treeUrl,
			async : false,
			data : {
				param : JSON.stringify({
					metas : [ 'yjwy_app_role_func' ],
					andList : [ {
						key : 'pk_crop_',
						operator : 'eq',
						value : pc
					}, {
						key : 'pk_role_',
						operator : 'eq',
						value : role.pk_role
					} ]
				})
			},
			success : function(result) {
				if (result.success) {
					$("#app_function_tree_id").tree({
						animate : true,
						dataPlan : true,
						checkbox : true,
						cascadeCheck : false,
						data : result.data
					});
				}
			}
		});
	}
	$("#appBtnFunc").click(function() {
		closewin_flg = false;
		var role = rolegrid.datagrid("getSelected");
		if (!role) {
			$.messager.alert('提示', '请选择角色');
			return;
		}
		$('#app_dlg_func').dialog("open");
		loadAPPFunctionTree();
	});
	/** *************以上是机APP授权代码***************** */
});

var renderPre = function(value, row, index) {
	var is_pre = row.is_pre;
	return is_pre == '1' ? '<div style="color:blue">是</div>' : '否';
}

/* 关联 */
var renderUsable = function(value, row, index) {
	var is_able = row.is_able;
	return is_able == '1' ? '<div style="color:green">启用</div>' : '<div style="color:red">停用</div>';
}
var renderPre = function(value, row, index) {
	var is_pre = row.is_pre;
	return is_pre == '1' ? '<div style="color:blue">是</div>' : '否';
}
function loadUser(pk_role) {
	var queryParam = {
		pk_role : pk_role,
		flag : 'in'
	}
	var queryUrl = "ezfm/baseinfo/role/user/roleuser/query";
	var options = {
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true
	}
	// 初始化grid
	initLoadGridDataByCustom("yjwy_user_main_grid", queryUrl, queryParam,options);
}

/****************以下是加入用户操作****************/
var addUserQueryParam ={};
function showAddUserDiaLog() {
	closewin_flg = false;
	var recs = $("#rolegrid").datagrid('getSelections');
	if (!recs.length) {
		$.messager.alert('提示', '请选择角色');
		return;
	}
	$("#role_adduser_dlg_id").window("open");
	addUserQueryParam = {
		pk_role : recs[0]['pk_role'],
		flag : 'not in'
	}
	var queryUrl = "ezfm/baseinfo/role/user/roleuser/query";
	// 初始化grid
	var options = {
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true
	}
	initLoadGridDataByCustom("yjwy_user_son1_main_grid", queryUrl, addUserQueryParam, options);
};
function queryNotAddUsers(){
	var user_name = $("#user_son1_name_queryid").val();
	addUserQueryParam.user_name = user_name;
	$('#yjwy_user_son1_main_grid').datagrid('reload');
};
function addUserFormatter(value, row, index) {
	return "<a href='javascript:void(0);' onclick='addUser(\"" + row.pk_user + "\")'>加入</a>"
}
//加入用户操作
function addUser(pk_user) {
	var recs = $("#rolegrid").datagrid('getSelections');
	var addUrl = "ezfm/baseinfo/role/user/add/" + recs[0]['pk_role'] + "/" + pk_user;
	$.ajax({
		type : 'get',
		url : addUrl,
		async : false,
		success : function(result) {
			if (result.success) {
				$.messager.show({
					title : '提示',
					msg : '用户加入成功',
					timeout : 2000,
					showType : 'slide'
				});
			}
		}
	})
	$('#yjwy_user_son1_main_grid').datagrid('reload');
	$('#yjwy_user_main_grid').datagrid('reload');
	//此位置还要刷新标准tab
}
/****************以上是加入用户操作****************/