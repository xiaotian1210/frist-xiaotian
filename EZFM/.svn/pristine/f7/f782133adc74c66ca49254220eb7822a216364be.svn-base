//操作符，save 和 update;
var operation;

//打开新增窗口
function openSaveDialog() {
	close_flag=false;
	operation="save";
	$("#dialog_areaPersonnel").dialog({
		title: '新增人员',
		width: 480,
		height: 390,
		modal: true,
		shadow: true,
		closable: true,
		cache: false,
		buttons: "#toolbar_areaPersonnel",
	});
	closewin_flg = false;
	var url = "ezfm/worktask/areapersonnel/queryDict";
	queryData("major_one",url,"dict_code","dict_name","engineer_specialtys");
	queryData("major_two",url,"dict_code","dict_name","engineer_specialtys");
	queryData("major_three",url,"dict_code","dict_name","engineer_specialtys");
	queryData("major_four",url,"dict_code","dict_name","engineer_specialtys");
	queryData("major_five",url,"dict_code","dict_name","engineer_specialtys");
	queryData("certificate",url,"dict_code","dict_name","engineer_certificate");
	// //绑定事件
	// $("#user_account").textbox({
	// 	onChange:userAccountChange,
	// });
    $('#user_account').combobox({
        url:'ezfm/worktask/areapersonnel/queryUser?userData=',
        valueField:'text',
        textField:'text',
        onChange:function(values,o)
        {
        	initQueryCombox(values)
            // if(values!="")
            // {
				// var url='ezfm/worktask/areapersonnel/queryUser?userData='+encodeURIComponent(encodeURIComponent(values.trim()));
            //     $("#user_account").combobox("reload",url);
            // }
        },
        
		onSelect:function (record) {
			$("#user_id").val(record.id)
			$("#user_aa").val(record.text)
        }
       
    })
};

function initQueryCombox(value) {
	
    $('#user_account').combobox({
        url:'ezfm/worktask/areapersonnel/queryUser?userData='+value,
        valueField:'text',
        textField:'text',
        onChange:function(values,o)
        {
                var url='ezfm/worktask/areapersonnel/queryUser?userData='+encodeURIComponent(encodeURIComponent(values.trim()));
                $("#user_account").combobox("reload",url);
        },
        onSelect:function (record) {
            $("#user_id").val(record.id)
            $("#user_aa").val(record.text)
        }
    })
    $('#user_account').combobox("setValue",value);

}


function userAccountChange(newValue, oldValue){
	var url = "ezfm/worktask/areapersonnel/queryUser";
	queryUserData(url,newValue);
}

function submitFormAreaPersonnel(datagridName, dialogName, formName, urlName){
	submitFormAreaPersonnels(datagridName, dialogName, formName, urlName+operation);
}


/**
 * ajax查询用户信息
 * @param {Object} comboboxName
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 * @param {Object} value
 */
function queryUserData(urlName,user) {
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		data:{userData:user},
		url: urlName,
		success: function(json) {
			if (json.data!=null&&json.data.length>0) {
				var data = json.data[0];
				$("#user_name_disabled").textbox('setValue',data.user_name);
				$("#user_id").val(data.pk_user);
			}else{
				$.messager.alert('提示', '没有此账号用户！', 'info');
				$("#user_name_disabled").textbox('setValue',"");
				$("#user_id").val("");
				return;
			}
		}
	});
}
/**
 * ajax请求下拉框数据
 * @param {Object} comboboxName
 * @param {Object} urlName
 * @param {Object} valueField
 * @param {Object} textField
 * @param {Object} value
 */
function queryData(comboboxName, urlName, valueField, textField,dictCode,code) {
	$.ajax({
		async : false,  
		cache:false,  
		type: 'POST',  
		dataType : "json",
		data:{dictCode:dictCode,code:code},
		url: urlName,
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
 * datagrid格式化操作按钮
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function formatDetails(value, row, index) {
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='onclickEdit(\""+index+"\")' >编辑</a>"+
	"<a href='javascript:void(0);' class='easyui-linkbutton' onclick='deleteData(\""+index+"\")' >删除</a>"
	return operation ;
};


//修改
function onclickEdit(index){
	var row;
	if(index != null) {
		$("#datagrid_areaPersonnelList").datagrid("selectRow", index);
		row = $("#datagrid_areaPersonnelList").datagrid("getSelections");
		close_flag=false;
		operation="update";
		// $("#user_name_disabled").textbox('setValue',row[0].user_name);

        $('#user_account').combobox({
            url:'ezfm/worktask/areapersonnel/queryUser?userData='+row[0].user_name,
            valueField:'text',
            textField:'text',
            onChange:function(values,o)
            {
                    var url='ezfm/worktask/areapersonnel/queryUser?userData='+encodeURIComponent(encodeURIComponent(values.trim()));
                    $("#user_account").combobox("reload",url);
            },
            onSelect:function (record) {
                $("#user_id").val(record.id)
            }
        })

		$("#user_account").combobox("setValue",row[0].user_name);
        $("#user_aa").attr("value",row[0].user_name);
//        var a=$("#user_aa").val();
		$("#dialog_areaPersonnel").dialog({
			title: '编辑人员',
			width: 480,
			height: 390,
			modal: true,
			shadow: true,
			closable: true,
			cache: false,
			buttons: "#toolbar_areaPersonnel",
		});
		closewin_flg = false;
		var url = "ezfm/worktask/areapersonnel/queryDict";
		queryData("major_one",url,"dict_code","dict_name","engineer_specialtys");
		queryData("major_two",url,"dict_code","dict_name","engineer_specialtys");
		queryData("major_three",url,"dict_code","dict_name","engineer_specialtys");
		queryData("major_four",url,"dict_code","dict_name","engineer_specialtys");
		queryData("major_five",url,"dict_code","dict_name","engineer_specialtys");
		queryData("certificate",url,"dict_code","dict_name","engineer_certificate");
//		console.log(a);
//		console.log(row[0]);
		$("#form_areaPersonnel").form('load',row[0]);
	}
}

//删除
function deleteData(index) {
	closewin_flg = false;
	var row;
	if(index != null) {
		$("#datagrid_areaPersonnelList").datagrid("selectRow", index);
		row = $("#datagrid_areaPersonnelList").datagrid("getSelections");
		$.messager.confirm("确认", "确定要删除吗？", function(r) {
			if(r) {
				$.request.restPost({
					url: "ezfm/worktask/areapersonnel/delete",
					data: row,
					success: function(rs){
						$('#datagrid_areaPersonnelList').datagrid('reload');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}
				});
			}
		});
	}	
};



/**
 * 查询提交
 */
function querySubmit(){
	var user_name = $("#user_name").val();
	//获取下拉框参数
	$("#datagrid_areaPersonnelList").datagrid("load",{
		andList: [{
			key: "user_name",
			operator: 'like',
			value: user_name
		}]
	});
}
/**
 * 提交表单
 * @param {Object} datagridName
 * @param {Object} dialogName
 * @param {Object} formName
 * @param {Object} urlName
 */
function submitFormAreaPersonnels(datagridName, dialogName, formName, urlName) {
	var operation = operation;
	// var user_name_disabled = $("#user_name_disabled").textbox('getText');
	var user_id = $("#user_id").val();
	// var user_name = $("#user_name").val();
	var form_data = $("#form_areaPersonnel").serializeObject();
	var user_name= form_data.user_name;
	var user_aa=form_data.user_aa;
//	console.log(form_data);
	// var user_account = $("#user_account").textbox('getText');
	if(user_name != user_aa){
		$.messager.alert('提示', '此用户不存在,请选择用户！', 'info');
		return;
	}
	if (user_id=="") {
		$.messager.alert('提示', '请选择用户！', 'info');
		return;
	}
	
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
				$("#user_account").textbox('setText','');
				$("#user_name_disabled").textbox('setText','');
				closeDialogProblem(dialogName);
				$("#" + datagridName).datagrid('reload');
				$.messager.show({
					title:'提示',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});

			}
			else if(json.message=="该用户已添加！"){
				$.messager.alert('错误', '该用户已添加！', 'error');
			}else {
				$.messager.alert('错误', '操作失败!请稍后再试！', 'error');
			}
		}
	});
};