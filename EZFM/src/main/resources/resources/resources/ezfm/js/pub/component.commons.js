var buttons = $.extend([], $.fn.datebox.defaults.buttons);
buttons.splice(1, 0, {
	text: '清空',
	handler: function(target){
		$(target).datebox("clear");
	}
});