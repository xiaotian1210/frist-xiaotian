var areas = {};
function areaCellFormatter(value, row, index) {
	if (row.pk_area && areas[row.pk_area]) {
		return areas[row.pk_area].area_name;
	} else {
		return "";
	}
}
// 坐标formatter
function siteFormatter(value, row, index) {
	return "<a href='javascript:void(0);' onclick='showBtMap(\""
			+ row.pk_project + "\")'>查看</a>"
}
function showBtMap(id) {
	var options = {
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			id : "rails_save_mainbtn"
		}, {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#project_process_site_mapid").dialog('close');
			}
		} ]
	};
	var mapUrl = "ezfm/baseinfo/project/rail/index/" + id;
	showCustomWindow("project_process_site_mapid", "项目围栏", mapUrl, options);
}
// 省份改变事件
function provinceChange(newValue, oldValue) {
	yjwyLoadCityCombobox("pk_city_id", {
		pid : newValue
	}, null, false);
}
//建筑面积与占地面积比较
$(function() {
	$("#pk_area").combobox({
		valueField : 'pk_area',
		textField : 'area_name',
		url : 'ezfm/baseinfo/area/query',
		loader : function(param, success, error) {
			param = param || {};
			var extraParam = {
				metas : [ 'yjwy_area' ],
				'andList' : [ {
					key : 'pk_crop_',
					operator : 'eq',
					value : pc
				} ]
			};
			$.extend(param, extraParam);
			$.request.httpPost({
				/* async:false, */
				url : 'ezfm/baseinfo/area/query',
				data : {
					param : JSON.stringify(param)
				},
				success : function(rs) {
					areas = rs.others;
					success(rs.data);
				},
				failure : function(rs) {
					$.messager.alert('提示', rs.message || '查询失败');
				}
			});
			return true;
		}
	});
	yjwyLoadCityCombobox("pk_province_id", {
		pid : 0
	}, null, false);
	var qryParam = {};
	var projectgrid = $('#projectgrid').datagrid({
		singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true,
		loadMsg : '数据装载中......',
		striped : true,
		pagination : true,
		pageSize : 20,
		pageNumber : 1,
		rownumbers : true,
		url : 'ezfm/baseinfo/project/query',
		loader : function(param, success, error) {
			param = param || {};
			var extraParam = {
				metas : [ 'yjwy_project' ],
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
				url : 'ezfm/baseinfo/project/query',
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

	$('#projectgrid').datagrid('getPager').pagination(
			{
				onSelectPage : function(pPageIndex, pPageSize) {
					dataQuery('ezfm/baseinfo/project/query', qryParam,
							(pPageIndex - 1) * pPageSize, pPageSize);
				}
			});

	var dataQuery = function(url, jsonParam, start, limit) {
		$('#projectgrid').datagrid('loading');
		var andList = [];
		andList.push({
			key : 'pk_crop_',
			operator : 'eq',
			value : pc
		});
		if (!$.isEmptyObject(jsonParam)) {
			andList.push(jsonParam);
		}

		var param = {
			metas : [ 'yjwy_project' ],
			'andList' : andList,
			limit : limit,
			start : start
		};

		$.request.httpPost({
			url : 'ezfm/baseinfo/project/query',
			data : {
				param : JSON.stringify(param)
			},
			success : function(rs) {
				$('#projectgrid').datagrid('loadData', {
					total : rs.total,
					rows : rs.data
				});
				$('#projectgrid').datagrid('loaded');
			},
			failure : function(rs) {
				$.messager.alert('提示', rs.message || '查询错误');
			}
		});
		return true;
	}
	$('#querysearch').searchbox({
		prompt : '请输入项目名称',
		searcher : function(value, name) {
			var options = $('#projectgrid').datagrid('options');
			var pageSize = options.pageSize;
			var qryParam = {};
			if (value) {
				qryParam = {
					key : name,
					operator : 'like',
					value : value
				};
			}
			dataQuery('ezfm/baseinfo/project/query', qryParam, 0, pageSize);
		}
	});

	$("#btnNew").click(function() {
		closewin_flg = false;
		$('#projectform').form('clear')
		$('#project_mainform_crop_id').val(pc);
		$("#pk_city_id").combobox("loadData", []);
		$('#projectdlg').window({
			title : '新增项目',
			closed : false
		});
	});

	$("#btnEdit").click(function() {
		closewin_flg = false;
		var data = $("#projectgrid").datagrid("getSelected");
		if (!data) {
			$.messager.alert('提示', '请选择需要编辑的数据');
			return;
		}
		$('#projectform').form('clear')
		$('#projectform').form('load', data);
		yjwyLoadCityCombobox("pk_city_id", {
			pid : data.pk_province
		}, data.pk_city, false);
		var json = $('#projectform').serializeJson();
		$('#projectdlg').window({
			title : '编辑项目信息',
			closed : false
		});
	});

	$('#btnDelete').bind(
			'click',
			function() {
				var recs = projectgrid.datagrid('getSelections');
				if (!recs.length) {
					$.messager.alert('提示', '请选择待删除行');
					return;
				}
				$.messager.confirm('提示', '是否确认删除选中项目，请谨慎操作', function(r) {
					if (r) {
						var url = 'ezfm/baseinfo/project/delete';
						var successFunc = function(result) {
							if (result.success) {
								$('#projectgrid').datagrid('getPager').pagination('select');
								$.messager.show({
									title : '提示',
									msg : '数据已删除',
									timeout : 2000,
									showType : 'slide'
								});
							} else {
								$.messager.alert("提示", result.message,"warning");
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

	var projectdlg = $('#projectdlg').dialog(
					{
						width : 550,
						height : 450,
						closed : true,
						cache : false,
						left : ($(window).width() - 600) / 2,
						top : ($(window).height() - 450) / 2,
						buttons : [{
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								if ($('#projectform').form('validate')) {
										var json = $('#projectform').serializeJson();
										$.request.restPost({
											url : json.pk_project ? 'ezfm/baseinfo/project/update': 'ezfm/baseinfo/project/save',
											data : json,
											success : function(result) {
												if (result.success) {
													$('#projectgrid').datagrid('getPager').pagination('select');
													$('#projectform').form('clear');
													projectdlg.window('close');
													$.messager.show({
														title : '提示',
														msg : '数据已保存',
														timeout : 2000,
														showType : 'slide'
													});
												} else {
													$.messager.alert("提示",result.message);
												}
											},
											failure : function(rs) {
												$.messager.alert('提示',rs.message || '保存失败');
											}
										})
								}
							}
						},{
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								$.messager.confirm('提示','当前单据未保存，是否确认退出',function(r) {
									if (r) {
										$('#projectform').form('clear');
										projectdlg.window('close');
									}
								});
							}
						} ]
					});
});