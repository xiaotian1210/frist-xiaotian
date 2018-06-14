package com.shareworx.ezfm.performance.leave.service;

/**
 * 休假备案接口
 * @author yuting.wang
 *
 */
public interface YJWYLeaveService {
	String ID = "yJWYLeaveService";
	
	/**
	 * 根据用户id判断用户当前是否休假中
	 * @param pk_user
	 * @return true:休假中；false:未休假
	 */
	boolean isLeaveByUserId(String pk_user);
}
