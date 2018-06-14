
/**
 * 格式化字段：是否报废
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatReject(value,row,index){
	if(value == 1){
		return "否";
	}else{
		return "是";
	}
}

/**
 * 格式化字段：设备状态
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatStatus(value,row,index){
	if(value == 1){
		return "正常";
	}else{
		return "故障";
	}
}
function bodyload(){
	//初始化设备列表
    
	datagridInit("datagrid_list", "ezfm/device/list/query", parent.$('#centerTabs').tabs('getSelected').panel('options').headerTitle, "toolbar_datagrid_list", true, true);
}
/**
 * 查询提交
 */
function querySubmit(){
	var eqKey = $("#textbox_eqKey").val();
	//获取下拉框参数
	var area_name = $("#combobox_area").combobox("getText");
	var project_name = $("#combobox_project").combobox("getText");
	var rm_id = $("#combobox_room").combobox("getValue");
	var active= $("#active").combobox("getValue");
	$("#datagrid_list").datagrid("load",{
		eqKey:eqKey,
		area_name:area_name,
		project_name:project_name,
		rm_id:rm_id,
		active:active
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
    $("#combobox_room").combobox("setText","机房选择");
    $("#combobox_room").combobox("setValue","default");
    $("#textbox_eqKey").textbox('setText','');
    $("#textbox_eqKey").textbox('setValue','');
    $("#active").combobox("setText","设备正常");
    $("#active").combobox("setValue","1");
    var eqKey = $("#textbox_eqKey").val();
	var area_name = $("#combobox_area").combobox("getText");
	var project_name = $("#combobox_project").combobox("getText");
	var rm_id = $("#combobox_room").combobox("getValue");
	var active= $("#active").combobox("getValue");

	$("#datagrid_list").datagrid("load",{
		eqKey:eqKey,
		area_name:area_name,
		project_name:project_name,
		rm_id:rm_id,
		active:active
	});
}



function updateData(datagridName, index, titleName, dialogName, formName, toolbarName) {
	
	var recList = $("#" + datagridName).datagrid("getSelections");
	var active=recList[0].active;
	if(active == 0){
		$.messager.alert("错误", "报废设备不能被修改！", "error");
		return;
	}
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

function beforOpenFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName) {
	var pk_area = "0";
	var site_id = "0";
	var pk_project = "0";
	var rm_id = "0";
	var csi_id = "0";
	var eq_id = "0";
	// 判断是否编辑
	if (index != null) {
		$("#" + datagridName).datagrid("selectRow", index);
		var rec = $("#" + datagridName).datagrid("getSelected");
		site_id = rec.site_id;
		csi_id = rec.csi_id;
		rm_id = rec.rm_id;
		csi_name = rec.csi_name;
	}
	//初始化分类下拉列表
	queryData("form_combobox_csi","ezfm/device/query/newcsi?csi_id="+csi_id,"pk_dict","dict_name");
	
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
	var form_data = $("#" + formName).serializeObject();
	/** 设备名称 */
	var name = $("#form_textbox_name").val();

	var fm_code = $("#form_textbox_code").val();
	/** 分类编码 */
	var csi_id = $("#form_combobox_csi").combobox("getValue");
	/** 项目代码 */
//	var site_id = $("#form_combobox_project").combobox("getValue");
	/** 设备状态 */
	var status = $("#form_combobox_status").combobox("getValue");
	/** 房间编号（位置编号） */
//	var rm_id = $("#form_combobox_room").combobox("getValue");
	/** 报废 */
	var active = $("#form_combobox_active").combobox("getValue");

	var rm_name = $("#form_textbox_room").textbox('getValue');

	form_data.name = name;
	form_data.csi_id = csi_id;
//	form_data.site_id = site_id;
	form_data.status = status;
//	form_data.rm_id = rm_name;
	form_data.active = active;
	var submit_method = $("input[name='submit_method']").val();
	if(submit_method == 'save'){
		var flag = checkCsi(fm_code,form_data.eq_id);
		if(flag==false){
			$.messager.alert("提示",'此设备编码已经存在于数据库，请您更换设备编码！');
			return;
		}
	}
	$("#submit_form_device_button").linkbutton('disable');
	submitForm(datagridName, dialogName, formName, urlName, form_data);
	$("#submit_form_device_button").linkbutton('enable');
};

//下载模板按钮事件
function download_mainbtn_device_class(){
	window.location.href="ezfm/device/list/imptemplete/download";
};

//导入按钮事件
function import_mainbtn_device_class(){
	$("#uplaod_excel_dialog_device_class").dialog("open");
};
//Excel文件导入
function submitImportExcelDeviceClass(){
  $.messager.progress();
	$('#importExcelFormId').form('submit', {
		url: "ezfm/device/list/import/excel",
		success: function(result){
			result = eval('('+result+')');
			$.messager.progress('close');// 如果提交成功则隐藏进度条
			if(result.success){
				$.messager.alert("提示",result.message,"info");
				$("#uplaod_excel_dialog_device_class").dialog("close");
			}else{
				$.messager.alert('提示','数据导入失败,请检查导入数据是否符合要求','warning');
			}
		}
	});

};

//ezfm/device/fmdata_eq/query
function addRoomPlace(){
    var sonpageUrl = "ezfm/device/list/place/room";
	showSonPageEvent("机房（双击选中）",sonpageUrl,{width:600,height:450,id:'roomId'});
};

function addSystemId(){
    // var sonpageUrl = "ezfm/device/fmdata_eq/query";
    var sonpageUrl = "ezfm/device/list/place/sys";

    var param = {
        metas : [ 'yjwy_device_fmdata_eq_sys' ],
    }
    var params = {param : JSON.stringify(param),
        isWindow:'true'
    }
    showSonPageEvent("所属系统（双击选中）",sonpageUrl,{width:600,height:450,id:'sys_id'});
};
function checkCsi(fm_code,eq_id){
	var finalflag;
	$.ajax({
		type: "post",
		url: 'ezfm/device/list/queryFmeq',
		contentType: 'application/json',
		data:JSON.stringify({"fm_code": fm_code,"eq_id":eq_id}),
		async: false,
		dataType: "json",
		success: function(json) {
			var flag = json.success;
			if(flag==true)
				finalflag = true;
			else
				finalflag = false;
		}
	});
	return finalflag;
}

function printPage(){

	var head = "<!DOCTYPE html>"+

        "<html lang='en'>"+
        "<head>"+
        "<meta charset='UTF-8'>"+
        "<base href='${basePath?if_exists}'>"+
        "<title>二维码打印</title>"+
		"<script type='text/javascript' src="+contextPath+"'/resources/lib/js/jquery-1.11.1.min.js'></script>"+
		"<script type='text/javascript' src="+contextPath+"'/resources/ezfm/qrcode/qrcode.min.js'></script>"+
        "<style>"+
        "table{"+
        "text-align: center;"+
    "}"+
    "table p{"+
        "margin: 10px 0;"+
    "}"+

    "td.fir{"+
        "padding: 4px 36px 4px 0;"+
    "}"+
    "td{"+
        "padding: 4px 0 4px 36px;"+
    "}"+
	"</style>"+

    "</head>"+
    "<body>";
    var end =  "</body>"+
       " <script>"+
        " var arr = $('.qr-imgs');"+
        "  for(var i = 0 ;i < arr.length;i+=1){"+
        "  var id = $(arr[i]).attr('id');"+
        " var content = window.location.origin+"+contextPath+"'/ezfm/mobile/'+id;"+
        " var qrcode = new QRCode(id, {"+
        "  text: content,"+
        "   width: 180,"+
        "    height: 180,"+
        "    colorDark : '#000000',"+
        "    colorLight : '#ffffff',"+
        "    correctLevel : QRCode.CorrectLevel.H"+
        " });"+
        " }"+
		 " window.print();"+
        " </script>"+
    "</html>";


	var data = $("#datagrid_list").datagrid("getRows");
	var content = "";
	var tabLen = Math.round(data.length/6);
	for(var i = 0;i < tabLen;i+=1){
        var table;
        if(i == tabLen-1){
             table ="<table >";
		}else{
             table ="<table style='page-break-after:always'>";
		}

		for(var j = 0;j<6;j+=1){

			if(i*6+j+1>data.length){
                table+='</tr>';
                break;
			}
			var rm_name = data[i*6+j].rm_name;
			rm_name  = rm_name.substr(rm_name.indexOf("|")+1)
			if(j%2==0){
				table+=
					"<tr>"+
						"<td class='fir'>"+
								//getQrcode(data[i*6+j].eq_id)+
							"<div class='qr-imgs' id='"+data[i*6+j].eq_id+"' style='height: 180px;width: 180px; margin:0 auto;'></div>"+
							"<p>设备："+data[i*6+j].eq_name+"</p>"+
							"<p>位置："+rm_name+"</p>"+
                		"</td>";
			}else{
				table+=
						"<td>"+
                        	"<div class='qr-imgs'id='"+data[i*6+j].eq_id+"' style='height: 180px;width: 180px; margin:0 auto;'></div>"+
                        "<p>设备："+data[i*6+j].eq_name+"</p>"+
                        "<p>位置："+rm_name+"</p>"+
						"</td>"+
                	"</tr>";
			}
		}
		table+="</table>";
		content+=table;
	}
	content = head+content+end;
    var pwin=window.open("Print.htm"); //如果是本地测试，需要先新建Print.htm，如果是在域中使用，则不需要
    pwin.document.write(content);
    pwin.document.close();                   //这句很重要，没有就无法实现
   // pwin.print();

}

function getQrcode(id) {
    var content = window.location.origin+contextPath+"/ezfm/mobile/"+id;
    $("#qrcode").empty()
    var qrcode = new QRCode('qrcode', {
        text: content,
        width: 200,
        height: 200,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });
    var str = $("#qrcode");
    return str ;
}


function showQR() {




    $("#qrcode").empty()
    var recList = $("#datagrid_list").datagrid("getSelections");
    if(recList.length>1||recList.length == 0) {
        $.messager.alert("错误", "请选择一条记录", "error");
        return;
    }
    var data = recList[0];
	var content = window.location.origin+contextPath+"/ezfm/mobile/"+data.eq_id;
    var qrcode = new QRCode('qrcode', {
        text: content,
        width: 256,
        height: 256,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });


    $("#show-qr-code").dialog({title:data.eq_name,
		closed:false,
		buttons:[{
        text:'打印',
        iconCls:'icon-print',
        handler:function(){
        	myPrint($("#qrcode")[0])
		}
    }]})
}

function myPrint(obj){
    var newWindow=window.open("打印窗口","_blank");
    var docStr = obj.innerHTML;
    newWindow.document.write(docStr);
    newWindow.document.close();
    newWindow.print();
    newWindow.close();
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

$(function () {
    var metas = [ 'yjwy_dict' ];
    var p_station_level = [ {
        key : 'dict_parent_',
        operator : 'eq',
        value : 'jobDepartment'
    } ];
    dictInitAjax($('.station'), {
        metas : metas,
        'andList' : p_station_level
    });
})


