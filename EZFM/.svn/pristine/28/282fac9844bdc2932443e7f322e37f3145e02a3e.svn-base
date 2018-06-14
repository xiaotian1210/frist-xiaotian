//加载区域
function loadArea(){
	var url = "ezfm/baseinfo/pub/query/dictionary/area/query";
	var simpleFormat = {valueField: 'pk_area',textField: 'area_name'};
	initDictionaryCombobox("queray_main_area_id",url,null,simpleFormat);
}
function areaChange(newValue,oldValue){
	loadProject(newValue);
	loadUser(newValue);
}
//签到区域
function loadSignArea(){
	var url = "ezfm/baseinfo/pub/query/dictionary/area/query";
	var simpleFormat = {valueField: 'pk_area',textField: 'area_name'};
	initDictionaryCombobox("queray_sign_area_id",url,null,simpleFormat);
}
function areaSignChange(newValue,oldValue){
	loadProject(newValue);
	loadUser(newValue);
}

//加载项目
function loadProject(areaId){
	var url = "ezfm/baseinfo/pub/query/dictionary/project/query";
	var param = {areaId:areaId};
	var simpleFormat = {valueField: 'pk_project',textField: 'project_name'};
	initDictionaryCombobox("queray_main_project_id",url,param,simpleFormat); 
}
function projectChange(newValue,oldValue){
	loadStation(newValue);
	loadUser(null,newValue);
}

//加载签到项目
function loadSignProject(areaId){
	var url = "ezfm/baseinfo/pub/query/dictionary/project/query";
	var param = {areaId:areaId};
	var simpleFormat = {valueField: 'pk_project',textField: 'project_name'};
	initDictionaryCombobox("queray_sign_project_id",url,param,simpleFormat); 
}
function projectSignChange(newValue,oldValue){
	loadStation(newValue);
	loadUser(null,newValue);
}

//加载部门
function loadDept(areaId,projectId){
	var url = "ezfm/baseinfo/pub/query/dictionary/dict/query";
	var param = {code:"jobDepartment",state:1};
	var simpleFormat = {valueField: 'pk_dict_',textField: 'dict_name_'};
	initDictionaryCombobox("queray_main_dept_id",url,param); 
}

//加载岗位
function loadStation(projectId){
	var url = "ezfm/baseinfo/pub/query/dictionary/station/query";
	var param = {projectId:projectId};
	var simpleFormat = {valueField: 'pk_station',textField: 'station_name'};
	initDictionaryCombobox("queray_main_station_id",url,param,simpleFormat); 
}
function stationChange(newValue,oldValue){
	loadUser(null,null,newValue);
}

//加载人员
function loadUser(areaId,projectId,stationId){
	var url = "ezfm/baseinfo/pub/query/dictionary/user/query";
	var param = {areaId:areaId,projectId:projectId,stationId:stationId};
	var simpleFormat = {valueField: 'pk_user',textField: 'user_name'};
	initDictionaryCombobox("queray_main_user_id",url,param,simpleFormat); 
}

//加载下拉框查询条件
function initLoadQueryMainData(){
	loadArea();
	loadProject();
	loadSignArea();
	loadSignProject();
	loadDept();
	loadUser();
}
var jsonData = {};
//页面加载
$(function(){	
	//下拉框
	initLoadQueryMainData();
	
	var queryUrl = "ezfm/performance/sign/query";
	initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
	
	
//	验证时间
	$('#rectification_startTime').datebox({
		onSelect: function(date){
			
			$('#rectification_EndTime').datebox().datebox('calendar').calendar({
				validator: function(dateAfter){
					
					var startDate = $("#rectification_startTime").datebox('getValue');
					if(startDate == ""){
						return true;
					}
					var s =startDate+' 00:00:00';
					s = s.replace(/-/g,"/");
					var dateBefore = new Date(s );
													
					return dateBefore <= dateAfter;

				}
			});

		}
	});
	
	
	
	
	// 查询
	$("#btn_mainquery").click(function() {
//		var d = new Date();
//		var starttime = $("#rectification_startTime").datebox('getValue');
//		var endtime = $("#rectification_EndTime").datebox('getValue');
//		if(!starttime){
//			var start_date = d.getFullYear() + "-" + (d.getMonth() + 1) + "-01";
//			$("#rectification_startTime").datebox('setValue', start_date);
//		}
//		if(!endtime){
//			var end_date = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
//			$("#rectification_EndTime").datebox('setValue', end_date);
//		}
		jsonData = $("#yjwy_query_mianform").serializeObject();
		initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
		//$('#yjwy_main_grid').datagrid('reload');
	});
	
	
	// 重置
	$("#btn_resetQuery").click(function() {
		
//	    $("#queray_main_area_id").combobox("setText","请选择");
	    $("#queray_main_area_id").combobox("setValue","");    //区域
	    $("#queray_main_project_id").combobox("setValue",""); //项目
	    $("#queray_main_station_id").combobox("setValue",""); //岗位
	    $("#queray_main_dept_id").combobox("setValue","");	  //部门
	    $("#queray_main_user_id").combobox("setValue","");    //人员
	    $("#rectification_startTime").datebox('setValue', '');   //提交开始时段
	    $("#rectification_EndTime").datebox('setValue', '');     //提交结束时段
	    $("#rectification_startTime1").spinner("setValue","");   //规定时间
	    $("#rectification_EndTime1").spinner("setValue","");
		jsonData = $("#yjwy_query_mianform").serializeObject();
		initLoadGridDataByCustom("yjwy_main_grid", queryUrl, jsonData);
	});
	
	
	//根据条件导出excel
	$("#export_mainbtn").click(function(){
		var url = "ezfm/performance/sign/export?param="+JSON.stringify(jsonData);
		window.location.href=url;
	});
	
	
	
	//加载百度地图
	$('#allmap').show().dialog({
		closed:true
	});
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(0,0);
	map.centerAndZoom(point,12);
	var myGeo = new BMap.Geocoder();
	myGeo.getPoint("", function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
		}
	}, "");

	
});

//格式化定位
function formatlocation(value, row, index) {
	var enable = null;
	if(row.sign_location != 0) {
		var xy=row.sign_location;
		enable = "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='selectlocation(\""+xy+"\")' >查看</a>";
	} else {
		enable="未定位";
	}
	return enable;
}

//格式化动作
function action(value, row, index) {
	var enable = null;
	if(row.sign_action == 1) {
		enable = "签到";
	} else {
		enable="签退";
	}
	return enable;
}

function selectlocation(xy){
/*	$('#yjwy_main_grid').datagrid('reload');*/
	$('#allmap').dialog({
		width: 600,
		height: 400,
		top:($(window).height() - 400) * 0.5,  
	    left:($(window).width() - 600) * 0.5,
		title:'查看地图',
		closed:false,
		modal: true,
		shadow: true,
		closable: true
	});
	// 百度地图API功能
    var map = new BMap.Map('allmap');
    //经纬度
	var result=xy.split(",");
	var x=result[0];
	var y=result[1];
    var poi = new BMap.Point(x,y);
	
	var marker = new BMap.Marker(poi);
	map.addOverlay(marker);  
	map.centerAndZoom(poi, 16);
    map.enableScrollWheelZoom();  
    var overlays = [];	
	var polygon = null;
	var overlaycomplete = function(e){
		var arr = new Array();
		var l = e.overlay.ia;
		for (var i=0; i<l.length; i++){
			var point = new BMap.Point(l[i].lng ,l[i].lat);
			arr.push(point);
		}
		polygon = new BMap.Polygon(arr);		
		overlays.push(e.overlay);
    };
	
}



