//表单验证
$.extend($.fn.validatebox.defaults.rules, {
	validLetterNumberUnderline : {
		validator : function(value, param) {
			var reg=/^\w+$/; 
			return reg.test(value);
		},
		message : '请输入字母、数字、下划线'
	},
	validLengthThreeHundred: {
		validator: function(value, param) {
			return value.length <= 300;
		},
		message: '长度不能大于300'
	},
	validLetterNumber : {
		validator : function(value, param) {
			var reg=/^[A-Z,a-z,0-9]+$/; 
			return reg.test(value);
		},
		message : '请输入字母、数字'
	}
});