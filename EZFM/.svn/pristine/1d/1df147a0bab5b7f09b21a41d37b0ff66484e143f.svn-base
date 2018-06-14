package com.shareworx.ezfm.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;

public interface IAPPLoginAuthorizationService {
	public static final String ID = "appLoginAuthorizationService";
	/**
	 * 验证用户信息
	 * @param loginModel
	 * @param dbModel
	 * @return
	 */
	Integer loginValid(YJWYUserModel loginModel, YJWYUserModel dbModel);
	
	/**
	 * 登录成功返回token 登录失败返回状态码
	 * @param loginModel
	 * @param request
	 * @param response
	 * @return
	 */
	Integer doLogin(YJWYUserModel loginModel, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 判断账号是否登录
	 * @param userCode
	 * @return
	 */
	boolean isAlreadyLogin(String userCode);
	
	/**
	 * 判断账号是否本人登录
	 * @param sessionId
	 * @return
	 */
	boolean isAlreadyLoginForSelf(String sessionId);
	
	/**
	 * 退出当前登录用户
	 * @param request
	 */
	void exitLogin(HttpServletRequest request);
	
	/**
	 * 根据RequestCookie判断当前登录是否本人登陆
	 * @param request
	 * @return
	 */
	boolean isAlreadyLoginForSelf(HttpServletRequest request);
	
	/**
	 * 返回登录信息
	 * @param username
	 * @return
	 */
	Map<String, Object> loginResult(String username);
	
	/**
	 * 验证更新用户登录设备信息
	 * @param user
	 * @param mobileId
	 * @param mobilePlatform
	 */
	void validMobile(String mobileId, String mobilePlatform); 
}
