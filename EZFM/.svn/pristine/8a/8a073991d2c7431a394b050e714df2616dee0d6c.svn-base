package com.shareworx.ezfm.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shareworx.platform.exception.BusinessException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;

/**
 * 登录验证服务
 * @author zhentong.jia
 *
 */
public interface IYJWYLoginAuthorizationService {
	public static final String ID = "yjwyloginAuthorizationService";
	/**
	 * 验证用户是否存在
	 * @param username 用户名
	 * @param password 密码
	 * @param domain 所属域
	 * @throws BusinessException
	 */
	YJWYUserModel loginValid(String username, String password, String domain) throws BusinessException;
	
	/**
	 * 验证用户是否存在
	 * @param username 用户名
	 * @param password 密码
	 * @param domain 所属域
	 * @throws BusinessException
	 */
	YJWYUserModel loginValid(String username, String password, String domain, boolean validatePassword) throws BusinessException;
	
	/**
	 * 系统登录验证
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	ModelAndResult doLogin(YJWYUserModel model, HttpServletRequest request, HttpServletResponse response, boolean validatePassword);
	/**
	 * 用户退出或注销
	 * @param httpSession
	 */
	void exitLogin(HttpSession httpSession);

	/**
	 * 判断用户是否已经登录
	 * @param user
	 * @return
	 */
	boolean isAlreadyLogin(YJWYUserModel user);
	
	/**
	 * 判断本人是否已经登录
	 * @param sessionId
	 * @return
	 */
	boolean isSelfAlreadyLoginForSessionId(String sessionId);
	
	/**
	 * 判断本人是否已经登录
	 * @param sessionId
	 * @return
	 */
	boolean isSelfAlreadyLoginForSession(HttpServletRequest request);
	
	/**
	 * 判断本人是否已经登录
	 * @param sessionId
	 * @return
	 */
	boolean isSelfAlreadyLoginForCookie(HttpServletRequest request);
	
	/**
	 * 查询总登录数
	 * @return
	 */
	int queryLoginCount();
	
	/**
	 * 查询系统登录数、APP登录数、总数
	 * @return
	 */
	String queryLoginCountByClassify();

	
}
