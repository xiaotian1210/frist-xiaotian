/**
 * 转向附件查看下载页面
 * @param {Object} query_table
 * 目标表名
 * @param {Object} table_name
 * 附件所属表名
 * @param {Object} record_id
 * 附件所属记录id
 */
function read_download_files(query_table, table_name, record_id) {
	record_id = (record_id+"").replace(/\//g,'');
	var url = "ezfm/file/index?query_table=" + query_table + "&table_name=" + table_name + "&record_id=" + record_id;
	window.open(url, '_blank');
}