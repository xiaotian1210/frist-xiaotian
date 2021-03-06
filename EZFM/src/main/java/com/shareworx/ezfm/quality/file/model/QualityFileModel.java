/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.quality.file.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 品质核查实体类
 * 
 * @author dms
 * @version since Shareworx platform 2.0
 */
@SuppressWarnings("serial")
public class QualityFileModel extends DomainModel {
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_quality_file";
	
	/** 主键 */
	public final static String FILE_ID = "file_id";	
	/** 文件名称 */
	public final static String FILE_NAME = "file_name";	
	/** 文件路径 */
	public final static String FILE_PATH = "file_path";	
	/** 文件类型(1.图片，2音频，3视频)扩展 */
	public final static String FILE_TYPE = "file_type";	
	/** 文件大小 */
	public final static String FILE_SIZE = "file_size";	
	/** 文件所属表名称 */
	public final static String TABLE_NAME = "table_name";	
	/** 文件所属记录ID */
	public final static String RECORD_ID = "record_id";	
	/** 文件上传人 */
	public final static String CREATE_USER = "create_user";	
	/** 文件上传时间 */
	public final static String CREATE_TIME = "create_time";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

	public QualityFileModel() {
		super(META_ID);
	}
	
	/**
	 * 获取元数据名称
	 */
	@Override
	public String getMetaName() {
		return META_ID;
	}
	
	/**
	 * 获取实体主键
	 */
	public String getPrimaryKey() {
		return FILE_ID;
	}
	
	/**
	 * 获取映射表字段
	 * @param key
	 * @return
	 */
	public String getColumn(String key) {
		switch (key) {
		case FILE_ID:
			return "file_id";
		case FILE_NAME:
			return "file_name";
		case FILE_PATH:
			return "file_path";
		case FILE_TYPE:
			return "file_type";
		case FILE_SIZE:
			return "file_size";
		case TABLE_NAME:
			return "table_name";
		case RECORD_ID:
			return "record_id";
		case CREATE_USER:
			return "create_user";
		case CREATE_TIME:
			return "create_time";
		case PK_CROP:
			return "pk_crop";
		}
		return null;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public String getFile_id() {
		return getAttribute(FILE_ID);
	}

	/**
	 * 设置主键
	 * @param file_id
	 */
	public void setFile_id(String file_id) {
		setAttribute(FILE_ID, file_id);
	}

	/**
	 * 获取文件名称
	 * @return
	 */
	public String getFile_name() {
		return getAttribute(FILE_NAME);
	}

	/**
	 * 设置文件名称
	 * @param file_name
	 */
	public void setFile_name(String file_name) {
		setAttribute(FILE_NAME, file_name);
	}

	/**
	 * 获取文件路径
	 * @return
	 */
	public String getFile_path() {
		return getAttribute(FILE_PATH);
	}

	/**
	 * 设置文件路径
	 * @param file_path
	 */
	public void setFile_path(String file_path) {
		setAttribute(FILE_PATH, file_path);
	}

	/**
	 * 获取文件类型(1.图片，2音频，3视频)扩展
	 * @return
	 */
	public String getFile_type() {
		return getAttribute(FILE_TYPE);
	}

	/**
	 * 设置文件类型(1.图片，2音频，3视频)扩展
	 * @param file_type
	 */
	public void setFile_type(String file_type) {
		setAttribute(FILE_TYPE, file_type);
	}

	/**
	 * 获取文件大小
	 * @return
	 */
	public String getFile_size() {
		return getAttribute(FILE_SIZE);
	}

	/**
	 * 设置文件大小
	 * @param file_size
	 */
	public void setFile_size(String file_size) {
		setAttribute(FILE_SIZE, file_size);
	}

	/**
	 * 获取文件所属表名称
	 * @return
	 */
	public String getTable_name() {
		return getAttribute(TABLE_NAME);
	}

	/**
	 * 设置文件所属表名称
	 * @param table_name
	 */
	public void setTable_name(String table_name) {
		setAttribute(TABLE_NAME, table_name);
	}

	/**
	 * 获取文件所属记录ID
	 * @return
	 */
	public String getRecord_id() {
		return getAttribute(RECORD_ID);
	}

	/**
	 * 设置文件所属记录ID
	 * @param record_id
	 */
	public void setRecord_id(String record_id) {
		setAttribute(RECORD_ID, record_id);
	}

	/**
	 * 获取文件上传人
	 * @return
	 */
	public String getCreate_user() {
		return getAttribute(CREATE_USER);
	}

	/**
	 * 设置文件上传人
	 * @param create_user
	 */
	public void setCreate_user(String create_user) {
		setAttribute(CREATE_USER, create_user);
	}

	/**
	 * 获取文件上传时间
	 * @return
	 */
	public String getCreate_time() {
		return getAttribute(CREATE_TIME);
	}

	/**
	 * 设置文件上传时间
	 * @param create_time
	 */
	public void setCreate_time(String create_time) {
		setAttribute(CREATE_TIME, create_time);
	}

	/**
	 * 获取所属公司
	 * @return
	 */
	public String getPk_crop() {
		return getAttribute(PK_CROP);
	}

	/**
	 * 设置所属公司
	 * @param pk_crop
	 */
	public void setPk_crop(String pk_crop) {
		setAttribute(PK_CROP, pk_crop);
	}

}
