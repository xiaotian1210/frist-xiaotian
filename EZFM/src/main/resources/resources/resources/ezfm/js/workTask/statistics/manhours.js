/*维修单量工时统计表*/

/**
 * 初始化下拉框
 * @param {Object} areaId
 * @param {Object} projectId
 */
function initAreaProject(areaId, projectId) {
	//初始化区域下拉列表
	queryData(areaId, "ezfm/device/query/area", "pk_area", "area_name");
	$("#" + areaId).combobox({
		onSelect: function(rec) {
			var url = 'ezfm/device/query/project?pk_area=' + rec.pk_area;
			queryData(projectId, url, "pk_project", "project_name");
		}
	});
	//初始化项目下拉列表
	queryData(projectId, "ezfm/device/query/project", "pk_project", "project_name");

}

/**
 * 提交查询条件
 */
function querySubmit() {
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	$("#datagrid_manhours").datagrid("load", {
		pk_area: pk_area,
		pk_project: pk_project,
		start_time: start_time,
		end_time: end_time,
	});
}



/**
 * 重置并查询
 */
function resetSubmit() {
	
	$("#combobox_area").combobox("setValue","default");
    $("#combobox_project").combobox("setValue","default");
    $('#datebox_start_time').datebox('setValue', '');
    $('#datebox_end_time').datebox('setValue', '');
    
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	$("#datagrid_manhours").datagrid("load", {
		pk_area: pk_area,
		pk_project: pk_project,
		start_time: start_time,
		end_time: end_time,
	});
}
/**
 * EasyUI DataGrid根据字段动态合并单元格
 * @param fldList 要合并table的id
 * @param fldList 要合并的列,用逗号分隔(例如："name,department,office");
 */
function MergeCells(tableID, fldList) {
	//获取列集合
	var Arr = fldList.split(",");
	//获取datagrid对象
	var dg = $('#' + tableID);
	var fldName;
	//获取datagrid数据数量
	var RowCount = dg.datagrid("getRows").length;
	var span;
	var PerValue = "";
	//存放数据中合并列的值
	var CurValue = "";
	//数据行
	var row;
	//遍历要合并的列集合
	for(i = 0; i < Arr.length; i++) {
		//获取列属性名
		fldName = Arr[i];
		PerValue = "";
		span = 1;
		//循环datagrid的rows数据
		for(j = 0; j <= RowCount; j++) {
			//如果j等于总数，遍历完成，将合并列的值置空
			row = dg.datagrid("getRows")[j];
			if(j == RowCount) {
				CurValue = "";
			} else {
				//如果j不等于总数，没有遍历完，获取当前遍历行合并列的值
				CurValue = row[fldName];
			}
			//合并列的值与标识变量的值如果相同，不做处理，合并列相同的行标识数量加1
			if(PerValue == CurValue) {
				span += 1;
			} else {
				//如果不等于，则证明合并列的值变了，需要新起一个合并行
				var index = j - span;
				dg.datagrid('mergeCells', {
					index: index,
					field: fldName,
					rowspan: span, //合并行的个数为标识数量
					colspan: null
				});
				//判断是否插入空白行，如果插入空白行需要将循环数和总数++
				if(j != 0 && j != RowCount) {
					dg.datagrid('insertRow', {
						index: j,
						row: {}
					});
					j++;
					RowCount++;
				}
				span = 1;
				//将当前合并列的值设置给标识变量
				PerValue = CurValue;
			}
		}

	}
}