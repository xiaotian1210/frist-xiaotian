var renderUsable = function(value, row, index){
	var is_able = row.is_able;
	return is_able == '1' ? '<div style="color:green">启用</div>' : '<div style="color:red">停用</div>';
}
var renderPre = function(value, row, index){
	var is_pre = row.is_pre;
	return is_pre == '1' ? '<div style="color:blue">是</div>' : '否';
}
var fnRow = function(index, button){
	if(index < 0) return;
	$('#datagrid').datagrid('selectRow', index);
	$('#' + button).click();
}

var formatOper = function(value, row, index) {
	var opt = '<a href="javascript:void(0);" onclick="fnRow(' + index + ', \'btnEdit\')">修改</a> ';
	opt += '<a href="javascript:void(0);" onclick="fnRow(' + index + ', \'btnEnable\')">启用</a> ';
	return opt;
}
$(function(){
	var qryParam = {};	
	var roleload = function(index,row){
		queryRoleRel(row);
	}
	var queryRoleRel = function(row){
		var pk_user = row.pk_user;
		var extraParam = { 
			metas: ['yjwy_role_user'],
			'andList': [{
				key: 'pk_crop_',
				operator: 'eq',
				value: pc
			},{
				key:'pk_user_',
				operator:'eq',
				value:pk_user
			}] 
		};
		$.request.httpPost({
			url: 'ezfm/baseinfo/role/user/show',
			data: { param: JSON.stringify(extraParam)},
			success: function(rs){
				rolegrid.datagrid('loadData',rs.data);
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '保存失败');
			}
		});
	}
	var userList = $('#userList').datagrid({
		title:'用户管理',
		toolbar:'#tb',
		fit:true,
		singleSelect: true,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:true,
		pageSize: 25,
		pageList:[25,50,100],
		pageNumber: 1,
		rownumbers: true,
		onSelect:function(index, row){
			roleload(index,row);
		},
		url: 'ezfm/baseinfo/user/query',
		loader: function(param, success, error){
			param = param || {};
			var extraParam = { 
				metas: ['yjwy_user'],
				'andList': [{
					key: 'pk_crop_',
					operator: 'eq',
					value: pc
				}] 
			};
			if(param.rows){
				extraParam.limit = param.rows;
				if(param.page){
					extraParam.start = (param.page - 1) * param.rows;
				}
			}
			$.extend(param, extraParam);
			$.request.httpPost({
				url: 'ezfm/baseinfo/user/query',
				data: { param: JSON.stringify(param)},
				success: function(rs){
					success({
						total: rs.total,
						rows: rs.data
					});
				},
				failure: function(rs){
					$.messager.alert('提示', rs.message || '查询失败');
				}
			});
			return true;
		}
	});
	$('#querysearch').searchbox({ 
		prompt:'请输入关键字', 
		searcher:function(value,name){
			var options = $('#userList').datagrid('options');
			var pageSize = options.pageSize;
			var qryParam = {};
			if(value){
				qryParam = {
					key: name,
					operator: 'like',
					value: value
				};
			}
			dataQuery(null, qryParam, 0, pageSize);
		}
	});
	
	$('#btnQuery').click(function(){
		var jsonObj = $('#qryForm').serializeJson();
		
		var pArray = [],
			jValue = null;
			
		for(var item in jsonObj){
			jValue = jsonObj[item];
        	if("" === jValue){
        		continue;
        	}
          	pArray.push({key:item,operator:'eq',value: jValue});
        } 
        
        qryParam = {'andList':pArray};
		options = $('#userList').datagrid('getPager').data("pagination").options;
		pagesize = options.pageSize;
		dataQuery('ezfm/baseinfo/user/query', qryParam, 0 * pagesize, pagesize);
	});
	
	$('#userList').datagrid('getPager').pagination({   
	    onSelectPage : function(pPageIndex, pPageSize) {   
	    	dataQuery('ezfm/baseinfo/user/query', qryParam, (pPageIndex - 1) * pPageSize, pPageSize);
	    }   
	});

	var dataQuery = function(url, jsonParam, start, limit) {
		$('#userList').datagrid('loading');
		var andList = [];
		andList.push({
			key: 'pk_crop_',
			operator: 'eq',
			value: pc
		});
		if(!$.isEmptyObject(jsonParam)){
			andList.push(jsonParam);
		}
		var param= {
			metas: ['yjwy_user'],
			'andList':andList,
			limit: limit,
			start: start
		};
		$.request.httpPost({
			url: 'ezfm/baseinfo/user/query',
			data: { param: JSON.stringify(param)},
			success: function(rs){
				$('#userList').datagrid('loadData', {
					total: rs.total,
					rows: rs.data
				});
				$('#userList').datagrid('loaded');
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '查询失败');
			}
		});
		return true;
	}
	
	$("#btnNew").click(function() {
		closewin_flg = false;
		$('#userForm').form('clear');
		$('#pk_crop').val(pc);
		$('#dialog').window({
			title:'新增员工',
			closed:false
		});
	});
	
	$("#btnEdit").click(function() {
		closewin_flg = false;
		var data = $("#userList").datagrid("getSelected");
		if(!data){
			$.messager.alert('提示','请选择需要编辑的数据');
			return ;
		}
		$('#userForm').form('clear');
		$('#userForm').form('load',data);
		$('#dialog').window({
			title:'编辑员工信息',
			closed:false
		});
	});
	
	$("#btnrole").click(function() {	
	
	});
	$('#btnEnable').click(function(){
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待启用行');
			return;
		}
		if(!recs.length>1){
			$.messager.alert('提示', '请每次选择一行数据');
			return;
		}
		if(recs[0].is_able=="1"){
			$.messager.alert('提示', '请选择一条未启用的数据');
			return;
		}
		$.messager.confirm('提示', '是否确认启用选定行', function(r){
			if(r){
				var url = 'ezfm/baseinfo/user/enable';
				var successFunc = function(result){
					if(result.success==false) {
						$.messager.alert('提示', result.message);
					}
					$('#userList').datagrid('getPager').pagination('select');
				}
				$.request.restPost({
					url: url,
					data: recs,
					success: successFunc
				});
			}
		});
	});
	$('#btnDisable').click(function(){
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待停用行');
			return;
		}
		if(!recs.length>1){
			$.messager.alert('提示', '请每次选择一行数据');
			return;
		}
		if(recs[0].is_able=="0"){
			$.messager.alert('提示', '请选择一条停用的数据');
			return;
		}
		$.messager.confirm('提示', '是否确认停用选定行', function(r){
			if(r){
				var url = 'ezfm/baseinfo/user/disable';
				var successFunc = function(result){
					if(result.success==false) {
						$.messager.alert('提示', result.message);
					}
					$('#userList').datagrid('getPager').pagination('select');
				}
				$.request.restPost({
					url: url,
					data: recs,
					success: successFunc
				});
			}
		});
	});
	
	
	
	$('#btnDelete').bind('click', function(){
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待删除行');
			return;
		}
		for(var i in recs){
			if("1"==recs[i].is_pre || true===recs[i].is_pre){
				$.messager.alert("提示","系统预置人员不可以删除","warning");
				return;
			}
		}
		$.messager.confirm('提示', '是否确定删除选中人员，请谨慎操作', function(r){
			if(r){
				var url = 'ezfm/baseinfo/user/delete';
				var successFunc = function(result){
					if(result.success){
						$('#userList').datagrid('getPager').pagination('select');
						$.messager.show({
							title:'提示',
							msg:'数据已删除',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$.messager.alert("提示",result.message,"warning");
					}
				}
				$.request.restPost({
					url: url,
					data: recs,
					success: successFunc
				});
			}
		});
	});

	var dialog = $('#dialog').dialog({
		width: 540,
		height: 300,
		left:($(window).width()-540)/2,
		top:($(window).height()-400)/2,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				if($('#userForm').form('validate')){
					var json = $('#userForm').serializeJson();
					$.request.restPost({
						url: json.pk_user ? 'ezfm/baseinfo/user/update':'ezfm/baseinfo/user/save',
						data: json,
						success: function(result){
							if(result.success){
								$('#userList').datagrid('getPager').pagination('select');
								$('#userForm').form('clear')
								dialog.window('close');
								$.messager.show({
									title:'提示',
									msg:'数据已保存',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert("提示",result.message);
							}
						},
						failure: function(rs){
							$.messager.alert('提示', rs.message || '保存失败');
						}
					});
				}
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '当前单据未保存，是否确认退出', function(r){
					if(r){
						$('#userForm').form('clear')
						dialog.window('close');
					}
				});
			}
		}]
	});
	
	//角色关联部分
	var rolegrid = $('#rolegrid').datagrid({
		toolbar:'#roletb',
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,
		loadMsg : '数据装载中......',
		striped: true,
		pagination:false,
		loader: function(param, success, error){	
			$('#rolegrid').datagrid('loaded');
			return true;
		}
	});
	
	var roledlg = $('#roledlg').dialog({
		toolbar:'#roledlgtb',
		title: '选择角色',
		width:395,
		height:400,
		left:($(window).width()-395)/2,
		top:($(window).height()-400)/2,
		resizable:true, modal: true, closed:true,
		cache:false
	});
	
	$("#btnRoleAdd").click(function() {
		closewin_flg = false;
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待分配用户');
			return;
		}	
		$('#roledlg').dialog().window('open');
		$('#addRoleGrid').datagrid({data:roledata()});
		
	});
	$("#btnRoleRemove").click(function() {
		var rows = $('#rolegrid').datagrid('getSelections');
		if(!rows.length){
			$.messager.alert('提示', '请选择要移除的角色');
			return;
		}
		$.messager.confirm('提示', '您确认移除选中的角色？', function(r){
			if(r){
				var submitData = [];
				for(var i in rows){
					submitData.push({
						pk_role: rows[i].pk_role,
						pk_user: getUser(),
						pk_crop: pc
					});
				}
				$.request.restPost({
					url: 'ezfm/baseinfo/role/user/delete',
					data: submitData,
					success: function(rs){
						var row = $('#userList').datagrid('getSelected');
						queryRoleRel(row);
						$.messager.show({
							title:'提示',
							msg:'角色已经移除',
							timeout:2000,
							showType:'slide'
						});
					},
					failure: function(rs){
						$.messager.alert('提示', rs.message || '删除失败');
					}
				});
			}
		});
	});
	var roledata = function(){
		var roleArray = null;
		var extraParam = { 
			metas: ['yjwy_role_user'],
			'andList': [{
				key: 'pk_crop_',
				operator: 'eq',
				value: pc
			},{
				key:'pk_user_',
				operator:'eq',
				value:getUser()
			}],pk_crop: pc
		};
		$.request.httpPost({
			async:false,
			url: 'ezfm/baseinfo/role/user/filter',
			data: { param: JSON.stringify(extraParam)},
			success: function(rs){
				roleArray = rs.data;
			}
		});
		return roleArray;
	};	
	$('#btnClose').click(function(){
		$('#roledlg').dialog().window('close');
	});
	
	$('#btnSelect').click(function(){
		var rows = $('#addRoleGrid').datagrid('getSelections');
		if(!rows.length){
			$.messager.alert('提示', '请选择行');
			return;
		}
		var submitData = [];
		for(var i in rows){
			submitData.push({
				pk_role: rows[i].pk_role,
				pk_user: getUser(),
				pk_crop: pc
			});
		}
		$.request.restPost({
			url: 'ezfm/baseinfo/role/user/save',
			data: submitData,
			success: function(rs){
				for(var i in rows){
					$('#rolegrid').datagrid('appendRow', rows[i]);
				}
				$('#roledlg').dialog().window('close');
				$.messager.show({
					title:'提示',
					msg:'角色已添加',
					timeout:2000,
					showType:'slide'
				});
			},
			failure: function(rs){
				$.messager.alert('提示', rs.message || '保存失败');
			}
		});
	});
	getUser = function(){
		var u = $('#userList').datagrid('getSelected');
		return u.pk_user;
	}
	var stationdlg = $('#stationdlg').dialog({
		title: '岗位分配',
		width: 600,
		height: 400,
		left:($(window).width()-600)/2,
		top:($(window).height()-400)/2,
		resizable:true, modal: true, closed:true,
		cache:false,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function(){
				var user = $("#userList").datagrid("getSelected");
				var nodes = tree.getCheckedNodes(true);
				var param = [];
				for(i = 0;i<nodes.length;i++){
					p = {
						pk_crop:pc,
						pk_station:nodes[i].id,
						pk_user:user.pk_user
					};
					param.push(p);
				}
				
				if(param.length<1){
					p = {
						pk_crop:pc,
						pk_station:null,
						pk_user:user.pk_user
					};
					param.push(p);
				}
				
				$.request.restPost({
					url: 'ezfm/baseinfo/userstation/save',
					data: param,
					success: function(result){
						stationdlg.window('close');
						$.messager.show({
							title:'提示',
							msg:'数据已保存',
							timeout:2000,
							showType:'slide'
						});
					},
					failure: function(rs){
						$.messager.alert('提示', rs.message || '保存失败');
					}
				})
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function(){
				$.messager.confirm('提示', '当前操作尚未保存，是否确认退出', function(r){
					if(r){
						stationdlg.window('close');
					}
				});
			}
		}]
	});
	$('#btnstation').click(function(){
		closewin_flg = false;
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择待分配用户');
			return;
		}
		$('#stationdlg').dialog().window('open');
		$.fn.zTree.init($("#stationtree"), setting, zNodes());
		tree = $.fn.zTree.getZTreeObj("stationtree");
	});
	$('#btnReset').bind('click', function(){
		var recs = userList.datagrid('getSelections');
		if(!recs.length){
			$.messager.alert('提示', '请选择需重置的用户');
			return;
		}
		$.messager.confirm('提示', '是否确定重置密码（密码：111111）', function(r){
			if(r){
				var url = 'ezfm/baseinfo/user/reset';
				var successFunc = function(result){
					if(result.success){
						$('#userList').datagrid('getPager').pagination('select');
						$.messager.show({
							title:'提示',
							msg:'密码已重置',
							timeout:2000,
							showType:'slide'
						});
					}else{
						$.messager.alert("提示",result.message,"warning");
					}
				}
				$.request.restPost({
					url: url,
					data: recs,
					success: successFunc
				});
			}
		});
	});
});
var tree = null;
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "s", "N": "" }
	},

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

	}
};



var zNodes = function(){
	var user = $("#userList").datagrid("getSelected");
	var param = {};
	/*var extraParam = {
		metas: ['yjwy_user_station'],
		andList:[{
			key: 'pk_crop_',
			operator: 'eq',
			value: pc
		},{
			key: 'pk_user_',
			operator: 'eq',
			value: user.pk_user
		}],
	}*/
	var result = null;
	var dataParam = {pk_crop:pc,pk_user:user.pk_user};
	//$.extend(param, extraParam);
	$.request.httpPost({
		url: 'ezfm/baseinfo/station/userstation/query/all',
		async:false,
		data: dataParam,
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

