/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.resources.file.model;

import com.shareworx.platform.persist.DomainModel;

/**
 * 资源附件实体类
 * 
 * @author Administrator
 * @version since Shareworx platform 2.0
 */
public class YJWYResourceFileModel extends DomainModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 元数据名称
	 */
	public final static String META_ID = "yjwy_resource_file";
	
	/** 主键id */
	public final static String FILE_ID = "file_id";	
	/** 资源id */
	public final static String PK_RESCOURCE = "pk_rescource";	
	/** 文件路径 */
	public final static String FILE_PATH = "file_path";	
	/** 文件名称 */
	public final static String FILE_NAME = "file_name";	
	/** 文件类型 */
	public final static String FILE_TYPE = "file_type";	
	/** 文件大小 */
	public final static String FILE_SIZE = "file_size";	
	/** 所属表 */
	public final static String FILE_TABLE = "file_table";	
	/** 上传人 */
	public final static String FILE_UPNAME = "file_upname";	
	/** 上传时间 */
	public final static String FILE_UPTIME = "file_uptime";	
	/** 所属公司 */
	public final static String PK_CROP = "pk_crop";	

	public YJWYResourceFileModel() {
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
		case PK_RESCOURCE:
			return "pk_rescource";
		case FILE_PATH:
			return "file_path";
		case FILE_NAME:
			return "file_name";
		case FILE_TYPE:
			return "file_type";
		case FILE_SIZE:
			return "file_size";
		case FILE_TABLE:
			return "file_table";
		case FILE_UPNAME:
			return "file_upname";
		case FILE_UPTIME:
			return "file_uptime";
		case PK_CROP:
			return "pk_crop";
		}
		return null;
	}

	/**
	 * 获取主键id
	 * @return
	 */
	public String getFile_id() {
		return getAttribute(FILE_ID);
	}

	/**
	 * 设置主键id
	 * @param file_id
	 */
	public void setFile_id(String file_id) {
		setAttribute(FILE_ID, file_id);
	}

	/**
	 * 获取资源id
	 * @return
	 */
	public String getPk_rescource() {
		return getAttribute(PK_RESCOURCE);
	}

	/**
	 * 设置资源id
	 * @param pk_rescource
	 */
	public void setPk_rescource(String pk_rescource) {
		setAttribute(PK_RESCOURCE, pk_rescource);
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
	 * 获取文件类型
	 * @return
	 */
	public String getFile_type() {
		return getAttribute(FILE_TYPE);
	}

	/**
	 * 设置文件类型
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
	 * 获取所属表
	 * @return
	 */
	public String getFile_table() {
		return getAttribute(FILE_TABLE);
	}

	/**
	 * 设置所属表
	 * @param file_table
	 */
	public void setFile_table(String file_table) {
		setAttribute(FILE_TABLE, file_table);
	}

	/**
	 * 获取上传人
	 * @return
	 */
	public String getFile_upname() {
		return getAttribute(FILE_UPNAME);
	}

	/**
	 * 设置上传人
	 * @param file_upname
	 */
	public void setFile_upname(String file_upname) {
		setAttribute(FILE_UPNAME, file_upname);
	}

	/**
	 * 获取上传时间
	 * @return
	 */
	public String getFile_uptime() {
		return getAttribute(FILE_UPTIME);
	}

	/**
	 * 设置上传时间
	 * @param file_uptime
	 */
	public void setFile_uptime(String file_uptime) {
		setAttribute(FILE_UPTIME, file_uptime);
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