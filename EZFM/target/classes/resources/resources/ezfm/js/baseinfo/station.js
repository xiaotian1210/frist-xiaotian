$(function() {

	$.fn.zTree.init($("#stationtree"), setting, zNodes());
	var treeObj = $.fn.zTree.getZTreeObj("stationtree");
	treeObj.expandAll(false);
	$("#station-propertygrid").propertygrid('loadData', [ {
		name : '岗位编码',
		value : ''
	}, {
		name : '任务级别',
		value : ''
	}, {
		name : '岗位级别',
		value : ''
	}, {
		name : '所属部门',
		value : ''
	}, {
		name : '所属组织',
		value : ''
	}, {
		name : '岗位名称',
		value : ''
	}, {
		name : '岗位描述',
		value : ''
	} ]);
	$('#f_pk_org').combotree('loadData', orgTree());
	dictInit();
	var stationdlg = $('#stationdlg').dialog({
		width : 540,
		height : 450,
		resizable : true,
		modal : true,
		closed : true,
		cache : false,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				if ($('#form').form('validate')) {
					var nodes = treeObj.getSelectedNodes();
					var json = $('#form').serializeJson();
					$.request.restPost({
						url : json.pk_station ? 'ezfm/baseinfo/station/update' : 'ezfm/baseinfo/station/save',
						data : json,
						success : function(result) {
							if (result.success) {
								if (!json.pk_station) {
									var data = result.data[0];
									var node = {
										id : data.pk_station,
										pId : data.pk_parent,
										name : data.station_name,
										attributes : data
									};
									treeObj.addNodes(nodes[0], node);
									$("#station-propertygrid").propertygrid('loadData', []);
									$("#station-propertygrid").propertygrid('loadData', [ {
										name : '岗位编码',
										value : data.station_code
									}, {
										name : '岗位名称',
										value : data.station_name
									}, {
										name : '任务级别',
										value : data.task_level_name
									}, {
										name : '岗位级别',
										value : data.station_level_name
									}, {
										name : '所属部门',
										value : data.pk_dept_name
									}, {
										name : '所属组织',
										value : data.pk_org_name
									}, {
										name : '岗位描述',
										value : data.memo
									} ]);
								} else {
									var data = result.data[0];
									nodes[0].name = data.station_name;
									nodes[0].attributes = data;
									treeObj.updateNode(nodes[0]);
									$("#station-propertygrid").propertygrid('loadData', []);
									$("#station-propertygrid").propertygrid('loadData', [ {
										name : '岗位编码',
										value : data.station_code
									}, {
										name : '岗位名称',
										value : data.station_name
									}, {
										name : '任务级别',
										value : data.task_level_name
									}, {
										name : '岗位级别',
										value : data.station_level_name
									}, {
										name : '所属部门',
										value : data.pk_dept_name
									}, {
										name : '所属组织',
										value : data.pk_org_name
									}, {
										name : '岗位描述',
										value : data.memo
									} ]);
								}
								stationdlg.window('close');
//								$.fn.zTree.init($("#stationtree"), setting, zNodes());
//								treeObj.expandAll(true);
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
						stationdlg.window('close');
					}
				});
			}
		} ]
	});

	$('#btnNew').click(function() {
		closewin_flg = false;
		$('#form').form('clear');
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length <= 0) {
			$('#form').form('load', {
				pk_crop : pc
			});
		} else {
			$('#form').form('load', {
				pk_parent : nodes[0].attributes.pk_station,
				pk_crop : pc
			});
		}
		$('#stationdlg').window({
			title : '新增岗位',
			closed : false
		});
	});
	$('#btnEdit').click(function() {
		closewin_flg = false;
		$('#form').form('clear');
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length <= 0) {
			$.messager.alert('提示', '请选择待修改岗位');
		}
		$('#form').form('load', nodes[0].attributes);
		$('#stationdlg').window({
			title : '岗位编辑',
			closed : false
		});

	});
	$('#btnDelete').click(function() {
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length <= 0) {
			$.messager.alert('提示', '请选择待删除节点');
			return;
		}
		var list = [];
		var childs = treeObj.getNodesByFilter(function(node){return true}, false, nodes[0]); 
		//var nodese = treeObj.getNodesByParam(null, null, nodes[0]);
		for(var i=0; i<childs.length; i++){
			var n = childs[i];
			list.push(n.attributes);
		}
		list.push(nodes[0].attributes);
		$.messager.confirm('提示', '是否确认删除选中岗位，请谨慎操作', function(r) {
			if (r) {
				$.request.restPost({
					url : 'ezfm/baseinfo/station/delete',
					data : list,
					success : function(result) {
						if (result.success) {
							treeObj.removeNode(nodes[0]);
							$("#station-propertygrid").propertygrid('loadData', [ {
								name : '岗位编码',
								value : ''
							}, {
								name : '任务级别',
								value : ''
							}, {
								name : '岗位级别',
								value : ''
							}, {
								name : '所属部门',
								value : ''
							}, {
								name : '所属组织',
								value : ''
							}, {
								name : '岗位名称',
								value : ''
							}, {
								name : '岗位描述',
								value : ''
							} ]);
							$('#form').form('clear');
							$.messager.show({
								title : '提示',
								msg : '数据已删除',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示", result.message);
						}
					},
					failure : function(rs) {
						$.messager.alert('提示', rs.message || '删除失败');
					}
				});
			}
		});
	});
});

var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	view : {
		showLine : true,
		selectedMulti : false,
		txtSelectedEnable : false,
		showIcon : false
	},
	callback : {
		onClick : zTreeOnClick
	}
};

var zNodes = function() {
	var param = {};
	var extraParam = {
		metas : [ 'yjwy_station' ],
		'andList' : [ {
			key : 'pk_crop_',
			operator : 'eq',
			value : pc
		} ]
	}
	var result = null;
	$.extend(param, extraParam);
	$.request.httpPost({
		url : 'ezfm/baseinfo/station/query/all',
		async : false,
		data : {
			param : JSON.stringify(param)
		},
		success : function(rs) {
			if (rs.success) {
				result = rs.data;
			}
		},
		failure : function(rs) {
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
	return result;
}

function zTreeOnClick(event, id, node) {
	var attributes = node.attributes;
	$("#station-propertygrid").propertygrid('loadData', []);
	$("#station-propertygrid").propertygrid('loadData', [ {
		name : '岗位编码',
		value : attributes.station_code
	}, {
		name : '岗位名称',
		value : attributes.station_name
	}, {
		name : '任务级别',
		value : attributes.task_level_name
	}, {
		name : '岗位级别',
		value : attributes.station_level_name
	}, {
		name : '所属部门',
		value : attributes.pk_dept_name
	}, {
		name : '所属组织',
		value : attributes.pk_org_name
	}, {
		name : '岗位描述',
		value : attributes.memo
	} ]);
	if (node && node.id) {
		// 加载岗位关联的用户
		loadUser(node.id);
		// 加载岗位关联的版本
		loadStationEdition(node.id);
		// 加载岗位关联标准
		loadStationStandard(node.id)
	}
}
function orgTree() {
	var result = null;
	var param = {
		metas : [ 'yjwy_org' ],
		'andList' : [ {
			key : 'pk_crop_',
			operator : 'eq',
			value : pc
		} ]
	}
	$.request.httpPost({
		url : 'ezfm/baseinfo/org/query/easytree',
		async : false,
		data : {
			param : JSON.stringify(param)
		},
		success : function(rs) {
			if (rs.success) {
				result = rs.data;
			}
		},
		failure : function(rs) {
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
	return result;
}

function dictInit() {
	var metas = [ 'yjwy_dict' ];
	var p_task_level = [ {
		key : 'dict_parent_',
		operator : 'eq',
		value : 'jobLevel'
	} ];

	dictInitAjax($('#task_level'), {
		metas : metas,
		'andList' : p_task_level
	});

	var p_station_level = [ {
		key : 'dict_parent_',
		operator : 'eq',
		value : 'postLevel'
	} ];
	dictInitAjax($('#pk_station_level'), {
		metas : metas,
		'andList' : p_station_level
	});

	var p_dept = [ {
		key : 'dict_parent_',
		operator : 'eq',
		value : 'jobDepartment'
	} ];
	dictInitAjax($('#pk_dept'), {
		metas : metas,
		'andList' : p_dept
	});

}
function dictInitAjax(o, p) {
	$.request.httpPost({
		url : 'ezfm/system/dict/combobox',
		async : false,
		data : {
			param : JSON.stringify(p)
		},
		success : function(rs) {
			if (rs.success) {
				o.combobox('loadData', rs.data);
			}
		},
		failure : function(rs) {
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
}