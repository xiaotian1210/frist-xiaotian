//附件总数
var count = 0;
// 序号开始标识
var startLen = 0;
// 序号结束标识
var endLen = 0;
// 附件数据
var data;
// 当前显示图片索引
var index;

/**
 * 采用正则表达式获取地址栏参数
 * 
 * @param {Object}
 *            name
 */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r != null)
		return unescape(r[2]);
	return null;
}

/**
 * 获取附件集合
 * 
 * @param {Object}
 *            address
 * @param {Object}
 *            data
 */
function getFiles(params) {
	$.ajax({
		type: "post",
		url: queryUrl,
		contentType: 'application/json',
		data: params,
		async: true,
		dataType: 'json',
		success: function(mar) {
			if(mar.rows == null || mar.rows.length == 0) {
				$.messager.alert('提示', '当前记录无附件。', 'info', function() {
					window.close();
				});
			} else {
				showFiles(mar.rows);
			}
		},
	});
}

/**
 * 展示附件数据
 * 
 * @param {Object}
 *            rows
 */
function showFiles(rows) {
	data = rows;
	count = rows.length;
	$("#count").text(count);
	if(count > 5) {
		startLen = 6;
		// 剩余个数
		endLen = count - startLen;
		if(endLen <= 5) {
			endLen = count;
		} else {
			endLen = startLen + 5;
		}
	}
	for(var i = 0; i < count; i++) {
		if(i < 5) {
			$("#serial").append('<li>' + (i + 1) + '</li>');
		}
		$(".swiper-wrapper").append('<div class="swiper-slide"><a><img src="' + rows[i]['file_path'] + '"></a></div>');
	}
	if(count > 5) {
		$("#serial").append('<li id="last" class="last">……</li>');
	}
	index = 0;
	showDetail();
	initSwiper();
}

/**
 * 展示图片详情
 * 
 * @param {Object}
 *            index
 */
function showDetail() {
	var create_time = data[index]['create_time'];
	var file_size = data[index]['file_size'];
	$("#create_time").text(create_time);
	$("#file_size").text(file_size + '字节');
}

/**
 * 初始化Swiper
 */
function initSwiper() {
	var swiper = new Swiper('.swiper-container', {
		pagination: '.swiper-container .swiper-pagination',
		pagination: '.swiper-pagination',
		paginationClickable: true,
		nextButton: '.swiper-button-next',
		prevButton: '.swiper-button-prev'
	});
	var scaleNum = 1;
	var rotateNum = 0;

	function changeImg() {
		showDetail();
		var changeStr = "rotate(" + rotateNum + "deg) ";
		var changeHt = scaleNum * 100 + "%";
		$(".swiper-container img").css({
			"transform": changeStr,
			"-webkit-transform": changeStr,
			"-moz-transform": changeStr,
			"-o-transform": changeStr
		});
		$(".swiper-container img").height(changeHt);
	};

	// 下载
	$(".download").click(function() {
		window.location.href = downUrl + '?file_id=' + data[index]['file_id'] + '&query_table=' + query_table+ '&flag=' + flag;
	});

	// 还原
	$(".restore").click(function() {
		scaleNum = 1;
		rotateNum = 0;
		changeImg();
	});
	// 放大
	$(".bigger").click(function() {
		scaleNum += 0.2;
		if(scaleNum >= 10) {
			scaleNum = 10;
		}
		changeImg();
	});
	// 缩小
	$(".smaller").click(function() {
		scaleNum -= 0.2;
		if(scaleNum <= 0.2) {
			scaleNum = 0.2;
		}
		changeImg();
	});
	// 向左旋转
	$(".rotate-left").click(function() {
		rotateNum -= 90;
		if(rotateNum == -360) {
			rotateNum = 0;
		}
		changeImg();
	});
	// 向右旋转
	$(".rotate-right").click(function() {
		rotateNum += 90;
		if(rotateNum == 360) {
			rotateNum = 0;
		}
		changeImg();
	});
	// 下一个
	$(".swiper-button-next").get(0).addEventListener("click", function() {
		index += 1;
		scaleNum = 1;
		rotateNum = 0;
		changeImg();
	}, false);
	// 上一个
	$(".swiper-button-prev").get(0).addEventListener("click", function() {
		index -= 1;
		scaleNum = 1;
		rotateNum = 0;
		changeImg();
	}, false);
	// 省略按钮
	$(".nav-box .last").click(function() {
		var liStr = "";
		for(var i = startLen; i <= endLen; i++) {
			liStr += "<li>" + i + "</li>";
		}
		// 判断剩余数量
		if(endLen != count) {
			$("#serial").remove("#last");
			startLen = endLen + 1;
			// 剩余个数
			endLen = count - startLen;
			if(endLen <= 5) {
				endLen = count;
			} else {
				endLen = startLen + 5;
			}
		} else {
			// 没有剩余，隐藏省略按钮
			var ui = document.getElementById('last');
			ui.style.display = 'none';
		}
		$(this).before(liStr);
		var scrollHeight = parseInt($(".nav-box ul").height()) - 556;
		if(scrollHeight >= 0) {
			$(".nav-box").scrollTop(scrollHeight);
		}

	});
	// 序号单击事件
	$(".nav-box").delegate("li", "click", function() {
		index = parseInt($(this).text()) - 1;
		if(index <= $(".pic-box .swiper-slide").length) {
			swiper.slideTo(index, 1000, false);
		}
		if(!isNaN(index)) {
			showDetail();
		}
	});
}