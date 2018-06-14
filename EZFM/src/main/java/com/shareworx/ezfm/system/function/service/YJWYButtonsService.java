package com.shareworx.ezfm.system.function.service;

public interface YJWYButtonsService {
	public static final String ID = "yJWYButtonsService";
	
	
	/**
	 * 查询所有功能按钮
	 * @param funcId
	 * @return
	 */
	String queryAllButtonsForString(String funcId);

	/**
	 * 查询登录用户没有权限的按钮
	 * @param funcId
	 * @return
	 */
	String queryNotRoleButtonsForString(String funcId);
	
	/**
	 * 查询登录用户有权限的按钮
	 * @param funcId
	 * @return
	 */
	String queryRoleButtonsForString(String funcId);
}
