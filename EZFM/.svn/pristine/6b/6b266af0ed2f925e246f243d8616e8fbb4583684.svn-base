/**
 * 初始化下拉框
 * @param {Object} areaId
 * @param {Object} projectId
 */
function initAreaProject(areaId, projectId) {
	//初始化区域下拉列表
	queryData(areaId, "ezfm/device/query/allarea", "pk_area", "area_name");
	$("#" + areaId).combobox({
		onSelect: function(rec) {
			var url = 'ezfm/device/query/allproject?pk_area=' + rec.pk_area;
			queryData(projectId, url, "pk_project", "project_name");
		}
	});
	//初始化项目下拉列表
	queryData(projectId, "ezfm/device/query/allproject", "pk_project", "project_name");

}

/**
 * 请求数据
 */
function requestData() {
	// 获取下拉框参数
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var project_name = $("#combobox_project").combobox("getText");
	if (project_name == "") {
		project_name = "所有项目";
	}
	var start_time = $("#datebox_start_time").datebox('getValue');
	var end_time = $("#datebox_end_time").datebox('getValue');
	$.ajax({
		type: "post",
		url: "ezfm/device/rough/query",
		async: true,
		data: {
			pk_area: pk_area,
			pk_project: pk_project,
			start_time: start_time,
			end_time: end_time,
		},
		dataType: "json",
		success: function(data) {
			if(!data.success) {
				$.messager.alert('提示', '数据读取失败,请重试!', 'info');
				return;
			}
			var chartData = data.data;
			var intactRatio = chartData.intactRatio;
			if (intactRatio == null) {
				intactRatio = 100;
			}
			var checkRatio = chartData.checkRatio;
			if (checkRatio == null) {
				checkRatio = 100;
			}
			var maintRatio = chartData.maintRatio;
			if (maintRatio == null) {
				maintRatio = 100;
			}
			//加载图表
			initGauge('intactGauge', '设备完好率', intactRatio);
			initGauge('checkGauge', '巡检完成率', checkRatio);
			initGauge('maintGauge', '维保完成率', maintRatio);
			initBar('intactBar', '项目设备完好率', intactRatio, project_name,'设备完好率');
			initBar('checkBar', '项目巡检任务完成率', checkRatio, project_name,'巡检任务完成率');
			initBar('maintBar', '项目维保任务完成率', maintRatio, project_name,'维保任务完成率');

		}
	});
}


/**
 * 重置后提交
 */
function resetSubmit(){	
	$("#combobox_area").combobox("setText","区域选择");
	$("#combobox_area").combobox("setValue","default");
    $("#combobox_project").combobox("setText","项目选择");
    $("#combobox_project").combobox("setValue","default");
    $('#datebox_start_time').datebox('setValue', '');
    $('#datebox_end_time').datebox('setValue', '');

  
	var pk_area = $("#combobox_area").combobox("getValue");
	var pk_project = $("#combobox_project").combobox("getValue");
	var project_name = $("#combobox_project").combobox("getText");
	if (project_name == "") {
		project_name = "所有项目";
	}
	var start_time = $('#datebox_start_time').datebox('getValue');
	var end_time = $('#datebox_end_time').datebox('getValue');
	
	
	$.ajax({
		type: "post",
		url: "ezfm/device/rough/query",
		async: true,
		data: {
			pk_area: pk_area,
			pk_project: pk_project,
			start_time: start_time,
			end_time: end_time,
		},
		dataType: "json",
		success: function(data) {
			if(!data.success) {
				$.messager.alert('提示', '数据读取失败,请重试!', 'info');
				return;
			}
			var chartData = data.data;
			var intactRatio = chartData.intactRatio;
			if (intactRatio == null) {
				intactRatio = 100;
			}
			var checkRatio = chartData.checkRatio;
			if (checkRatio == null) {
				checkRatio = 100;
			}
			var maintRatio = chartData.maintRatio;
			if (maintRatio == null) {
				maintRatio = 100;
			}
			//加载图表
			initGauge('intactGauge', '设备完好率', intactRatio);
			initGauge('checkGauge', '巡检完成率', checkRatio);
			initGauge('maintGauge', '维保完成率', maintRatio);
			initBar('intactBar', '项目设备完好率', intactRatio, project_name,'设备完好率');
			initBar('checkBar', '项目巡检任务完成率', checkRatio, project_name,'巡检任务完成率');
			initBar('maintBar', '项目维保任务完成率', maintRatio, project_name,'维保任务完成率');

		}
	});
}


/**
 * 初始化仪表图
 * @param {Object} gaugeId
 * @param {Object} title
 */
function initGauge(gaugeId, title, data) {
	var chart = echarts.init(document.getElementById(gaugeId));
	chart.showLoading({
		text: '正在努力的读取数据中...'
	});
	// 指定图表的配置项和数据
	var option = {
		series: [{
			type: 'gauge',
			min: 0, //设置刻度最小值
			max: 100, //设置刻度最大值
			splitNumber: 10, //设置刻度数量
			radius: '110%', //设置表盘大小
			center: ['50%', '60%'], // 设置仪表位置，默认全局居中
			//					endAngle:45,//表盘显示区域大小
			detail: {
				formatter: '{value}%',
			},
			data: [{
				value: data,
				name: title
			}],
			axisLine: { // 设置表盘外圈线条样式
				lineStyle: { // 属性lineStyle控制线条样式
					color:[[0.2, '#c23531'], [0.8, '#63869e'], [1, '#91c7ae']],
					width: 15 //宽度
				}
			},
			axisTick: { // 设置表盘刻度线条样式
				length: 20, // 属性length控制线长
				lineStyle: { // 属性lineStyle控制线条样式
					color: 'auto'
				}
			},
			splitLine: { // 设置大刻度之间的分隔线
				length: 25, // 属性length控制线长
				lineStyle: { // 属性lineStyle控制线条样式
					color: 'auto'
				}
			},
			title: {
				textStyle: { // 设置标题样式（表盘内部标题）
					fontWeight: 'bolder',
					fontSize: 15,
					fontStyle: 'italic',
				},
				offsetCenter: [0, '-30%'], // x, y，单位px 标题位置
			},
			pointer: { //设置指针大小
				width: 5
			},
		}],
	};
	chart.hideLoading();
	// 使用刚指定的配置项和数据显示图表。
	chart.setOption(option);
}

/**
 * 初始化柱状表
 * @param {Object} chartId
 * @param {Object} title
 * @param {Object} chartType
 * @param {Object} data
 */
function initBar(barId, title, data, projectName,seriesName) {
	var chart = echarts.init(document.getElementById(barId));
	chart.showLoading({
		text: '正在努力的读取数据中...'
	});
	// 指定图表的配置项和数据
	var option = {
		title: {
			text: title,
			top: 10,
			left: 20,
		},
		grid: {
			left: 20,
			right: 20,
			bottom: 0,
			containLabel: true
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			},
			formatter: '{a} <br /> {b} : {c}%',
		},
		xAxis: {
			type: 'category',
			data: [projectName]
		},
		yAxis: {
			type: 'value',
			min: 0,
			max: 100,
			splitNumber: 5,
		},
		series: [{
			name: seriesName,
			type: 'bar',
			barWidth: 10,
			data: [data],
			itemStyle: {
				normal: {
					color: '#bda29a',
				}
			},
		}],
	};
	chart.hideLoading();
	// 使用刚指定的配置项和数据显示图表。
	chart.setOption(option);
}