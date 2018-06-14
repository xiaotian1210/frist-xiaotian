function checkMobile(str) {
	var reg = /^1\d{10}$/;
	return reg.test(str);
}

function checkPhone(str) {
	var reg = /^0\d{2,3}-?\d{7,8}$/;
	return reg.test(str);
}

function checkEmail(str) {
	var reg = /^[A-Za-z0-9]+([._\\-]*[A-Za-z0-9])*@([A-Za-z0-9]+[-A-Za-z0-9]*[A-Za-z0-9]+.){1,63}[A-Za-z0-9]+$/;
	return reg.test(str);
}

function checkPostCode(str) {
	var reg = /^[1-9]\d{5}$/;
	return reg.test(str);
}

function check() {
	var crop_code = $("form input[name=code]").val();
	var crop_name = $("form input[name=name]").val();
	var crop_address = $("form input[name=address]").val();
	var crop_phone = $("form input[name=phone]").val();
	var postcode = $("form input[name=post_code]").val();
	var crop_contact = $("form input[name=contact]").val();
	var crop_email = $("form input[name=email]").val();
	var crop_file = $("form input[name=crop_file]").val();
	if (!crop_code) {
		$("#errorInfoId").html("请输入企业编码");
		showProcessMessageMode(false);
		return false;
	}
	if (!crop_name) {
		$("#errorInfoId").html("请输入企业名称");
		showProcessMessageMode(false);
		return false;
	}
	if (!crop_address) {
		$("#errorInfoId").html("请输入联系地址");
		showProcessMessageMode(false);
		return false;
	}
	if (!postcode || !checkPostCode(postcode)) {
		$("#errorInfoId").html("请正确输入邮政编码");
		showProcessMessageMode(false);
		return false;
	}
	if (!crop_contact) {
		$("#errorInfoId").html("请输入联系人");
		showProcessMessageMode(false);
		return false;
	}
	if (!crop_phone || !(checkMobile(crop_phone) || checkPhone(crop_phone))) {
		$("#errorInfoId").html("请正确输入联系电话");
		showProcessMessageMode(false);
		return false;
	}
	if (!crop_email || !checkEmail(crop_email)) {
		$("#errorInfoId").html("请正确输入邮箱");
		showProcessMessageMode(false);
		return false;
	}
    if(!crop_file){
    	$("#errorInfoId").html("请上传企业营业执照");
        return false;
    }
	$("#errorInfoId").html("");
	return true;
}