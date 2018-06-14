function addRoomPlace(){
    var sonpageUrl = "ezfm/device/list/place/room";
	showSonPageEvent("机房（双击选中）",sonpageUrl,{width:600,height:450,id:'roomId'});
};




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
        value : 'nhblx'
    } ];
    dictInitAjax($('.station'), {
        metas : metas,
        'andList' : p_station_level
    });
})

$(function () {
    var metas = [ 'yjwy_dict' ];
    var p_purpose_level = [ {
        key : 'dict_parent_',
        operator : 'eq',
        value : 'BYTMC'
    } ];
    dictInitAjax($('.purpose'), {
        metas : metas,
        'andList' : p_purpose_level
    });
})

