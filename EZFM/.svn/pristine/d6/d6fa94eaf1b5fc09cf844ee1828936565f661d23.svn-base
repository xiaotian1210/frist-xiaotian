//机房全选标识
var roomFlag = true;
// 设备全选标识
var eqFlag = true;
/**
 * 全选机房节点
 * 
 * @param treeName
 */
function checkAllRoomNodes() {
	var roomTreeObj = $.fn.zTree.getZTreeObj("tree_room");
	// 全选
	if (roomFlag) {
		$("#btn_roomCheckAll").linkbutton({
			text : "取消"
		});
		roomFlag = false;
		roomTreeObj.checkAllNodes(true);
	} else {
		$("#btn_roomCheckAll").linkbutton({
			text : "全选"
		});
		roomFlag = true;
		roomTreeObj.checkAllNodes(false);
	}
	$.fn.zTree.destroy("tree_eq");
	// 清空设备和分类节点
	var data = [];
	initTreeEq(data);
	$("#datagrid_csi").datagrid({
		data : data,
	});
	// 获取所有节点
	var nodes = roomTreeObj.getNodesByParam("checked", true, null);
	if (nodes != null && nodes.length > 0) {
		var rm_ids = new Array();
		for (var i = 0; i < nodes.length; i++) {
			rm_ids.push(nodes[i].rm_id);
		}
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/query/eq',
			data : {
				rm_ids : rm_ids
			},
			async : true,
			dataType : "json",
			success : function(data) {
				initTreeEq(data.rows);
			},
		});
	}
}

/**
 * 全选设备节点
 * 
 * @param treeName
 */
function checkAllEqNodes() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_eq");
	// 全选
	if (eqFlag) {
		$("#btn_eqCheckAll").linkbutton({
			text : "取消"
		});
		eqFlag = false;
		treeObj.checkAllNodes(true);
	} else {
		$("#btn_eqCheckAll").linkbutton({
			text : "全选"
		});
		eqFlag = true;
		treeObj.checkAllNodes(false);
	}
	// 清空分类
	var data = [];
	$("#datagrid_csi").datagrid({
		data : data,
	});// 获取所有节点
	var nodes = treeObj.getNodesByParam("checked", true, null);
	if (nodes != null && nodes.length > 0) {
		var csi_ids = new Array();
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].pid != 0) {
				csi_ids.push(nodes[i].csi_id);
			}
		}
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/query/csi',
			data : {
				csi_ids : csi_ids
			},
			async : true,
			dataType : "json",
			success : function(data) {
				$("#datagrid_csi").datagrid({
					data : data.rows
				});
			},
		});
	}
}

/**
 * 初始化机房tree
 * 
 * @param data
 */
function initTreeRoom(data) {
	var zTreeObj;
	var setting = {
		// 数据
		data : {
			key : {
				// 节点名称
				name : "name",
				// 提示信息
				title : "name",
			},
			simpleData : {
				// id标识
				idKey : "rm_id",
			},
		},
		// 显示
		view : {
			// 是否显示连接线
			showLine : false,
			// 是否显示图表
			showIcon : false,
		},
		// 勾选
		check : {
			enable : true,
			chkStyle : "checkbox",
			// 是否触发beforeCheck / onCheck回调函数
			autoCheckTrigger : true,
		},
		// 回调函数
		callback : {
			onCheck : checkRoom,
		},
	};
	var zNodes = data;
	zTreeObj = $.fn.zTree.init($("#tree_room"), setting, zNodes);
}

/**
 * 勾选机房事件
 * 
 * @param event
 * @param treeId
 * @param treeNode
 */
function checkRoom(event, treeId, treeNode) {
	var roomTreeObj = $.fn.zTree.getZTreeObj("tree_room");
	// 获取所有节点
	var roomNodes = roomTreeObj.getNodesByParam("checked", true, null);
	if (roomNodes != null && roomNodes.length > 0) {
		var rm_ids = new Array();
		for (var i = 0; i < roomNodes.length; i++) {
			rm_ids.push(roomNodes[i].rm_id);
		}
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/query/eq',
			data : {
				rm_ids : rm_ids
			},
			async : true,
			dataType : "json",
			success : function(data) {
				$.fn.zTree.destroy("tree_eq");
				var rows = data.rows;
				initTreeEq(rows);
				var data = [];
				$("#datagrid_csi").datagrid({
					data : data,
				});
				// if (!treeNode.checked) {
				// var eqTreeObj = $.fn.zTree.getZTreeObj("tree_eq");
				// var csiNodes = eqTreeObj.getNodesByParam("pid", 0, null);
				// var csiRows = $("#datagrid_csi").datagrid("getRows");
				// for (var i = 0, len = csiRows.length; i < len; i++) {
				// var flag = true;
				// for (j in csiNodes) {
				// if (csiRows[i].csi_id == csiNodes[j].csi_id) {
				// flag = false;
				// }
				// }
				// if (flag) {
				// var index = $("#datagrid_csi").datagrid("getRowIndex",
				// csiRows[i]);
				// $("#datagrid_csi").datagrid("deleteRow", index);
				// csiRows = $("#datagrid_csi").datagrid("getRows");
				// i = -1;
				// }
				// if (csiRows.length == csiNodes.length) {
				// break;
				// }
				// }
				// }
			},
		});
	} else {
		$.fn.zTree.destroy("tree_eq");
		var data = [];
		$("#datagrid_csi").datagrid({
			data : data,
		});
	}
};

/**
 * 初始化设备tree
 * 
 * @param data
 */
function initTreeEq(data) {
	var zTreeObj;
	var setting = {
		// 数据
		data : {
			key : {
				// 节点名称
				name : "name",
				// 提示信息
				title : "name",
			},
			simpleData : {
				enable : true,
				// id标识
				idKey : "id",
				pIdKey : "pid",
				rootPId : 0
			},
		},
		// 显示
		view : {
			// 是否显示连接线
			showLine : false,
			// 是否显示图表
			showIcon : false,
		},
		// 勾选
		check : {
			enable : true,
			chkStyle : "checkbox",
			// 是否触发beforeCheck / onCheck回调函数
			autoCheckTrigger : true,
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			},
		},
		// 回调函数
		callback : {
			onCheck : checkEq,
		},
	};
	var zNodes = data;
	zTreeObj = $.fn.zTree.init($("#tree_eq"), setting, zNodes);
}

/**
 * 勾选设备事件
 * 
 * @param event
 * @param treeId
 * @param treeNode
 */
function checkEq(event, treeId, treeNode) {
	if (treeNode.pid == 0) {
		return;
	}
	var treeObj = $.fn.zTree.getZTreeObj("tree_eq");
	// 获取当前被选中的节点集合
	var nodes = treeObj.getNodesByParam("checked", true, null);
	var csi_id = treeNode.csi_id;
	if (treeNode.checked) {
		// 选中，添加分类
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/query/csi',
			data : {
				csi_id : csi_id
			},
			async : true,
			dataType : "json",
			success : function(data) {
				var rows = $("#datagrid_csi").datagrid("getRows");
				for (var i = 0; i < rows.length; i++) {
					// 如果有相同的，不需要对分类进行处理
					if (rows[i].pk_dict == csi_id) {
						return;
					}
				}
				$("#datagrid_csi").datagrid("appendRow", data.rows[0]);
			},
		});
	} else {
		var flag = true;
		if (nodes.length != 0) {
			for (i in nodes) {
				if (nodes[i].pk_dict == csi_id) {
					flag = false;
				}
			}
		}
		if (flag) {
			// 取消选中，移除分类
			var rows = $("#datagrid_csi").datagrid("getRows");
			for (i in rows) {
				if (csi_id == rows[i].pk_dict) {
					var index = $("#datagrid_csi").datagrid("getRowIndex", rows[i]);
					$("#datagrid_csi").datagrid("deleteRow", index);
				}
			}
		}
	}

};

/**
 * 编辑修改数据（获取选中索引）
 * 
 * @param {Object}
 *            datagridName
 * @param {Object}
 *            index
 * @param {Object}
 *            titleName
 * @param {Object}
 *            dialogName
 * @param {Object}
 *            formName
 * @param {Object}
 *            toolbarName
 */
function updateData(datagridName, index, titleName, dialogName, formName, toolbarName) {
	var recList = $("#" + datagridName).datagrid("getSelections");
	if (recList.length == 0) {
		$.messager.alert("错误", "请选择修改项！", "error");
		return;
	}
	if (recList.length > 1) {
		$.messager.alert("错误", "请只选择一项进行编辑！", "error");
		return;
	}
	index = $("#" + datagridName).datagrid("getRowIndex", recList[0]);


	beforOpenFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName);

    // $('#input_start_time').datebox({
    //     onSelect: function(date){
    //         $('#input_end_time').datebox().datebox('calendar').calendar({//开始时间大于结束时间
    //             validator: function(dateAfter){
    //                 var startDate = $("#input_start_time").datebox('getValue');
    //                 if(startDate == ""){
    //                     return true;
    //                 }
    //                 var s =startDate+' 00:00:00';
    //                 s = s.replace(/-/g,"/");
    //                 var dateBefore = new Date(s );
    //
    //                 return dateBefore <= dateAfter;
    //
    //             }
    //         });
    //
    //     }
    // }).datebox('calendar').calendar({//开始大于当前时间
    //     validator: function(dateAfter){
    //         var dateBefore = new Date();
    //
    //         return dateBefore <= dateAfter;
    //
    //     }
    // });

}

/**
 * 打开表单窗口前对人员选择列表进行初始化
 * 
 * @param {Object}
 *            datagridName
 * @param {Object}
 *            index
 * @param {Object}
 *            titleName
 * @param {Object}
 *            dialogName
 * @param {Object}
 *            formName
 * @param {Object}
 *            toolbarName
 */
function beforOpenFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName) {

	if(index == null){//新增
        $('#input_start_time').datebox({
            onSelect: function(date){

                $('#input_end_time').datebox().datebox('calendar').calendar({//开始时间大于结束时间
                    validator: function(dateAfter){
                        var startDate = $("#input_start_time").datebox('getValue');
                        if(startDate == ""){
                            return true;
                        }
                        var s =startDate+' 00:00:00';
                        s = s.replace(/-/g,"/");
                        var dateBefore = new Date(s );
                        return dateBefore < dateAfter;

                    }
                });

            }
		}).datebox('calendar').calendar({//开始大于当前时间
            validator: function(dateAfter){
                var dateBefore = new Date();
                dateBefore.setDate(dateBefore.getDate()-1);
                return dateBefore <= dateAfter;

            }
        });

	}else{
        $('#input_start_time').datebox({
            onSelect: function(date){

                $('#input_end_time').datebox().datebox('calendar').calendar({//开始时间大于结束时间
                    validator: function(dateAfter){
                        var startDate = $("#input_start_time").datebox('getValue');
                        if(startDate == ""){
                            return true;
                        }
                        var s =startDate+' 00:00:00';
                        s = s.replace(/-/g,"/");
                        var dateBefore = new Date(s );

                        return dateBefore < dateAfter;

                    }
                });

            }
        }).datebox('calendar').calendar({//开始大于当前时间
            validator: function(dateAfter){
              return true;
            }
        });
	}





	// 机房全选标识
	var roomFlag = true;
	// 设备全选标识
	var eqFlag = true;
	$("#btn_roomCheckAll").linkbutton({
		text : "全选"
	});
	$("#btn_eqCheckAll").linkbutton({
		text : "全选"
	});
	emptyData();
	var pk_area = "0";
	var pk_project = "0";
	var pk_group = "0";
	// 判断是否编辑
	if (index != null) {
		$("#" + datagridName).datagrid("selectRow", index);
		var rec = $("#" + datagridName).datagrid("getSelected");
		pk_area = rec.pk_area;
		pk_project = rec.pk_project;
		pk_group = rec.pk_group;
		// 加载机房列表
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/queryEmEqCsi',
			contentType : 'application/json',
			data : JSON.stringify(rec),
			async : true,
			dataType : "json",
			success : function(data) {
				initTreeRoom(data.rms);
				initTreeEq(data.eqs);
				$("#datagrid_csi").datagrid({
					data : data.csis,
				});
			},
		});

		// 频次
		var frequency = rec.frequency;
		var numUnit = frequency.split('/');
		rec.frequency_num = numUnit[0];
		rec.frequency_unit = numUnit[1];
	}
	// 初始化表单下拉列表
	queryData("form_combobox_area", "ezfm/device/query/area?pk_area=" + pk_area, "pk_area", "area_name");
	$("#form_combobox_area").combobox({
		onSelect : function(rec) {
			var url = 'ezfm/device/query/project?pk_area=' + rec.pk_area;
			queryData("form_combobox_project", url, "pk_project", "project_name");
			emptyData();
		}
	});
	queryData("form_combobox_project", "ezfm/device/query/project?pk_project=" + pk_project + "&pk_area=" + pk_area, "pk_project", "project_name");
	$("#form_combobox_project").combobox({
		onSelect : function(rec) {
			emptyData();
			$.ajax({
				type : "post",
				url : 'ezfm/patrol/plan/query/room',
				data : {
					pk_project : rec.pk_project
				},
				async : true,
				dataType : "json",
				success : function(data) {
					initTreeRoom(data.rows);
				},
			});
		}
	});
	queryData("form_combobox_group", "ezfm/device/query/group?pk_group=" + pk_group, "pk_group", "group_name");
	openFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName, 680, 470);
};

/**
 * 绑定数据
 */
function emptyData() {
	var data = [];
	initTreeRoom(data);
	initTreeEq(data);
	$("#datagrid_csi").datagrid({
		data : data,
	});
}

/**
 * 提交表单前对数据进行整理
 * 
 * @param {Object}
 *            datagridName
 * @param {Object}
 *            dialogName
 * @param {Object}
 *            formName
 * @param {Object}
 *            urlName
 */
function beforSubmitForm(datagridName, dialogName, formName, urlName) {
	var isValid = $("#" + formName).form("validate");
	if (!isValid) {
		return;
	}
	// 获取机房
	var treeRoom = $.fn.zTree.getZTreeObj("tree_room");
	// 获取当前被选中的节点集合
	var nodesRoom = treeRoom.getNodesByParam("checked", true, null);
	if (nodesRoom == null || nodesRoom.length == 0) {
		$.messager.alert('警告', '请选择机房！', 'error');
		return;
	}
	// 获取设备
	var treeEq = $.fn.zTree.getZTreeObj("tree_eq");
	var filter = function(node) {
		return (node.pid != 0 && node.checked);
	};
	var nodesEq = treeEq.getNodesByFilter(filter);
	if (nodesEq == null || nodesEq.length == 0) {
		$.messager.alert('警告', '请选择设备！', 'error');
		return;
	}
	// 获取分类工艺
	var csiRows = $("#datagrid_csi").datagrid('getRows');
	for (var i = 0; i < csiRows.length; i++) {
		csiRows[i].csi_id = csiRows[i].pk_dict;
		if (csiRows[i].pmp_id == undefined) {
			$.messager.alert('警告', '工艺程序不能为空', 'error');
			return;
		}
	}
	var form_data = $("#" + formName).serializeObject();
	// 获取区域id
	var pk_area = $("#form_combobox_area").combobox("getValue");
	// 获取项目id
	var pk_project = $("#form_combobox_project").combobox("getValue");
	// 获取分组id
	var group_id = $("#form_combobox_group").combobox("getValue");
	form_data.rooms = JSON.stringify(nodesRoom);
	form_data.eqs = JSON.stringify(nodesEq);
	form_data.csis = JSON.stringify(csiRows);
	form_data.pk_area = pk_area;
	form_data.pk_project = pk_project;
	form_data.group_id = group_id;
	form_data.plan_type = whole_plan_type;
	form_data.is_enable = 1;
	submitForm(datagridName, dialogName, formName, urlName, form_data);
};

/**
 * 格式化字段：是否启用
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatIs_enable(value, row, index) {
	if (value == 1) {

		return '<span style="color: green;">启用</span>';
	} else {
		return '<span style="color: red;">禁用</span>';
	}
}

/**
 * 格式化设备分类操作（工艺步骤设置）
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatStep(value, row, index) {
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='setPmp(" + index + ")' >工艺步骤</a>";
}

/**
 * 设置工艺步骤
 * 
 * @param {Object}
 *            index
 */
function setPmp(index) {
	if (index != null) {
		$("#datagrid_pmp").datagrid({
			queryParams : {
				bus_type : whole_plan_type + 1,
			},
			onDblClickRow : selectPmp,
		});
		datagridInit('datagrid_pmp', 'ezfm/patrol/plan/query/pmp', null, null, true, false);
	}
	openDialog('dialog_pmp', "程序选择（双击选择）", 'toolbar_pmp', 400, 260);
}

/**
 * 选择工艺事件
 */
function selectPmp(rowIndex, rowData) {
	var description = rowData.description;
	var pmp_id = rowData.pmp_id;
	var csiRow = $("#datagrid_csi").datagrid("getSelected");
	var csiIndex = $("#datagrid_csi").datagrid("getRowIndex", csiRow);
	csiRow.pmp_id = pmp_id;
	csiRow.pmp_description = description;
	$("#datagrid_csi").datagrid('updateRow', {
		index : csiIndex,
		row : JSON.stringify(csiRow),
	});
	closeDialog(null, 'dialog_pmp');
}

/**
 * 格式化字段：人员分组
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatGroup(value, row, index) {
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='selectGroup(" + index + ")' >" + value + "</a>";
}

/**
 * 选择人员分组窗口
 * 
 * @param {Object}
 *            index
 */
function selectGroup(index) {
	indexed = index;
	if (index != null) {
		$("#datagrid_plan").datagrid("selectRow", index);
		var rec = $("#datagrid_plan").datagrid("getSelected");
		// 对datagrid数据进行初始化清空
		$("#datagrid_groupList").datagrid('loadData', {
			total : 0,
			rows : []
		});
		$("#datagrid_groupList").datagrid({
			url : 'ezfm/basic/executor/query',
		});
	}
	openDialog('dialog_groupList', '分组列表', 'toolbar_groupList', 420, 300);
}
/**
 * 提交选择后的人员分组信息
 */
function updateGroup() {
	$("#datagrid_plan").datagrid("selectRow", indexed);
	var rec = $("#datagrid_plan").datagrid("getSelected");
	var group = $("#datagrid_groupList").datagrid('getSelected');
	rec.group_id = group.pk_group;
	$.ajax({
		type : "post",
		url : 'ezfm/patrol/plan/updateEnable',
		contentType : 'application/json',
		data : JSON.stringify(rec),
		async : true,
		dataType : "json",
		success : function(json) {
			var flag = json.success;
			if (flag) {
				closeDialog(null, 'dialog_groupList');
				$("#datagrid_plan").datagrid('reload');
			}
		}
	});
}

/**
 * 格式化字段：操作
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatOperate(value, row, index) {
	var enable = null;
	if (row.is_enable == '1') {
		enable = "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='updateEnable(" + index + ")' >禁用</a>";
	} else {
		enable = "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='updateEnable(" + index + ")' >启用</a>";
	}
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='eqList(" + index + ")' >设备列表</a>" + "&nbsp;&nbsp;" + "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='userList(" + index + ")' >人员列表</a>" + "&nbsp;&nbsp;" + enable;
}

/**
 * 修改启用状态
 * 
 * @param {Object}
 *            index
 */
function updateEnable(index) {
	$("#datagrid_plan").datagrid("selectRow", index);
	var rec = $("#datagrid_plan").datagrid("getSelected");
	(rec.is_enable == 1) ? (rec.is_enable = 0) : (rec.is_enable = 1);
	rec.group_id = rec.pk_group;
	$.ajax({
		type : "post",
		url : 'ezfm/patrol/plan/updateEnable',
		contentType : 'application/json',
		data : JSON.stringify(rec),
		async : true,
		dataType : "json",
		success : function(json) {
			var flag = json.success;
			if (flag) {
				$("#datagrid_plan").datagrid('reload');
			}
		}
	});
}

/**
 * 查询提交
 */
function querySubmit() {
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	$("#datagrid_plan").datagrid("load", {
		plan_type : whole_plan_type,
		pk_area : pk_area,
		pk_project : pk_project,
	});
}


/**
 * 重置后提交
 */
function resetSubmit(){				
	$("#combobox_area").combobox("setText","区域选择");
	$("#combobox_area").combobox("setValue","default");
    $("#combobox_project").combobox("setText","项目选择");
    $("#combobox_project").combobox("setValue","default");
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	$("#datagrid_plan").datagrid("load",{
		plan_type : whole_plan_type,
		pk_area : pk_area,
		pk_project : pk_project
	});
}


/**
 * 设备列表
 * 
 * @param {Object}
 *            index
 */
function eqList(index) {
	if (index != null) {
		$("#datagrid_plan").datagrid("selectRow", index);
		var rec = $("#datagrid_plan").datagrid("getSelected");
		// 对datagrid数据进行初始化清空
		$("#datagrid_eqList").datagrid('loadData', {
			total : 0,
			rows : []
		});
		$.ajax({
			type : "post",
			url : 'ezfm/patrol/plan/queryEqsByPlan',
			contentType : 'application/json',
			data : JSON.stringify(rec),
			async : true,
			dataType : "json",
			success : function(data) {
				var rows = data.rows;
				if (rows != null) {
					for (var i = 0; i < rows.length; i++) {
						var row = rows[i];
						$("#datagrid_eqList").datagrid('appendRow', row);
					}
				}
			}
		});
	}
	openDialog('dialog_eqList', '设备列表', null, 600, 360);
}

/**
 * 人员列表
 * 
 * @param {Object}
 *            index
 */
function userList(index) {
	if (index != null) {
		$("#datagrid_plan").datagrid("selectRow", index);
		var rec = $("#datagrid_plan").datagrid("getSelected");
		// 对datagrid数据进行初始化清空
		$('#datagrid_userList').datagrid('loadData', {
			total : 0,
			rows : []
		});
		$.ajax({
			type : "post",
			url : 'ezfm/basic/executor/querySelectedUsers',
			contentType : 'application/json',
			data : JSON.stringify(rec),
			async : true,
			dataType : "json",
			success : function(data) {
				var rows = data.rows;
				if (rows != null) {
					for (var i = 0; i < rows.length; i++) {
						var row = rows[i];
						$("#datagrid_userList").datagrid('appendRow', row);
					}
				}
			}
		});
	}
	openDialog('dialog_userList', '人员列表', null, 400, 260);
}