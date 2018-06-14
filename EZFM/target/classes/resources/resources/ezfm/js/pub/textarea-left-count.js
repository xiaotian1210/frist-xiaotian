/**
 * 
 */
checkLeftCountInit();
function checkLeftCountInit() {
	var allInitial = true;
	$(".easyui-textbox.left-count").each(function(){
		var initial = $(this).attr("data-initial");
		if(initial==true) {
			return;
		}
		$(this).attr("data-initial", true);
		var textbox = $(this).next(".textbox");
		if(textbox.length==0) {
			allInitial = false;
			return;
		}
		var id = new Date().getTime()+"_"+Math.floor(Math.random()*1000);
		$(this).attr("data-id", id);
		textbox.attr("data-id", id);
		textbox.addClass("left-count");
		var maxLength = $(this).attr("maxlength");
		var textarea = textbox.find("textarea");
		textarea.attr("maxlength", maxLength);
		textarea.attr("data-id", id);
		var left = maxLength-textarea.val().length;
		var countSpan = '<span class="leftspan" data-id="'+id+'" style="display:block;color:grey;">还能输入'+left+'个字</span>';
		textbox.after(countSpan);
	});
	if(allInitial==false) {
		setTimeout(checkLeftCountInit, 500);
	} else {
		addListener();
	}
}

function addListener() {
	$(document).off("keyup", ".left-count.textbox textarea");
	$(document).on("keyup", ".left-count.textbox textarea", function(e){
		var maxLength = $(this).attr("maxlength");
		var left = maxLength-$(this).val().length;
		var id = $(this).attr("data-id");
		$(".leftspan[data-id='"+id+"']").html('还能输入'+left+'个字');
	});
}