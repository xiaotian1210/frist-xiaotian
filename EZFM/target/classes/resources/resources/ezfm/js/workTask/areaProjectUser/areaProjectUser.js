//定义项目tree变量
var proZTreeObj;
var proZNodes;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var proSetting = {
	data : {
		simpleData : {
			enable : true,
		}
	},
	callback : {
		onClick : onNodeClick,
		onExpand: zTreeOnExpand
	},
	view : {
		selectedMulti : false
	}
};

//项目tree查询展示
function showprojectInfotTree(pk_crop) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		data : {
			class_id : '',
			pk_crop  : pk_crop
		},
		url : "ezfm/nexus/proandclass/query",//请求的action路径  
		error : function() {//请求失败处理函数  
			alert('请求失败');
		},
		success : function(data) { //请求成功后处理函数。
			proZNodes = data.rows; //把后台封装好的简单Json格式赋给treeNodes  
		}
	});
	proZTreeObj = $.fn.zTree.init($("#projectInfo_tree"), proSetting,
			proZNodes);
}
//打开新增窗口
/**
 * datagrid打开人员列表
 * @param {Object} index
 */
function userList() {
	var pNode = proZTreeObj.getSelectedNodes();
	var project_id;
	if (pNode.length > 0 && pNode[0].type == 2) {
		project_id = pNode[0].id;
	} else {
		$.messager.alert("提示", "请选择您要关联的项目！", "info");
		return;
	}
	var addProjectUrl = "ezfm/worktask/projectuser/sonAddProjectUser/"+project_id;
	showSon1SonPageInfo("关联用户",addProjectUrl);
};


//树节点展开事件
function zTreeOnExpand(event, treeId, treeNode){
	var nodes;
	var treeObj = $.fn.zTree.getZTreeObj("projectInfo_tree");
	treeObj.removeChildNodes(treeNode);
	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",
        data:{class_id:treeNode.id},
        url: "ezfm/nexus/proandclass/query",//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。
            nodes = data.rows;   //把后台封装好的简单Json格式赋给treeNodes  
        }  
    });
	nodes = treeObj.addNodes(treeNode, nodes);
}
//单击树节点时，展示详细信息
function onNodeClick(event, treeId, treeNode) {
	if (treeNode.type != 2) {
		return;
	}
	var andList = [];
	var projectId = treeNode.id;
	if(projectId){
		var condition={
			key: 'project_id',
			operator: 'eq',
			value: projectId
		}
		andList.push(condition);
	}
	queryParam.andList=andList;
	$('#datagrid_areaProjectUserList').datagrid('reload');
}

function deleteUser(grid){
	var removeUrl = "ezfm/worktask/projectuser/delete";
	var removeRecods = $('#'+grid).datagrid("getSelections");
	if(removeRecods.length<1){
		$.messager.alert('提示','请您先选择移出的用户!','warning');
		return;
	}
	$.messager.confirm('提示', '您确认移出选中的用户吗?', function(r){
		if (r){
			$.request.restPost({
				url: removeUrl,
				data: removeRecods,
				success: function(rs){
					if(rs.success){
						$('#'+grid).datagrid('reload');
						$.messager.show({
							title:'提示',
							msg:'已移出',
							timeout:2000,
							showType:'slide'
						});
					}
				}
			});
		}
	});
}
