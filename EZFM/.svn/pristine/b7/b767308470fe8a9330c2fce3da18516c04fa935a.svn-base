/**
 * 获取参数
 * @param name
 * @returns {null}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


//datagrid加载
var initLoadGridData = function(datagrid_id,queryUrl,queryParam){
	$('#'+datagrid_id).datagrid({
		url:queryUrl,
		singleSelect:false,
		checkOnSelect:true,
		selectOnCheck:true,
		striped:true,
		rownumbers:true,
		loadMsg:"数据加载中...",
		pagination:true,
		pagePosition:"bottom",
		pageNumber:1,
		pageSize:25,
		pageList:[25,50,100],
		toolbar:'#'+datagrid_id+"_tb",
		fit:true,
		loader:function(param, success, error){
			param = param || {};
			var extraParam = queryParam;
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: queryUrl,
				data: { param: JSON.stringify(param)},
				success: function(respose){
					success({
						total: respose.total,
						rows: respose.data
					});
				}
			});
			return true;
		}
	});
	
	var pager_info = $('#'+datagrid_id).datagrid('getPager'); 
	$(pager_info).pagination({
    	displayMsg: '当前显示 第{from} - {to} 条     共{total}条记录',
    	onChangePageSize:function(size){
    		closewin_flg = false;
    	}
    });
}
//自定义datagrid加载
var initLoadGridDataByCustom = function(datagrid_id,queryUrl,jsonData,options){
	var defaultOptions = {
		url:queryUrl,
		singleSelect:false,
		checkOnSelect:true,
		selectOnCheck:true,
		striped:true,
		rownumbers:true,
		loadMsg:"数据加载中...",
		pagination:true,
		pagePosition:"bottom",
		pageNumber:1,
		pageSize:25,
		pageList:[25,50,100],
		toolbar:'#'+datagrid_id+"_tb",
		fit:true,
		loader:function(param, success, error){
			param = param || {};
			if(param.rows){
				jsonData.limit = param.rows;
				if(param.page>0){
					jsonData.start = (param.page - 1) * param.rows;
				}else{
					jsonData.start = 0;
				}
			}
			$.request.restPost({
				url: queryUrl,
				data: jsonData,
				success: function(respose){
					success({
						total: respose.total,
						rows: respose.data
					});
				}
			});
			
			return true;
		}
	};
	if(options){
		$.extend(defaultOptions,options);
	}
	$('#'+datagrid_id).datagrid(defaultOptions);
	var pager_info = $('#'+datagrid_id).datagrid('getPager'); 
    $(pager_info).pagination({
    	displayMsg: '当前显示 第{from} - {to} 条     共{total}条记录',
    	onChangePageSize:function(size){
    		closewin_flg = false;
    	}
    });
}

//添加按钮
var addMainBtnEvent = function(title,actionUrl,options,params,method){
	closewin_flg = false;
	showMainForm(title,actionUrl,options,params,method);
}

//修改按钮
var editMainBtnEvent = function(title,actionUrl,pk_name,options,params,method){
	closewin_flg = false;
	var updateRecods = $('#yjwy_main_grid').datagrid("getSelections");
	if(updateRecods.length!=1){
		$.messager.alert('提示','请您先选择一条记录修改!','warning');
		return;
	}
	actionUrl = actionUrl+"/"+updateRecods[0][pk_name];
	showMainForm(title,actionUrl,options,params,method);
}

//删除按钮
var deleteMainBtnEvent = function(actionUrl){
	closewin_flg = false;
	var deleteRecods = $('#yjwy_main_grid').datagrid("getSelections");
	
	if(deleteRecods.length<1){
		$.messager.alert('提示','请您先选择删除的记录!','warning');
		return;
	}
	$.messager.confirm('提示', '您确认删除选中的记录吗?', function(r){
		if (r){
			$.request.restPost({
				url: actionUrl,
				data: deleteRecods,
				success: function(rs){
					if(rs.success){
						$('#yjwy_main_grid').datagrid('reload');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$.messager.alert("提示",rs.message,"warning");
					}
				}
			});
		}
	});
}

//主表单保存
var saveMainForm = function(actionUrl,formJosn){
	closewin_flg = false;
	$.request.restPost({
		url: actionUrl,
		data:formJosn,
		success: function(result){
			if(result.success){
				$("#szw_dlg_mainform_id").dialog("close");
	    		$('#yjwy_main_grid').datagrid('reload');
				$.messager.show({
					title:'提示',
					msg:'数据已保存',
					timeout:2000,
					showType:'slide'
				});
	    	}else{
	    		$.messager.alert("提示",result.message);
	    	}
		}
	}); 
}
//SONPAGE EVENT 显示子页面
var showSonPageEvent = function(title,actionUrl,options,params,method){
	closewin_flg = false;
	showSonPageInfo(title,actionUrl,options,params,method);
}

//初始化下拉框 FOR字典
var initDictionaryCombobox = function(combId,combUrl,param,simpleFormat,defaultValue,isDefaultCombo){
	var combId = "#"+combId;
	param=param||{};
	simpleFormat=simpleFormat||{valueField: 'dict_code',textField: 'dict_name'};
	isDefaultCombo=isDefaultCombo===false?false:true;
	var defaultComb = {};
	var keyfield;
	var valuefield
	var keyfield_flag = false;
	for(var key in simpleFormat){
		if(!keyfield_flag){
			keyfield = simpleFormat[key];
			keyfield_flag = true;
		}else{
			valuefield = simpleFormat[key];
			keyfield_flag = false;
			break;
		}
	}
	defaultComb[keyfield] = "";
	defaultComb[valuefield]= "请选择";
	$(combId).combobox(simpleFormat);
	$.ajax({
		url: combUrl,
        type: "get",
        data: param,
        dataType: "json",
        async:false,
        success:function(result){
        	if(result.success){
        		if(!result.data){
        			result.data = [];
        		}
        		//默认加载请选择
        		if(isDefaultCombo){
        			result.data.unshift(defaultComb);
        		}
				$(combId).combobox({data:result.data});
				if(defaultValue){
					$(combId).combobox('select',defaultValue);
				}
				//如果不设置默认值 default=false;
				if(false === defaultValue){
					$(combId).combobox("clear");
				}
        	}
        },
        error:function(request, textStatus, errorThrown){
        	//$(combId).combobox("loadData",[defaultComb]);
        }
	});
}
//加载项目下拉框数据(自己所属项目)
var yjwyLoadProjectCombobox = function(comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/project/query";
	var param = param||{};
	var simpleFormat = {valueField: 'pk_project',textField: 'project_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo);
}
//加载项目下拉框数据(所有项目)
var yjwyLoadAllProjectCombobox = function(comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/all/project/query";
	var param = param||{};
	var simpleFormat = {valueField: 'pk_project',textField: 'project_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo);
}

//加载区域下拉框数据(自己所属区域)
var yjwyLoadAreaCombobox = function (comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/area/query";
	var simpleFormat = {valueField: 'pk_area',textField: 'area_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo);
}

//加载区域下拉框数据(所有区域)
var yjwyLoadAllAreaCombobox = function (comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/all/area/query";
	var simpleFormat = {valueField: 'pk_area',textField: 'area_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo);
}

//加载岗位下拉框数据
var yjwyLoadStationCombobox = function(comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/station/query";
	var param = param||{};
	var simpleFormat = {valueField: 'pk_station',textField: 'station_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo); 
}
//加载人员下拉框数据
var yjwyLoadUserCombobox = function(comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/user/query";
	var param = param||{};
	var simpleFormat = {valueField: 'pk_user',textField: 'user_name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo); 
}
//加载城市下拉框数据
var yjwyLoadCityCombobox = function (comboId,param,defaultValue,isDefaultCombo){
	var url = "ezfm/baseinfo/pub/query/dictionary/city/query";
	var simpleFormat = {valueField: 'id',textField: 'name'};
	initDictionaryCombobox(comboId,url,param,simpleFormat,defaultValue,isDefaultCombo);
}