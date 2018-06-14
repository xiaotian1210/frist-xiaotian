package com.shareworx.interceptor;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.mvc.Session;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.UserUtil;


public class WebAuthInterceptor implements HandlerInterceptor {
	
	private List<String> excludeMappings;
	
	public List<String> getExcludeMappings() {
		return excludeMappings;
	}

	public void setExcludeMappings(List<String> excludeMappings) {
		this.excludeMappings = excludeMappings;
	}
	
	public void addExcludeMapping(String excludeMapping) {
		if(this.excludeMappings == null){
			this.excludeMappings = new ArrayList<>();
		}
		this.excludeMappings.add(excludeMapping);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String uri = request.getRequestURI();
//		String serverUri = request.getContextPath();
//		String requestUri = uri.substring(serverUri.length());
//		
//		if(!ArrayUtils.isEmpty(this.excludeMappings)){
//			for(String excludeMapping: excludeMappings){
//				if(requestUri.startsWith(excludeMapping)){
//					return true;
//				}
//			}
//		}
//		HttpSession session = request.getSession();
//		if(!validateSession(session)){
//			throw new ShareworxAuthencatinException("");
//		}
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		String crop = currUser.getPk_crop();
		request.setAttribute("crop",crop);
		return true;
	}
	
	private boolean validateSession(HttpSession httpSession) {
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		String sessionKey = (String) httpSession.getAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY);
		if(StringUtils.isEmpty(sessionKey)){
			//return false;
		}
		Session session = sessionFactory.getSession(sessionKey);
		if(session == null){
			//return false;
		}
		SessionContext context = new SessionContext(sessionKey);
		ThreadLocalContextHolder.setContext(context);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
