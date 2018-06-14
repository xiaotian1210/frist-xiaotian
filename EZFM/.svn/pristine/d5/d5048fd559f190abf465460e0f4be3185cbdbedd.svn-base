/**
 * 显示所有待选人员
 */
function reloadUser() {
	$("#datagrid_unselectUsers").datagrid({
		queryParams : {
			user_name : null
		},
		onLoadSuccess : afterLoad,
	});

}
/**
 * 搜索待选人员
 */
function searchUser() {
	// 表单判断
	var flag = $("#search_username").textbox('isValid');
	if (!flag) {
		return;
	}
	var user_name = $("#search_username").textbox('getValue');
	$("#datagrid_unselectUsers").datagrid({
		queryParams : {
			user_name : user_name
		},
		onLoadSuccess : afterLoad,
	});
}

/**
 * datagrid加载成功后选中
 * 
 * @param {Object}
 *            data
 */
function afterLoad(data) {
	// 已选列表的当前所有行
	var selectRows = $("#datagrid_selectUsers").datagrid('getRows');
	var unselectRows = data.rows;
	for (var i = 0; i < selectRows.length; i++) {
		var selectRow = selectRows[i]
		for (var j = 0; j < unselectRows.length; j++) {
			var unselectRow = unselectRows[j];
			// 判断相同数据
			if (unselectRow.pk_user == selectRow.pk_user) {
				var index = $("#datagrid_unselectUsers").datagrid('getRowIndex', unselectRow);
				$("#datagrid_unselectUsers").datagrid('selectRow', index);
			}
		}
	}
}

/**
 * datagrid格式化操作按钮
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatOperate(value, row, index) {
	return "<a  href='javascript:void(0)' class='easyui-linkbutton' onclick='userList(" + index + ")' >人员列表</a>";
	// return "<a href='javascript:void(0)' class='easyui-linkbutton'
	// onclick='beforOpenFormDialog(" + '"datagrid_executor"' + "," + index +
	// "," + '"修改分组"' + "," + '"dialog_executor"' + "," + '"form_executor"' +
	// "," + '"toolbar_executor"' + ")' >编辑</a>&nbsp<a href='javascript:void(0)'
	// class='easyui-linkbutton' onclick='userList(" + index + ")' >人员列表</a>";
};

/**
 * 格式化字段：创建时间
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function formatCreateTime(value, row, index) {
	if (null != value) {
		return value.substr(0, 19);
	} else {
		return "";
	}
}

/**
 * datagrid打开人员列表
 * 
 * @param {Object}
 *            index
 */
function userList(index) {
	if (index != null) {4
		$("#datagrid_executor").datagrid("selectRow", index);
		var rec = $("#datagrid_executor").datagrid("getSelected");
		// 对datagrid数据进行初始化清空
		var listRows = $("#datagrid_userList").datagrid('getRows');
		var rows = listRows.length;
		for (var i = 0; i < rows; i++) {
			$("#datagrid_userList").datagrid('deleteRow', 0);
		}
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
};

/**
 * 选择人员
 * 
 * @param {Object}
 *            rowIndex
 * @param {Object}
 *            rowData
 */
function selectUser(rowIndex, rowData) {
	// 返回已选列表的当前所有行
	var selectRows = $("#datagrid_selectUsers").datagrid('getRows');
	for (var i = 0; i < selectRows.length; i++) {
		var selectRow = selectRows[i];
		var selectUser = selectRow.pk_user;
		// 已存在
		if (selectUser == rowData.pk_user) {
			var index = $("#datagrid_selectUsers").datagrid('getRowIndex', selectRow);
			$("#datagrid_selectUsers").datagrid('deleteRow', index);
			return;
		}
	}
	// 将被点击的数据行添加到已选列表中
	$("#datagrid_selectUsers").datagrid('appendRow', {
		pk_user : rowData.pk_user,
		em_code : rowData.em_code,
		user_name : rowData.user_name,
	});
};

/**
 * 取消选择人员
 * 
 * @param {Object}
 *            rowIndex
 * @param {Object}
 *            rowData
 */
function unselectUser(rowIndex, rowData) {
	var unselectRows = $("#datagrid_unselectUsers").datagrid('getRows');
	for (var i = 0; i < unselectRows.length; i++) {
		var unselectRow = unselectRows[i];
		var unselectUser = unselectRow.pk_user;
		// 已存在
		if (unselectUser == rowData.pk_user) {
			var index = $("#datagrid_unselectUsers").datagrid('getRowIndex', unselectRow);
			$("#datagrid_unselectUsers").datagrid('unselectRow', index);
			// 删除已选行
			$("#datagrid_selectUsers").datagrid('deleteRow', rowIndex);
			return;
		}
	}
};

/**
 * 提交表单前获取已选人员数据
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
	// 获取已选人员
	var selectRows = $("#datagrid_selectUsers").datagrid('getRows');
	var form_data = $("#" + formName).serializeObject();
	form_data.users = JSON.stringify(selectRows);
	submitForm(datagridName, dialogName, formName, urlName, form_data);
};

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
	// 待选项取消选择
	$("#datagrid_unselectUsers").datagrid('unselectAll');
	// 获取已选项集合
	var selectRows = $("#datagrid_selectUsers").datagrid('getRows');
	var rows = selectRows.length;
	for (var i = 0; i < rows; i++) {
		$("#datagrid_selectUsers").datagrid('deleteRow', 0);
	}
	// 初始化加载待选
	$("#datagrid_unselectUsers").datagrid({
		queryParams : {
			user_name : null
		},
	});
	// 判断编辑
	if (index != null) {
		$("#" + datagridName).datagrid("selectRow", index);
		var rec = $("#" + datagridName).datagrid("getSelected");
		$.ajax({
			type : "post",
			url : 'yjwy/basic/executor/querySelectedUsers',
			contentType : 'application/json',
			data : JSON.stringify(rec),
			async : true,
			dataType : "json",
			success : function(data) {
				datagridAappendRow(data);
			}
		});
	}

	openFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName, 680, 475);
};

/**
 * 打开修改窗口时间对数据表格进行加载
 * 
 * @param {Object}
 *            data
 */
function datagridAappendRow(data) {
	var rows = data.rows;
	if (rows != null) {
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			$("#datagrid_selectUsers").datagrid('appendRow', row);
			var unselectRows = $("#datagrid_unselectUsers").datagrid('getRows');
			for (var j = 0; j < unselectRows.length; j++) {
				var unselectRow = unselectRows[j];
				if (unselectRow.pk_user == row.pk_user) {
					var index = $("#datagrid_unselectUsers").datagrid('getRowIndex', unselectRow);
					$("#datagrid_unselectUsers").datagrid('selectRow', index);
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
}