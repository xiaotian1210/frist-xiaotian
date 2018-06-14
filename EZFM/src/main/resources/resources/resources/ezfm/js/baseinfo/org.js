/**
 * 如果组织结构再变需求，业务逻辑代码怕是要大改，再次提示下
 */
var renderUsable = function(value, row, index){
	return value == '1' ? '<div style="color:green">启用</div>' : '<div style="color:red">停用</div>';
}
var renderOrgType = function(value){
	switch(value){
		case '0':
			return '组织'; 
		case '1':
			return '区域'; 
		case '2':
			return '项目';
		case '3':
			return '部门';
		default:
			return value;
	}
}
function cancel_all() {
    var treeObj = $.fn.zTree.getZTreeObj("orgtree");
    treeObj.cancelSelectedNode();
    $("#org-propertygrid").propertygrid('loadData',
        [{name:'组织编码',value:''},{name:'组织名称',value:''},{name:'组织类型',value:''},{name:'关联区域',value:''},{name:'关联项目',value:''},{name:'组织描述',value:''}]);
    $('#userorggrid').datagrid('loadData',{'total':0,'rows':[]});
}
//修改时org必填 isAdd为false
function formFiledShowHide(orgType,org,isAdd){
	if("0"==orgType){
		$("#tr_org_area_id").hide();
		$("#tr_org_project_id").hide();
		$("#org_area").combobox("clear");
		$("#org_project").combobox("clear");
		if(true ===isAdd){
			$("#org_type").combobox("readonly",true);
		}
		if(false ===isAdd){
			//如果跟组织下的组织可以编辑，将注释放开，之所有不放开，是以为第二层组织下含有区域的话，修改第二层组织，这是个BUG
			//if("root"==org.pk_parent){
				$("#org_type").combobox("readonly",true);
			//}
		}
	}else if('1' == orgType){
		$("#tr_org_area_id").show();
		$("#tr_org_project_id").hide();
		$("#org_area").combobox("clear");
		$("#org_project").combobox("clear");
		if(false ===isAdd){
			$("#org_area").combobox("select",org.org_area);
			$("#org_type").combobox("readonly",true);
		}
		if(true ===isAdd){
			$("#org_type").combobox("readonly",false);
		}
		$("#org_area").combobox("readonly",false);
	}else if('2' == orgType){
		$("#tr_org_project_id").show();
		$("#tr_org_area_id").show();
		$("#org_area").combobox("clear");
		$("#org_project").combobox("clear");
		initProject();
		if(true ===isAdd){
			$("#org_area").combobox("select",org.org_area);
		}
		if(false ===isAdd){
			$("#org_area").combobox("select",org.org_area);
			$("#org_project").combobox("select",org.org_project);
		}
		$("#org_type").combobox("readonly",true);
		$("#org_area").combobox("readonly",true);
		$("#org_project").combobox("readonly",false);
	}else{
		$("#tr_org_area_id").show();
		$("#tr_org_project_id").show();
		$("#org_area").combobox("clear");
		$("#org_project").combobox("clear");
		$("#org_area").combobox("select",org.org_area);
		$("#org_project").combobox("select",org.org_project);
		$("#org_type").combobox("readonly",true);
		$("#org_area").combobox("readonly",true);
		$("#org_project").combobox("readonly",true);
	}
}

//根据父ID加载下一级组织类型，如果不限制下一级，将此方法干掉，并审理formFiledShowHide方法
//修改时org必填 isAdd为false
function loadOrgType(orgType,org,isAdd){
	if(isAdd){
		if(orgType){
			orgType = eval(orgType)+1;
			if(orgType>3){
				orgType=3;
			}
			orgType = orgType+"";
		}else{
			orgType = 0;
		}
	}
	if("0"==orgType){
		var data = [{id:"0",text:"组织"}];
		if((org && "root"!=org.pk_parent) || null ===isAdd){
			data = [{id:"0",text:"组织"},{id:"1",text:"区域"}];
		}
		
		$("#org_type").combobox("loadData",data);
		$("#org_type").combobox("select",orgType);
	}else if("1"==orgType){
		var data = [{id:"0",text:"组织"},{id:"1",text:"区域"}];
		$("#org_type").combobox("loadData",data);
		$("#org_type").combobox("select",orgType);
	}else if("2"==orgType){
		var data = [{id:"2",text:"项目"}];
		$("#org_type").combobox("loadData",data);
		$("#org_type").combobox("select",orgType);
	}else if("3"==orgType){
		var data = [{id:"3",text:"部门"}];
		$("#org_type").combobox("loadData",data);
		$("#org_type").combobox("select",orgType);
	}else{
		$("#org_type").combobox("loadData",[]);
	}
	formFiledShowHide(orgType,org,isAdd);
}
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	view:{
		showLine: true,
		selectedMulti: false,
		txtSelectedEnable: false,
		showIcon: false
	},
	callback: {
		onClick: zTreeOnClick
	}
};
function zTreeOnClick(event, id, node){
	var attributes = node.attributes;
	$("#org-propertygrid").propertygrid('loadData',[]);
	$("#org-propertygrid").propertygrid('loadData',[{
			name:'组织编码',value:attributes.org_code
		},{
			name:'组织名称',value:attributes.org_name
		},{
			name:'组织类型',value:renderOrgType(attributes.org_type)
		},{
			name:'关联区域',value:attributes.area_name
		},{
			name:'关联项目',value:attributes.project_name
		},{
			name:'组织描述',value:attributes.memo
		}]
	);
	var pk_org = node.id;
	$.request.httpPost({
		url: 'ezfm/baseinfo/org/user',
		data: { pk_org: pk_org},
		success: function(rs){
			if(rs.success){
				var data = rs.data;
				if(data){
					var total = data.length;
					$('#userorggrid').datagrid('loadData',{'total':total,'rows':data});
				}else{
					$('#userorggrid').datagrid('loadData',{'total':0,'rows':[]});
				}				
			}
		},
		failure: function(rs){
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
}

function initArea(){
	/*var param = {
		metas: ['yjwy_area'],
		andList:[{
			key: 'pk_crop_',
			operator: 'eq',
			value: pc
		}]
	};*/
	yjwyLoadAllAreaCombobox("org_area",null,false);
}

function areaChange(newValue,oldValue){
	yjwyLoadAllProjectCombobox("org_project",{areaId:newValue},false);
}
function initProject(){
	/*var param = {
		metas: ['yjwy_project'],
		andList:[{
			key: 'pk_crop_',
			operator: 'eq',
			value: pc
		}]
	};*/
	yjwyLoadProjectCombobox("org_project",null,false);
}
function projectChange(newValue,oldValue){
	if(!newValue){
		return;
	}
	var data = $("#org_project").combobox("options").data;
	for(var i=0; i<data.length; i++){
		if(newValue == data[i].pk_project){
			$("#org_area").combobox("select",data[i].pk_area);
			$("#org_project").combobox("select",newValue);
			break;
		}
	}
	
}
var zNodes = function(){
	var result = null;
	$.request.httpPost({
		url: 'ezfm/baseinfo/org/query/all',
		data:{crop:pc},
		async:false,
		success: function(rs){
			if(rs.success){
				result = rs.data;
			}
		},
		failure: function(rs){
			$.messager.alert('提示', rs.message || '查询失败');
		}
	});
	return result; 
}

var treeObj=null;
$(function(){
	$.fn.zTree.init($("#orgtree"), setting, zNodes());
	treeObj = $.fn.zTree.getZTreeObj("orgtree");
	treeObj.expandAll(false);
	$("#org-propertygrid").propertygrid('loadData',
		[{name:'组织编码',value:''},{name:'组织名称',value:''},{name:'组织类型',value:''},{name:'关联区域',value:''},{name:'关联项目',value:''},{name:'组织描述',value:''}]);
	
	var orgdlg = $('#orgdlg').dialog({
		width: 550,
		height:340,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				if($('#form').form('validate')){
					var nodes = treeObj.getSelectedNodes();
					var json = $('#form').serializeJson();
					$.request.restPost({
						url: json.pk_org ? 'ezfm/baseinfo/org/update':'ezfm/baseinfo/org/save',
						data: json,
						success: function(result){
							if(result.success){
								if(!json.pk_org){
									var data = result.data[0];
									var node = {id:data.pk_org,pId:data.pk_parent,name:data.org_name,attributes:data};
									treeObj.addNodes(nodes[0], node);
									
									$("#org-propertygrid").propertygrid('loadData',[]);
                                    $("#org-propertygrid").propertygrid('loadData',[{
                                            name:'组织编码',value:data.org_code
                                        },{
                                            name:'组织名称',value:data.org_name
                                        },{
                                            name:'组织类型',value:renderOrgType(data.org_type)
                                        },{
                                            name:'关联区域',value:data.area_name
                                        },{
                                            name:'关联项目',value:data.project_name
                                        },{
                                            name:'组织描述',value:data.memo
                                        }]
                                    );

								}else{
									var data = result.data[0];
									nodes[0].name = data.org_name;
									nodes[0].attributes = data;
									treeObj.updateNode(nodes[0]);
                                    $("#org-propertygrid").propertygrid('loadData',[]);
                                    $("#org-propertygrid").propertygrid('loadData',[{
                                            name:'组织编码',value:data.org_code
                                        },{
                                            name:'组织名称',value:data.org_name
                                        },{
                                            name:'组织类型',value:renderOrgType(data.org_type)
                                        },{
                                            name:'关联区域',value:data.area_name
                                        },{
                                            name:'关联项目',value:data.project_name
                                        },{
                                            name:'组织描述',value:data.memo
                                        }]
                                    );
								}
								orgdlg.window('close');
//								$.fn.zTree.init($("#orgtree"), setting, zNodes());
//								treeObj.expandAll(true);  //展开所有节点
								
								$.messager.show({
									title:'提示',
									msg:'数据已保存',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert("提示",result.message,"warning");
							}
						},
						failure: function(rs){
							$.messager.alert('提示', rs.message || '保存失败');
						}
					})
				}
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r){
					if(r){
						$('#form').form('clear');
						orgdlg.window('close');
					}
				});
			}
		}]
	});
	
	$('#org_type').combobox({
		//height:25,
		width:'auto',
		onChange:function(nv,ov){
			if("0"==nv || "1"==nv){
				loadOrgType(nv,null,null);
			}
		}
	});
	$('#org_area').combobox({
		//height:25,
		width:'auto',
		loadData:initArea()
	});
	$('#btnNew').click(function(){
		$('#org_project').combobox({
			//height:25,
			width:'auto',
			loadData:initProject()
		});
		closewin_flg = false;
		$('#form').form('clear');
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length<=0){
			$('#form').form('load', {pk_parent: 'root',pk_crop:pc});
			loadOrgType(null,null,true);
		}else{
			if("3"==nodes[0].attributes.org_type){
				$.messager.alert("提示","部门下不可再添加组织结构");
				return;
			}
			$('#form').form('load', {pk_parent: nodes[0].attributes.pk_org,pk_crop:pc});
			loadOrgType(nodes[0].attributes.org_type+"",nodes[0].attributes,true);
		}
		$('#orgdlg').window({
			title:'新增组织',
			closed:false
		});
		//如果干掉loadOrgType请将此放开
		//formFiledShowHide();
	});	
	
	$('#btnEdit').click(function(){
		$('#org_project').combobox({
			//height:25,
			width:'auto',
			loadData:initProject()
		});
		closewin_flg = false;
		$('#form').form('clear');
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length<=0){
			$.messager.alert('提示','请选择待修改的组织节点');
		}
		$('#form').form('load', nodes[0].attributes);
		$('#orgdlg').window({
			title:'组织编辑',
			closed:false
		});
		loadOrgType(nodes[0].attributes.org_type,nodes[0].attributes,false);
		//如果干掉loadOrgType请将此放开
		//formFiledShowHide(nodes[0].attributes.org_type+"");
	});	
	
	$('#btnDelete').click(function(){
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length<=0){
			$.messager.alert('提示', '请选择待删除节点');
			return;
		}
		$.messager.confirm('提示', '是否确认删除选中组织，请谨慎操作', function(r){
			if(r){	
				$.request.restPost({
					url: 'ezfm/baseinfo/org/delete',
					data: [nodes[0].attributes],
					success: function(result){
						if(result.success){
							$.messager.show({
								title:'提示',
								msg:'数据已删除',
								timeout:2000,
								showType:'slide'
							});
							treeObj.removeNode(nodes[0]);
							$("#org-propertygrid").propertygrid('loadData',[{
                                name:'组织编码',value:''
                            },{
                                name:'组织名称',value:''
                            },{
                                name:'组织类型',value:''
                            },{
                                name:'关联区域',value:''
                            },{
                                name:'关联项目',value:''
                            },{
                                name:'组织描述',value:''
                            }]
                            );																					
							$('#form').form('clear');
						}else{
							$.messager.alert("提示",result.message,"warning");
						}
					},
					failure: function(rs){
						$.messager.alert('提示', rs.message || '删除失败');
					}
				});
			}
		});
	});
});
