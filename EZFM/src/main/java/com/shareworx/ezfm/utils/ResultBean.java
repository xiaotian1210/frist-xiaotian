package com.shareworx.ezfm.utils;

import java.io.Serializable;

public class ResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486318617937304992L;
	
	public static final String FAIL = "fail";

	
	public static final String SUCCESS = "success";
	
	private String status = SUCCESS;

	private String message = "";
	
	private Object data;

	public ResultBean() {
		this.status = SUCCESS;
	}

	public ResultBean(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResultBean(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
