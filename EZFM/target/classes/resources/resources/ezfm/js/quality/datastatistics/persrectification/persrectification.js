var userId = "";
var projectId = "";
var type = "";
/**
 * 未完成整改详细列表 超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function clickUnfinishedDetails(value, row, index){
	var currentUserId = row.rectify_user_id;
	var currentProjectId = row.project_id;
	if (value>0) {
		var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='sonpage(\""+index+"\",0,\""+currentUserId+"\",\""+currentProjectId+"\")' >"+value+"</a>";
	}else{
		var operation = value;
	}
	return operation ;
}

/**
 * 完成整改详细列表 超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}  
 */
function clickFinishedDetails(value, row, index){
	var currentUserId = row.rectify_user_id;
	var currentProjectId = row.project_id;
	if (value>0) {
		var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='sonpage(\""+index+"\",1,\""+currentUserId+"\",\""+currentProjectId+"\")' >"+value+"</a>";
	}else{
		var operation = value;
	}
	return operation ;
}

/**
 * 过期完成整改详细列表 超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}  
 */
function clickExpireDetails(value, row, index){
	var currentUserId = row.rectify_user_id;
	var currentProjectId = row.project_id;
	if (value>0) {
		var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='sonpage(\""+index+"\",2,\""+currentUserId+"\",\""+currentProjectId+"\")' >"+value+"</a>";
	}else{
		var operation = value;
	}
	return operation ;
}

/**
 * 总任务详细列表 超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}  
 */
function clickTotalDetails(value, row, index){
	var currentUserId = row.rectify_user_id;
	var currentProjectId = row.project_id;
	if (value>0) {
		var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='sonpage(\""+index+"\",3,\""+currentUserId+"\",\""+currentProjectId+"\")' >"+value+"</a>";
	}else{
		var operation = value;
	}
	return operation ;
}
/**
 * 核查总任务详细列表 超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}  
 */
function clickCheckDetails(value, row, index){
	var currentUserId = row.rectify_user_id;
	var currentProjectId = row.project_id;
	if (value>0) {
		var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='sonpage(\""+index+"\",4,\""+currentUserId+"\",\""+currentProjectId+"\")' >"+value+"</a>";
	}else{
		var operation = value;
	}
	return operation ;
}

/**
 * 根据value 判断点击的 是哪种类型的 
 * 0:未完成整改详细列表；
 * 1:完成整改详细列表;
 * 2:过期完成整改详细列表;
 * 3:总任务详细列表;
 * 4:核查总任务详细列表
 * @param index
 * @param value
 */
function sonpage(index,val1,val2,val3) {
	indexed = index;
	type = val1;
	userId = val2;
	projectId = val3;
	if (index != null) {
		var url = "ezfm/quality/datastatistics/persrectification/sonpage";
		showCustomWindow("dialog_unfinishedList_id","未完成整改列表",url,null,{});
	}
}


/**
 * 子页面 查看附件的两个超链接
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatProblem(value, row, index){
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='onclickCheckPage(\""+index+"\")' >"+查看+"</a>";
	return operation ;
}
function formatRectification(value, row, index){
	var operation ="<a href='javascript:void(0);' class='easyui-linkbutton' onclick='onclickCheckPage(\""+index+"\")' >"+查看+"</a>";
	return operation ;
}