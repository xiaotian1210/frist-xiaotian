//点击窗体关闭该窗体 添加窗体的class为szw_window 在show该窗体的时候请提前closewin_flg = false
//var closewin_flg = false;
//$(document).click(function(e){
//	if(closewin_flg){
//		var winObj = $(".szw_window:visible:last");
//		if(winObj[0]){
//			var mouseX = e.pageX;//鼠标X轴坐标 
//			var mouseY = e.pageY;//鼠标Y轴坐标 
//			var ewinXL = $($(winObj).parent()[0]).offset().left;//Window DIV X轴 左侧坐标
//			var ewinXR = $($(winObj).parent()[0]).offset().left + $($(winObj).parent()[0]).width()+12;//Window DIV X轴 右侧坐标 与实际稍差(12PX)
//			var ewinYT = $($(winObj).parent()[0]).offset().top;//Window DIV Y轴 上侧坐标
//			var ewinYB =$($(winObj).parent()[0]).offset().top + $($(winObj).parent()[0]).height()+12;//Window DIV Y轴 下侧侧坐标 与实际稍差(12PX)
//			if(mouseX >= ewinXL && mouseX <= ewinXR && mouseY >= ewinYT && mouseY <= ewinYB){
//				
//			}else{
//				var winClass = winObj.attr("class");
//				if(winClass.indexOf("easyui-window")>-1){
//					$(winObj).window('close');
//				}else{
//					if(closewin_flg){
////			             $(winObj).dialog('close');
//					}
//				}
//			}
//		}
//	}else{
//		closewin_flg=true;
//	}
//}); 
	
//显示主页面的FORM面板的WINDOW
var showMainForm = function(title,url,options,params,method){
	closewin_flg = false;
	if(!$("#szw_dlg_mainform_id")[0]){
		var dlgflag = '<div id="szw_dlg_mainform_id" class="easyui-dialog szw_window"></div>';
		$(document.body).append(dlgflag);
	}
	params = params || {};
	$('#szw_dlg_mainform_id').dialog({    
	    title: title,    
	    href: url,
	    queryParams:params,
	    method:method?method:"get",
	    width: 600,    
	    height: 480,    
	    closed: true,    
	    cache: false,    
	    modal: true,
	    resizable:true,
	    modal:true,
	    maximizable:true,
	    loadingMessage:"数据加载中..."
	});
	if(options){
		$('#szw_dlg_mainform_id').dialog(options);
	}
	$('#szw_dlg_mainform_id').dialog('open');
}
//显示子页面的window  只适用于主页面只有一个子页面
var showSonPageInfo = function(title,url,options,params,method){
	closewin_flg = false;
	var dilogId = "szw_dlg_sonpage_id";
	if(options && options.id){
		dilogId = options.id;
	}
	
	if(!$("#"+dilogId)[0]){
		var dlgflag = '<div id="'+dilogId+'" class="easyui-dialog szw_window"></div>';
		$(document.body).append(dlgflag);
	}
	params = params || {};
	$('#'+dilogId).dialog({    
	    title: title,    
	    href: url,
	    queryParams:params,
	    method:method?method:"get",
	    width: 950,    
	    height: 580,
	    closed: true,    
	    cache: false,    
	    modal: true,
	    resizable:true,
	    modal:true,
	    maximizable:true,
	    loadingMessage:"数据加载中..."
	});
	if(options){
		$('#'+dilogId).dialog(options);
	}
	$('#'+dilogId).dialog('open');
}

//显示子页面的子页面  只适用于只有功能只有一个子页面 且子页面也只有一个子页面(主页面的孙子页面)
var showSon1SonPageInfo = function(title,url,options,params,method){
	closewin_flg = false;
	if(!$("#szw_dlg_son1sonpage_id")[0]){
		var dlgflag = '<div id="szw_dlg_son1sonpage_id" class="easyui-dialog szw_window"></div>';
		$(document.body).append(dlgflag);
	}
	params = params || {};
	$('#szw_dlg_son1sonpage_id').dialog({    
	    title: title,    
	    href: url,
	    queryParams:params,
	    method:method?method:"get",
	    width: 800,    
	    height: 480,    
	    closed: true,    
	    cache: false,    
	    modal: true,
	    resizable:true,
	    modal:true,
	    maximizable:true,
	    loadingMessage:"数据加载中..."
	});
	if(options){
		$('#szw_dlg_son1sonpage_id').dialog(options);
	}
	$('#szw_dlg_son1sonpage_id').dialog('open');
}

//自定义弹窗页面 用于自定义面板 windowId不允许为空、不允许重复
var showCustomWindow = function(windowId,title,url,options,params,method){
	closewin_flg = false;
	var $winId = "#"+windowId;
	if(!$($winId)[0]){
		var dlgflag = '<div id="'+windowId+'" class="easyui-dialog szw_window"></div>';
		$(document.body).append(dlgflag);
	}
	params = params || {};
	$($winId).dialog({    
	    title: title,    
	    href: url,
	    queryParams:params,
	    method:method?method:"get",
	    width: 800,    
	    height: 480,    
	    closed: true,    
	    cache: false,    
	    modal: true,
	    resizable:true,
	    modal:true,
	    maximizable:true,
	    loadingMessage:"数据加载中..."
	});
	if(options){
		$($winId).dialog(options);
	}
	$($winId).dialog('open');
}