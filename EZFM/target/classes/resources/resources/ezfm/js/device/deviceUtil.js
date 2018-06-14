/**
 * 表单验证
 */
  $.extend($.fn.validatebox.defaults.rules, {
	varcharTen: {
		validator: function(value, param) {
			return value.length <= 10;
		},
		message: '请输入长度小于10的内容'
	},
	varcharFifty: {
		validator: function(value, param) {
			return value.length <= 50;
		},
		message: '请输入长度小于50的内容'
	},
	isEmpty: {
		validator: function(value, param) {
			return value != "" && value != null && value != undefined;
		},
		message: '该项不能为空'
	},
	endDate: {
		validator: function(value, param) {
			var startDate = $("#input_start_time").datebox('getValue');
			return(value >= startDate);
		},
		message: '结束日期不能小于开始日期'
	},
	startDate: {
		validator: function(value, param) {
			var endDate = $("#input_end_time").datebox('getValue');
			return(value <= endDate);
		},
		message: '开始日期不能大于结束日期'
	},
	area: {
		validator: function(value, param) {
			return value != '区域选择';
		},
		message: null
	},
	project: {
		validator: function(value, param) {
			return value != '项目选择';
		},
		message: null
	},
	site: {
		validator: function(value, param) {
			return value != 'FM选择';
		},
		message: null
	},
	room: {
		validator: function(value, param) {
			return value != '机房选择';
		},
		message: null
	},
	csi: {
		validator: function(value, param) {
			return value != '分类选择';
		},
		message: null
	},
	group: {
		validator: function(value, param) {
			return value != '分组选择';
		},
		message: null
	},
	planName: {
		validator: function(value, param) {
			return value.length <= 200;
		},
		message: '请输入长度小于200的内容'
	},
});

/**
 * 日期选择框只显示年份和月份
 * @param {Object} dateId
 */
function formatDateYearMouth(dateId) {
	$('#' + dateId).datebox({
		onShowPanel: function() { // 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层    
			span.trigger('click'); // 触发click事件弹出月份层    
			if(!tds)
				setTimeout(function() { // 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔    
					tds = p.find('div.calendar-menu-month-inner td');
					tds.click(function(e) {
						e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件    
						var year = /\d{4}/.exec(span.html())[0] // 得到年份    
							,
							month = parseInt($(this).attr('abbr'), 10) + 1; // 月份    
						$('#' + dateId).datebox('hidePanel') // 隐藏日期对象    
							.datebox('setValue', year + '-' + month); // 设置日期的值    
					});
				}, 0);
		},
		parser: function(s) { // 配置parser，返回选择的日期    
			if(!s)
				return new Date();
			var arr = s.split('-');
			return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		},
		formatter: function(d) {
				var month = d.getMonth();
				if(month < 10) {
					month = "0" + month;
				}
				if(d.getMonth() == 0) {
					return d.getFullYear() - 1 + '-' + 12;
				} else {
					return d.getFullYear() + '-' + month;
				}
			} // 配置formatter，只返回年月    
	});
	var p = $('#' + dateId).datebox('panel'), // 日期选择对象    
		tds = false, // 日期选择对象中月份    
		span = p.find('span.calendar-text'); // 显示月份层的触发控件    
}

/**
 * 采用正则表达式获取地址栏参数
 * @param {Object} name
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null) return unescape(r[2]);
	return null;
}

/**
 * ajax请求下拉框数据
 * @param {Object} comboboxName
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 * @param {Object} value
 */
function queryData(comboboxName, urlName, valueField, textField) {
	$.ajax({
		type: "get",
		url: urlName,
		contentType: 'application/json',
		async: true,
		dataType: "json",
		success: function(json) {
			var data = json.data;
			comboboxInit(comboboxName, urlName, valueField, textField, data);
		}
	});
}

/**
 * combobox下拉框初始化
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 */
function comboboxInit(comboboxName, urlName, valueField, textField, data) {
	$("#" + comboboxName).combobox({
		valueField: valueField,
		textField: textField,
		editable: false,
		data: data,
	});
}

/**
 * datagrid数据删除
 * @param {Object} datagridName
 * @param {Object} urlName
 */
function deleteData(datagridName, urlName) {
	var recList = $("#" + datagridName).datagrid("getSelections");
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择删除项！", "error");
		return;
	}
	$.messager.confirm("确认", "确定要删除吗？", function(r) {
		if(r) {
			$.ajax({
				type: "POST",
				url: urlName,
				contentType: 'application/json',
				data: JSON.stringify(recList),
				dataType: "json",
				success: function(json) {
					if(json.usedFlag == 1) {
						$.messager.alert('提示', '数据已被关联使用,无法删除!', 'info');
					} else {
						var flag = json.success;
						if(flag) {
							$.messager.show({
								title: '成功',
								msg: '删除操作成功!',
								timeout: 2000,
							});
							$("#" + datagridName).datagrid('reload');
						} else {
							$.messager.alert('错误', '删除操作失败!请稍后再试！', 'error');
						}
					}
				},
			});
		}
	});
};

/**
 * datagrid 设备报废
 * @param {Object} datagridName
 * @param {Object} urlName
 */
function inactiveData(datagridName, urlName) {
	var recList = $("#" + datagridName).datagrid("getSelections");
	if(recList.length == 0) {
		$.messager.alert("错误", "请选择报废项！", "error");
		return;
	}
	if(recList.length>1) {
		$.messager.alert("错误", "请一次操作一条记录！", "error");
		return;
	}
	if(recList[0].active=="0") {
		$.messager.alert("错误", "请选择一台未报废的设备！", "error");
		return;
	}
	$.messager.confirm("确认", "确定要将设备报废吗？", function(r) {
		if(r) {
			$.ajax({
				type: "POST",
				url: urlName,
				contentType: 'application/json',
				data: JSON.stringify(recList),
				dataType: "json",
				success: function(json) {
					var flag = json.success;
					if(flag) {
						$.messager.show({
							title: '成功',
							msg: '设备报废成功!',
							timeout: 2000,
						});
						$("#" + datagridName).datagrid('reload');
					} else {
						$.messager.alert('错误', '设备报废操作失败!请稍后再试！', 'error');
					}
				},
			});
		}
	});
};

/**
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitForm(datagridName, dialogName, formName, urlName, form_data) {
	var flag = $("#" + formName).form("validate");
	if(!flag) {
		return;
	}
	var submit_method = $("input[name='submit_method']").val();
	var method_name = "";
	switch(submit_method) {
		case 'save':
			method_name = '新增';
			break;
		case 'update':
			method_name = '修改';
	}
	$.ajax({
		type: "post",
		url: urlName + submit_method,
		contentType: 'application/json',
		data: JSON.stringify(form_data),
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				$.messager.show({
					title: '成功',
					msg: method_name + '操作成功!',
					timeout: 2000,
				});
				closeDialog(formName, dialogName);
				$("#" + datagridName).datagrid('reload');
			} else {
				//$.messager.alert('错误', method_name + '操作失败!请稍后再试！', 'error');
				$.messager.alert('错误', json.message, 'error');
			}
		}
	});

};
/**
 * 表单窗口(新增、修改)
 * @param {Object} titleName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} datagridName
 * @param {Object} toolbarName
 * @param {Object} index
 */
function openFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName, wsize, hsize) {
	$("#" + formName).form("reset");
	$("input[name='submit_method']").val('save');
	if(index != null) {
		$("input[name='submit_method']").val('update');
		$("#" + datagridName).datagrid("selectRow", index);
		var rec = $("#" + datagridName).datagrid("getSelected");
		$("#" + formName).form("load", rec);
	}
	$("#" + dialogName).dialog({
		title: titleName,
		width: wsize,
		height: hsize,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#" + toolbarName,
	});
	closewin_flg = false;
};

/**
 * pagination格式化分页工具栏
 * @param {Object} datagridName
 */
function formatPager(datagridName) {
	var pager = $("#" + datagridName).datagrid('getPager');
	$(pager).pagination({
		showPageList: false, //每页显示数量选择框
		showRefresh: false, //刷新按钮
		displayMsg: "",
	});
};

/**
 * 关闭窗口，并清空表单数据
 * @param {Object} dialogName
 */
function closeDialog(formName, dialogName) {
	$("#" + formName).form('clear');
	$("#" + dialogName).window('close');
};

/**
 * datagrid数据表格初始化加载（无事件绑定）
 * @param {Object} datagridName
 * @param {Object} urlName
 * @param {Object} titleName
 * @param {Object} toolbarName
 * @param {Object} showPage
 */
function datagridInit(datagridName, urlName, titleName, toolbarName, singleSelect, showPage) {
	$("#" + datagridName).datagrid({
		url: urlName,
		title: titleName,
		pageSize: 15, //分页大小
		pageList:[15,30,45,60],//每页显示个数可选项
		loadMsg: "正在加载数据...", // 数据加载中显示信息
		rownumbers: true, //显示行号列
		singleSelect: singleSelect, // 只允许选择一行
		striped: true, // 显示斑马线效果
		fit: true, // 使表格铺满容器
		pagination: showPage, // 显示分页工具栏
		toolbar: "#" + toolbarName, // 顶部工具栏
		border: false,
	});
};

/**
 * dialog打开窗口
 * @param {Object} dialogName
 * @param {Object} titleName
 * @param {Object} toolbarName
 */
function openDialog(dialogName, titleName, toolbarName, wsize, hsize) {
	$("#" + dialogName).dialog({
		title: titleName,
		width: wsize,
		height: hsize,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#" + toolbarName,
	});
	closewin_flg = false;
};

/**
 * 对datagrid进行数据选中（编辑时）
 * @param {Object} selectRows
 * @param {Object} datagridName
 */
function editSelectData(datagridName, selectRows, idName) {
	if(selectRows != null && selectRows.length > 0) {
		for(var i = 0; i < selectRows.length; i++) {
			var selectRow = selectRows[i];
			var allRows = $("#" + datagridName).datagrid('getRows');
			for(var j = 0; j < allRows.length; j++) {
				var row = allRows[j];
				if(row[idName] == selectRow[idName]) {
					var index = $("#" + datagridName).datagrid('getRowIndex', row);
					$("#" + datagridName).datagrid('selectRow', index);
				}
			}
		}
	}
}
