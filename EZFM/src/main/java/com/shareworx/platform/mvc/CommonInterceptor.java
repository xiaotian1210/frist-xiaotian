package com.shareworx.platform.mvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.util.StringUtils;

@Component
@ConfigurationProperties(prefix="system.web")
public class CommonInterceptor implements HandlerInterceptor {
	
	private String ui = "extjs";
	
	private String theme;
	
	private String baseColor;
	
	private boolean debug = false;

	public void setUi(String ui) {
		this.ui = ui;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getUi() {
		return ui;
	}

	public String getTheme() {
		return theme;
	}

	public String getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(String baseColor) {
		this.baseColor = baseColor;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(handler instanceof HandlerMethod){
			response.setHeader("Expireds", "0");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			
			//设置默认路径
			String path = request.getContextPath();
			if(!path.endsWith("/")){
				path += "/";
			}
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
			request.setAttribute("basePath", basePath);
			if(!StringUtils.isEmpty(ui)){
				if(ui.equalsIgnoreCase("extjs")){
					//设置ExtJS路径相关信息
					StringBuffer sb = new StringBuffer();
					sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />");
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/extjs421/resources/css/ext-all.css\">");
//					String theme = null;
//					String baseColor = null;
					try {
						HttpSession httpSession = request.getSession();
						if(httpSession != null){
							String personalTheme = (String) httpSession.getAttribute("theme");
							String personalColor = (String) httpSession.getAttribute("baseColor");
							if(!StringUtils.isEmpty(personalTheme)){
								theme = personalTheme;
							}
							if(!StringUtils.isEmpty(personalColor)){
								baseColor = personalColor;
							}
						}
					} catch (Exception e) {
						
					}
//					if(StringUtils.isEmpty(theme)){
//						theme = getTheme();
//					}
//					if(StringUtils.isEmpty(baseColor)){
//						
//					}
					if(!StringUtils.isEmpty(theme) && !theme.equalsIgnoreCase("default") && !StringUtils.isEmpty(baseColor)){
						sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/extjs421/resources/ext-theme-neptune/dms-theme-" + theme + ".css\">");
						request.setAttribute("baseColor", baseColor);
					}
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/shareworx/css/dms.css\">");
					sb.append("<link rel=\"shortcut icon\" type=\"image/ico\" href=\"resources/shareworx/image/favicon.ico\" />");
					sb.append("<script type=\"text/javascript\" src=\"resources/extjs421/ext-all.js\"></script>");
					sb.append("<script type=\"text/javascript\" src=\"resources/extjs421/locale/ext-lang-zh_CN.js\"></script>");
//					if(isDebug()){
//						sb.append("<script type=\"text/javascript\" src=\"resources/shareworx/js/Dms.js\"></script>");
//					}else{
//						sb.append("<script type=\"text/javascript\" src=\"resources/shareworx/js/Dms.min.js\"></script>");
//					}
					sb.append("<script type=\"text/javascript\" src=\"resources/shareworx/js/ux/boot.js\"></script>");
					request.setAttribute("libs", sb.toString());
				}else if(ui.equalsIgnoreCase("easyui")){
					//设置EasyUI路径相关信息
					if(StringUtils.isEmpty(theme)) theme = "default";
					StringBuffer sb = new StringBuffer();
					sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />");
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/easyui/themes/" + theme + "/easyui.css\">");
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/easyui/themes/icon.css\">");
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/shareworx/css/dms.css\">");
					sb.append("<link style=\"text/css\" rel=\"stylesheet\" href=\"resources/shareworx/css/dms-easyui.css\">");
					sb.append("<link rel=\"shortcut icon\" type=\"image/ico\" href=\"resources/shareworx/image/favicon.ico\" />");
					sb.append("<script type=\"text/javascript\" src=\"resources/lib/js/jquery-1.11.1.min.js\"></script>");
					sb.append("<script type=\"text/javascript\" src=\"resources/easyui/jquery.easyui.min.js\"></script>");
					sb.append("<script type=\"text/javascript\" src=\"resources/easyui/locale/easyui-lang-zh_CN.js\"></script>");
					sb.append("<script type=\"text/javascript\" src=\"resources/shareworx/js/ux/boot.js\"></script>");
					request.setAttribute("libs", sb.toString());
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadLocalContextHolder.clearContext();
	}
	
	public static void main(String[] args) {
		String str = "query/{pk_file}/{pk_org}";
		Pattern p = Pattern.compile("[{]([^{^}]*)[}]");
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("done");
		String realPath = "system/loginforward/aa";
		String rulePath = "system/*tt";
		System.out.println(PatternMatchUtils.simpleMatch(rulePath, realPath));
	}
}
