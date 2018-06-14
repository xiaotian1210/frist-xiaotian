package com.shareworx.interceptor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.system.function.service.YJWYButtonsService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.mvc.Session;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

public class YJWYHttpRequestInterceptor implements HandlerInterceptor {
	//将不走监听的Action的url放进FILTERURIS
//	private static final String[] FILTERURIS = new String[]{"/ezfm/api/dv/","/ezfm/mobile","/ezfm/system/crop/presave","/ezfm/system/crop/apply","/ezfm/apply/apply","/ezfm/login","/error","/ezfm/help","/ezfm/cloudHttp"};
	//将不走postHandle监听的Action的url放进postFILTERURIS
	private static final String[] postFILTERURIS = new String[]{"/ezfm/home/exit"};
	
	private static String[] FILTERURIS;
	
	static {
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream infile = cl.getResourceAsStream("filter-urls.txt");
			InputStreamReader isr = new InputStreamReader(infile, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp = null;
			List<String> strList = new ArrayList<String>();
			while((temp=br.readLine())!=null) {
				strList.add(temp);
			}
			FILTERURIS = strList.toArray(new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestUri = request.getRequestURI().substring(request.getContextPath().length());
		for(String uri : FILTERURIS){
			if(requestUri.startsWith(uri)){
				return true;
			}
		}
		String validState = validateSession(request.getSession());
		if(LoginCommons.ACCESSALLOW.equals(validState)){
			return true;
		}else{
			if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
				response.getWriter().write("{'code':'"+validState+"','intro':'http_session_invalid为httpsession失效,dms_session_invalid为账户被顶掉'}");
			}else{
				if(LoginCommons.HTTPSESSIONINVALID.equals(validState)){
					if(requestUri.startsWith(LoginCommons.HOMEURL)){
						response.sendRedirect(LoginCommons.LOGINURL);
					}else{
						response.sendRedirect(LoginCommons.SESSIONINVALIDURL+"/"+LoginCommons.HTTPSESSIONINVALID);
					}
				}else{
					if(requestUri.startsWith(LoginCommons.HOMEURL)){
						response.sendRedirect(LoginCommons.LOGINURL);
					}else{
						response.sendRedirect(LoginCommons.SESSIONINVALIDURL+"/"+LoginCommons.DMSSESSIONINVALID);
					}
				}
			}
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String requestUri = request.getRequestURI().substring(request.getContextPath().length());
		for(String uri : FILTERURIS){
			if(requestUri.startsWith(uri)){
				return;
			}
		}
		for(String uri : postFILTERURIS){
			if(requestUri.startsWith(uri)){
				return;
			}
		}
		String current_user_pk = UserUtil.getCurrentUserPk();
		YJWYUserModel current_user =UserUtil.getCurrentUser();
		request.setAttribute("current_user_pk", current_user_pk);
		request.setAttribute("current_user", current_user);
		request.setAttribute("crop",current_user.getPk_crop());
		if(requestUri.contains("index")||requestUri.contains("metrofunc")){
			String funcId = request.getParameter("funcId");
			if(!StringUtils.isEmpty(funcId)){
				YJWYButtonsService btnService = SpringUtils.getBean(YJWYButtonsService.ID);
				String roleButtons = btnService.queryRoleButtonsForString(funcId);
				if(!StringUtils.isEmpty(roleButtons)){
					request.setAttribute("roleButtons",roleButtons);
				}
				String notRoleButtons = btnService.queryNotRoleButtonsForString(funcId);
				if(!StringUtils.isEmpty(notRoleButtons)){
					request.setAttribute("notRoleButtons",notRoleButtons);
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	/**
	 * 
	 * @param httpSession
	 * @return
	 */
	private String validateSession(HttpSession httpSession) {
		
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		String sessionKey = (String) httpSession.getAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY);
		if(StringUtils.isEmpty(sessionKey)){
			return LoginCommons.HTTPSESSIONINVALID;
		}
		Session session = sessionFactory.getSession(sessionKey);
		if(session == null){
			return LoginCommons.DMSSESSIONINVALID;
		}
		SessionContext context = new SessionContext(sessionKey);
		ThreadLocalContextHolder.setContext(context);
		return LoginCommons.ACCESSALLOW;
	}
}
