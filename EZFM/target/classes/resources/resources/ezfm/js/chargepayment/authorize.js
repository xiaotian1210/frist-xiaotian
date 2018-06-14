
// 页面加载

$(function() {
	$.ajax({
        url: "ezfm/system/crop/authorizeCount",
        type: "get",
        dataType: "json",
        contentType:"application/json",
        async:false,
        success: function(result){
        	if(result.success){
        		var count = parseInt(result.data);
        		$("#count").text(count);
        		getDifferencePrice(count);
        	}

		},
        error: error
    });


});


function error(request, textStatus, errorThrown) {
	alert(request);
	alert(textStatus);
	alert(errorThrown);
}
function getDifferencePrice(count){

	$.ajax({
	        url: "ezfm/system/crop/authorizePrice",
	        type: "get",
	        dataType: "json",
	        async:false,
	        data:{count:count},
	        success: function(result){
	        	if(result.success){
	        		$("#difference_price").text(result.data);
	        	}
	        	else{
	        		$("#difference_price").text(result.message);
	        	}
			},
	        error: error
	});
}
function changeCount(value){

	var sum = 0;
	$("input.textbox-value").each(function(index,e){
		if(index==0)
		   sum += parseInt(e.value) * 100;
		if(index==1)
		   sum += parseInt(e.value) * 10;
		if(index==2)
		   sum += parseInt(e.value);
	});
	getDifferencePrice(sum);
}

function changeAuthorize(){
	var sum = 0;
	$("input.textbox-value").each(function(index,e){
		if(index==0)
		   sum += parseInt(e.value) * 100;
		if(index==1)
		   sum += parseInt(e.value) * 10;
		if(index==2)
		   sum += parseInt(e.value);
	});
	$.ajax({
        url: "ezfm/system/crop/changeAuthorize",
        type: "post",
        dataType: "json",
        async:false,
        data:{count:sum,amount:$("#difference_price").text()},
        success: function(result){
        	if(!result.success){
        		alert(result.message);
        	}
        	else
        	 location.reload();
		},
        error: error
    });
}


