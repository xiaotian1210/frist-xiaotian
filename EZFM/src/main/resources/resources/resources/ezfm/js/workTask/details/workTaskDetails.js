
function showDetailsSon(value, row, index) {
	return "<a href='javascript:void(0);' onclick='showSonPage(\""+row.pk_details_id+"\")'>"+value+"</a>"
}
function formatOperate(value, row, index) {
	var operation ="";
	//未派单
	if(row.task_state=='0'||row.task_state=='4'||row.task_state=='5'){
		if (row.task_state=='0') {
			operation += 
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delegate(\""+row.pk_details_id+"\")' >派单</a>"
		}
		if (row.task_state=='4'||row.task_state=='5') {
			operation += 
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delegate(\""+row.pk_details_id+"\")' >重派</a>"
		}
	}
	//待接单
	else if(row.task_state=='1'){
		operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='orders(\""+row.pk_details_id+"\")' >接单</a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='refuse(\""+row.pk_details_id+"\")' >拒单</a>";
	}
	//维修中
	else if(row.task_state=='2'){
		operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='complete(\""+row.pk_details_id+"\")' >完成</a>"+
		"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='cancel(\""+row.pk_details_id+"\")' >取消</a>"+
		"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='workTaskclose(\""+row.pk_details_id+"\")' >关闭</a>";
	}
	operation +="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='print(\""+row.pk_details_id+"\")' >打印</a>";
	return operation ;
};



/**
 * 打开地点页面
 *  @param {Object} id
 */
function addPlace(){
	var projectId = $("#fk_project_id").combobox("getValue");
	if (projectId!=null&&projectId!=="") {
		var sonpageUrl = "ezfm/worktask/details/place/"+projectId+"/workTask";
		showSonPageEvent("地点（双击选中）",sonpageUrl,{width:400,height:250,id:'workTaskPlaceId'});
		return false;
	}
}


/**
 * 打开打印页面
 *  @param {Object} id
 */
function print(id){
	var sonpageUrl = "ezfm/worktask/details/print/"+id;
	window.open(sonpageUrl);
	return false;
}
/**
 * 打开派单页面
 *  @param {Object} id
 */
function delegate(id){
	var sonpageUrl = "ezfm/worktask/details/delegate/"+id;
	showSonPageEvent("派单",sonpageUrl,{width:800,height:450,id:'workTaskDelegateId'});
	return false;
}

/**
 * 打开接单页面
 *  @param {Object} id
 */
function orders(id){
	var sonpageUrl = "ezfm/worktask/details/ordersOrReduseCancel/"+id+"/1";
	showSonPageEvent("接单",sonpageUrl,{width:800,height:350,id:'workTaskOrdersOrReduseCancelId'});
	return false;
}

/**
 * 打开拒单页面
 *  @param {Object} id
 */
function refuse(id){
	var sonpageUrl = "ezfm/worktask/details/ordersOrReduseCancel/"+id+"/2";
	showSonPageEvent("拒单",sonpageUrl,{width:800,height:350,id:'workTaskOrdersOrReduseCancelId'});
	return false;
}

/**
 * 打开取消页面
 *  @param {Object} id
 */
function cancel(id){
	var sonpageUrl = "ezfm/worktask/details/ordersOrReduseCancel/"+id+"/3";
	showSonPageEvent("取消",sonpageUrl,{width:800,height:350,id:'workTaskOrdersOrReduseCancelId'});
	return false;
}

/**
 * 打开完成页面
 *  @param {Object} id
 */
function complete(id){
	var sonpageUrl = "ezfm/worktask/details/complete/"+id;
	showSonPageEvent("完成",sonpageUrl,{width:800,height:450,id:'workTaskCompleteId'});
	return false;
}

function showSonPage(id){
	var sonpageUrl = "ezfm/worktask/details/showSonPage/"+id;
	showSonPageEvent("详情",sonpageUrl);
}

//SONPAGE EVENT 显示子页面
var showSonPageEvent = function(title,actionUrl,options){
	closewin_flg = false;
	showSonPageInfo(title,actionUrl,options);
}
//打开新增窗口
function openSaveDialog() {
	$("#form_details").form("reset");
	close_flag=false;
	$("#dialog_details").dialog({
		title: '新增工单',
		width: 700,
		height: 500,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#toolbar_details",
	});
	closewin_flg = false;
	$("#appointment_time").css("display","none");
	//查询区域，以及绑定级联
	queryData("problem_area","ezfm/baseinfo/pub/query/dictionary/area/query","pk_area","area_name");
	$("#problem_area").combobox({
		onSelect:function(rec){
			var url = 'ezfm/baseinfo/pub/query/dictionary/project/query?areaId='+rec.pk_area;
			queryData("fk_project_id",url,"pk_project","project_name");
		}
	});
	//为是查询事件
	$("#fk_project_id").combobox({
		onSelect: getClassByProject,
	});
	
	//查询维修类别
	queryData("service_type","ezfm/baseinfo/pub/query/dictionary/dict/query?code=maintainServiceCate&state=1","dict_code","dict_name");
	//为是否预约绑定事件
	$("#whether_appointment").combobox({
		onSelect: whetheraAppointment,
	});
	//为维修机房绑定事件
	$("#fk_repair_equipment_room").combobox({
		onSelect: getVagueEq
	});
	//添加事件
	$("#datagrid_detailsAddUserList").datagrid({
		onDblClickRow: selectedUser,
	});
	//初始化人员列表
	datagridInit('datagrid_detailsAddUserList', null, null, null, false,false);
}; 


/**
 * 根据项目查询种类
 * @param record
 */
function getClassByProject(record){
	var pk_project = record.pk_project;
	//查询维修种类
	var urlClass = 'ezfm/orktask/repairclass/queryClassByProject?projectId='+pk_project;
	queryData("repair_class_id",urlClass,"pk_class_id","class_name");
	var urlRoom = "ezfm/worktask/details/queryRoomByProject?projectId="+pk_project;
	queryData("fk_repair_equipment_room",urlRoom,"rm_id","name");
//	var urlResources = "ezfm/worktask/details/queryResourcesByProject?projectId="+pk_project;
//	queryData("fk_repair_address",urlResources,"pk_resources","project");
};

/**
 * 打开添加跟进人界面
 * @param type 1：接口人员；2：项目人员
 */
function addDutyUser(type){
	//初始化人员列表
	var url = "";
	//填充选择人员类型，1：接口人员；2：维修人员
	$("input[id=duty_user_type]").val(type);
	if (type==1) {
		var projectId = $("#fk_project_id").combobox("getValue");
		url = "ezfm/worktask/projectuser/queryProjectByUserAll";
		$.ajax({
			async : false,  
	        cache:false,  
	        type: 'POST',  
	        dataType : "json",
	        data:{'projectId':projectId},
	        url: url,//请求的action路径  
			success: function(data) {
				var rows = data.data;
				if(rows != null) {
					$('#datagrid_detailsAddUserList').datagrid('loadData', {
						rows: rows
					});
				}
			}
		});
		openDialog('datagrid_unselectUsers', '人员列表(双击选中)', null, 385, 260);
	}else if(type==2){
		var areaId = $("#problem_area").combobox("getValue");
		url = "ezfm/worktask/areauser/queryAreaByUserRepairAll";
		$.ajax({
			async : false,  
	        cache:false,  
	        type: 'POST',  
	        dataType : "json",
	        data:{'areaId':areaId},
	        url: url,//请求的action路径  
			success: function(data) {
				var rows = data.data;
				if(rows != null) {
					$('#datagrid_detailsAddUserList').datagrid('loadData', {
						rows: rows
					});
				}
			}
		});
		openDialog('datagrid_unselectUsers', '人员列表(双击选中)', null, 385, 260);
	}
}
/**
 * dialog打开窗口
 * @param {Object} dialogName
 * @param {Object} titleName
 * @param {Object} toolbarName
 */
function openDialog(dialogName,titleName,toolbarName,wsize,hsize){
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
 * 添加跟进人
 * @param node
 */
function selectedUser(rowIndex, rowData){
	//添加跟进人
	$("#duty_user_name").textbox('setText',rowData.user_name);
	$("input[id=duty_user_id]").val(rowData.user_id);
	$("#datagrid_unselectUsers").window('close');
}

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
		pageSize: 10, //分页大小
		// pageList:[5,10,15,20],//每页显示个数可选项
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

function getVagueEq(record){
	var roomId = record.rm_id;
	var urlEq = "ezfm/worktask/details/queryEqByRoom?roomId="+roomId;
	queryData("fk_repair_equipment",urlEq,"eq_id","name");
}

/**
 * 关闭
 */
function workTaskclose(id){
	debugger;
	$.messager.confirm("确认", "确定要关闭该工单吗？", function(r) {
		if(r) {
			/*$.request.restPost({
				url: "ezfm/worktask/details/save/close",
				data:{detailsId:id},
				success: function(rs){
					$("#datagrid_detailsList").datagrid('reload');
					$.messager.show({
						title:'提示',
						msg:'工单已关闭',
						timeout:2000,
						showType:'slide'
					});
				}
			});
			*/
			$.ajax({  
		        async : false,  
		        cache:false,  
		        type: 'POST',  
		        dataType : "json",
		        data:{detailsId:id},
		        url: "ezfm/worktask/details/save/close",//请求的action路径  
		        error: function () {//请求失败处理函数  
		            alert('请求失败');  
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	$("#datagrid_detailsList").datagrid('reload');
					$.messager.show({
						title:'提示',
						msg:'工单已关闭',
						timeout:2000,
						showType:'slide'
					});
		        }  
		    });
		}
	});
}
/**
 * 是否预约
 * @param record
 */
function whetheraAppointment(record){
	if (record.value==1) {
		$("#appointment_time").css("display","");
	}else{
		$("#appointment_time").css("display","none");
	}
};

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
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitForm(datagridName, dialogName, formName, urlName) {
	var projectId = $("#fk_project_id").combobox("getValue");
	if (projectId==""||projectId=="项目选择") {
		$.messager.alert('提示', '请选择项目！', 'info');
		return;
	}
	var fk_service_type_id = $("#service_type").combobox("getValue");
	if (fk_service_type_id==""||fk_service_type_id=="root") {
		$.messager.alert('提示', '请选择维修类别！', 'info');
		return;
	}
	var repair_class = $("#repair_class_id").combobox("getValue");
	if (repair_class==""||repair_class=="root") {
		$.messager.alert('提示', '请选择维修种类！', 'info');
		return;
	}
	var repair_details = $("#repair_details").textbox("getValue");
	if (repair_details=="") {
		$.messager.alert('提示', '请填写详细地点！', 'info');
		return;
	}
	var repair_user = $("#repair_user").textbox("getValue");
	if (repair_user=="") {
		$.messager.alert('提示', '请填写报修人！', 'info');
		return;
	}
	var form_data = $("#form_details").serializeObject();
	$.ajax({
		type: "post",
		url: urlName,
		contentType: 'application/json',
		data: JSON.stringify(form_data),
		async: true,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag) {
				closeDialogProblem(dialogName);
				$("#" + datagridName).datagrid('reload');
				$.messager.show({
					title:'提示',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
			} else {
				$.messager.alert('错误', '操作失败!请稍后再试！', 'error');
			}
		}
	});
}
