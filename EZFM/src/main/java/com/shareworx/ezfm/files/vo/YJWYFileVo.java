package com.shareworx.ezfm.files.vo;

/**
 * 附件查询类
 * 
 * @author jin.li
 *
 */
public class YJWYFileVo {
	/** 标识字段 */
	private String flag;
	/** 查询目标表名 */
	private String query_table;
	/** 附件所属表名 */
	private String table_name;
	/** 附件所属记录id */
	private String record_id;
	/** 附件id */
	private String file_id;

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQuery_table() {
		return query_table;
	}

	public void setQuery_table(String query_table) {
		this.query_table = query_table;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

}