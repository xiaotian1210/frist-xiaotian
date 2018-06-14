package com.shareworx.platform.model;

import java.util.HashMap;

/**
 * 控制器中返回Json对象
 * @author zhentong.jia
 *
 */
@SuppressWarnings("serial")
public class ModelAndResult extends HashMap<String, Object> {

	/** 操作是否成功 */
	public final static String SUCCESS = "success";
	
	/** 返回数据 */
	public final static String DATA = "data";
	
	/** 返回消息 */
	public final static String MESSAGE = "message";
	
	/** 返回查询总数 */
	public final static String TOTAL = "total";
	
	/** 返回查询当前页码 */
	public final static String PAGE = "page";
	
	/** 返回其他信息 */
	public final static String OTHERS = "others";

	public ModelAndResult() {
		setSuccess(true);
	}
	
	public ModelAndResult(boolean success) {
		setSuccess(success);
		setData(success);
	}
	
	public ModelAndResult(Object data) {
		setSuccess(true);
		setData(data);
	}
	
	public ModelAndResult(boolean success,String message) {
		setSuccess(success);
		setMessage(message);
	}
	public ModelAndResult(boolean success,String message, Object data) {
		setSuccess(success);
		setMessage(message);
		setData(data);
	}

	public ModelAndResult(boolean success, Object data, String message, long total) {
		super();
		setSuccess(success);
		setData(data);
		setMessage(message);
		setTotal(total);
	}
	
	public <T> void setAttribute(String key, T value){
		put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key){
		return (T) get(key);
	}

	public boolean isSuccess() {
		return getAttribute(SUCCESS);
	}

	public void setSuccess(boolean success) {
		setAttribute(SUCCESS, success);
	}

	public Object getData() {
		return getAttribute(DATA);
	}

	public void setData(Object data) {
		setAttribute(DATA, data);
	}

	public String getMessage() {
		return getAttribute(MESSAGE);
	}

	public void setMessage(String message) {
		setAttribute(MESSAGE, message);
	}

	public long getTotal() {
		return getAttribute(TOTAL);
	}

	public void setTotal(long total) {
		setAttribute(TOTAL, total);
	}
	
	public long getPage() {
		return getAttribute(PAGE);
	}
	
	public void setPage(long page) {
		setAttribute(PAGE, page);
	}

	public Object getOthers() {
		return getAttribute(OTHERS);
	}

	public void setOthers(Object others) {
		setAttribute(OTHERS, others);
	}
	
}
