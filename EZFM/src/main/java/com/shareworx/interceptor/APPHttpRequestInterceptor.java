package com.shareworx.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.login.commons.APPResultMsgCommons;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.service.IAPPLoginAuthorizationService;

public class APPHttpRequestInterceptor implements HandlerInterceptor {
	// 将不走监听的Action的url放进FILTERURIS
	private static final String[] FILTERURIS = new String[] { "/app/login","/app/isalready/login" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI().substring(request.getContextPath().length());
		for (String uri : FILTERURIS) {
			if (requestUri.startsWith(uri)) {
				return true;
			}
		}
		int validState = validateRequest(request);
		if (LoginCommons.SUCCESSCODE.equals(validState)) {
			return true;
		} else {
			JSONObject json = new JSONObject();
			json.put(APPResultMsgCommons.CODE, validState);
			String msg = "";
			if(LoginCommons.USEROTHORALREADYLOGIN.equals(validState)){
				msg="用户已经在其他地点登录";
			}else if(LoginCommons.USERCOOKIEERROR.equals(validState)){
				msg="请重新登录";
			}
			json.put(APPResultMsgCommons.MSG, msg);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(json.toJSONString());
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Integer validateRequest(HttpServletRequest request) {
		IAPPLoginAuthorizationService service = SpringUtils.getBean(IAPPLoginAuthorizationService.ID);
		String sessionId="";
		String userName="";
		Cookie[] cookies = request.getCookies();
		if(null==cookies||cookies.length<1){
			return LoginCommons.USERCOOKIEERROR;
		}
		for(Cookie cookie : cookies){
			if(APPResultMsgCommons.LOGINCOOKIENAME.equals(cookie.getName())){
				sessionId = cookie.getValue();
			}
			if(APPResultMsgCommons.LOGINUSERNAME.equals(cookie.getName())){
				userName = cookie.getValue();
			}
		}
		if(service.isAlreadyLoginForSelf(sessionId)){
			SessionContext context = new SessionContext(sessionId);
			ThreadLocalContextHolder.setContext(context);
			return LoginCommons.SUCCESSCODE;
		}
		if(service.isAlreadyLogin(userName)){
			return LoginCommons.USEROTHORALREADYLOGIN;
		}
		return LoginCommons.USERCOOKIEERROR;
	}
}
