

//全局属性，用于存储跟进人框名称和id
var textBoxId;
var textBoxName
/**
 * datagrid格式化操作按钮
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatOperate(value, row, index) {
//	console.log(row);
	var operation ="";
	row.fk_project_id ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='userList(\""+row.pk_details_id+"\")' >"+row.fk_project_id+"</a>"
	//待办任务
	if(row.state=='1'){
		/**
		 * 此处添加工单状态判断，未生成工单有【报修】和【处理】操作。生成了工单，并工单状态为已取消有【重派】和【完成】操作
		 */
		if (row.fk_details_id!=null&&row.fk_details_id!="") {
			operation += 
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delegate(\""+row.fk_details_id+"\")' >重派</a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showComplete(\""+row.pk_details_id+"\")' >完成</a>";
		}else{
			operation += 
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showHandle(\""+row.pk_details_id+"\")' >处理</a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showRepair(\""+row.pk_details_id+"\")' >报修</a>";
		}
	}
	//处理中
	else if(row.state=='2'){
		/**
		 * 此处添加工单状态判断，未生成工单有【完成】操作。生成了工单有【打单】操作,如果工单状态是未取消状态，有【取消】操作，如果已取消，有【重派】操作
		 */
		if (row.fk_details_id!=null&&row.fk_details_id!="") {
			//未派单
			if(row.task_state=='0'||row.task_state=='4'||row.task_state=='5'){
				if (row.task_state=='0') {
					operation += 
						"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delegate(\""+row.fk_details_id+"\")' >派单</a>"
				}
				if (row.task_state=='4'||row.task_state=='5') {
					operation += 
						"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delegate(\""+row.fk_details_id+"\")' >重派</a>"
				}
			}
			//待接单
			else if(row.task_state=='1'){
				operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='orders(\""+row.fk_details_id+"\")' >接单</a>"+
						"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='refuse(\""+row.fk_details_id+"\")' >拒单</a>";
			}
			//维修中
			else if(row.task_state=='2'){
				operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showComplete(\""+row.pk_details_id+"\")' >完成</a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='cancel(\""+row.fk_details_id+"\")' >取消</a>"+
				"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='workTaskclose(\""+row.fk_details_id+"\")' >关闭</a>";
			}
			operation +="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='print(\""+row.fk_details_id+"\")' >打印</a>";
		}else{
			operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showComplete(\""+row.pk_details_id+"\")' >完成</a>";
		}
	}
	//已完成待回访
	else if(row.state=='3'){
		/**
		 * 此处添加工单状态判断，未生成工单有【回访】操作。生成了工单有【回访】和【打单】操作，回访后直接完结任务
		 */
		operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showVisit(\""+row.pk_details_id+"\")' >回访</a>";
		if (row.fk_details_id!=null&&row.fk_details_id!="") {
			operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='print(\""+row.fk_details_id+"\")' >打单</a>";
		}
	}
	//已回访
	else if(row.state=='4'){
		/**
		 * 此处添加工单状态判断。生成了工单有【打单】操作
		 */
		if (row.fk_details_id!=null&&row.fk_details_id!="") {
			operation += "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='print(\""+row.fk_details_id+"\")' >打单</a>";
		}
	}
	return operation ;
};
/**
 * datagrid格式化操作按钮
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatDetails(value, row, index) {
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showSonPage(\""+row.pk_details_id+"\")' >"+row.details_number+"</a>"
	return operation ;
};

/**
 * 格式化工单按钮
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatDetailsWorkTask(value, row, index) {
	var operation="";
	if (row.fk_details_id!=null&&row.fk_details_id!="") {
		operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='showSonPageWorkTask(\""+row.fk_details_id+"\")' >"+row.datails_code+"</a>"
	}
	return operation ;
};
/**
 * 打开子页面
 *  @param {Object} id
 */
function showSonPage(id){
	var sonpageUrl = "ezfm/problem/details/sonpage/"+id;
	showSonPageEvent("详情",sonpageUrl);
	return false;
}

function showSonPageWorkTask(id){
	var sonpageUrl = "ezfm/worktask/details/showSonPage/"+id;
	showSonPageEvent("详情",sonpageUrl);
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
 * 打开处理页面
 *  @param {Object} id
 */
function showHandle(id){
	var sonpageUrl = "ezfm/problem/details/handle/"+id;
	showSonPageEvent("详情",sonpageUrl,{width:800,height:450,id:'problemHandleId'});
	return false;
}

/**
 * 打开维修页面
 *  @param {Object} id
 */
function showRepair(id){
	var sonpageUrl = "ezfm/problem/details/repair/"+id;
	showSonPageEvent("详情",sonpageUrl,{width:800,height:450,id:'problemRepairId'});
	return false;
}


/**
 * 打开完成页面
 *  @param {Object} id
 */
function showComplete(id){
	var sonpageUrl = "ezfm/problem/details/complete/"+id;
	showSonPageEvent("详情",sonpageUrl,{width:800,height:450,id:'problemCompleteId'});
	return false;
}

///**
// * 打开完成页面
// *  @param {Object} id
// */
//function complete(id){
//	var sonpageUrl = "ezfm/worktask/details/complete/"+id;
//	showSonPageEvent("完成",sonpageUrl,{width:800,height:450,id:'workTaskCompleteId'});
//	return false;
//}


/**
 * 打开回访页面
 *  @param {Object} id
 */
function showVisit(id){
	var sonpageUrl = "ezfm/problem/details/visit/"+id;
	showSonPageEvent("详情",sonpageUrl,{width:800,height:450,id:'problemVisitId'});
	return false;
}
/**
 * 打开重单页面
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
 * 关闭
 */
function workTaskclose(id){
	debugger;
	$.messager.confirm("确认", "确定要关闭该报事吗？", function(r) {
		if(r) {
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
		        	$("#datagrid_problemAgency").datagrid('reload');
					$.messager.show({
						title:'提示',
						msg:'报事已关闭',
						timeout:2000,
						showType:'slide'
					});
					
		        }  
		    });
		}
	});
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
//SONPAGE EVENT 显示子页面
var showSonPageEvent = function(title,actionUrl,options){
	closewin_flg = false;
	showSonPageInfo(title,actionUrl,options);
}


/**
 * 是否维修，是展开维修信息，否隐藏维修信息
 * @param record
 */
function whetherRepair(value){
	var value = $('input[name="whether_repair"]:checked').val();
	if (value==2) {
		$("#repair_information").hide();
		$("#problem_class").css("display","");
		$("#problem_duty_user").css("display","");
		$("#houseAddress").css("display","");
		$("#detailedAddress").css("display","");
	}else{
		$("#repair_information").show();
		$("#problem_class").css("display","none");
		$("#problem_duty_user").css("display","none");
		$("#houseAddress").css("display","none");
		$("#detailedAddress").css("display","none");
		//为是否预约绑定事件
		$("#whether_appointment").combobox({
			onSelect: whetheraAppointment,
		});
		//查询维修类别
		queryData("service_type","ezfm/baseinfo/pub/query/dictionary/dict/query?code=maintainServiceCate&state=1","dict_code","dict_name");
	}
};
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
 * 表单窗口(新增)
 * @param {Object} titleName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} datagridName
 * @param {Object} toolbarName
 * @param {Object} index
 */
function openFormDialogProblem(datagridName, index, titleName, dialogName, formName, toolbarName) {
	close_flag=false;
	$("#appointment_time").css("display","none");
	$("#" + dialogName).dialog({
		title: titleName,
		width: 680,
		height: 470,
		modal: true,
		shadow: true,
		cache: false,
		buttons: "#" + toolbarName,
	});
	closewin_flg = false;
	//区域下拉框
	queryData("problem_area","ezfm/device/query/area","pk_area","area_name");
	
	$("#problem_area").combobox({
		onSelect:function(rec){
			var url = 'ezfm/device/query/project?pk_area='+rec.pk_area;
			queryData("fk_project_id",url,"pk_project","project_name");
		}
	});
//	queryDataClass("fk_class_id","ezfm/problem/classadmin/combotreeQuery","id","name");
	//为是否维修下拉绑定事件
	$("#whether_repair").combobox({
		onSelect: whetherRepair
	});
	//为是查询事件
	$("#fk_project_id").combobox({
		onSelect: getClassByProject,
	});
//	//默认不维修；隐藏维修信息；
	$("#repair_information").hide();
	//添加报事跟进人事件
	$("#fk_duty_user_name").textbox({
		icons: [{
			iconCls:'icon-search',
			handler: function(e){
				addDutyUser('fk_duty_user_id','fk_duty_user_name');
			}
		}],
	});
	//添加事件
	$("#datagrid_unselectUsers_tab").datagrid({
		onDblClickRow: selectedUser,
	});
	//为维修机房绑定事件
	$("#work_fk_repair_equipment_room").combobox({
		onSelect: getVagueEq
	});
	//查询维修类别"ezfm/baseinfo/pub/query/dictionary/dict/query?code=maintainServiceCate&state=1
	queryData("work_service_type","ezfm/baseinfo/pub/query/dictionary/dict/query?code=maintainServiceCate&state=1","dict_code","dict_name");
	//初始化人员列表
	datagridInit('datagrid_detailsAddUserList', null, null, null, false,false);
	//初始化窗口时，默认否，不维修
	$("#repair_information").hide();
	$("#problem_class").css("display","");
	$("#problem_duty_user").css("display","");
	$("#houseAddress").css("display","");
	$("#detailedAddress").css("display","");
};

/**
 * 根据项目查询种类
 * @param record
 */
function getClassByProject(record){
	var pk_project = record.pk_project;
	//查询报事分类
	var urlProblemClass = 'ezfm/problem/classadmin/queryClassByProject?projectId='+pk_project;
	queryData("fk_class_id",urlProblemClass,"pk_class_id","class_name");
	//查询维修种类
	var urlWorkTaskClass = 'ezfm/orktask/repairclass/queryClassByProject?projectId='+pk_project;
	queryData("work_repair_class_id",urlWorkTaskClass,"pk_class_id","class_name");
	//查询机房
	var urlRoom = "ezfm/worktask/details/queryRoomByProject?projectId="+pk_project;
	queryData("work_fk_repair_equipment_room",urlRoom,"rm_id","name");
	//查询报事地点
	var urlResources = "ezfm/worktask/details/queryResourcesByProject?projectId="+pk_project;
	queryData("fk_house_address_id",urlResources,"pk_resources","project");
	//查询维修地点
	var urlResources = "ezfm/worktask/details/queryResourcesByProject?projectId="+pk_project;
	queryData("work_fk_house_address_id",urlResources,"pk_resources","project");
	//查询地点(工单)
	var urlResources = "ezfm/worktask/details/queryResourcesByProject?projectId="+pk_project;
	queryData("work_fk_repair_address",urlResources,"pk_resources","project");
	//报事跟进人清空
	$("#fk_duty_user_name").textbox('setText',"");
	$("input[id=fk_duty_user_id]").val("");
	//报修跟进人清空
	$("#work_duty_user_name").textbox('setText',"");
	$("input[id=work_duty_user_type]").val("");
	$("input[id=work_duty_user_id]").val("");
};
//根据机房查询设备
function getVagueEq(record){
	var roomId = record.rm_id;
	var urlEq = "ezfm/worktask/details/queryEqByRoom?roomId="+roomId;
	queryData("work_fk_repair_equipment",urlEq,"eq_id","name");
}

/**
 * 打开添加跟进人界面
 * @param type 1：接口人员；2：项目人员
 */
function addWorkTaskDutyUser(type){
	//初始化人员列表
	var url = "";
	//填充选择人员类型，1：接口人员；2：维修人员
	$("input[id=duty_user_type]").val(type);
	//全局属性，用于存储跟进人框名称和id
	textBoxId = "work_duty_user_id";
	textBoxName = "work_duty_user_name";
	work_duty_user_id
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
					$('#datagrid_unselectUsers_tab').datagrid('loadData', {
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
					$('#datagrid_unselectUsers_tab').datagrid('loadData', {
						rows: rows
					});
				}
			}
		});
		openDialog('datagrid_unselectUsers', '人员列表(双击选中)', null, 385, 260);
	}
}
/**
 * combotree下拉框初始化
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 */
function combotreeInit(comboboxName,urlName,valueField,textField,data){
	$("#"+comboboxName).combotree({
		editable:false,
		data:data,
		onSelect : onSelect,
	});
}


/**
 * 报事分类选中，判断是否为叶子节点。非叶子节点，清空选中。
 * @param node
 */
function onSelect(node){
	//返回树对象  
	var tree = $(this).tree;  
	//选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
	var isLeaf = tree('isLeaf', node.target);  
	if (!isLeaf) {  
		//清除选中  
		$('#class_id').combotree('clear');  
	}  
}

/**
 * 打开地点页面
 *  @param {Object} id
 */
function addProblemPlace(){
	var projectId = $("#fk_project_id").combobox("getValue");
	if (projectId!=null&&projectId!=="") {
		var sonpageUrl = "ezfm/worktask/details/place/"+projectId+"/problem";
		showSonPageEvent("地点（双击选中）",sonpageUrl,{width:400,height:250,id:'problemPlaceId'});
		return false;
	}
}

/**
 * 打开地点页面
 *  @param {Object} id
 */
function eliminateProblemPlace(name){
	if(name=="fk_house_address"){
		$("input[id=fk_house_address_id]").val();
		$("#fk_house_address_name").textbox('setText','');
	}else if(name=="work_fk_repair_address"){
		$("input[id=work_fk_repair_address]").val();
		$("#work_fk_repair_address_name").textbox('setText','');
	}else if(name=="fk_repair_address_repair"){
		$("input[id=fk_repair_address_repair]").val();
		$("#fk_repair_address_repair_name").textbox('setText','');
	}
}

/**
 * 打开地点页面
 *  @param {Object} id
 */
function addPlace(){
	var projectId = $("#fk_project_id").combobox("getValue");
	if (projectId!=null&&projectId!=="") {
		var sonpageUrl = "ezfm/worktask/details/place/"+projectId+"/problemWorkTask";
		showSonPageEvent("地点（双击选中）",sonpageUrl,{width:400,height:250,id:'problemPlaceId'});
		return false;
	}
}


/**
 * 打开添加跟进人
 * @param node
 */
function addDutyUser(id,name){
	//全局属性，用于存储跟进人框名称和id
	textBoxId = id;
	textBoxName = name;
	var projectId = $("#fk_project_id").combobox("getValue");
	$.ajax({
		type: "get",
		url: 'ezfm/baseinfo/pub/query/dictionary/user/query?projectId='+projectId,
		contentType: 'application/json',
		async: true,
		dataType: "json",
		success: function(data) {
			var rows = data.data;
			if(rows != null) {
				$('#datagrid_unselectUsers_tab').datagrid('loadData', {
					rows: rows
				});
			}
		}
	});
	openDialog('datagrid_unselectUsers', '人员列表(双击选中)', null, 400, 260);
}

/**
 * 添加跟进人
 * @param node
 */
function selectedUser(rowIndex, rowData){
	//添加跟进人
	$("#"+textBoxName).textbox('setText',rowData.user_name);
	$("input[id="+textBoxId+"]").val(rowData.pk_user);
	$("#datagrid_unselectUsers").window('close');
	textBoxId="";
	textBoxName="";
}


/**
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitFormProblem(datagridName, dialogName, formName, urlName) {
	var whether_repair = $('input[name="whether_repair"]:checked').val();
	if(whether_repair=="" || whether_repair==null){
		$.messager.alert('提示', '请选择是否下单！', 'info');
		return;
	}
	//提交表单验证,是否报事；
	if (whether_repair==1) {
		var fk_project_id = $("#fk_project_id").combobox("getValue");
		if(fk_project_id=="" || fk_project_id=="default"){
			$.messager.alert('提示', '请选择项目！', 'info');
			return;
		}
		var work_service_type = $("#work_service_type").combobox("getValue");
		if(work_service_type==""){
			$.messager.alert('提示', '请选择维修类别！', 'info');
			return;
		}
		var work_repair_class_id = $("#work_repair_class_id").textbox("getValue");
		if(work_repair_class_id==""){
			$.messager.alert('提示', '请选择维修种类！', 'info');
			return;
		}
		var work_repair_details = $("#work_repair_details").textbox("getValue");
		if(work_repair_details==""){
			$.messager.alert('提示', '请输入详细地址！', 'info');
			return;
		}
	}else if(whether_repair==2){
		var fk_project_id = $("#fk_project_id").combobox("getValue");
		if(fk_project_id=="" || fk_project_id=="default"){
			$.messager.alert('提示', '请选择项目！', 'info');
			return;
		}
		var fk_class_id = $("#fk_class_id").combobox("getValue");
		if(fk_class_id==""){
			$.messager.alert('提示', '请选择报事分类！', 'info');
			return;
		}
		/*var fk_duty_user_id = $("#fk_duty_user_id").val();
		if(fk_duty_user_id==""){
			$.messager.alert('提示', '请选择跟进人！', 'info');
			return;
		}*/
	}
//	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	var reg = /^1\d{10}$/;
	var customer_number = $("form input[name=customer_number]").val();
	if(!reg.test(customer_number)){
		$.messager.alert('提示', '请填写正确号码！', 'info');
		return;
	}
	$("#submit_form_problem_button").linkbutton('disable');
	$('#'+formName).form('submit', {
		url: urlName,
		success: function(result){
			result = eval('('+result+')');
			if(result.success){
				closeDialogProblem(dialogName);
				$("#" + datagridName).datagrid('reload');
				clearForm();
				$('#submit_form_problem_button').linkbutton('enable');
				$.messager.show({
					title:'提示',
					msg:'数据已保存',
					timeout:2000,
					showType:'slide'
				});
			}
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown){
			 $('#submit_form_problem_button').linkbutton('enable');
			 var result = eval("(" + XMLHttpRequest.responseText + ")");
			 $.messager.alert('错误', result.message, 'error');
		}
	});

};

function clearForm(){
	$("input[name='whether_repair'][value='2']").prop("checked",true);
	$('#problem_area').combobox('setValue','default');
	$('#fk_project_id').combobox('setValue','');
	$('#fk_class_id').combobox('setValue','');
	$('#details_content').textbox('setText','');
	$('#fk_duty_user_name').textbox('setText','');
	$('#fk_duty_user_id').val('');
	$('#fk_house_address_id').val('');
	$("#fk_house_address_name").textbox('setText','');
	$('#detailed_address').textbox('setText','');
	$('#customer_name').textbox('setText','');
	$('#customer_number').textbox('setText','');
	$('#work_repair_class_id').combobox('setValue','');
	$('#work_service_type').combobox('setValue','');
	$('#work_fk_repair_address').val('');
	$('#work_fk_repair_address_name').textbox('setText','');
	$('#work_repair_details').textbox('setText','');
	$('#work_fk_repair_equipment').combobox('setValue','');
	$('#work_fk_repair_equipment_room').combobox('setValue','');
	$('#work_duty_user_type').val('');
	$('#work_duty_user_id').val('');
	$('#work_duty_user_name').textbox('setText','');
	$('#problem_file').filebox('setValue','');
	$('#whether_appointment').combobox('setValue','2');
	$('#bookings_time').datetimebox('setValue','3/4/2010 2:3');
}
