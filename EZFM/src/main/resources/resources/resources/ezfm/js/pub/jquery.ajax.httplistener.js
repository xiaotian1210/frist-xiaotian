/*$(document).ajaxError(function(event,xhr,options,exc){
	console.log(event);
	console.log(xhr);
	console.log(options);
	console.log(exc);
});
$.ajaxSetup({
  error:function(xhr,status,exc){
	  console.log(xhr);
	  console.log(status);
	  console.log(exc);
  }
});*/
$(document).ajaxComplete(function(event,xhr,options){
	var result = xhr.responseText;
	if(result && "string"== (typeof result) && "{"==result.charAt(0) && "}"==result.charAt(result.length-1)){
		result = eval("("+result+")");
		if("http_session_invalid"==result.code){
			$.messager.confirm('提示', '本次访问已经结束，是否重新登录?', function(r){
				if (r){
					window.top.location.href="ezfm/login/index";
				}
			});
		}else if("dms_session_invalid"==result.code){
			$.messager.confirm('提示', '您的账户已在其他地点登录，是否重新登录?', function(r){
				if (r){
					window.top.location.href="ezfm/login/index";
				}
			});
		}
	}
});

