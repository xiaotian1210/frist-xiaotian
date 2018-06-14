$(function() {


    $.fn.zTree.init($("#stationtree"), setting, zNodes());
    var treeObj = $.fn.zTree.getZTreeObj("stationtree");
    treeObj.expandAll(false);
   // nowrap:false
    $("#station-propertygrid").propertygrid({'nowrap':false});
    $("#station-propertygrid").propertygrid('loadData', [ {
        name : '资源类型编码',
        value :''
    }, {
        name : '资源类型名称',
        value : ''
    }, {
        name : '创建人',
        value : ''
    }, {
        name : '创建时间',
        value : ''
    }, {
        name : '所选属性',
        value : ''
    }]);

    //$('#f_pk_org').combotree('loadData', orgTree());
  //  dictInit();
    var stationdlg = $('#stationdlg').dialog({
        width : 600,
        height : 450,
        resizable : true,
        modal : true,
        closed : true,
        cache : false,
        buttons : [ {
            text : '保存',
            iconCls : 'icon-ok',
            handler : function() {
                if ($('#form').form('validate')) {
                    var nodes = treeObj.getSelectedNodes();
                    var json = $('#form').serializeJson();
                    //获取被选中的复选框
                    var chk_value =[];
                    $('input[name="attribute_code"]:checked').each(function(){ 
                    	chk_value.push($(this).val()); 
                   	});
                    $.request.restPost({
                        url : json.type_id ? 'ezfm/baseinfo/type/update':'ezfm/baseinfo/type/save',
                        data : json,
                        success : function(result) {
                            if (result.success) {
                                if (!json.type_id) {
                                    var data = result.data[0];
                                    var node = {
                                        id : data.type_id,
                                        pId : data.type_pid,
                                        name : data.type_name,
                                        attributes : data
                                    };
                                    treeObj.addNodes(nodes[0], node);
                                } else {
                                    var data = result.data[0];
                                    nodes[0].name = data.type_name;
                                    nodes[0].attributes = data;
                                    treeObj.updateNode(nodes[0]);
                                    zTreeOnClick(null,null,nodes[0]);
                                }
                                stationdlg.window('close');
                                //$.fn.zTree.init($("#stationtree"), setting, zNodes());
                               // treeObj.expandAll(true);
                                $.messager.show({
                                    title : '提示',
                                    msg : '数据已保存',
                                    timeout : 2000,
                                    showType : 'slide'
                                });
                            } else {
                                $.messager.alert("提示", result.message);
                            }
                        },
                        failure : function(rs) {
                            $.messager.alert('提示', rs.message || '保存失败');
                        }
                    })
                }
            }
        }, {
            text : '取消',
            iconCls : 'icon-cancel',
            handler : function() {
                $.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r) {
                    if (r) {
                        $('#form').form('clear');
                        stationdlg.window('close');
                    }
                });
            }
        } ]
    });

//    function setSelectedNodes(nodes) {
//
//        var data = [{
//            label: '根节点',
//            value: '根节点'
//        }];
//        if(nodes.length == 1){
//            data = [{
//                label: nodes[0].attributes.type_name,
//                value: nodes[0].attributes.type_name
//            },{
//                label: '根节点',
//                value: '根节点'
//            }];
//        }
//        $('#node_now').combobox({
//            valueField: 'label',
//            textField: 'value',
//            data: data,
//            onLoadSuccess: function (data) {
//                $('#node_now').combobox('setValue',data[0].value);
//            }
//        });
//
//    }

    $('#btnNew').click(function() {
        closewin_flg = false;
        $('#form').form('clear');
        var nodes = treeObj.getSelectedNodes()
//        $('#node_now').combobox({disabled:false})
       // var nodes = treeObj.getSelectedNodes();
        if (nodes.length <= 0) {
        } else {
            $('#form').form('load', {
                type_pid : nodes[0].attributes.type_id,
            });
        }
        $('#stationdlg').window({
            title : '资源类型',
            closed : false
        });
    });
    $('#btnEdit').click(function() {
        closewin_flg = false;
        $('#form').form('clear');
        var nodes = treeObj.getSelectedNodes();
//        $('#node_now').combobox({disabled:true})

        if (nodes.length <= 0) {
            $.messager.alert('提示', '请选择待修改资源类型');
        }
        $('#form').form('load', nodes[0].attributes);
        $('#stationdlg').window({
            title : '类型编辑',
            closed : false
        });

    });
    $('#btnDelete').click(function() {
        var nodes = treeObj.getSelectedNodes();
        if (nodes.length <= 0) {
            $.messager.alert('提示', '请选择待删除节点');
            return;
        }
        var list = [];
        var childs = treeObj.getNodesByFilter(function(node){return true}, false, nodes[0]);
        //var nodese = treeObj.getNodesByParam(null, null, nodes[0]);
        for(var i=0; i<childs.length; i++){
            var n = childs[i];
            list.push(n.attributes);
        }
        list.push(nodes[0].attributes);
        $.messager.confirm('提示', '是否确认删除选中类型，删除可能导致关联数据丢失,请谨慎操作', function(r) {
            if (r) {
                $.request.restPost({
                    url : 'ezfm/baseinfo/type/delete',
                    data : list,
                    success : function(result) {
                        if (result.success) {
                            treeObj.removeNode(nodes[0]);
                            $('#form').form('clear');
                            $.messager.show({
                                title : '提示',
                                msg : '数据已删除',
                                timeout : 2000,
                                showType : 'slide'
                            });
                        } else {
                            $.messager.alert("提示", result.message);
                        }
                    },
                    failure : function(rs) {
                        $.messager.alert('提示', rs.message || '删除失败');
                    }
                });
            }
        });
    });
});

var setting = {
    data : {
        simpleData : {
            enable : true
        }
    },
    view : {
        showLine : true,
        selectedMulti : false,
        txtSelectedEnable : false,
        showIcon : false
    },
    callback : {
        onClick : zTreeOnClick
    }
};

var zNodes = function() {
    var param = {};
    var extraParam = {
        metas : [ 'resource_type' ]
    }
    var result = null;
    $.extend(param, extraParam);
    $.request.httpPost({
        url : 'ezfm/baseinfo/type/query',
        async : false,
        data : {
            param : JSON.stringify(param)
        },
        success : function(rs) {
            if (rs.success) {
                result = rs.data;
            }
        },
        failure : function(rs) {
            $.messager.alert('提示', rs.message || '查询失败');
        }
    });
    return result;
}

var gen_name ='';
function setGenName(node) {


    while(node.getParentNode() != null){
        node = node.getParentNode();
    }
    gen_name= node.type_name;
}


function zTreeOnClick(event, id, node) {
    var attributes = node.attributes;
    setGenName(node);
    attributename(attributes.type_id,attributes);

}
function attributename(id,attributes){
	var attri_name='';
	var extraparam={
			metas:['yjwy_attribute_name']
	}
	var name=null;
	$.ajax({
		type:"POST",
		url:"ezfm/baseinfo/resources/attributename/queryName",
		data:{
			type_id:id
		},
		success:function(rs){
			name=rs.data;
			if(rs.success){
//				for(var i in name){
//					attri_name=attri_name+name[i].attribute_name+',';
//				}
				attri_name=$.map(name,function(n){return n.attribute_name;}).join(",");
			}
			 $("#station-propertygrid").propertygrid('loadData', []);
			    $("#station-propertygrid").propertygrid('loadData', [ {
			        name : '资源类型编码',
			        value : attributes.type_code
			    }, {
			        name : '资源类型名称',
			        value : attributes.type_name
			    },{
			        name : '创建人',
			        value : attributes.type_upname
			    }, {
			        name : '创建时间',
			        value : attributes.type_time
			    }, {
			        name : '所选属性',
			        value :attri_name
			    }]);
		}
	});
}
//动态生成复选框
window.onload = function () {
	var param = {};
    var extraParam = {
        metas : [ 'yjwy_attribute_name' ]
    }
    var  html='';
    var check=null;
    $.extend(param, extraParam);
	 $.ajax({
			type : "POST",
			url : "ezfm/baseinfo/resources/attributename/query",
			data : {
				param : JSON.stringify(param)
			},
			
			success : function(rs){
				
				if(rs.success){
					check =rs.data;
					for(var i in check){
						if(i%3==0){
							html=html+'</br>';
						}
//						html= html+'<input type="checkbox" id="'+check[i].attribute_code+'" name="attribute_code" value="'+check[i].attribute_code+'" /><lable>'+ check[i].attribute_name +'</lable>';
				        html= html+'<div style="text-align:left;float:left;width:110px;"><input type="checkbox" id="'+check[i].attribute_code+'" name="atribute_code" value="'+check[i].attribute_code+'"/><lable>'+check[i].attribute_name +'</lable></div>'
					}
					
					return $("#checkbox").html(html);
				}
			}
			
			
		});
		
	 
	
	
}
