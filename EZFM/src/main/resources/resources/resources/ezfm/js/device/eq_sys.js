$(function() {


    $(function () {
        $('#form_textbox_room').numberbox('textbox').attr('maxlength', 9);
    });
    $.fn.zTree.init($("#stationtree"), setting, zNodes());
    var treeObj = $.fn.zTree.getZTreeObj("stationtree");
    treeObj.expandAll(false);
   // nowrap:false
    $("#station-propertygrid").propertygrid({'nowrap':false});
    $("#station-propertygrid").propertygrid('loadData', [ {
        name : '系统编码',
        value :''
    }, {
        name : '系统名称',
        value : ''
    }, {
        name : '所属系统',
        value : ''
    }, {
        name : '排序号',
        value : ''
    },{
        name : '已关联设备',
        value : ''
    },  {
        name : '创建人',
        value : ''
    }, {
        name : '创建时间',
        value : ''
    }, {
        name : '模型关联ID',
        value : ''
    }, {
        name : '系统描述',
        value : '',

    }]);

    function getAllName(treeNode){
        var name =treeNode.name;
        while(treeNode.getParentNode() != null){
            treeNode = treeNode.getParentNode();
            name = treeNode.name  +"|"+name;

        }
        return name;

    }
    function getAllIds(treeNode){
        if(!treeNode){
            return "";
        }
        var name =treeNode.id;
        while(treeNode.getParentNode() != null){
            treeNode = treeNode.getParentNode();
            name = treeNode.id  +"|"+name;

        }
        return name;

    }
    //$('#f_pk_org').combotree('loadData', orgTree());
  //  dictInit();
    var stationdlg = $('#stationdlg').dialog({
        width : 600,
        height : 350,
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
                    var node = nodes[0];
                    var all_ids = getAllIds(node);
                    var json = $('#form').serializeJson();
                    json.all_ids = all_ids;
                    if(json.node_now == '根节点'){
                        json.parent_id = '';
                    }
                    $.request.restPost({
                        url : json.eq_sys_id ? 'ezfm/device/fmdata_eq/update' : 'ezfm/device/fmdata_eq/save',
                        data : json,
                        success : function(result) {
                            if (result.success) {
                                if (!json.eq_sys_id) {
                                    var data = result.data[0];
                                    var node = {
                                        id : data.eq_sys_id,
                                        pId : data.parent_id,
                                        name : data.name,
                                        attributes : data
                                    };
                                    treeObj.addNodes(nodes[0], node);
                                } else {
                                    var data = result.data[0];
                                    nodes[0].name = data.name;
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

    function setSelectedNodes(nodes) {

        var data = [{
            label: '根节点',
            value: '根节点'
        }];
        if(nodes.length == 1){
            data = [{
                label: nodes[0].attributes.name,
                value: nodes[0].attributes.name
            },{
                label: '根节点',
                value: '根节点'
            }];
        }
        $('#node_now').combobox({
            valueField: 'label',
            textField: 'value',
            data: data,
            onLoadSuccess: function (data) {
                $('#node_now').combobox('setValue',data[0].value);
            }
        });

    }

    $('#btnNew').click(function() {
        closewin_flg = false;
        $('#form').form('clear');
        var nodes = treeObj.getSelectedNodes()
        setSelectedNodes(nodes);
        $('#node_now').combobox({disabled:false})
       // var nodes = treeObj.getSelectedNodes();
        if (nodes.length <= 0) {
        } else {
            $('#form').form('load', {
                parent_id : nodes[0].attributes.eq_sys_id,
            });
        }
        $('#stationdlg').window({
            title : '新增设备系统',
            closed : false
        });
    });
    $('#btnEdit').click(function() {
        closewin_flg = false;
        $('#form').form('clear');
        var nodes = treeObj.getSelectedNodes();
        setSelectedNodes(nodes);
        $('#node_now').combobox({disabled:true})

        if (nodes.length <= 0) {
            $.messager.alert('提示', '请选择待修改系统');
        }
        $('#form').form('load', nodes[0].attributes);
        $('#stationdlg').window({
            title : '系统编辑',
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
        $.messager.confirm('提示', '是否确认删除选中系统，请谨慎操作', function(r) {
            if (r) {
                $.request.restPost({
                    url : 'ezfm/device/fmdata_eq/delete',
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
        metas : [ 'yjwy_device_fmdata_eq_sys' ]
    }
    var result = null;
    $.extend(param, extraParam);
    $.request.httpPost({
        url : 'ezfm/device/fmdata_eq/query',
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
    gen_name= node.name;
}


function zTreeOnClick(event, id, node) {
    var attributes = node.attributes;
    setGenName(node);
    $("#station-propertygrid").propertygrid('loadData', []);
    $("#station-propertygrid").propertygrid('loadData', [ {
        name : '系统编码',
        value : attributes.code
    }, {
        name : '系统名称',
        value : attributes.name
    }, {
        name : '所属系统',
        value : gen_name
    }, {
            name : '排序号',
            value : attributes.sort_no
    },{
        name : '已关联设备',
        value : attributes.eq_count
    },  {
        name : '创建人',
        value : attributes.user_name
    }, {
        name : '创建时间',
        value : attributes.create_time
    },  {
        name : '模型关联ID',
        value : attributes.eq_class
    },{
        name : '系统描述',
        value : attributes.description
    }]);

}
