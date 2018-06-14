/**
 * 打开新增/编辑窗口前
 * 
 * @param {Object}
 *            datagridName
 * @param {Object}
 *            index
 * @param {Object}
 *            titleName
 * @param {Object}
 *            dialogName
 * @param {Object}
 *            formName
 * @param {Object}
 *            toolbarName
 */
function beforOpenFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName) {
	queryData("combobox_area", "ezfm/device/query/area", "pk_area", "area_name");
	$("#combobox_area").combobox({
		onSelect : function(rec) {
			var url = 'ezfm/device/project/queryProject?pk_area=' + rec.pk_area;
			queryData("combobox_project", url, "pk_project", "project_name");
		}
	});
	queryData("combobox_project", "ezfm/device/project/queryProject", "pk_project", "project_name");
	queryData("combobox_site", "ezfm/device/project/querySite", "site_id", "name");
	openFormDialog(datagridName, index, titleName, dialogName, formName, toolbarName, 380, 240);
}

/**
 * 提交表单前操作
 * 
 * @param {Object}
 *            datagridName
 * @param {Object}
 *            dialogName
 * @param {Object}
 *            formName
 * @param {Object}
 *            urlName
 */
function beforSubmitForm(datagridName, dialogName, formName, urlName) {
	var form_data = $("#" + formName).serializeObject();
	submitForm(datagridName, dialogName, formName, urlName, form_data);
};